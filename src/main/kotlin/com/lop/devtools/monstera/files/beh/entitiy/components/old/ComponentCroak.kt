package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class ComponentCroak: MonsteraFile {
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

    fun interval(start: Number, end: Number) {
        unsafe.general["interval"] = listOf(start, end)
    }

    var duration: Number = 0
        set(value) {
            unsafe.general["duration"] = value
            field = value
        }

    fun filters(data: BehEntityFilter.() -> Unit) {
        unsafe.general["duration"] = BehEntityFilter().apply(data)
    }
}