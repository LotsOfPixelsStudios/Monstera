package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentScaleByAge {
    val general = mutableMapOf<String, Any>()

    var endScale: Float? = null
    var startScale: Float? = null

    fun getData(): MutableMap<String, Any> {
        endScale?.let { general["end_scale"] = it }
        startScale?.let { general["start_scale"] = it }
        return general
    }
}