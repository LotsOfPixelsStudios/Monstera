package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentDamageOverTime : MonsteraFile {

    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        override fun getData(): MutableMap<String, Any> {
            damagePerHurt?.let { general["damage_per_hurt"] = it }
            timeBetweenHurt?.let { general["time_between_hurt"] = it }
            return general
        }
    }

    var damagePerHurt: Int? = null

    var timeBetweenHurt: Float? = null
}