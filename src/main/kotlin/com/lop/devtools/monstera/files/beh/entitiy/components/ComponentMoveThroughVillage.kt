package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentMoveThroughVillage {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var speedMultiplier: Float? = null
    var onlyAtNight: Boolean? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        onlyAtNight?.let { general["only_at_night"] = it }
        return general
    }
}