package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class BehPanic : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null

    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null

    @SerializedName("force")
    @Expose
    var force: Boolean? = null

    @SerializedName("ignore_mob_damage")
    @Expose
    var ignoreMobDamage: Boolean? = null

    @SerializedName("panic_sound")
    @Expose
    var panicSound: String? = null

    @SerializedName("sound_interval")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var soundIntervalData: SoundInterval? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * soundInterval {
     *     rangeMin = 1.0
     *     rangeMax = 3.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun soundInterval(value: SoundInterval.() -> Unit) {
        soundIntervalData = (soundIntervalData ?: SoundInterval()).apply(value)
    }

    @SerializedName("prefer_water")
    @Expose
    var preferWater: Boolean? = null
        
    class SoundInterval : MonsteraRawFile() {
        @SerializedName("range_min")
        @Expose
        var rangeMin: Number? = null
        
        @SerializedName("range_max")
        @Expose
        var rangeMax: Number? = null
    }
}
