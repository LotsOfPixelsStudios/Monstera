package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentPriority {
    val general = mutableMapOf<String, Any>()
    var priority: Int? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        return general
    }
}