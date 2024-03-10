package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentControlledByPlayer : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            priority?.let { general["priority"] = it }
            mountSpeedMultiplier?.let { general["mount_speed_multiplier"] = it }

            return general
        }
    }

    var priority: Int? = null
    var mountSpeedMultiplier: Float? = null
}