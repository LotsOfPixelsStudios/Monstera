package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentPushable {
    val general = mutableMapOf<String, Any>()

    var isPushable: Boolean? = null
    var isPushableByPiston: Boolean? = null

    fun getData(): MutableMap<String, Any> {
        isPushable?.let { general["is_pushable"] = it }
        isPushableByPiston?.let { general["is_pushable_by_piston"] = it }
        return general
    }
}