package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentTradeInterest {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var cooldown: Float? = null
    var withinRadius: Float? = null
    var interestTime: Float? = null
    var removeItemTime: Float? = null
    var carriedItemSwitchTime: Float? = null
    var cooldownTime: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        cooldown?.let { general["priority"] = it }
        withinRadius?.let { general["priority"] = it }
        interestTime?.let { general["priority"] = it }
        removeItemTime?.let { general["priority"] = it }
        carriedItemSwitchTime?.let { general["priority"] = it }
        cooldownTime?.let { general["priority"] = it }
        return general
    }
}