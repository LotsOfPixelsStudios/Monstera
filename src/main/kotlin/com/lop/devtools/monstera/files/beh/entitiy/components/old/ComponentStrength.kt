package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentStrength {
    val general = mutableMapOf<String, Any>()

    var value: Int? = null
    var max: Int? = null

    fun getData(): MutableMap<String, Any> {
        value?.let { general["value"] = it }
        max?.let { general["max"] = it }
        return general
    }
}