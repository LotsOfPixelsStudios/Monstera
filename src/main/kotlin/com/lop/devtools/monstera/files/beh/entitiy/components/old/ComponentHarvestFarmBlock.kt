package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentHarvestFarmBlock {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var maxSecondsBeforeSearch: Float? = null
    var searchCooldownMaxSeconds: Float? = null
    var secondsUntilNewTask: Float? = null
    var speedMultiplier: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        maxSecondsBeforeSearch?.let { general["max_seconds_before_search"] = it }
        searchCooldownMaxSeconds?.let { general["search_cooldown_max_seconds"] = it }
        secondsUntilNewTask?.let { general["seconds_until_new_task"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        return general
    }
}