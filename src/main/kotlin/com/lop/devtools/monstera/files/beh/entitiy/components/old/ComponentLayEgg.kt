package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentLayEgg {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var speedMultiplier: Float? = null
    var searchRange: Number? = null
    var searchHeight: Number? = null
    var goalRadius: Float? = null

    /**
     * 0..1
     *
     * Event to run when this mob lays the egg.
     */
    fun onLay(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if(target != null)
            data["target"] = target.toString().lowercase()
        general["on_lay"] = data
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        searchHeight?.let { general["search_height"] = it }
        searchRange?.let { general["search_range"] = it }
        goalRadius?.let { general["goal_radius"] = it }
        return general
    }
}