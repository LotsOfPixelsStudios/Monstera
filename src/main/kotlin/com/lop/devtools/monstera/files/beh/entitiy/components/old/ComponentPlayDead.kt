package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter
import com.lop.devtools.monstera.files.beh.entitiy.data.DamageType

class ComponentPlayDead {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var duration: Number? = null
    var forceBelowHealth: Int? = null
    var randomStartChance: Float? = null
    var damageSources: ArrayList<DamageType>? = null
    var applyRegeneration: Boolean? = null

    fun randomDamageRange(lower: Int, upper: Int) {
        general["random_damage_range"] = arrayListOf(lower, upper)
    }

    fun filters(value: BehEntityFilter.() -> Unit) {
        general["filters"] = BehEntityFilter().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        duration?.let { general["duration"] = it }
        forceBelowHealth?.let { general["force_below_health"] = it }
        randomStartChance?.let { general["random_start_chance"] = it }
        damageSources?.let { general["damage_sources"] = it }
        applyRegeneration?.let { general["apply_regeneration"] = it }
        return general
    }
}