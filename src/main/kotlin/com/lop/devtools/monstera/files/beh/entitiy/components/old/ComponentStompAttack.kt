package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentStompAttack {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var trackTarget: Boolean? = null
    var requireCompletePath: Boolean? = null
    var stompRangeMultiplier: Float? = null
    var noDamageRangeMultiplier: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        trackTarget?.let { general["track_target"] = it }
        requireCompletePath?.let { general["require_complete_path"] = it }
        stompRangeMultiplier?.let { general["stomp_range_multiplier"] = it }
        noDamageRangeMultiplier?.let { general["no_damage_range_multiplier"] = it }
        return general
    }
}