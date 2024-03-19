package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentBreakBlocks : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        override fun getData(): MutableMap<String, Any> {
            breakableBlocks?.let { general["breakable_blocks"] = it }
            return general
        }
    }

    var breakableBlocks: ArrayList<String>? = null
        set(value) {
            if (value == null || field == null) {
                field = value
            } else {
                field?.addAll(value) ?: error("field changed to null while trying to write to it!")
            }
        }
}