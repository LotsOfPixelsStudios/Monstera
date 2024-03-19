package com.lop.devtools.monstera.files.properties.types

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.getMonsteraLogger
import org.slf4j.LoggerFactory

@Suppress("MemberVisibilityCanBePrivate")
class EnumProperty: GenericProperty<String>, MonsteraFile {
    private fun logger() = getMonsteraLogger("Enum Property")

    override val unsafe = Unsafe()
    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            if (values.size > 16)
                logger().warn("max entries for enum values is 16!")
            if(values.isEmpty())
                logger().warn("entries are empty, enum property will be ignored!")
            values.filter { it.length > 32 || it.isEmpty() }.forEach {
                logger().warn("entry '$it' is invalid, length must be between 1 and 32, length was ${it.length}!")
            }
            values.filter { !it.first().isLetter() }.forEach {
                logger().warn("entry '$it' is invalid, must start with a alphabetic character found '${it.first()}'!")
            }
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