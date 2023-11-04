package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.beh.entitiy.data.DamageType

class ComponentPanic {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var speedMultiplier: Float? = null
    var preferWater: Boolean? = null
    var ignoreMobDamage: Boolean? = null
    var force: Boolean? = null
    var damageSources: ArrayList<DamageType>? = null
    var panicSound: String? = null

    fun soundInterval(rangeMin: Float, rangeMax: Float) {
        general["sound_interval"] = mutableMapOf("range_min" to rangeMin, "range_max" to rangeMax)
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        speedMultiplier?.let { general["priority"] = it }
        preferWater?.let { general["priority"] = it }
        ignoreMobDamage?.let { general["priority"] = it }
        force?.let { general["priority"] = it }
        damageSources?.let { list ->
            val data = arrayListOf<String>()
            list.forEach { data.add(it.toString().lowercase()) }
            general["damage_sources"] = data
        }
        panicSound?.let { general["priority"] = it }
        return general
    }
}