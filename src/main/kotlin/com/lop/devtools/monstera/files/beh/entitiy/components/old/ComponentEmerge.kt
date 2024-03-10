package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentEmerge: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        override fun getData() = general
    }

    var duration: Number = 0
        set(value) {
            unsafe.general["duration"] = value
            field = value
        }

    fun onDone(event: String, target: Subject = Subject.SELF) {
        unsafe.general["on_done"] = mapOf(
            "event" to event,
            "target" to target.toString().lowercase()
        )
    }
}