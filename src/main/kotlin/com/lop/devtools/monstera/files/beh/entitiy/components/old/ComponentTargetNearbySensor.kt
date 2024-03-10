package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentTargetNearbySensor {
    val general = mutableMapOf<String, Any>()

    var insideRange: Float? = null
    var outSideRange: Float? = null
    var mustSee: Boolean? = null

    /**
     * 0..1
     *
     * Event to call when an entity gets in the inside range. Can specify 'event' for the name of the event and 'target' for the target of the event
     */
    fun onInsideRange(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if(target != null)
            data["target"] = target.toString().lowercase()
        general["on_inside_range"] = data
    }

    /**
     * 0..1
     *
     * Event to call when an entity gets in the outside range. Can specify 'event' for the name of the event and 'target' for the target of the event
     */
    fun onOutsideRange(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if(target != null)
            data["target"] = target.toString().lowercase()
        general["on_outside_range"] = data
    }

    /**
     * 0..1
     *
     * Event to call when an entity exits visual range. Can specify 'event' for the name of the event and 'target' for the target of the event
     */
    fun onVisionLostInsideRange(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if(target != null)
            data["target"] = target.toString().lowercase()
        general["on_vision_lost_inside_range"] = data
    }

    fun getData(): MutableMap<String, Any> {
        insideRange?.let { general["inside_range"] = it }
        outSideRange?.let { general["outside_range"] = it }
        mustSee?.let { general["must_see"] = it }
        return general
    }
}