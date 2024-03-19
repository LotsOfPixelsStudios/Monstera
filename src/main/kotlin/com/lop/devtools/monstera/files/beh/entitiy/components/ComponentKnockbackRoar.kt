package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentKnockbackRoar {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var duration: Float? = null
    var attackTime: Float? = null
    var knockbackDamage: Int? = null
    var knockbackStrength: Int? = null
    var knockbackRange: Number? = null
    var cooldownTime: Float? = null

    fun knockbackFilters(value: BehEntityFilter.() -> Unit) {
        general["knockback_filters"] = BehEntityFilter().apply(value).getData()
    }

    fun damageFilters(value: BehEntityFilter.() -> Unit) {
        general["damage_filters"] = BehEntityFilter().apply(value).getData()
    }

    fun onRoarEnd(event: String, target: Subject? = Subject.SELF) {
        val data = mutableMapOf("event" to event)
        if(target != null) {
            data["target"] = target.toString().lowercase()
        }
        general["on_roar_end"] = data
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        duration?.let { general["duration"] = it }
        attackTime?.let { general["attack_time"] = it }
        knockbackDamage?.let { general["knockback_damage"] = it }
        knockbackStrength?.let { general["knockback_strength"] = it }
        knockbackRange?.let { general["knockback_range"] = it }
        cooldownTime?.let { general["cooldown_time"] = it }
        return general
    }
}