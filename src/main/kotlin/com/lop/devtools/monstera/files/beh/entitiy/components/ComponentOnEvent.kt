package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentOnEvent {
    val general = mutableMapOf<String, Any>()

    var event: String? = null
    var target: Subject? = null

    fun filter(filer: BehEntityFilter.() -> Unit) {
        general["filters"] = BehEntityFilter().apply(filer).getData()
    }

    fun getData(): MutableMap<String, Any> {
        event?.let { general["event"] = it }
        target?.let { general["target"] = it.toString().lowercase() }
        return general
    }
}