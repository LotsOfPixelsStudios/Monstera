package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentLeashable {
    val general = mutableMapOf<String, Any>()

    var softDistance: Float? = null
    var hardDistance: Float? = null
    var maxDistance: Float? = null
    var canBeStolen: Boolean? = null
    var onLeash: String? = null
    var onUnLeash: String? = null

    fun getData(): MutableMap<String, Any> {
        softDistance?.let { general["soft_distance"] = it }
        hardDistance?.let { general["hard_distance"] = it }
        maxDistance?.let { general["max_distance"] = it }
        canBeStolen?.let { general["can_be_stolen"] = it }
        onLeash?.let { general["on_leash"] = it }
        onUnLeash?.let { general["on_unleash"] = it }
        return general
    }
}