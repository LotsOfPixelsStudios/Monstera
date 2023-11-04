package com.lop.devtools.monstera.files.res.sounds

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.getUniqueFileName
import java.io.File

@Suppress("unused")
class ResSoundDef: MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        /**
         * access to all defined animations
         */
        val general = mutableMapOf<String, Any>()
        val sounds = arrayListOf<Any>()

        override fun getData(): MutableMap<String, Any> {
            unsafe.general["sounds"] = unsafe.sounds
            return unsafe.general
        }
    }

    /**
     * 0..1
     *
     * @param value bool
     */
    fun useLegacyMaxDistance(value: Boolean = true) {
        unsafe.general["__use_legacy_max_distance"] = value
    }

    /**
     * 0..1
     *
     * @param value the category to choose from
     */
    fun category(value: SoundCategory) {
        unsafe.general["category"] = value.toString().lowercase()
    }

    fun maxDistance(value: Float = 8.0f) {
        unsafe.general["max_distance"] = value
    }

    fun minDistance(value: Float = 1.0f) {
        unsafe.general["min_distance"] = value
    }

    /**
     * 0..*
     *
     * add a complex sound
     * @param sound the obj where all values are listed
     */
    fun sound(sound: ResSoundComp.() -> Unit) {
        unsafe.sounds.add(ResSoundComp().apply(sound).unsafe.getData())
    }

    /**
     * 0..1
     *
     * add a simple sound path
     * @param sound a string with the sound path e.g. "sounds/block/beehive/drip2"
     */
    fun sound(sound: String) {
        unsafe.sounds.add(sound)
    }

    /**
     * 0..1
     *
     * add a simple sound path
     * @param sound a file that will be copied into the resource pack
     */
    fun sound(sound: File, addon: Addon) {
        val uniqueFileName = getUniqueFileName(sound)
        val targetPath = addon.config.paths.resSounds.resolve("monstera").resolve(uniqueFileName)
        sound.copyTo(
            targetPath.toFile(),
            overwrite = true
        )

        val fileType = sound.name.split(".").last()
        unsafe.sounds.add("sounds/monstera/" + uniqueFileName.removeSuffix(".$fileType"))
    }
}