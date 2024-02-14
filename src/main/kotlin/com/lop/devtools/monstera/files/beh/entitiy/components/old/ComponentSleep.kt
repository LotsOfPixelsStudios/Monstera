package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentSleep {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var cooldownTime: Float? = null
    var goalRadius: Float? = null
    var speedMultiplier: Float? = null
    var sleepColliderHeight: Float? = null
    var sleepColliderWidth: Float? = null
    var sleepYOffset: Float? = null
    var timeoutCooldown: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        cooldownTime?.let { general["cooldown_time"] = it }
        goalRadius?.let { general["goal_radius"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        sleepColliderHeight?.let { general["sleep_collider_height"] = it }
        sleepColliderWidth?.let { general["sleep_collider_width"] = it }
        sleepYOffset?.let { general["sleep_y_offset"] = it }
        timeoutCooldown?.let { general["timeout_cooldown"] = it }
        return general
    }
}