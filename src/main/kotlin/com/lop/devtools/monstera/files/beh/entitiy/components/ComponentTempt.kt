package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentTempt {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var canGetScared: Boolean? = null
    var items: ArrayList<String>? = null
    var speedMultiplier: Float? = null
    var withinRadius: Float? = null
    var canTemptVertically: Boolean? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        canGetScared?.let { general["can_get_scared"] = it }
        items?.let { general["items"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        withinRadius?.let { general["within_radius"] = it }
        canTemptVertically?.let { general["can_tempt_vertically"] = it }
        return general
    }
}