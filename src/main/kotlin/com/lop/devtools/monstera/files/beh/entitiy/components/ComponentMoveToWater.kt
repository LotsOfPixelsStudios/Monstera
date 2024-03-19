package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentMoveToWater {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var speedMultiplier: Float? = null
    var searchRange: Number? = null
    var searchHeight: Number? = null
    var searchCount: Int? = null
    var goalRadius: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        searchRange?.let { general["search_range"] = it }
        searchHeight?.let { general["search_height"] = it }
        searchCount?.let { general["search_count"] = it }
        goalRadius?.let { general["goal_radius"] = it }
        return general
    }
}