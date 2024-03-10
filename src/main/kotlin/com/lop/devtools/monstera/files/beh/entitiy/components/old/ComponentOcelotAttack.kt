package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentOcelotAttack {
    val general = mutableMapOf<String, Number>()

    var priority: Int? = null
    var cooldownTime: Float? = null
    var xMaxRotation: Float? = null
    var yMaxRotation: Float? = null
    var maxDistance: Float? = null
    var maxSneakRange: Float? = null
    var maxSprintRange: Float? = null
    var reachMultiplier: Float? = null
    var sneakSpeedMultiplier: Float? = null
    var sprintSpeedMultiplier: Float? = null
    var walkSpeedMultiplier: Float? = null

    fun getData(): MutableMap<String, Number> {
        priority?.let { general["priority"] = it }
        cooldownTime?.let { general["cooldown_time"] = it }
        xMaxRotation?.let { general["x_max_rotation"] = it }
        yMaxRotation?.let { general["y_max_head_rotation"] = it }
        maxDistance?.let { general["max_distance"] = it }
        maxSneakRange?.let { general["max_sneak_range"] = it }
        maxSprintRange?.let { general["max_sprint_range"] = it }
        reachMultiplier?.let { general["reach_multiplier"] = it }
        sneakSpeedMultiplier?.let { general["sneak_speed_multiplier"] = it }
        sprintSpeedMultiplier?.let { general["sprint_speed_multiplier"] = it }
        walkSpeedMultiplier?.let { general["walk_speed_multiplier"] = it }
        return general
    }
}