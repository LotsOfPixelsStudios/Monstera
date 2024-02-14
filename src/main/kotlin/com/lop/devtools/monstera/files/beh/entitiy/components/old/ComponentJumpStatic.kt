package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentJumpStatic {
    val general = mutableMapOf<String, Any>()

    var jumpPower: Float? = null

    fun getData(): MutableMap<String, Any> {
        jumpPower?.let { general["jump_power"] = it }
        return general
    }
}