package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentMaxTurn {
    val general = mutableMapOf<String, Any>()
    var maxTurn: Float? = null

    fun getData(): MutableMap<String, Any> {
        maxTurn?.let { general["max_turn"] = it }
        return general
    }
}