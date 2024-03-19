package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter
import com.lop.devtools.monstera.files.beh.entitiy.data.DamageType

class ComponentHurtOnDamage {
    val general = arrayListOf<Any>()

    /**
     * 1..*
     */
    fun condition(value: HurtOnDamageCond.() -> Unit) {
        general.add(HurtOnDamageCond().apply(value).getData())
    }

    fun getData(): ArrayList<Any> {
        return general
    }
}

class HurtOnDamageCond {
    val general = mutableMapOf<String, Any>()

    var cause: DamageType? = null
    var damagePerTick: Int? = null

    fun filters(value: BehEntityFilter.() -> Unit) {
        general["filters"] = BehEntityFilter().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        cause?.let { general["cause"] = it.toString().lowercase() }
        damagePerTick?.let { general["damage_per_tick"] = it }
        return general
    }
}