package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentWitherTargetHighestDamage {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null

    fun entityTypes(entityTypes: BehEntityTypes.() -> Unit) {
        general["entity_types"] = BehEntityTypes().apply(entityTypes).getData()
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        return general
    }
}