package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentTrusting {
    val general = mutableMapOf<String, Any>()

    var probability: Float? = null
    var trustItems: ArrayList<String>? = null

    /**
     * 0..1
     *
     * trustEvent Event to run when this entity becomes trusting
     */
    fun trustEvent(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if(target != null)
            data["target"] = target.toString().lowercase()
        general["trust_event"] = data
    }

    fun getData(): MutableMap<String, Any> {
        probability?.let { general["probability"] = it }
        trustItems?.let { general["trust_items"] = it }
        return general
    }
}