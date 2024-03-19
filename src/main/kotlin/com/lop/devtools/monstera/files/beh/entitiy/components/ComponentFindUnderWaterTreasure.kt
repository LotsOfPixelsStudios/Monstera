package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentFindUnderWaterTreasure {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var speedMultiplier: Float? = null
    var searchRange: Number? = null
    var stopDistance: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        searchRange?.let { general["search_range"] = it }
        stopDistance?.let { general["stop_distance"] = it }
        return general
    }
}