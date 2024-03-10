package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentRandomStroll {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var interval: Number? = null
    var speedMultiplier: Float? = null
    var xzDist: Number? = null
    var yDist: Number? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        interval?.let { general["interval"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        xzDist?.let { general["xz_dist"] = it }
        yDist?.let { general["y_dist"] = it }
        return general
    }
}