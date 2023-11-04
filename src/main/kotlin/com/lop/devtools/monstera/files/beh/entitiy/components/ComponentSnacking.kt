package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentSnacking {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var snackingCooldown: Float? = null
    var snackingCooldownMin: Float? = null
    var snackingStopChance: Float? = null
    var items: ArrayList<String>? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        snackingCooldown?.let { general["snacking_cooldown"] = it }
        snackingCooldownMin?.let { general["snacking_cooldown_min"] = it }
        snackingStopChance?.let { general["snacking_stop_chance"] = it }
        items?.let { general["items"] = it }
        return general
    }
}