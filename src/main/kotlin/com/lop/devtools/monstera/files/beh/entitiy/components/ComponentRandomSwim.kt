package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentRandomSwim {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var interval: Number? = null
    var xzDist: Int? = null
    var yDist: Int? = null
    var speedMultiplier: Float? = null
    var avoidSurface: Boolean? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        interval?.let { general["interval"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        avoidSurface?.let { general["avoid_surface"] = it }
        xzDist?.let { general["xz_dist"] = it }
        yDist?.let { general["y_dist"] = it }
        return general
    }
}