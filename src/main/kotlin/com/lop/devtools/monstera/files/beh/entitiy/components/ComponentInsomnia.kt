package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentInsomnia {
    val general = mutableMapOf<String, Any>()

    var daysUntilInsomnia: Float? = null

    fun getData(): MutableMap<String, Any> {
        daysUntilInsomnia?.let { general["days_until_insomnia"] = it }
        return general
    }
}