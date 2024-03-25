package com.lop.devtools.monstera.files.animcontroller

import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.addon.molang.Variable

class VariableApi {
    val variables = mutableListOf<String>()

    fun set(name: String, value: String) {
        val variable = if (name.startsWith("variable.") || name.startsWith("temp.") || name.startsWith("v.")) {
            name
        } else {
            "variable.$name"
        }
        variables.add("$variable=$value;")
    }

    fun set(name: String, exp: Molang) {
        set(name, exp.toString())
    }

    fun set(value: Variable) {
        variables.add(value.data)
    }
}