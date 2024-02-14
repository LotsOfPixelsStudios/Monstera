package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentMovementJump {
    val general = mutableMapOf<String, Any>()

    var maxTurn: Float? = null

    fun jumpDelay(min: Float, max: Float = min) {
        general["jump_delay"] = arrayListOf(min, max)
    }

    fun getData(): MutableMap<String, Any> {
        maxTurn?.let { general["max_turn"] = it }
        return general
    }
}