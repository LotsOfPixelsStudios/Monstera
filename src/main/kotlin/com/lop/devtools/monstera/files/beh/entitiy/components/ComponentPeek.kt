package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentPeek {
    val general = mutableMapOf<String, Any>()

    var onOpen: String? = null
    var onClose: String? = null
    var onTargetOpen: String? = null

    fun getData(): MutableMap<String, Any> {
        onOpen?.let { general["on_open"] = it }
        onClose?.let { general["on_close"] = it }
        onTargetOpen?.let { general["on_target_open"] = it }
        return general
    }
}