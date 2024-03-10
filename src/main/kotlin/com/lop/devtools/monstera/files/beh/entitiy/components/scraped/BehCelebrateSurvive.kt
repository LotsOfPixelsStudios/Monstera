package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class BehCelebrateSurvive {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("fireworks_interval")
    @Expose
    var fireworksIntervalData: FireworksInterval? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * fireworksInterval {
     *     rangeMin = 2.0
     *     rangeMax = 7.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun fireworksInterval(value: FireworksInterval.() -> Unit) {
        fireworksIntervalData = (fireworksIntervalData ?: FireworksInterval()).apply(value)
    }

    @SerializedName("duration")
    @Expose
    var duration: Number? = null
        

    @SerializedName("on_celebration_end_event")
    @Expose
    var onCelebrationEndEventData: OnCelebrationEndEvent? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onCelebrationEndEvent {
     *     event = minecraft:stop_celebrating
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onCelebrationEndEvent(value: OnCelebrationEndEvent.() -> Unit) {
        onCelebrationEndEventData = (onCelebrationEndEventData ?: OnCelebrationEndEvent()).apply(value)
    }

    class FireworksInterval {
        @SerializedName("range_min")
        @Expose
        var rangeMin: Number? = null
            

        @SerializedName("range_max")
        @Expose
        var rangeMax: Number? = null
            
    }

    class OnCelebrationEndEvent {
        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }
}
