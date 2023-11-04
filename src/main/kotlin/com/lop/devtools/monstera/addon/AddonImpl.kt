package com.lop.devtools.monstera.addon

import com.lop.devtools.monstera.Config
import com.lop.devtools.monstera.addon.api.InvokeBeforeEnd
import com.lop.devtools.monstera.addon.api.PackageInvoke
import com.lop.devtools.monstera.addon.block.Block
import com.lop.devtools.monstera.addon.block.BlockImpl
import com.lop.devtools.monstera.addon.dev.buildToMcFolder
import com.lop.devtools.monstera.addon.dev.validateTextures
import com.lop.devtools.monstera.addon.entity.BasicEntity
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

abstract class AddonImpl(
    final override val config: Config
) : Addon {
    val unsafe = Unsafe()
    private val logger = LoggerFactory.getLogger("Addon")

    inner class Unsafe {
        val sounds = SoundUtil(this@AddonImpl)
        val mcFunctions = mutableListOf<McFunction>()
    }

    override val onEndListener: ArrayList<InvokeBeforeEnd> = arrayListOf()
    override val onPackage: ArrayList<PackageInvoke> = arrayListOf()

    override var buildFunctions: Boolean = true
    override var buildTextureList: Boolean = true
    override var buildItemTextureIndex: Boolean = true
    override var buildToMcFolder: Boolean = true
    override var manifestMinEnginVersion: ArrayList<Int> = config.targetMcVersion

    override var includeInfoMcFunction: Boolean = true

    override fun entity(name: String, displayName: String, entity: Entity.() -> Unit): Entity {
        val ent = BasicEntity(this, name, displayName).apply(entity)
        ent.build()
        return ent
    }

    override fun sounds(sounds: SoundUtil.() -> Unit): SoundUtil {
        return unsafe.sounds.apply(sounds)
    }

    override fun item(name: String, displayName: String, item: Item.() -> Unit): Item {
        val data = Item(name, displayName, this).apply(item)
        data.build()
        return data
    }

    override fun mcFunction(name: String, data: McFunction.() -> Unit): String {
        val func = McFunction(name).apply(data)
        unsafe.mcFunctions.add(func)
        return func.name
    }

    override fun loadParticle(value: File) {
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

    override fun block(name: String, displayName: String, data: Block.() -> Unit): Block {
        return BlockImpl(this, name, displayName).apply(data).build()
    }

    override fun scripts(directory: File) {
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

    override fun build() {
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