package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentRandomSitting {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var startChance: Float? = null
    var stopChance: Float? = null
    var cooldownTime: Number? = null
    var minSitTime: Number? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        startChance?.let { general["start_chance"] = it }
        stopChance?.let { general["stop_chance"] = it }
        cooldownTime?.let { general["cooldown_time"] = it }
        minSitTime?.let { general["min_sit_time"] = it }
        return general
    }
}