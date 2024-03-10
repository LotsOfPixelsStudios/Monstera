package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentLayDown {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var interval: Int? = null
    var randomStopInterval: Int? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        interval?.let { general["interval"] = it }
        randomStopInterval?.let { general["random_stop_interval"] = it }
        return general
    }
}