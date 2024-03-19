package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentLeapAtTarget {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var targetDist: Float? = null
    var yd: Float? = null
    var mustBeOnGround: Boolean? = null
    var setPersistent: Boolean? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        targetDist?.let { general["target_dist"] = it }
        yd?.let { general["yd"] = it }
        mustBeOnGround?.let { general["must_be_on_ground"] = it }
        setPersistent?.let { general["set_persistent"] = it }
        return general
    }
}