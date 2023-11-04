package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.beh.entitiy.components.BehEntityTypes

class ComponentSwimWithEntity {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var successRate: Float? = null
    var chanceToStop: Float? = null
    var stateCheckInterval: Float? = null
    var catchUpThreshold: Float? = null
    var matchDirectionThreshold: Float? = null
    var catchUpMultiplier: Float? = null
    var speedMultiplier: Float? = null
    var searchRange: Float? = null
    var stopDistance: Float? = null

    fun entityTypes(value: BehEntityTypes.() -> Unit) {
        general["entity_types"] = BehEntityTypes().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        successRate?.let { general["success_rate"] = it }
        chanceToStop?.let { general["chance_to_stop"] = it }
        stateCheckInterval?.let { general["state_check_interval"] = it }
        catchUpThreshold?.let { general["catch_up_threshold"] = it }
        matchDirectionThreshold?.let { general["match_direction_threshold"] = it }
        catchUpMultiplier?.let { general["catch_up_multiplier"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        searchRange?.let { general["search_range"] = it }
        stopDistance?.let { general["stop_distance"] = it }
        return general
    }
}