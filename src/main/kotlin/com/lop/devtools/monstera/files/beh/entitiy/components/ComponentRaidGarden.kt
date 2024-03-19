package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentRaidGarden {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var blocks: ArrayList<String>? = null
    var speedMultiplier: Float? = null
    var searchRange: Number? = null
    var searchHeight: Number? = null
    var goalRadius: Float? = null
    var maxToEat: Number? = null
    var initialEatDelay: Number? = null
    var fullDelay: Number? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        blocks?.let { general["blocks"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        searchRange?.let { general["search_range"] = it }
        searchHeight?.let { general["search_height"] = it }
        goalRadius?.let { general["goal_radius"] = it }
        maxToEat?.let { general["max_to_eat"] = it }
        initialEatDelay?.let { general["initial_eat_delay"] = it }
        fullDelay?.let { general["full_delay"] = it }
        return general
    }
}