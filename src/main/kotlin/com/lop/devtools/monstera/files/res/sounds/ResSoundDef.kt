package com.lop.devtools.monstera.files.res.sounds

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.getUniqueFileName
import java.io.File

@Suppress("unused")
class ResSoundDef: MonsteraFile {
    @SerializedName("sounds")
    @Expose
    var soundsData: MutableList<Any>? = null
        @MonsteraBuildSetter set

    @SerializedName("category")
    @Expose
    var category: SoundCategory? = null

    @SerializedName("__use_legacy_max_distance")
    @Expose
    var useLegacyMaxDistance: Boolean? = null

    @SerializedName("max_distance")
    @Expose
    var maxDistance: Number? = null

    @SerializedName("min_distance")
    @Expose
    var minDistance: Number? = null

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
    @Deprecated("use field", ReplaceWith("useLegacyMaxDistance = value"))
    fun useLegacyMaxDistance(value: Boolean = true) {
        useLegacyMaxDistance = value
    }

    /**
     * 0..1
     *
     * @param value the category to choose from
     */
    @Deprecated("use field", ReplaceWith("category = value"))
    fun category(value: SoundCategory) {
        category = value
    }

    @Deprecated("use field", ReplaceWith("maxDistance = value"))
    fun maxDistance(value: Float = 8.0f) {
        maxDistance = value
    }

    @Deprecated("use field", ReplaceWith("minDistance = value"))
    fun minDistance(value: Float = 1.0f) {
        minDistance = value
    }

    /**
     * 0..*
     *
     * add a complex sound
     * @param sound the obj where all values are listed
     */
    @OptIn(MonsteraBuildSetter::class)
    fun sound(sound: ResSoundComp.() -> Unit) {
        soundsData = (soundsData ?: mutableListOf()).apply { add(ResSoundComp().apply(sound)) }
    }

    /**
     * 0..1
     *
     * add a simple sound path
     * @param sound a string with the sound path e.g. "sounds/block/beehive/drip2"
     */
    @OptIn(MonsteraBuildSetter::class)
    fun sound(sound: String) {
        soundsData = (soundsData ?: mutableListOf()).apply { add(sound) }
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
        sound("sounds/monstera/" + uniqueFileName.removeSuffix(".$fileType"))
    }
}