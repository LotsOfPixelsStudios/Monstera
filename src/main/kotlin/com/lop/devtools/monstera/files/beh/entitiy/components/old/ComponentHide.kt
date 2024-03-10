package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentHide {
    val general = mutableMapOf<String, Any>()

    var duration: Float? = null
    var poiType: String? = null
    var speedMultiplier: Float? = null
    var timeoutCooldown: Float? = null
    var priority: Int? = null

    fun getData(): MutableMap<String, Any> {
        duration?.let { general["duration"] = it }
        poiType?.let { general["poi_type"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        timeoutCooldown?.let { general["timeout_cooldown"] = it }
        priority?.let { general["priority"] = it }
        return general
    }
}