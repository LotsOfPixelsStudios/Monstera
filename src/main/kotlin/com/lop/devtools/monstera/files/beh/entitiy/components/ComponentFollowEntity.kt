package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentFollowEntity {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var speedMultiplier: Float? = null
    var stopDistance: Float? = null
    var searchRange: Number? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        stopDistance?.let { general["stop_distance"] = it }
        searchRange?.let { general["search_range"] = it }
        return general
    }
}

class ComponentFollowOwner {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var speedMultiplier: Float? = null
    var stopDistance: Float? = null
    var startDistance: Number? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        stopDistance?.let { general["stop_distance"] = it }
        startDistance?.let { general["start_distance"] = it }
        return general
    }
}