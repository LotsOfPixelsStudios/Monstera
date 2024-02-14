package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentSlimeAttack {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var setPersistent: Boolean? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        setPersistent?.let { general["set_persistent"] = it }
        return general
    }
}