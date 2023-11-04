package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentMoveOutdoors: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        override fun getData() = general
    }

    var priority: Int = 0
        set(value) {
            unsafe.general["priority"] = value
            field = value
        }

    var speedMultiplier: Number = 0
        set(value) {
            unsafe.general["speed_multiplier"] = value
            field = value
        }

    var timeoutCooldown: Number = 0
        set(value) {
            unsafe.general["timeout_cooldown"] = value
            field = value
        }

}