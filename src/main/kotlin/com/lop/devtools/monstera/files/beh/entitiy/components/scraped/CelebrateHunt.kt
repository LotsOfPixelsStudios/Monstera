package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class CelebrateHunt {
    @SerializedName("celebration_targets")
    @Expose
    var celebrationTargetsData: BehEntityFilter? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * celebrationTargets {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun celebrationTargets(value: BehEntityFilter.() -> Unit) {
        celebrationTargetsData = (celebrationTargetsData ?: BehEntityFilter()).apply(value)
    }

    @SerializedName("broadcast")
    @Expose
    var broadcast: Boolean? = null
        

    @SerializedName("duration")
    @Expose
    var duration: Number? = null
        

    @SerializedName("celebrate_sound")
    @Expose
    var celebrateSound: String? = null
        

    @SerializedName("sound_interval")
    @Expose
    var soundIntervalData: SoundInterval? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * soundInterval {
     *     rangeMin = 2.0
     *     rangeMax = 5.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun soundInterval(value: SoundInterval.() -> Unit) {
        soundIntervalData = (soundIntervalData ?: SoundInterval()).apply(value)
    }

    @SerializedName("radius")
    @Expose
    var radius: Number? = null
        

    class SoundInterval {
        @SerializedName("range_min")
        @Expose
        var rangeMin: Number? = null
            

        @SerializedName("range_max")
        @Expose
        var rangeMax: Number? = null
            
    }
}
