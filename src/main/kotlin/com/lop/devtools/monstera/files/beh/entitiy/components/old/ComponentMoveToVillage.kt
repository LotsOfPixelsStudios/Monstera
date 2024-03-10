package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentMoveToVillage {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var speedMultiplier: Float? = null
    var goalRadius: Float? = null
    var coolDownTime: Float? = null
    var searchRange: Number? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        goalRadius?.let { general["goal_radius"] = it }
        coolDownTime?.let { general["cooldown_time"] = it }
        searchRange?.let { general["search_range"] = it }
        return general
    }
}