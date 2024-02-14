package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentExploreOutskirts {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var nextXZ: Number? = null
    var nextY: Number? = null
    var minWaitTime: Float? = null
    var maxWaitTime: Float? = null
    var maxTravelTime: Float? = null
    var speedMultiplier: Float? = null
    var exploreDist: Float? = null
    var minPerimeter: Float? = null
    var minDistFromTarget: Float? = null
    var timerRatio: Float? = null

    /**
     * 0..1
     */
    fun distFromBoundary(x: Float, y: Float, z: Float) {
        general["dist_from_boundary"] = arrayListOf(x, y, z)
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        nextXZ?.let { general["next_xz"] = it }
        nextY?.let { general["next_y"] = it }
        minWaitTime?.let { general["min_wait_time"] = it }
        maxWaitTime?.let { general["max_wait_time"] = it }
        maxTravelTime?.let { general["max_travel_time"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        exploreDist?.let { general["explore_dist"] = it }
        minPerimeter?.let { general["min_perimeter"] = it }
        minDistFromTarget?.let { general["min_dist_from_target"] = it }
        timerRatio?.let { general["timer_ratio"] = it }
        return general
    }
}