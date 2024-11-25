@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.lop.devtools.monstera.addon

import com.lop.devtools.monstera.Config
import com.lop.devtools.monstera.MonsteraLoggerContext
import com.lop.devtools.monstera.addon.api.InvokeBeforeEnd
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeField
import com.lop.devtools.monstera.addon.api.PackageInvoke
import com.lop.devtools.monstera.addon.block.Block
import com.lop.devtools.monstera.addon.dev.buildToMcFolder
import com.lop.devtools.monstera.addon.dev.overwriteResourceInMcFolder
import com.lop.devtools.monstera.addon.dev.validateTextures
import com.lop.devtools.monstera.addon.dev.zipper.zipWorld
import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.addon.item.Item
import com.lop.devtools.monstera.addon.mcfunction.McFunction
import com.lop.devtools.monstera.addon.mcfunction.buildMcFunctions
import com.lop.devtools.monstera.addon.sound.SoundUtil
import com.lop.devtools.monstera.files.File
import com.lop.devtools.monstera.files.getVersionAsString
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

open class Addon(val config: Config, val args: Array<String>) {
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

    open val onEndListener: ArrayList<InvokeBeforeEnd> = arrayListOf()
    open val onPackage: ArrayList<PackageInvoke> = arrayListOf()

    @MonsteraUnsafeField
    open val entities: MutableMap<String, Entity> = mutableMapOf()

    @MonsteraUnsafeField
    open val items: MutableMap<String, Item> = mutableMapOf()

    @MonsteraUnsafeField
    open val blocks: MutableMap<String, Block> = mutableMapOf()

    open var buildFunctions: Boolean = true
    open var buildTextureList: Boolean = true
    open var buildItemTextureIndex: Boolean = true
    open var buildToMcFolder: Boolean = true
    open var manifestMinEnginVersion: ArrayList<Int> = ArrayList(config.targetMcVersion)

    open var includeInfoMcFunction: Boolean = true

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
    open fun entity(name: String, displayName: String = name, entity: Entity.() -> Unit): Entity {
        MonsteraLoggerContext.setEntity(name)
        entities[name] = (entities[name] ?: Entity(this, name, displayName)).apply(entity)
        MonsteraLoggerContext.clear()
        return entities[name]!!
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
    open fun sounds(sounds: SoundUtil.() -> Unit): SoundUtil {
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
    open fun item(name: String, displayName: String = name, item: Item.() -> Unit): Item {
        MonsteraLoggerContext.setItem(name)
        items[name] = (items[name] ?: Item(name, displayName, this)).apply(item)
        MonsteraLoggerContext.clear()
        return items[name]!!
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
    open fun mcFunction(name: String, data: McFunction.() -> Unit): String {
        val func = McFunction(name).apply(data)
        unsafe.mcFunctions.add(func)
        return func.name
    }

    open fun loadParticle(value: File) {
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
     *    menuCategory { }
     *    components { }
     *
     *    defaultBlock {
     *         //...
     *    }
     *    //or
     *    blockGeometry(file)
     *    texture(file)
     *    sound { }
     * }
     * ```
     */
    @AddonTopLevel
    open fun block(name: String, displayName: String, data: Block.() -> Unit): Block {
        MonsteraLoggerContext.setBlock(name)
        blocks[name] = (blocks[name] ?: Block(this, name, displayName)).apply(data)
        MonsteraLoggerContext.clear()
        return blocks[name]!!
    }

    open fun scripts(directory: File) {
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
            logger.warn("${directory.path}' does not exist or is not a directory (scripting)")
        }
    }

    open fun build() {
        val argParsed = args
            .map { it.lowercase() }
            .map { it.split("=") }
            .associate {
                if (it.size == 1)
                    it[0] to null
                else
                    it[0] to it[1]
            }

        //entities
        entities.forEach { (name, body) ->
            MonsteraLoggerContext.setEntity(name)
            body.build()
            MonsteraLoggerContext.clear()
        }
        //items
        items.forEach { (name, body) ->
            MonsteraLoggerContext.setItem(name)
            body.build()
            MonsteraLoggerContext.clear()
        }
        //blocks
        blocks.forEach { (name, body) ->
            MonsteraLoggerContext.setBlock(name)
            body.build()
            MonsteraLoggerContext.clear()
        }
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
            minEnginVersion = ArrayList(manifestMinEnginVersion),
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
        when (argParsed["buildtomcfolder"]) {
            null, "true" -> if (buildToMcFolder) buildToMcFolder(config)
            "resourcepack", "resource" -> overwriteResourceInMcFolder(config)
        }

        onPackage.forEach {
            it.invoke(this)
        }

        when(argParsed["zipworld"]) {
            null, "true" -> zipWorld(config)
            else -> zipWorld(config, argParsed["zipworld"]!!)
        }

        when(argParsed["zipaddon"]) {
            null, "true" -> zipWorld(config)
            else -> zipWorld(config, argParsed["zipaddon"]!!)
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
            "say §b#§a build version: ${
                (System.getenv("CI_COMMIT_REF_NAME") ?: System.getenv("GITHUB_REF") ?: getVersionAsString(
                    addon.config.version
                ))
            }",
            "say §b#§a build time: $time",
            "say §b$format",
        )
    }
}