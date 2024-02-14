package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class ComponentGroupSize {
    val general = mutableMapOf<String, Any>()

    var radius: Float? = null

    fun filters(filters: BehEntityFilter.() -> Unit) {
        general["filters"] = BehEntityFilter().apply(filters).getData()
    }

    fun getData(): MutableMap<String, Any> {
        radius?.let { general["radius"] = it }
        return general
    }
}