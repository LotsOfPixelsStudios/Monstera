package com.lop.devtools.monstera.addon.block

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.block.resource.DefaultBLock
import com.lop.devtools.monstera.addon.block.resource.DefaultBlockImpl
import com.lop.devtools.monstera.addon.entity.getGeoId
import com.lop.devtools.monstera.addon.sound.Sound
import com.lop.devtools.monstera.addon.sound.SoundData
import com.lop.devtools.monstera.addon.sound.unsafeApplySoundData
import com.lop.devtools.monstera.files.beh.blocks.BehBlocks
import com.lop.devtools.monstera.files.createWithDirs
import com.lop.devtools.monstera.files.getUniqueFileName
import com.lop.devtools.monstera.files.getVersionAsString
import com.lop.devtools.monstera.files.res.blocks.BlockDefs
import com.lop.devtools.monstera.files.res.blocks.TerrainTextures
import java.io.File

open class BlockImpl(private val addon: Addon, override var name: String, override var displayName: String) : Block {
    val unsafeBehBlock = BehBlocks()
    val unsafeBlockDefs = BlockDefs.instance(addon)
    val unsafeTerrainTextures = TerrainTextures.instance(addon)
    val unsafeSoundData: MutableList<SoundData> = mutableListOf()

    override fun identifier() = "${addon.config.namespace}:$name"

    /**
     * set a geometry with id, note: generally not save to use, make sure the model is already in the build files
     */
    override fun geometry(geoId: String) {
        unsafeBehBlock.components {
            geometry(geoId)
        }
    }

    override fun geometry(file: File) {
        val uniqueFilename = getUniqueFileName(file)
        val target = addon.config.paths.resModels.resolve("blocks").resolve(uniqueFilename).toFile()
        file.copyTo(target.createWithDirs(), true)
        geometry(getGeoId(file))
    }

    override fun texture(name: String, path: String, settings: BehBlocks.MaterialSettings.() -> Unit) {
        unsafeBehBlock.components {
            materialInstance {
                all {
                    texture(name)
                    this.apply(settings)
                }
            }
        }
        unsafeTerrainTextures.addBlockTexture(name, path)   //terrainTextures obj manages duplicate block names
    }

    override fun texture(file: File, settings: BehBlocks.MaterialSettings.() -> Unit) {
        val uniqueFilename = getUniqueFileName(file)
        val target = addon.config.paths.resTextures.resolve("monstera").resolve(uniqueFilename).toFile()
        file.copyTo(target.createWithDirs(), true)
        val name = getUniqueFileName(file).removeSuffix(".png")
        val path = "textures/monstera/$name"
        texture(name, path, settings)
    }

    override fun defaultBlock(data: DefaultBLock.() -> Unit) {
        DefaultBlockImpl(this, addon).apply(data)
    }

    override fun sound(data: Sound.() -> Unit): String {
        val soundData = SoundData(addon).apply(data)
        unsafeSoundData.add(soundData)
        return soundData.identifier
    }

    override fun sound(name: String) {
        unsafeBlockDefs.addDefinition(identifier()) {
            sound = name
        }
    }

    override fun build(): Block {
        addon.config.langFileBuilder.addonRes.add("tile.${identifier()}.name", displayName)
        unsafeBehBlock.apply {
            description {
                identifier("${addon.config.namespace}:$name")
            }
        }
        unsafeBehBlock.unsafe.build(
            name,
            getVersionAsString(addon.config.targetMcVersion),
            addon.config.paths.behBlocks
        )
        if (unsafeSoundData.isNotEmpty()) {
            unsafeBlockDefs.addDefinition(identifier()) {
                sound = name
            }
            addon.unsafeApplySoundData(unsafeSoundData, name)
        }
        return this
    }
}