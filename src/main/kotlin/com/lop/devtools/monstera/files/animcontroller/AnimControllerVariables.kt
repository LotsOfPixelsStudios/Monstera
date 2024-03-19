package com.lop.devtools.monstera.files.animcontroller

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.addon.molang.Variable

class AnimControllerVariables: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData() = general

        fun getAsList(): List<String> {
            return general.map { (k, v) -> "$k=$v;" }
        }
    }

    fun set(name: String, value: String) {
        val variable = if (name.startsWith("variable.") || name.startsWith("temp.") || name.startsWith("v.")) {
            name
        } else {
            "variable.$name"
        }
        unsafe.general[variable] = value
    }

    fun set(name: String, exp: Molang) {
        set(name, exp.toString())
    }

    fun set(value: Variable) {
        val (k, v) = value.data.dropLastWhile { it == ';' }.split("=")
        unsafe.general[k] = v
    }
}