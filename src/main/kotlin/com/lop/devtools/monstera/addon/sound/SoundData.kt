package com.lop.devtools.monstera.addon.sound

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.files.res.sounds.*
import com.lop.devtools.monstera.getMonsteraLogger
import java.io.File

class SoundData(val addon: Addon) : Sound {
    private fun logger() = getMonsteraLogger("Sound")

    override var identifier: String = ""
        set(value) {
            if (value.contains(":")) {
                logger().warn("($identifier) Sound identifier should not contain a ':', follow the naming convention" +
                        " '<projectShort>.<entity>.<sound>' like 'tp.test.drive'")
            }
            field = value
        }
    override var pitch: Pair<Number, Number> = 1 to 1
    override var volume: Pair<Number, Number> = 1 to 1
    override var maxDistance: Number = -1
    override var minDistance: Number = -1
    override var category: SoundCategory = SoundCategory.BLOCK
    override var categorySound: CategorySound = CategorySound.BLOCK

    private var files: MutableMap<File, Sound.SoundDefData> = mutableMapOf()
    private var filePath: MutableMap<String, Sound.SoundDefData> = mutableMapOf()
    var skipSoundDefComponent = false
    val soundEvents = mutableMapOf<SoundEvent, SoundEventSettings>()

    override fun sound(file: File, is3D: Boolean, weight: Number, pitch: Number, volume: Number) {
        files[file] = Sound.SoundDefData(is3D, volume, weight, pitch)
    }

    override fun sound(files: MutableList<Pair<File, Sound.SoundDefData>>) {
        this.files.putAll(files)
    }

    override fun sound(path: String, is3D: Boolean, weight: Number, pitch: Number, volume: Number) {
        filePath[path] = Sound.SoundDefData(is3D, volume, weight, pitch)
    }

    override fun importSound(identifier: String) {
        skipSoundDefComponent = true
        this.identifier = identifier
    }

    override fun onEvent(event: SoundEvent, pitch: Pair<Number, Number>, volume: Pair<Number, Number>) {
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
}