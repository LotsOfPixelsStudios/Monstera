package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentHoldGround {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var minRadius: Float? = null
    var broadcast: Boolean? = null
    var broadcastRange: Float? = null

    fun withinRadiusEvent(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if(target != null)
            data["target"] = target.toString().lowercase()
        general["within_radius_event"] = data
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        minRadius?.let { general["min_radius"] = it }
        broadcast?.let { general["broadcast"] = it }
        broadcastRange?.let { general["broadcast_range"] = it }
        return general
    }
}