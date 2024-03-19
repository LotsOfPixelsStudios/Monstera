package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentRandomHover {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var xzDist: Number? = null
    var yDist: Number? = null
    var yOffset: Float? = null
    var interval: Int? = null
    var speedMultiplier: Float? = null

    /**
     * 0..1
     */
    fun hoverHeight(min: Number, max: Number) {
        general["hover_height"] = arrayListOf(min, max)
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        xzDist?.let { general["xz_dist"] = it }
        yDist?.let { general["y_dist"] = it }
        yOffset?.let { general["y_offset"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        interval?.let { general["interval"] = it }
        return general
    }
}