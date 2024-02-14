package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentRailMovement {
    val general = mutableMapOf<String, Any>()

    var maxSpeed: Float? = null

    fun getData(): MutableMap<String, Any> {
        maxSpeed?.let { general["max_speed"] = it }
        return general
    }
}