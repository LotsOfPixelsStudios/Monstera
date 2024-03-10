package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentFloatWander {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var xzDist: Number? = null
    var yDist: Number? = null
    var yOffset: Float? = null
    var randomReselect: Boolean? = null
    var mustReach: Boolean? = null

    /**
     * 0..1
     *
     * Range of time in seconds the mob will float around before landing and choosing to do something else
     */
    fun floatDuration(min: Float, max: Float) {
        general["float_duration"] = arrayListOf(min, max)
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        xzDist?.let { general["xz_dist"] = it }
        yDist?.let { general["y_dist"] = it }
        yOffset?.let { general["y_offset"] = it }
        randomReselect?.let { general["random_reselect"] = it }
        mustReach?.let { general["float_duration"] = it }

        return general
    }
}