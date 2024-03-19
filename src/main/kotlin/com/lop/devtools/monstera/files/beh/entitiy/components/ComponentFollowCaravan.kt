package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentFollowCaravan {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var speedMultiplier: Float? = null
    var entityCount: Int? = null

    /**
     * 0..1
     */
    fun entityTypes(value: BehEntityTypes.() -> Unit) {
        general["entity_types"] = BehEntityTypes().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        entityCount?.let { general["entity_count"] = it }
        return general
    }
}