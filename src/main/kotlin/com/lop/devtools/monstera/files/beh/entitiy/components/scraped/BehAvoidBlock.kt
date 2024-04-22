package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class BehAvoidBlock : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("tick_interval")
    @Expose
    var tickInterval: Number? = null
        
    @SerializedName("search_range")
    @Expose
    var searchRange: Number? = null
        
    @SerializedName("search_height")
    @Expose
    var searchHeight: Number? = null
        
    @SerializedName("walk_speed_modifier")
    @Expose
    var walkSpeedModifier: Number? = null
        
    @SerializedName("sprint_speed_modifier")
    @Expose
    var sprintSpeedModifier: Number? = null
        
    @SerializedName("avoid_block_sound")
    @Expose
    var avoidBlockSound: String? = null
        
    @SerializedName("sound_interval")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
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

    @SerializedName("target_selection_method")
    @Expose
    var targetSelectionMethod: String? = null
        

    @SerializedName("target_blocks")
    @Expose
    var targetBlocksData: MutableList<String>? = null
        

    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun targetBlocks(vararg value: String) {
        targetBlocksData = (targetBlocksData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("on_escape")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var onEscapeData: MutableList<OnEscape>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onEscape {
     *     event = escaped_event
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onEscape(value: OnEscape.() -> Unit) {
        onEscapeData = (onEscapeData ?: mutableListOf()).also { it.add(OnEscape().apply(value)) }
    }

    class SoundInterval : MonsteraRawFile() {
        @SerializedName("range_min")
        @Expose
        var rangeMin: Number? = null

        @SerializedName("range_max")
        @Expose
        var rangeMax: Number? = null
    }

    class OnEscape : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null
            
        @SerializedName("target")
        @Expose
        var target: Subject? = null
    }
}
