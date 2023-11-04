package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentBreed : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            priority?.let { general["priority"] = it }
            speedMultiplier?.let { general["speed_multiplier"] = it }
            return general
        }
    }

    var priority: Int? = null
    var speedMultiplier: Float? = null
}