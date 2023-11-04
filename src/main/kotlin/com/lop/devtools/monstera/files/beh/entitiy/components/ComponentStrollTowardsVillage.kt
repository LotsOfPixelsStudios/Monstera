package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentStrollTowardsVillage {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var speedMultiplier: Float? = null
    var goalRadius: Float? = null
    var coolDownTime: Float? = null
    var searchRange: Number? = null
    var startChance: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] }
        speedMultiplier?.let { general["speed_multiplier"] }
        goalRadius?.let { general["search_range"] }
        coolDownTime?.let { general["cooldown_time"] }
        searchRange?.let { general["goal_radius"] }
        startChance?.let { general["start_chance"] }
        return general
    }
}