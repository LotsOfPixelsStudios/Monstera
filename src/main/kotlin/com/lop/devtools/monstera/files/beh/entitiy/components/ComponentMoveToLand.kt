package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentMoveToLand {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var goalRadius: Float? = null
    var searchCount: Int? = null
    var searchHeight: Number? = null
    var searchRange: Number? = null
    var speedMultiplier: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        goalRadius?.let { general["goal_radius"] = it }
        searchCount?.let { general["search_count"] = it }
        searchHeight?.let { general["search_height"] = it }
        searchRange?.let { general["search_range"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        return general
    }
}