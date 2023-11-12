package com.lop.devtools.monstera.addon.block.resource

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.block.Block
import com.lop.devtools.monstera.addon.sound.Sound
import com.lop.devtools.monstera.files.getUniqueFileName
import com.lop.devtools.monstera.files.res.blocks.BlockDefs
import com.lop.devtools.monstera.files.res.blocks.TerrainTextures
import java.io.File

open class DefaultBlockImpl(private val parentRef: Block, private val addonRef: Addon) : DefaultBLock {
    override var isotropic: Boolean = false
        set(value) {
            BlockDefs.instance(addonRef).addDefinition(parentRef.name) {
                isotropic = value
            }
            field = value
        }
    override var brightnessGamma: Number = 1
        set(value) {
            BlockDefs.instance(addonRef).addDefinition(parentRef.name) {
                brightnessGamma = value
            }
            field = value
        }

    override fun texture(path: String, name: String, face: DefaultBLock.Face) {
        TerrainTextures.instance(addonRef).addBlockTexture(name, path)

        BlockDefs.instance(addonRef).addDefinition(parentRef.name) {
            texture {
                when (face) {
                    DefaultBLock.Face.ALL -> texture(name)
                    DefaultBLock.Face.UP -> up(name)
                    DefaultBLock.Face.DOWN -> down(name)
                    DefaultBLock.Face.SITE -> side(name)
                    DefaultBLock.Face.NORTH -> north(name)
                    DefaultBLock.Face.SOUTH -> south(name)
                    DefaultBLock.Face.WEST -> west(name)
                    DefaultBLock.Face.EAST -> east(name)
                }
            }
        }
    }

    override fun texture(file: File, face: DefaultBLock.Face) {
        val fileName = getUniqueFileName(file)
        val target = addonRef.config.paths.resTextures.resolve("monstera").resolve(fileName)
        file.copyTo(target.toFile(), true)
        texture(
            "textures/monstera/${fileName.removeSuffix(".png")}",
            fileName.removeSuffix(".png"),
            face
        )
    }

    override fun sound(identifier: String, data: Sound.() -> Unit): String = parentRef.sound(identifier, data)

    override fun sound(name: String) {
        parentRef.sound(name)
    }

    override fun carriedTextures(vararg files: File) {
        val textureFile = files[0]
        val fileName = getUniqueFileName(textureFile)
        val fileNameWithoutSuffix = fileName.removeSuffix(".png")
        val target = addonRef.config.paths.resTextures.resolve("monstera").resolve(fileName)
        textureFile.copyTo(target.toFile(), true)
        TerrainTextures.instance(addonRef).addBlockTexture(
            fileNameWithoutSuffix,
            "textures/monstera/${fileNameWithoutSuffix}"
        )
        BlockDefs.instance(addonRef).addDefinition(parentRef.name) {
            carriedTextures = fileNameWithoutSuffix
        }
    }
}