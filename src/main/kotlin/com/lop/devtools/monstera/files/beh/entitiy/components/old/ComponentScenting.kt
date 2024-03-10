package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentScenting: MonsteraFile {
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

    fun cooldownRange(start: Number, end: Number) {
        unsafe.general["cooldown_range"] = listOf(start, end)
    }

    var durationRange: Number = 0
        set(value) {
            unsafe.general["duration_range"] = value
            field = value
        }

    fun onEnd(event: String, target: Subject = Subject.SELF) {
        unsafe.general["on_end"] = mapOf(
            "event" to event,
            "target" to target
        )
    }
}