package com.lop.devtools.monstera.addon

import com.lop.devtools.monstera.Config
import com.lop.devtools.monstera.MonsteraLoggerContext
import com.lop.devtools.monstera.addon.api.InvokeBeforeEnd
import com.lop.devtools.monstera.addon.api.PackageInvoke
import com.lop.devtools.monstera.addon.block.Block
import com.lop.devtools.monstera.addon.dev.buildToMcFolder
import com.lop.devtools.monstera.addon.dev.validateTextures
import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.addon.item.Item
import com.lop.devtools.monstera.addon.mcfunction.McFunction
import com.lop.devtools.monstera.addon.mcfunction.buildMcFunctions
import com.lop.devtools.monstera.addon.sound.SoundUtil
import com.lop.devtools.monstera.files.File
import com.lop.devtools.monstera.files.manifest.generateManifest
import com.lop.devtools.monstera.files.res.ItemTextureIndex
import com.lop.devtools.monstera.files.res.TextureIndex
import com.lop.devtools.monstera.files.res.blocks.BlockDefs
import com.lop.devtools.monstera.files.res.blocks.TerrainTextures
import com.lop.devtools.monstera.files.res.materials.Materials
import com.lop.devtools.monstera.files.res.particleGetTexturePath
import org.slf4j.LoggerFactory
import java.io.File
import java.lang.Integer.max
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

open class Addon(val config: Config)  {
    @DslMarker
    annotation class AddonTopLevel

    companion object {
        var active: Addon? = null
    }

    val unsafe = Unsafe()
    private val logger = LoggerFactory.getLogger("Addon")

    inner class Unsafe {
        val sounds = SoundUtil(this@Addon)
        val mcFunctions = mutableListOf<McFunction>()
    }

    val onEndListener: ArrayList<InvokeBeforeEnd> = arrayListOf()
    val onPackage: ArrayList<PackageInvoke> = arrayListOf()

    var buildFunctions: Boolean = true
    var buildTextureList: Boolean = true
    var buildItemTextureIndex: Boolean = true
    var buildToMcFolder: Boolean = true
    var manifestMinEnginVersion: ArrayList<Int> = config.targetMcVersion

    var includeInfoMcFunction: Boolean = true

    /**
     * Define an abstract entity
     *
     * ```
     * entity("id", "Display") {
     *    behaviour {  }
     *    resource {  }
     * }
     * ```
     */
    @AddonTopLevel
    fun entity(name: String, displayName: String, entity: Entity.() -> Unit): Entity {
        MonsteraLoggerContext.setEntity(name)
        val ent = Entity(this, name, displayName).apply(entity)
        ent.build()
        MonsteraLoggerContext.clear()
        return ent
    }

    /**
     * Define sounds
     *
     * ```
     *  sounds {
     *    soundsDefinitions(Props.currentAddon) {
     *      newSoundDef("tp.test.drive") {
     *      category(SoundCategory.BLOCK)
     *      maxDistance(2f)
     *      minDistance(1f)
     *      //can be called multiple times
     *      sound {
     *        name(File("test.ogg"))
     *        volume(1f)
     *        pitch(1f)
     *      }
     *      sound(File("test.ogg"))
     *      sound(File("test.ogg"))
     *    }
     *  }
     *  categorySounds {
     *    blockSounds {
     *      soundEventGroup("test") {
     *        event(SoundEvent.ATTACK) {
     *          sound("test.sound")
     *          volume(0.2f)
     *        }
     *        event(SoundEvent.AMBIENT) {
     *          sound("test2.sound")
     *          volume(0.2f)
     *          pitch(0.3f)
     *        }
     *      }
     *      soundEventGroup("test2") {
     *        event(SoundEvent.SCREAM) { }
     *      }
     *    }
     *    entitySounds {
     *
     *    }
     *    defaultEntitySounds {
     *
     *    }
     *    individualEventSounds {
     *
     *    }
     *    interactiveSounds {
     *
     *    }
     *  }
     * }
     * ```
     */
    @AddonTopLevel
    fun sounds(sounds: SoundUtil.() -> Unit): SoundUtil {
        return unsafe.sounds.apply(sounds)
    }

    /**
     * ```
     *  item("test_item", "Test Item") {
     *      renderOffset("tools")
     *      texture(getResource("test_item"))
     *      category("Equipment")
     *      components {
     *          //...
     *      }
     *  }
     * ```
     */
    @AddonTopLevel
    fun item(name: String, displayName: String, item: Item.() -> Unit): Item {
        MonsteraLoggerContext.setItem(name)
        val data = Item(name, displayName, this).apply(item)
        data.build()
        MonsteraLoggerContext.clear()
        return data
    }

    /**
     * ```
     *  mcFunction {
     *      name = "my_function"
     *      entries = arrayListOf("say hello")
     *      execEveryTick = true
     *  }
     * ```
     */
    @AddonTopLevel
    fun mcFunction(name: String, data: McFunction.() -> Unit): String {
        val func = McFunction(name).apply(data)
        unsafe.mcFunctions.add(func)
        return func.name
    }

    fun loadParticle(value: File) {
        if (value.isDirectory) {
            var texturePath = ""
            var texture = File()
            value.walk().forEach {
                if (it.name.contains("particle.json")) {
                    //copy the particle json into the res pack
                    val target = config.paths.resParticles.resolve(it.name).toFile()
                    it.copyTo(target, true)
                    //set the texture path that is given in the json
                    texturePath = particleGetTexturePath(it).getOrElse { e ->
                        logger.warn("Invalid Particle at ${it.name}, see: ${e.stackTrace}")
                        return@forEach
                    }
                } else if (it.name.contains(".png")) {
                    //set the texture, don't copy yet as there may be no path yet
                    texture = it
                }
            }
            val target = config.paths.resTextures.toFile()
            texture.copyTo(File(target, "$texturePath.png"), true)
        } else if (value.isFile) {
            //just copy, no need for a texture
            var fileName = value.name
            if (!fileName.contains(".json"))
                fileName += ".json"
            val target = config.paths.resParticles.resolve(fileName)
            value.copyTo(target.toFile(), true)
        } else {
            logger.warn("Could not find particle file ${value.path}")
        }
    }

    /**
     * add a block
     *
     * ```
     * block {
     *    defaultBlock {
     *         //...
     *    }
     *    //or
     *    geometry(file)
     *    texture(file)
     *    sound { }
     * }
     * ```
     */
    @AddonTopLevel
    fun block(name: String, displayName: String, data: Block.() -> Unit): Block {
        MonsteraLoggerContext.setBlock(name)
        val mData = Block(this, name, displayName).apply(data)
        mData.build()
        MonsteraLoggerContext.clear()
        return mData
    }

    fun scripts(directory: File) {
        val scriptingDir = config.paths.behScripts.toFile()

        if (directory.isDirectory) {
            directory
                .walk()
                .maxDepth(1)
                .filter { directory.name != it.name }
                .forEach {
                    it.copyRecursively(File(scriptingDir, it.name), true)
                }
        } else {
            logger.warn("${directory.name}' is not a directory (scripting)")
        }
    }

    fun build() {
        BlockDefs.instance(this).unsafe.build(config.resPath)
        TerrainTextures.instance(this).unsafe.buildFile(this)
        Materials.instance(this).apply {
            version("1.0.0")
        }.unsafe.build(this)
        onEndListener.forEach {
            it.invoke(this)
        }
        if (includeInfoMcFunction) {
            buildInformation(this)
        }
        generateManifest(
            config.version,
            config,
            minEnginVersion = manifestMinEnginVersion,
            scriptEntryFile = config.scriptEntryFile
        )

        if (unsafe.mcFunctions.isNotEmpty())
            buildMcFunctions(config.paths.behMcFunction, unsafe.mcFunctions)
        if (buildTextureList)
            TextureIndex.instance(this).build(this)
        if (buildItemTextureIndex)
            ItemTextureIndex.instance(this).build(this)

        unsafe.sounds.unsafe.build()

        config.langFileBuilder.addonRes.sort().build()
        config.langFileBuilder.addonBeh.sort().build()

        validateTextures(this)
        if (buildToMcFolder)
            buildToMcFolder(config)

        onPackage.forEach {
            it.invoke(this)
        }
    }
}

fun buildInformation(addon: Addon) {

    val time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

    val characterLength = max(
        max(
            addon.config.monsteraVersion.length + 21,
            (System.getenv("CI_COMMIT_REF_NAME") ?: "local").length + 17
        ), time.length + 14
    )
    val format = StringBuilder()

    for (i in 0 until characterLength) {
        format.append("#")
    }

    addon.mcFunction(name = "build_information") {
        entries = arrayListOf(
            "say §b$format",
            "say §b#§a Monstera version: ${addon.config.monsteraVersion}",
            "say §b#§a build version: ${(System.getenv("CI_COMMIT_REF_NAME") ?: System.getenv("GITHUB_REF") ?: "local")}",
            "say §b#§a build time: $time",
            "say §b$format",
        )
    }
}