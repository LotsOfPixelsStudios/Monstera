package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentMountPathing {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var speedMultiplier: Float? = null
    var targetDist: Float? = null
    var trackTarget: Boolean? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        targetDist?.let { general["target_dist"] = it }
        trackTarget?.let { general["track_target"] = it }
        return general
    }
}