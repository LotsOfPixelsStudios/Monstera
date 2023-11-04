package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentRandomBreach {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var yDist: Number? = null
    var xzDist: Number? = null
    var interval: Int? = null
    var speedMultiplier: Float? = null
    var coolDownTime: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        yDist?.let { general["y_dist"] = it }
        xzDist?.let { general["xz_dist"] = it }
        interval?.let { general["interval"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        coolDownTime?.let { general["cooldown_time"] = it }
        return general
    }
}