package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentHome {
    val general = mutableMapOf<String, Any>()

    var restrictionRadius: Number? = null
    var homeBlockList: ArrayList<String>? = null

    fun getData(): MutableMap<String, Any> {
        restrictionRadius?.let { general["restriction_radius"] = it }
        homeBlockList?.let { general["home_block_list"] = it }
        return general
    }
}