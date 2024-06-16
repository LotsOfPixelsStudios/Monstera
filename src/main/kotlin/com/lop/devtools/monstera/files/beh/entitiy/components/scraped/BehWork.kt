package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class BehWork : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("active_time")
    @Expose
    var activeTime: Number? = null
        
    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null
        
    @SerializedName("goal_cooldown")
    @Expose
    var goalCooldown: Number? = null
        
    @SerializedName("sound_delay_min")
    @Expose
    var soundDelayMin: Number? = null
        
    @SerializedName("sound_delay_max")
    @Expose
    var soundDelayMax: Number? = null
        
    @SerializedName("can_work_in_rain")
    @Expose
    var canWorkInRain: Boolean? = null
        
    @SerializedName("work_in_rain_tolerance")
    @Expose
    var workInRainTolerance: Number? = null
        
    @SerializedName("on_arrival")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var onArrivalData: OnArrival? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onArrival {
     *     event = minecraft:resupply_trades
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onArrival(value: OnArrival.() -> Unit) {
        onArrivalData = (onArrivalData ?: OnArrival()).apply(value)
    }

    class OnArrival : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null
            
        @SerializedName("target")
        @Expose
        var target: Subject? = null
    }
}
