package com.lop.devtools.monstera.files.properties.types

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.addon.molang.Molang

class IntProperty: NumberProperty<Int>, MonsteraFile {
    override val unsafe = Unsafe()
    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            general["type"] = "int"

            if(range.first != 0 || range.second != 0)
                general["range"] = listOf(range.first, range.second)

            if(clientSync)
                general["client_sync"] = true
            return general
        }
    }

    override var range: Pair<Int, Int> = 0 to 0

    override fun default(value: Number) {
        unsafe.general["default"] = value
    }

    override fun default(value: Molang) {
        unsafe.general["default"] = value.data
    }

    override var clientSync: Boolean = false
}