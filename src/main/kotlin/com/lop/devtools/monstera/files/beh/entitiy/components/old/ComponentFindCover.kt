package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentFindCover {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var speedMultiplier: Float? = null
    var coolDownTime: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        coolDownTime?.let { general["cooldown_time"] = it }
        return general
    }
}