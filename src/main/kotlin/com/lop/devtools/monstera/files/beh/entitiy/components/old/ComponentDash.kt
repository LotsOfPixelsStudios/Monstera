package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentDash : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        override fun getData() = general
    }

    var cooldownTime: Number = 0
        set(value) {
            unsafe.general["cooldown_time"] = value
            field = value
        }

    var horizontalMomentum: Number = 0
        set(value) {
            unsafe.general["horizontal_momentum"] = value
            field = value
        }

    var verticalMomentum: Number = 0
        set(value) {
            unsafe.general["vertical_momentum"] = value
            field = value
        }
}