package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentInstantDespawn {
    val general = mutableMapOf<String, Any>()

    var removeChildEntities: Boolean? = null

    fun getData(): MutableMap<String, Any> {
        removeChildEntities?.let { general["remove_child_entities"] = it }
        return general
    }
}