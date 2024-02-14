package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentDelayedAttack {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var attackOnce: Boolean? = null
    var trackTarget: Boolean? = null
    var requireCompletePath: Boolean? = null
    var randomStopInterval: Number? = null
    var reachMultiplier: Float? = null
    var speedMultiplier: Float? = null
    var attackDuration: Float? = null
    var hitDelayPct: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        attackOnce?.let { general["attack_once"] = it }
        trackTarget?.let { general["track_target"] = it }
        requireCompletePath?.let { general["require_complete_path"] = it }
        randomStopInterval?.let { general["random_stop_interval"] = it }
        reachMultiplier?.let { general["reach_multiplier"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        attackDuration?.let { general["attack_duration"] = it }
        hitDelayPct?.let { general["hit_delay_pct"] = it }

        return general
    }
}