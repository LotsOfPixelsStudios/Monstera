package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentTargetWhenPushed {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var percentChance: Float? = null

    fun entityTypes(value: BehEntityTypes.() -> Unit) {
        general["entity_types"] = value
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        percentChance?.let { general["percent_chance"] = it }
        return general
    }
}