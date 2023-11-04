package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentHorseJumpStrength {
    val general = mutableMapOf<String, Any>()

    var value: Float? = null

    fun value(min: Float, max: Float) {
        general["value"] = mutableMapOf(
            "range_min" to min,
            "range_max" to max
        )
    }

    fun getData(): MutableMap<String, Any> {
        value?.let { general["value"] = it }
        return general
    }
}