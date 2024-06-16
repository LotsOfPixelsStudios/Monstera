package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehCircleAroundAnchor : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("radius_change")
    @Expose
    var radiusChange: Number? = null
        
    @SerializedName("radius_adjustment_chance")
    @Expose
    var radiusAdjustmentChance: Number? = null
        
    @SerializedName("height_adjustment_chance")
    @Expose
    var heightAdjustmentChance: Number? = null
        
    @SerializedName("goal_radius")
    @Expose
    var goalRadius: Number? = null
        
    @SerializedName("angle_change")
    @Expose
    var angleChange: Number? = null
        
    @SerializedName("radius_range")
    @Expose
    var radiusRangeData: MutableList<Number>? = null

    fun radiusRange(vararg value: Number) {
        radiusRangeData = (radiusRangeData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("height_offset_range")
    @Expose
    var heightOffsetRangeData: MutableList<Number>? = null

    fun heightOffsetRange(vararg value: Number) {
        heightOffsetRangeData = (heightOffsetRangeData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("height_above_target_range")
    @Expose
    var heightAboveTargetRangeData: MutableList<Number>? = null

    fun heightAboveTargetRange(vararg value: Number) {
        heightAboveTargetRangeData = (heightAboveTargetRangeData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }
}