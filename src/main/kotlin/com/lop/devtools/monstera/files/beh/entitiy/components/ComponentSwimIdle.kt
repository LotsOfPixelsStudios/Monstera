package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentSwimIdle {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var idleTime: Float? = null
    var successRate: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        idleTime?.let { general["idle_time"] = it }
        successRate?.let { general["success_rate"] = it }
        return general
    }
}