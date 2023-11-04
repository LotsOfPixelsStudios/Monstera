package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentValue {
    val general = mutableMapOf<String, Any>()

    var value: Number? = null

    fun getData(): MutableMap<String, Any> {
        value?.let { general["value"] = it }
        return general
    }
}