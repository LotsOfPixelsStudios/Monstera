package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentSwoopAttack {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var damageReach: Float? = null
    var speedMultiplier: Float? = null

    fun delayRange(lower: Float, upper: Float = lower) {
        general["delay_range"] = arrayListOf(lower, upper)
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        damageReach?.let { general["damage_reach"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        return general
    }
}