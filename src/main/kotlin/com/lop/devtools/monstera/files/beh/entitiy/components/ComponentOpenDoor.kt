package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentOpenDoor {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var closeDoorAfter: Boolean? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        closeDoorAfter?.let { general["close_door_after"] = it }
        return general
    }
}