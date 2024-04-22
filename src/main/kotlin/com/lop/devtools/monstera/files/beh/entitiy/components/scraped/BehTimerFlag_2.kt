package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class BehTimerFlag_2 : MonsteraRawFile() {
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
    var cooldownRange: Number? = null
        
    @SerializedName("duration_range")
    @Expose
    var durationRangeData: MutableList<Number>? = null
        

    fun durationRange(vararg value: Number) {
        durationRangeData = (durationRangeData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("on_end")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var onEndData: OnEnd? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onEnd {
     *     event = on_rising_end
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onEnd(value: OnEnd.() -> Unit) {
        onEndData = (onEndData ?: OnEnd()).apply(value)
    }

    class OnEnd : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null
            
        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }
}
