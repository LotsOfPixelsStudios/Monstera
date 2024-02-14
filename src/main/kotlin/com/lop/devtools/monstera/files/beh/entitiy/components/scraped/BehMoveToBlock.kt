package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class BehMoveToBlock {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("tick_interval")
    @Expose
    var tickInterval: Number? = null
        

    @SerializedName("start_chance")
    @Expose
    var startChance: Number? = null
        

    @SerializedName("search_range")
    @Expose
    var searchRange: Number? = null
        

    @SerializedName("search_height")
    @Expose
    var searchHeight: Number? = null
        

    @SerializedName("goal_radius")
    @Expose
    var goalRadius: Number? = null
        

    @SerializedName("stay_duration")
    @Expose
    var stayDuration: Number? = null
        

    @SerializedName("target_selection_method")
    @Expose
    var targetSelectionMethod: String? = null
        

    @SerializedName("target_offset")
    @Expose
    var targetOffsetData: MutableList<Number>? = null

    fun targetOffset(vararg value: Number) {
        targetOffsetData = (targetOffsetData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("target_block_filters")
    @Expose
    var targetBlockFiltersData: BehEntityFilter? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * targetBlockFilters {
     *     test = is_waterlogged
     *     subject = block
     *     operator = ==
     *     value = false
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun targetBlockFilters(value: BehEntityFilter.() -> Unit) {
        targetBlockFiltersData = (targetBlockFiltersData ?: BehEntityFilter()).apply(value)
    }

    @SerializedName("target_blocks")
    @Expose
    var targetBlocksData: MutableList<String>? = null

    fun targetBlocks(vararg value: String) {
        targetBlocksData = (targetBlocksData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("on_stay_completed")
    @Expose
    var onStayCompletedData: MutableList<OnStayCompleted>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onStayCompleted {
     *     event = collected_nectar
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onStayCompleted(value: OnStayCompleted.() -> Unit) {
        onStayCompletedData = (onStayCompletedData ?: mutableListOf()).also { it.add(OnStayCompleted().apply(value)) }
    }

    @SerializedName("on_reach")
    @Expose
    var onReachData: MutableList<OnReach>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onReach {
     *     event = minecraft:bee_returned_to_hive
     *     target = block
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onReach(value: OnReach.() -> Unit) {
        onReachData = (onReachData ?: mutableListOf()).also { it.add(OnReach().apply(value)) }
    }

    class OnStayCompleted {
        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }

    class OnReach {
        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }
}
