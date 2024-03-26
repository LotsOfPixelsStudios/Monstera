package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class BehTimerFlag_1 {

    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("control_flags")
    @Expose
    var controlFlagsData: MutableList<String>? = null
        

    fun controlFlags(vararg value: String) {
        controlFlagsData = (controlFlagsData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("cooldown_range")
    @Expose
    var cooldownRangeData: MutableList<Number>? = null
        

    fun cooldownRange(vararg value: Number) {
        cooldownRangeData = (cooldownRangeData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("duration_range")
    @Expose
    var durationRange: Number? = null
        

    @SerializedName("on_end")
    @Expose
    var onEndData: OnEnd? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onEnd {
     *     event = on_scenting_success
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onEnd(value: OnEnd.() -> Unit) {
        onEndData = (onEndData ?: OnEnd()).apply(value)
    }

    class OnEnd {
        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }
}
