package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class BehExploreOutskirts {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("next_xz")
    @Expose
    var nextXz: Number? = null
        

    @SerializedName("next_y")
    @Expose
    var nextY: Number? = null
        

    @SerializedName("min_wait_time")
    @Expose
    var minWaitTime: Number? = null
        

    @SerializedName("max_wait_time")
    @Expose
    var maxWaitTime: Number? = null
        

    @SerializedName("max_travel_time")
    @Expose
    var maxTravelTime: Number? = null
        

    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null
        

    @SerializedName("explore_dist")
    @Expose
    var exploreDist: Number? = null
        

    @SerializedName("min_perimeter")
    @Expose
    var minPerimeter: Number? = null
        

    @SerializedName("min_dist_from_target")
    @Expose
    var minDistFromTarget: Number? = null
        

    @SerializedName("timer_ratio")
    @Expose
    var timerRatio: Number? = null
        

    @SerializedName("dist_from_boundary")
    @Expose
    var distFromBoundaryData: MutableList<Number>? = null

    fun distFromBoundary(vararg value: Number) {
        distFromBoundaryData = (distFromBoundaryData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }
}