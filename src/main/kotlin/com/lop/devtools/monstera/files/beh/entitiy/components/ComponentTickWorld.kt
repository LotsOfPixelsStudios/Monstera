package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentTickWorld {
    val general = mutableMapOf<String, Any>()

    var distanceToPlayer: Number? = null
    var neverDespawn: Boolean? = null
    var radius: Number? = null

    fun getData(): MutableMap<String, Any> {
        distanceToPlayer?.let { general["distance_to_players"] = it }
        neverDespawn?.let { general["never_despawn"] = it }
        radius?.let { general["radius"] = it }
        return general
    }
}