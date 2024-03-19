package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentEatCarriedItem {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var delayBeforeEating: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        delayBeforeEating?.let { general["delay_before_eating"] = it }
        return general
    }
}