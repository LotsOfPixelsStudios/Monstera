package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter
import com.lop.devtools.monstera.files.beh.entitiy.data.DamageType

class ComponentAreaAttack: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            damageRange?.let { general["damage_range"] = it }
            damagePerTick?.let { general["damage_per_tick"] = it }
            cause?.let { general["cause"] = it.toString().lowercase() }
            return general
        }
    }
    var damageRange: Float? = null
    var damagePerTick: Int? = null

    var cause: DamageType? = null

    fun entityFilter(value: BehEntityFilter.() -> Unit) {
        unsafe.general["entity_filter"] = BehEntityFilter().apply(value).getData()
    }
}