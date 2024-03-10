package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentShareItems {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var maxDist: Number? = null
    var speedMultiplier: Float? = null
    var goalRadius: Float? = null

    fun entityTypes(value: BehEntityTypes.() -> Unit) {
        general["entity_types"] = BehEntityTypes().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        maxDist?.let { general["max_dist"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        goalRadius?.let { general["goal_radius"] = it }
        return general
    }
}