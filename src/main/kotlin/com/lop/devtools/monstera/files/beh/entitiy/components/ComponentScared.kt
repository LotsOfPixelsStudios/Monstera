package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentScared {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var soundInterval: Int? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        soundInterval?.let { general["sound_interval"] = it }
        return general
    }
}