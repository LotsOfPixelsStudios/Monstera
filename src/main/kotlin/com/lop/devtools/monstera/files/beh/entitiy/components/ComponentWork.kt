package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentWork {
    val general = mutableMapOf<String, Any>()

    var activeTime: Int? = null
    var canWorkInRain: Boolean? = null
    var goalCooldown: Int? = null
    var soundDelayMax: Int? = null
    var soundDelayMin: Int? = null
    var speedMultiplier: Float? = null
    var workInRainTolerance: Int? = null
    var priority: Int? = null

    fun onArrival(event: String, target: Subject = Subject.SELF) {
        general["on_arrival"] = mutableMapOf(
            "event" to event,
            "target" to target.toString().lowercase()
        )
    }

    fun getData(): MutableMap<String, Any> {
        activeTime?.let { general["active_time"] = it }
        canWorkInRain?.let { general["can_work_in_rain"] = it }
        goalCooldown?.let { general["goal_cooldown"] = it }
        soundDelayMax?.let { general["sound_delay_max"] = it }
        soundDelayMin?.let { general["sound_delay_min"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        workInRainTolerance?.let { general["work_in_rain_tolerance"] = it }
        priority?.let { general["priority"] = it }
        return general
    }
}