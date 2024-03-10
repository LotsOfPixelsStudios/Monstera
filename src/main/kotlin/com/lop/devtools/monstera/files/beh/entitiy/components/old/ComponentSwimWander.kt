package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentSwimWander {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var interval: Int? = null
    var lookAhead: Float? = null
    var speedMultiplier: Float? = null
    var wanderTime: Float? = null

    fun getData(): MutableMap<String, Any> {
        return general
    }
}