package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentOwnerHurtByTarget {
    val general = mutableMapOf<String, Any>()
    var priority: Int? = null

    fun entityTypes(value: BehEntityTypes.() -> Unit) {
        general["entity_types"] = BehEntityTypes().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        return general
    }
}