package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentStayNearNoteblock : MonsteraFile {
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

    var speed: Number = 0
        set(value) {
            unsafe.general["speed"] = value
            field = value
        }

    var startDistance: Number = 0
        set(value) {
            unsafe.general["start_distance"] = value
            field = value
        }

    var stopDistance: Number = 0
        set(value) {
            unsafe.general["stop_distance"] = value
            field = value
        }
}