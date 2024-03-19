package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentGiveable {
    val general = mutableMapOf<String, Any>()

    var cooldown: Float? = null
    var items: ArrayList<String>? = null

    /**
     * 0..1
     *
     * Event to fire when the correct item is given.
     */
    fun onGive(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if(target != null)
            data["target"] = target.toString().lowercase()
    }

    fun getData(): MutableMap<String, Any> {
        cooldown?.let { general["cooldown"] = it }
        items?.let { general["items"] = it }
        return general
    }
}