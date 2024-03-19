package com.lop.devtools.monstera.addon.block

import com.lop.devtools.monstera.addon.sound.Sound
import com.lop.devtools.monstera.files.beh.blocks.BehBlocks
import com.lop.devtools.monstera.addon.block.resource.DefaultBLock
import java.io.File

interface Block {
    /**
     * get the name of the block, set with name()
     */
    val name: String

    /**
     * get the display name, set with name()
     */
    val displayName: String

    /**
     * returns the identifier of the block that can be called in a command
     */
    fun identifier(): String

    /**
     * set a custom geometry, note: generally not save to use, make sure the model is already in the build files
     */
    fun geometry(geoId: String)

    /**
     * set a custom geometry
     */
    fun geometry(file: File)

    fun texture(name: String, path: String, settings: BehBlocks.MaterialSettings.() -> Unit = {})

    /**
     * set a texture that is applied on all sites
     */
    fun texture(file: File, settings: BehBlocks.MaterialSettings.() -> Unit = {})

    /**
     * add block with the default geometry, some assumptions can be made, use this to add a more efficient block
     *
     * ```
     * defaultBlock {
     *     isotropic = true
     *     brightnessGamma = 1
     *     texture(file, Face.ALL)
     *     sound(file) //may change in the future
     *     carriedTextures(file)
     * }
     * ```
     */
    fun defaultBlock(data: DefaultBLock.() -> Unit)

    /**
     * add a sound to the block, call multiple times for different sound files
     *
     * ```
     * sound(identifier = "block.sand.fall") {
     *     pitch = 1f to 1.2f   //default 1 to 1
     *     volume = 0.7f to 1f  //default 1 to 1
     *     maxDistance = 16
     *     minDistance = 2
     *     category = SoundCategory.BLOCK //default
     *     sound(getResource(/*...*/))  //load a single ogg file with optional settings, see docs of [Sounds.sound()], can be called multiple times
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

    fun build(): Block
}