package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentSlimeRandomRotation {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var addRandomTimeRange: Int? = null
    var turnRange: Int? = null
    var minChangeDirectionTime: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        addRandomTimeRange?.let { general["add_random_time_range"] = it }
        turnRange?.let { general["turn_range"] = it }
        minChangeDirectionTime?.let { general["min_change_direction_time"] = it }
        return general
    }
}