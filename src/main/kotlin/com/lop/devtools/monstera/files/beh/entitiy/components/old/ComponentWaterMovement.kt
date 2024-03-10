package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentWaterMovement {
    val general = mutableMapOf<String, Any>()

    var dragFactor: Float? = null

    fun getData(): MutableMap<String, Any> {
        dragFactor?.let { general["drag_factor"] = it }
        return general
    }
}