package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentMovementSway {
    val general = mutableMapOf<String, Any>()

    var maxTurn: Float? = null
    var swayAmplitude: Float? = null

    fun getData(): MutableMap<String, Any> {
        maxTurn?.let { general["max_turn"] = it }
        swayAmplitude?.let { general["sway_amplitude"] = it }
        return general
    }
}