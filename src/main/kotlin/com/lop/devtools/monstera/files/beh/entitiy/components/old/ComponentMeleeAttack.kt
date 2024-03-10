package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentMeleeAttack {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var attackOnce: Boolean? = null
    var speedMultiplier: Float? = null
    var trackTarget: Boolean? = null
    var randomStopInterval: Number? = null
    var reachMultiplier: Float? = null
    var requireCompletePath: Boolean? = null

    /**
     * 0..1
     *
     * @param event the event to trigger when killing an entity
     * @param target the subject where to execute the event
     */
    fun onKill(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if(target != null) {
            data["target"] = target.toString().lowercase()
        }
    }

    /**
     * 0..1
     *
     * @param event the event to trigger when entering this component
     * @param target the subject where to execute the event
     */
    fun onAttack(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if(target != null) {
            data["target"] = target.toString().lowercase()
        }
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        attackOnce?.let { general["attack_once"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        trackTarget?.let { general["track_target"] = it }
        randomStopInterval?.let { general["random_stop_interval"] = it }
        reachMultiplier?.let { general["reach_multiplier"] = it }
        requireCompletePath?.let { general["require_complete_path"] = it }
        return general
    }
}