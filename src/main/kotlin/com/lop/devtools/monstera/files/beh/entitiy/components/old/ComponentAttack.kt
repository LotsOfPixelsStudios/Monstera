package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentAttack : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            damageRange?.let { general["damage"] = it }
            damage?.let { general["damage"] = it }
            effectDuration?.let { general["effect_duration"] = it }
            effectName?.let { general["effect_name"] = it }
            return general
        }
    }

    var damageRange: ArrayList<Int>? = null
    var damage: Int? = null
    var effectDuration: Float? = null

    var effectName: String? = null
}