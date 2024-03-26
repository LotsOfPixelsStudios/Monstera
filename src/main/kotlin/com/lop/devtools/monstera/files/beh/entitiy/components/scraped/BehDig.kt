package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class BehDig {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("duration")
    @Expose
    var duration: Number? = null
        

    @SerializedName("idle_time")
    @Expose
    var idleTime: Number? = null
        

    @SerializedName("vibration_is_disturbance")
    @Expose
    var vibrationIsDisturbance: Boolean? = null
        

    @SerializedName("suspicion_is_disturbance")
    @Expose
    var suspicionIsDisturbance: Boolean? = null
        

    @SerializedName("digs_in_daylight")
    @Expose
    var digsInDaylight: Boolean? = null
        

    @SerializedName("on_start")
    @Expose
    var onStartData: OnStart? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onStart {
     *     event = on_digging_event
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onStart(value: OnStart.() -> Unit) {
        onStartData = (onStartData ?: OnStart()).apply(value)
    }

    class OnStart {
        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }
}
