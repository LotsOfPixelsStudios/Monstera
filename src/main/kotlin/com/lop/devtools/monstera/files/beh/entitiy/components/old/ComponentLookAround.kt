package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentLookAround {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null

    /**
     * 0..1
     *
     * The range of time in seconds the mob will stay looking in a random direction before looking elsewhere
     */
    fun lookTime(lower: Number, higher: Number) {
        general["look_time"] = arrayListOf(lower, higher)
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        return general
    }
}