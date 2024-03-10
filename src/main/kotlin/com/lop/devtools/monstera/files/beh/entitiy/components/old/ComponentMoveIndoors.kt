package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentMoveIndoors {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var speedMultiplier: Float? = null
    var timeoutCooldown: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        timeoutCooldown?.let { general["timeout_cooldown"] = it }
        return general
    }
}