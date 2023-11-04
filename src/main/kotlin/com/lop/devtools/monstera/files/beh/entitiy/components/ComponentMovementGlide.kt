package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentMovementGlide {
    val general = mutableMapOf<String, Any>()

    var startSpeed: Float? = null
    var speedWhenTurning: Float? = null
    var maxTurn: Float? = null

    fun getData(): MutableMap<String, Any> {
        startSpeed?.let { general["start_speed"] = it }
        speedWhenTurning?.let { general["speed_when_turning"] = it }
        maxTurn?.let { general["max_turn"] = it }
        return general
    }
}