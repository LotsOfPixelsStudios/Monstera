package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentItemControllable {
    val general = mutableMapOf<String, Any>()

    var controlItems: ArrayList<String>? = null

    fun getData(): MutableMap<String, Any> {
        controlItems?.let { general["control_items"] = it }
        return general
    }
}