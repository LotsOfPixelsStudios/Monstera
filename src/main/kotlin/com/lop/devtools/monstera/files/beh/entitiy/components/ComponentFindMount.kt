package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentFindMount {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var withInRadius: Float? = null
    var avoidWater: Boolean? = null
    var startDelay: Number? = null
    var targetNeeded: Boolean? = null
    var mountDistance: Float? = null
    var maxFailedAttempts: Int? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        withInRadius?.let { general["within_radius"] = it }
        avoidWater?.let { general["avoid_water"] = it }
        startDelay?.let { general["start_delay"] = it }
        targetNeeded?.let { general["target_needed"] = it }
        mountDistance?.let { general["mount_distance"] = it }
        maxFailedAttempts?.let { general["max_failed_attempts"] = it }
        return general
    }
}