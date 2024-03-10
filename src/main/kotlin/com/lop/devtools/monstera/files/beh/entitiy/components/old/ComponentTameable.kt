package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentTameable {
    val general = mutableMapOf<String, Any>()

    var probability: Float? = null
    var tameItems: ArrayList<String>? = null

    /**
     * 0..1
     *
     * Event to run when this entity becomes tamed
     */
    fun tameEvent(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if(target != null)
            data["target"] = target.toString().lowercase()
        general["tame_event"] = data
    }

    fun getData(): MutableMap<String, Any> {
        probability?.let { general["probability"] = it }
        tameItems?.let { general["tame_items"] = it }
        return general
    }
}