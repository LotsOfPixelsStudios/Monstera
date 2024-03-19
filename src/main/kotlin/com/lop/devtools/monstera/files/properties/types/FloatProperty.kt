package com.lop.devtools.monstera.files.properties.types

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.addon.molang.Molang

class FloatProperty: NumberProperty<Float>, MonsteraFile {
    override val unsafe = Unsafe()
    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            general["type"] = "float"

            if(range.first != 0f || range.second != 0f)
                general["range"] = listOf(range.first, range.second)

            if(clientSync)
                general["client_sync"] = true
            return general
        }
    }

    override var range: Pair<Float, Float> = 0f to 0f

    override fun default(value: Number) {
        unsafe.general["default"] = value
    }

    override fun default(value: Molang) {
        unsafe.general["default"] = value.data
    }

    override var clientSync: Boolean = false
}