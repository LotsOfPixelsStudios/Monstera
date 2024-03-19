package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentSwell {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var startDistance: Float? = null
    var stopDistance: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        startDistance?.let { general["start_distance"] = it }
        stopDistance?.let { general["stop_distance"] = it }
        return general
    }
}