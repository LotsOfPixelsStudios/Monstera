package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentPetSleepWithOwner {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var speedMultiplier: Float? = null
    var searchRadius: Number? = null
    var searchHeight: Number? = null
    var goalRadius: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        searchRadius?.let { general["search_radius"] = it }
        searchHeight?.let { general["search_height"] = it }
        goalRadius?.let { general["goal_radius"] = it }
        return general
    }
}