package com.lop.devtools.monstera.addon.block

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.sound.Sound
import com.lop.devtools.monstera.files.getUniqueFileName
import com.lop.devtools.monstera.files.res.blocks.BlockDefs
import com.lop.devtools.monstera.files.res.blocks.TerrainTextures
import java.io.File

open class DefaultBLock(private val parentRef: Block, private val addonRef: Addon) {
    var isotropic: Boolean = false
        set(value) {
            BlockDefs.instance(addonRef).addDefinition(parentRef.name) {
                isotropic = value
            }
            field = value
        }
    var brightnessGamma: Number = 1
        set(value) {
            BlockDefs.instance(addonRef).addDefinition(parentRef.name) {
                brightnessGamma = value
            }
            field = value
        }

    /**
     * call as often as there are faces, if called with the same face, the texture will be overwritten, you may don't use
     * this unless you make sure the file exists in the build files
     *
     * @param path the path in the build files to the texture
     * @param name the name of the texture referenced in the resource pack
     * @param face the face to apply on the bock see [Face]
     */
    fun texture(path: String, name: String, face: Face) {
        TerrainTextures.instance(addonRef).addBlockTexture(name, path)

        BlockDefs.instance(addonRef).addDefinition(parentRef.name) {
            texture {
                when (face) {
                    Face.ALL -> texture(name)
                    Face.UP -> up(name)
                    Face.DOWN -> down(name)
                    Face.SITE -> side(name)
                    Face.NORTH -> north(name)
                    Face.SOUTH -> south(name)
                    Face.WEST -> west(name)
                    Face.EAST -> east(name)
                }
            }
        }
    }

    /**
     * call as often as there are faces, if called with the same face, the texture will be overwritten
     *
     * @param file the texture file
     * @param face the face to apply on the bock see [Face]
     */
    fun texture(file: File, face: Face) {
        val fileName = getUniqueFileName(file)
        val target = addonRef.config.paths.resTextures.resolve("monstera").resolve(fileName)
        file.copyTo(target.toFile(), true)
        texture(
            "textures/monstera/${fileName.removeSuffix(".png")}",
            fileName.removeSuffix(".png"),
            face
        )
    }

    /**
     * add a sound to the block, call multiple time for different sound files
     *
     * ```
     * sound {
     *     identifier = "block.sand.fall"
     *     pitch = 1f to 1.2f   //default 1 to 1
     *     volume = 0.7f to 1f  //default 1 to 1
     *     maxDistance = 16
     *     minDistance = 2
     *     category = SoundCategory.BLOCK //default
     *     weight = 10 //use sound() with multiple files, this will be ignored on it's own!
     *     is3D = true //true is default
     *     sound(getResource(/*...*/))
     *    onEvent(event = SoundEvent.DEFAULT, pitch = 1 to 2, volume = 0.7f to 1)
     *    onEvent(event = SoundEvent.PLACE, pitch = 0.9f to 1, volume = 0.7f to 1)
     *
     *    //you can also import sounds definitions with some limitations like
     *    importSound("ambient.basalt_deltas.loop") //note: this will overwrite the identifier if one is set!
     *
     *    //or define multiple Sounds that will be randomly selected
     *    sound(arrayListOf(
     *        getResource("file.ogg") to SoundDefData(is3D = false, volume = 1.2f, pitch = 1, weight = 3),
     *        getResource("file.ogg") to SoundDefData(is3D = false, volume = 1.1f, pitch = 1, weight = 2),
     *        getResource("file.ogg") to SoundDefData() //use default values
     *    ))
     * }
     * ```
     *
     * @param data the sound data described in the sampe code
     * @return the identifier
     */
    fun sound(identifier: String, data: Sound.() -> Unit): String = parentRef.sound(identifier, data)

    /**
     * you may want to use this function to import existing sound sounds form sound.json like, note this will overwrite
     * other sound {} definitions as this will just take and apply the defined ones
     *
     * ```
     * sound("amethyst_block")
     * //or
     * sound("nether_brick")
     * ```
     */
    fun sound(name: String) {
        parentRef.sound(name)
    }

    /**
     * Experimental: not quite sure if this will work in the future, be safe for now and just give 1 file
     *
     * @param files the texture file that will be carried over
     */
    fun carriedTextures(vararg files: File) {
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

    enum class Face {
        ALL,
        UP,
        DOWN,
        SITE,
        NORTH,
        SOUTH,
        WEST,
        EAST;
    }
}