package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentHealth {
    val general = mutableMapOf<String, Any>()

    var value: Int? = null
    var min: Int? = null
    var max: Int? = null

    fun getData(): MutableMap<String, Any> {
        value?.let { general["value"] = it }
        min?.let { general["min"] = it }
        max?.let { general["max"] = it }
        return general
    }
}