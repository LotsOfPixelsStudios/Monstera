package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentBribeable : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        var general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            bribeCooldown?.let { general["bribe_cooldown"] = it }
            bribeItems?.let { general["bribe_items"] = it }
            return general
        }
    }

    var bribeCooldown: Float? = null
    var bribeItems: ArrayList<String>? = null
        set(value) {
            if (value == null || field == null) {
                field = value
            } else {
                field?.addAll(value) ?: error("field changed to null while trying to write to it!")
            }
        }
}