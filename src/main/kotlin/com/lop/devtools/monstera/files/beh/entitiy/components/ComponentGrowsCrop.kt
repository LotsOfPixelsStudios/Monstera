package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentGrowsCrop {
    val general = mutableMapOf<String, Any>()

    var chance: Float? = null
    var charges: Int? = null

    fun getData(): MutableMap<String, Any> {
        chance?.let { general["chance"] = it }
        charges?.let { general["charges"] = it }
        return general
    }
}