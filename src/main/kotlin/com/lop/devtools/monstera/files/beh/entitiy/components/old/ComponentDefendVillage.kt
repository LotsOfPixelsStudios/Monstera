package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentDefendVillage {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var mustReach: Boolean? = null
    var attackChance: Float? = null

    fun entityTypes(value: BehEntityTypes.() -> Unit) {
        general["entity_types"] = BehEntityTypes().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        mustReach?.let { general["must_reach"] = it }
        attackChance?.let { general["attack_chance"] = it }

        return general
    }
}