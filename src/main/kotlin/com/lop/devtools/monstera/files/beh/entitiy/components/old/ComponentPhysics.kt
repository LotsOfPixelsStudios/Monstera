package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentPhysics {
    val general = mutableMapOf<String, Any>()

    var hasCollision: Boolean? = null
    var hasGravity: Boolean? = null

    fun getData(): MutableMap<String, Any> {
        hasCollision?.let { general["has_collision"] = it }
        hasGravity?.let { general["has_gravity"] = it }
        return general
    }
}