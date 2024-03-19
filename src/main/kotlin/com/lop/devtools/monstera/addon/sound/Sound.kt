package com.lop.devtools.monstera.addon.sound

import com.lop.devtools.monstera.files.res.sounds.CategorySound
import com.lop.devtools.monstera.files.res.sounds.SoundCategory
import com.lop.devtools.monstera.files.res.sounds.SoundEvent
import java.io.File

interface Sound {
    /**
     * like `identifier = tp.test.drive`
     */
    var identifier: String

    /**
     * the pitch that should be applied, as a range (from, to)
     */
    var pitch: Pair<Number, Number>

    /**
     * the volume that should be applied, as a range (from, to)
     */
    var volume: Pair<Number, Number>

    /**
     * the maximum distance the sound can be heard from
     */
    var maxDistance: Number

    /**
     * the minimum distance the sound can be heard from
     */
    var minDistance: Number

    /**
     * the category gives some custom behaviour that one might want to use -> environment has no direction from where
     * the sound is coming from ...
     */
    var category: SoundCategory

    var categorySound: CategorySound

    /**
     * the sound as a .ogg file, can be called multiple times, but it will be randomised what file is played!
     *
     * @param is3D determines if the sound should have a direction where it's coming from
     * @param weight if called multiple times, the sounds will be randomly selected, give a weight for a relation
     * @param pitch a custom pitch for that sound file
     * @param volume a custom volume for that sound file
     */
    fun sound(file: File, is3D: Boolean = true, weight: Number = 1, pitch: Number = 1, volume: Number = 1)

    /**
     * define a sound that will choose randomly form the sounds provided
     *
     * ```
     * sound(arrayListOf(
     *     getResource("file.ogg") to SoundDefData(is3D = false, volume = 1.2f, pitch = 1, weight = 3),
     *     getResource("file.ogg") to SoundDefData(is3D = false, volume = 1.1f, pitch = 1, weight = 2)
     * ))
     * ```
     */
    fun sound(files: MutableList<Pair<File, SoundDefData>>)

    /**
     * generally not safe to use, use sound(file) unless one makes sure the File already exists in the build files
     *
     * @param path the path to the build file `sounds/sound.ogg`
     * @param is3D determines if the sound should have a direction where it's coming from
     * @param weight if called multiple times, the sounds will be randomly selected, give a weight for a relation
     * @param pitch a custom pitch for that sound file
     * @param volume a custom volume for that sound file
     */
    fun sound(path: String, is3D: Boolean = true, weight: Number = 1, pitch: Number = 1, volume: Number = 1)

    /**
     * import a sound form an already defined sound like
     *
     * ```
     * importSound("hit.nether_brick")
     * //or
     * importSound("block.muddy_mangrove_roots.break")
     * ```
     *
     * Note: this will overwrite the identifier and some functions will not work such as: `is3D`, `weight`
     */
    fun importSound(identifier: String)

    /**
     * when to the sound should get triggered, optional
     * note: if called with the same event, pitch and volume will be overwritten
     *
     * @param event the event on when the sound should get triggered
     * @param pitch a custom pitch for that event, defaults to the pitch set in [Sound.pitch]
     * @param volume a custom volume for that event, defaults to the volume set in [Sound.volume]
     */
    fun onEvent(
        event: SoundEvent = SoundEvent.DEFAULT,
        pitch: Pair<Number, Number> = this.pitch,
        volume: Pair<Number, Number> = this.volume
    )

    data class SoundDefData(
        var is3D: Boolean = true,
        var volume: Number = 1,
        var weight: Number = 1,
        var pitch: Number = 1
    )
}