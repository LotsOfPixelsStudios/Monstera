package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class ComponentDespawn {
    val general = mutableMapOf<String, Any>()

    var despawnFromSimulationEdge: Boolean? = null
    var removeChildEntities: Boolean? = null

    fun despawnFromDistance(min: Number? = null, max: Number? = null) {
        val data = mutableMapOf<String, Any>()
        if (min != null)
            data["min_distance"] = min
        if (max != null)
            data["max_distance"] = max
        general["despawn_from_distance"] = data
    }

    fun despawnFromChance(value: Boolean = true, minRangeRandomChance: Int? = null) {
        general["despawn_from_chance"] = value
        if (minRangeRandomChance != null)
            general["min_range_random_chance"] = minRangeRandomChance
    }

    fun despawnFromInactivity(value: Boolean = true, minRangeInactivityTimer: Int? = null) {
        general["despawn_from_inactivity"] = value
        if (minRangeInactivityTimer != null)
            general["min_range_inactivity_timer"] = minRangeInactivityTimer
    }

    fun filters(filters: BehEntityFilter.() -> Unit) {
        general["filters"] = BehEntityFilter().apply(filters).getData()
    }

    fun getData(): MutableMap<String, Any> {
        despawnFromSimulationEdge?.let { general["despawn_from_simulation_edge"] = it }
        removeChildEntities?.let { general["remove_child_entities"] = it }
        return general
    }
}