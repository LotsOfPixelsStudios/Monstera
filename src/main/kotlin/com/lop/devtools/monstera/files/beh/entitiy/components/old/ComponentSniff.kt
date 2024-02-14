package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentSniff: MonsteraFile {
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

    var duration: Number = 0
        set(value) {
            unsafe.general["duration"] = value
            field = value
        }

    var sniffingRadius: Number = 0
        set(value) {
            unsafe.general["sniffing_radius"] = value
            field = value
        }

    var suspicionRadiusHorizontal: Number = 0
        set(value) {
            unsafe.general["suspicion_radius_horizontal"] = value
            field = value
        }

    var suspicionRadiusVertical: Number = 0
        set(value) {
            unsafe.general["suspicion_radius_vertical"] = value
            field = value
        }

    fun cooldownRange(start: Number, end: Number) {
        unsafe.general["cooldown_range"] = listOf(start, end)
    }
}