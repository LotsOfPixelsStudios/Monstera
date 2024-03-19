package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentAttackCooldown : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            attackCooldownTime?.let { general["attack_cooldown_time"] = it }
            return general
        }
    }

    var attackCooldownTime: Float? = null

    fun attackCooldownTime(lower: Float, higher: Float) {
        unsafe.general["attack_cooldown_time"] = arrayListOf(lower, higher)
    }

    fun onComplete(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if (target != null)
            data["target"] = target.toString().lowercase()
        unsafe.general["attack_cooldown_complete_event"] = data
    }
}