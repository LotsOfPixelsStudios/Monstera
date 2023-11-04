package com.lop.devtools.monstera.files.properties.types

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.addon.molang.Molang

@Suppress("MemberVisibilityCanBePrivate")
class EnumProperty: GenericProperty<String>, MonsteraFile {
    override val unsafe = Unsafe()
    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            general["type"] = "enum"
            general["values"] = values
            if(clientSync)
                general["client_sync"] = true
            return general
        }
    }

    override fun default(value: String) {
        unsafe.general["default"] = value
    }

    override fun default(value: Molang) {
        unsafe.general["default"] = value.data
    }

    override var clientSync: Boolean = false
    var values: MutableList<String> = arrayListOf()
}