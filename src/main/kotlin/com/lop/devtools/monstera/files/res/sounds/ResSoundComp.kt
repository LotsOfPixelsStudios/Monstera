package com.lop.devtools.monstera.files.res.sounds

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.getUniqueFileName
import java.io.File

class ResSoundComp: MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        /**
         * access to all defined animations
         */
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            unsafe.general["is3D"] = is3D
            unsafe.general["weight"] = weight
            return unsafe.general
        }
    }
    var is3D: Boolean = false

    var weight: Number = 1

    /**
     * 0..1
     *
     * @param value The path to the file, such as: "sounds/music/game/creative/creative1"
     */
    fun name(value: String) {
        unsafe.general["name"] = value
    }

    fun name(value: File, addon: Addon) {
        val uniqueFileName = getUniqueFileName(value)
        val targetPath = addon.config.paths.resSounds.resolve("monstera").resolve(uniqueFileName)
        value.copyTo(targetPath.toFile(), true)
        val fileType = value.name.split(".").last()
        unsafe.general["name"] = "sounds/monstera/" + uniqueFileName.removeSuffix(".$fileType")
    }

    @ExperimentalUnsignedTypes
    fun stream() {
        unsafe.general["stream"] = mutableMapOf<String, String>()
    }

    /**
     * 0..1
     *
     * How loud the sound should play. Sounds cannot be made more audible than initially encoded.
     * @param value between 0.0 & 1.0
     */
    fun volume(value: Float = 1.0f) {
        unsafe.general["volume"] = value
    }

    /**
     * 0..1
     *
     * @param value The pitch of the sound (how low/high it sounds). Ranges from 0.0 to 1.0 (standard), but can be higher, such as 1.48.
     */
    fun pitch(value: Float = 1.0f) {
        unsafe.general["pitch"] = value
    }
}