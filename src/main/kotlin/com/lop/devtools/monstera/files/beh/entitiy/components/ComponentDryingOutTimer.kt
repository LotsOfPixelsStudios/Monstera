package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentDryingOutTimer {
    val general = mutableMapOf<String, Any>()

    var totalTime: Number? = null
    var waterBottleRefillTime: Number? = null

    /**
     * 0..1
     *
     * @param event the event that gets called
     * @param target no reference in documentation but makes sense
     */
    fun driedOutEvent(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if(target != null)
            data["target"] = target.toString().lowercase()
        general["dried_out_event"] = data
    }

    /**
     * 0..1
     *
     * @param event the event that gets called
     * @param target no reference in documentation but makes sense
     */
    fun stoppedDryingOutEvent(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if(target != null)
            data["target"] = target.toString().lowercase()
        general["stopped_drying_out_event"] = data
    }

    /**
     * 0..1
     *
     * @param event the event that gets called
     * @param target no reference in documentation but makes sense
     */
    fun recoverAfterDriedOutEvent(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if(target != null)
            data["target"] = target.toString().lowercase()
        general["recover_after_dried_out_event"] = data
    }

    fun getData(): MutableMap<String, Any> {
        totalTime?.let { general["total_time"] = it }
        waterBottleRefillTime?.let { general["water_bottle_refill_time"] = it }
        return general
    }
}