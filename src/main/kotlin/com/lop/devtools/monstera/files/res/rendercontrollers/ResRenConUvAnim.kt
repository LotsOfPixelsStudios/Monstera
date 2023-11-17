package com.lop.devtools.monstera.files.res.rendercontrollers

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.getMonsteraLogger

class ResRenConUvAnim : MonsteraFile {
    override val unsafe = Unsafe()
    private fun logger() = getMonsteraLogger("Uv Render")

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            return general
        }
    }

    private fun get2ElList(from: Any, to: Any): MutableList<Any> {
        val ls = mutableListOf<Any>()
        when (from) {
            is Molang -> ls.add(from.data)
            is Number -> ls.add(from)
            is String -> ls.add(from)

            else -> logger().warn(
                "type can't be parsed: invalid type '${from::class.java.name}'"
            )
        }
        when (to) {
            is Molang -> ls.add(to.data)
            is Number -> ls.add(to)
            is String -> ls.add(to)

            else -> logger().warn(
                "type can't be parsed: '${to::class.java.name}'"
            )
        }

        return ls
    }

    fun offset(from: Any, to: Any = from) {
        unsafe.general["offset"] = get2ElList(from, to)
    }

    fun scale(from: Any, to: Any = from) {
        unsafe.general["scale"] = get2ElList(from, to)
    }
}