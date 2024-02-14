package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentHurtByTarget {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var alertSameType: Boolean? = null
    var hurtOwner: Boolean? = null

    fun entityTypes(value: BehEntityTypes.() -> Unit) {
        general["entity_types"] = BehEntityTypes().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        alertSameType?.let { general["alert_same_type"] = it }
        hurtOwner?.let { general["hurt_owner"] = it }
        return general
    }
}