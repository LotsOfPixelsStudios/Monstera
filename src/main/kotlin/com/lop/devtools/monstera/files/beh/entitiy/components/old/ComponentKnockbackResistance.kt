package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentKnockbackResistance {
    val general = mutableMapOf<String, Any>()

    var value: Float? = null

    fun valOfMax(value: Int, max: Int) {
        general["value"] = value
        general["max"] = max
    }

    fun getData(): MutableMap<String, Any> {
        value?.let { general["value"] = it }
        return general
    }
}