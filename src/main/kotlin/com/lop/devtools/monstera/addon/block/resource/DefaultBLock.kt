package com.lop.devtools.monstera.addon.block.resource

import com.lop.devtools.monstera.addon.sound.Sound
import java.io.File

interface DefaultBLock {
    var isotropic: Boolean
    var brightnessGamma: Number

    /**
     * call as often as there are faces, if called with the same face, the texture will be overwritten, you may don't use
     * this unless you make sure the file exists in the build files
     *
     * @param path the path in the build files to the texture
     * @param name the name of the texture referenced in the resource pack
     * @param face the face to apply on the bock see [Face]
     */
    fun texture(path: String, name: String, face: Face)

    /**
     * call as often as there are faces, if called with the same face, the texture will be overwritten
     *
     * @param file the texture file
     * @param face the face to apply on the bock see [Face]
     */
    fun texture(file: File, face: Face = Face.ALL)

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
    fun sound(identifier: String, data: Sound.() -> Unit): String

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
    fun sound(name: String)

    /**
     * Experimental: not quite sure if this will work in the future, be safe for now and just give 1 file
     *
     * @param files the texture file that will be carried over
     */
    fun carriedTextures(vararg files: File)

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
