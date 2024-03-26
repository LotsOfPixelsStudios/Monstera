package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class Timer {
    @SerializedName("looping")
    @Expose
    var looping: Boolean? = null
        

    @SerializedName("time")
    @Expose
    var time: Number? = null
        

    @SerializedName("time_down_event")
    @Expose
    var timeDownEventData: TimeDownEvent? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * timeDownEvent {
     *     event = pickup_item_delay_complete
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun timeDownEvent(value: TimeDownEvent.() -> Unit) {
        timeDownEventData = (timeDownEventData ?: TimeDownEvent()).apply(value)
    }

    @SerializedName("randomInterval")
    @Expose
    var randomInterval: Boolean? = null
        

    @SerializedName("random_time_choices")
    @Expose
    var randomTimeChoicesData: MutableList<RandomTimeChoices>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * randomTimeChoices {
     *     weight = 50
     *     value = 2400
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun randomTimeChoices(value: RandomTimeChoices.() -> Unit) {
        randomTimeChoicesData =
            (randomTimeChoicesData ?: mutableListOf()).also { it.add(RandomTimeChoices().apply(value)) }
    }

    class TimeDownEvent {
        @SerializedName("event")
        @Expose
        var event: String? = null

        @SerializedName("target")
        @Expose
        var target: Subject? = null
    }

    class RandomTimeChoices {
        @SerializedName("weight")
        @Expose
        var weight: Number? = null
            

        @SerializedName("value")
        @Expose
        var value: Number? = null
            
    }
}
