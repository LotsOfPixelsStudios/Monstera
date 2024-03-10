package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentMoveTowardsTarget {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var speedMultiplier: Float? = null
    var withinRadius: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        withinRadius?.let { general["within_radius"] = it }
        return general
    }
}