package com.lop.devtools.monstera.addon.sound

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.files.res.sounds.*
import com.lop.devtools.monstera.getMonsteraLogger
import java.io.File

class Sound(val addon: Addon) {
    private fun logger() = getMonsteraLogger("Sound")

    /**
     * like `identifier = tp.test.drive`
     */
    var identifier: String = ""
        set(value) {
            if (value.contains(":")) {
                logger().warn(
                    "($identifier) Sound identifier should not contain a ':', follow the naming convention" +
                            " '<projectShort>.<entity>.<sound>' like 'tp.test.drive'"
                )
            }
            field = value
        }

    /**
     * the pitch that should be applied, as a range (from, to)
     */
    var pitch: Pair<Number, Number> = 1 to 1

    /**
     * the volume that should be applied, as a range (from, to)
     */
    var volume: Pair<Number, Number> = 1 to 1

    /**
     * the maximum distance the sound can be heard from
     */
    var maxDistance: Number = -1

    /**
     * the minimum distance the sound can be heard from
     */
    var minDistance: Number = -1

    /**
     * the category gives some custom behaviour that one might want to use -> environment has no direction from where
     * the sound is coming from ...
     */
    var category: SoundCategory = SoundCategory.BLOCK
    var categorySound: CategorySound = CategorySound.BLOCK

    private var files: MutableMap<File, Sound.SoundDefData> = mutableMapOf()
    private var filePath: MutableMap<String, Sound.SoundDefData> = mutableMapOf()
    var skipSoundDefComponent = false
    val soundEvents = mutableMapOf<SoundEvent, SoundEventSettings>()

    /**
     * the sound as a .ogg file, can be called multiple times, but it will be randomised what file is played!
     *
     * @param is3D determines if the sound should have a direction where it's coming from
     * @param weight if called multiple times, the sounds will be randomly selected, give a weight for a relation
     * @param pitch a custom pitch for that sound file
     * @param volume a custom volume for that sound file
     */
    fun sound(file: File, is3D: Boolean = true, weight: Number = 1, pitch: Number = 1, volume: Number = 1) {
        files[file] = Sound.SoundDefData(is3D, volume, weight, pitch)
    }

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
    fun sound(files: MutableList<Pair<File, Sound.SoundDefData>>) {
        this.files.putAll(files)
    }

    /**
     * generally not safe to use, use sound(file) unless one makes sure the File already exists in the build files
     *
     * @param path the path to the build file `sounds/sound.ogg`
     * @param is3D determines if the sound should have a direction where it's coming from
     * @param weight if called multiple times, the sounds will be randomly selected, give a weight for a relation
     * @param pitch a custom pitch for that sound file
     * @param volume a custom volume for that sound file
     */
    fun sound(path: String, is3D: Boolean = true, weight: Number = 1, pitch: Number = 1, volume: Number = 1) {
        filePath[path] = Sound.SoundDefData(is3D, volume, weight, pitch)
    }

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
    fun importSound(identifier: String) {
        skipSoundDefComponent = true
        this.identifier = identifier
    }

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
    ) {
        if (soundEvents.containsKey(event))
            soundEvents[event]!!.apply {
                applyOwnSettings()
            }
        else
            soundEvents[event] = SoundEventSettings().apply {
                applyOwnSettings()
            }
    }

    private fun SoundEventSettings.applyOwnSettings() {
        pitch(pitch)
        volume(volume)
        this.sound = identifier
    }

    fun getAsSoundComponent(): List<ResSoundComp.() -> Unit> {
        val ret = mutableListOf<ResSoundComp.() -> Unit>()

        files.forEach { (file, settings) ->
            ret.add {
                weight = settings.weight
                is3D = settings.is3D
                name(file, addon = addon)
                volume = settings.volume
                pitch = settings.pitch
            }
        }

        filePath.forEach { (file, settings) ->
            ret.add {
                weight = settings.weight
                is3D = settings.is3D
                name = file
                volume = settings.volume
                pitch = settings.pitch
            }
        }
        return ret
    }

    data class SoundDefData(
        var is3D: Boolean = true,
        var volume: Number = 1,
        var weight: Number = 1,
        var pitch: Number = 1
    )
}