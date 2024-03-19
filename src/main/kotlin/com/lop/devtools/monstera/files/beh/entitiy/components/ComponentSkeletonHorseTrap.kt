package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentSkeletonHorseTrap {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var withinRadius: Float? = null
    var duration: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        withinRadius?.let { general["within_radius"] = it }
        duration?.let { general["duration"] = it }
        return general
    }
}