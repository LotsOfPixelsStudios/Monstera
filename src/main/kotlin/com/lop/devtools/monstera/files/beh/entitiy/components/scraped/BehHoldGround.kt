package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class BehHoldGround : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("min_radius")
    @Expose
    var minRadius: Number? = null
        
    @SerializedName("broadcast")
    @Expose
    var broadcast: Boolean? = null
        
    @SerializedName("broadcast_range")
    @Expose
    var broadcastRange: Number? = null
        
    @SerializedName("within_radius_event")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var withinRadiusEventData: WithinRadiusEvent? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * withinRadiusEvent {
     *     event = minecraft:ranged_mode
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun withinRadiusEvent(value: WithinRadiusEvent.() -> Unit) {
        withinRadiusEventData = (withinRadiusEventData ?: WithinRadiusEvent()).apply(value)
    }

    class WithinRadiusEvent : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null
            
        @SerializedName("target")
        @Expose
        var target: Subject? = null
    }
}
