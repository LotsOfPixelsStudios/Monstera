package com.lop.devtools.monstera.files.res.sounds

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.HolidayCreatorFeature
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.getUniqueFileName
import java.io.File

open class ResSoundComp {
    @SerializedName("is3D")
    @Expose
    var is3D: Boolean? = null

    @SerializedName("weight")
    @Expose
    var weight: Number? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("stream")
    @Expose
    var stream: MutableMap<Any, Any>? = null
        @MonsteraBuildSetter set

    @SerializedName("volume")
    @Expose
    var volume: Number? = null

    @SerializedName("pitch")
    @Expose
    var pitch: Number? = null

    /**
     * 0..1
     *
     * @param value The path to the file, such as: "sounds/music/game/creative/creative1"
     */
    @Deprecated("use field", ReplaceWith("name = value"))
    fun name(value: String) {
        name = value
    }

    fun name(value: File, addon: Addon) {
        val uniqueFileName = getUniqueFileName(value)
        val targetPath = addon.config.paths.resSounds.resolve("monstera").resolve(uniqueFileName)
        value.copyTo(targetPath.toFile(), true)
        val fileType = value.name.split(".").last()
        name = "sounds/monstera/" + uniqueFileName.removeSuffix(".$fileType")
    }

    @OptIn(MonsteraBuildSetter::class)
    @HolidayCreatorFeature
    fun stream() {
        stream = mutableMapOf()
    }

    /**
     * 0..1
     *
     * How loud the sound should play. Sounds cannot be made more audible than initially encoded.
     * @param value between 0.0 & 1.0
     */
    @Deprecated("use field", ReplaceWith("volume = value"))
    fun volume(value: Float = 1.0f) {
        volume = value
    }

    /**
     * 0..1
     *
     * @param value The pitch of the sound (how low/high it sounds). Ranges from 0.0 to 1.0 (standard), but can be higher, such as 1.48.
     */
    fun pitch(value: Float = 1.0f) {
        pitch = value
    }
}