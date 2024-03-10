package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentSlimeFloat {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var jumpChancePercentage: Float? = null
    var speedMultiplier: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        jumpChancePercentage?.let { general["jump_chance_percentage"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        return general
    }
}