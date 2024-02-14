package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentRandomLookAroundAndSit {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var minLookCount: Int? = null
    var maxLookCount: Int? = null
    var minLookTime: Int? = null
    var maxLookTime: Int? = null
    var probability: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        minLookCount?.let { general["min_look_count"] = it }
        maxLookCount?.let { general["max_look_count"] = it }
        minLookTime?.let { general["min_look_time"] = it }
        maxLookTime?.let { general["max_look_time"] = it }
        probability?.let { general["probability"] = it }
        return general
    }
}