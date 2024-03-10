package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter
import com.lop.devtools.monstera.files.beh.entitiy.data.DamageType
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentDamageSensors {
    val general = arrayListOf<Any>()

    fun trigger(value: ComponentDamageSensor.() -> Unit) {
        general.add(ComponentDamageSensor().apply(value).unsafe.getData())
    }

    fun getData(): ArrayList<Any> {
        return general
    }
}

class ComponentDamageSensor: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        override fun getData(): MutableMap<String, Any> {
            cause?.let { general["cause"] = it.toString().lowercase() }
            dealsDamage?.let { general["deals_damage"] = it }
            damageMultiplier?.let { general["damage_multiplier"] = it }
            onDamageSoundEvent?.let { general["on_damage_sound_event"] = it }
            return general
        }
    }
    var cause: DamageType? = null
    var dealsDamage: Boolean? = null
    var damageMultiplier: Float? = null

    var onDamageSoundEvent: String? = null

    fun onDamage(event: String, filter: BehEntityFilter.() -> Unit) {
        val data = mutableMapOf<String, Any>("event" to event)
        data["filters"] = BehEntityFilter().apply(filter).getData()
        unsafe.general["on_damage"] = data
    }

    fun onDamage(data: OnDamageComp.() -> Unit) {
        unsafe.general["on_damage"] = OnDamageComp().apply(data).unsafe.getData()
    }
}

class OnDamageComp: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        override fun getData(): MutableMap<String, Any> {
            event?.let { general["event"] = it }
            target?.let { general["target"] = it.toString().lowercase() }
            return general
        }
    }

    var event: String? = null

    var target: Subject? = null

    fun filters(filter: BehEntityFilter.() -> Unit) {
        unsafe.general["filters"] = BehEntityFilter().apply(filter).getData()
    }
}