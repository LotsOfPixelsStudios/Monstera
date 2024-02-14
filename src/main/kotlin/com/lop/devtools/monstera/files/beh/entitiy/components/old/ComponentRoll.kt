package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentRoll {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var probability: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        probability?.let { general["probability"] = it }
        return general
    }
}