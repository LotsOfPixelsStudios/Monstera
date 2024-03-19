package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentLookAtSpecEntity {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var lookDistance: Float? = null
    var lookTime: Float? = null
    var angleOfViewHorizontal: Number? = null
    var angleOfViewVertical: Number? = null
    var targetDistance: Float? = null
    var probability: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        lookDistance?.let { general["look_distance"] = it }
        lookTime?.let { general["look_time"] = it }
        angleOfViewHorizontal?.let { general["angle_of_view_horizontal"] = it }
        angleOfViewVertical?.let { general["angle_of_view_vertical"] = it }
        targetDistance?.let { general["target_distance"] = it }
        probability?.let { general["probability"] = it }
        return general
    }
}