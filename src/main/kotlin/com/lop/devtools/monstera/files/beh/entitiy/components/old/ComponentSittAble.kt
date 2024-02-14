package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentSittAble {
    val general = mutableMapOf<String, Any>()

    var sitEvent: String? = null
    var standEvent: String? = null

    fun getData(): MutableMap<String, Any> {
        sitEvent?.let { general["sit_event"] = it }
        standEvent?.let { general["stand_event"] = it }
        return general
    }
}