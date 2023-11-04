package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentShooter {
    val general = mutableMapOf<String, Any>()

    var def: String? = null
    var type: String? = null
    var auxVal: Int? = null

    fun getData(): MutableMap<String, Any> {
        def?.let { general["def"] = it }
        type?.let { general["type"] = it }
        auxVal?.let { general["aux_val"] = it }
        return general
    }
}