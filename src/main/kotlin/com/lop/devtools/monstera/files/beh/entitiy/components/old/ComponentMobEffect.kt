package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class ComponentMobEffect {
    val general = mutableMapOf<String, Any>()

    var effectRange: Float? = null
    var mobEffect: String? = null
    var effectTime: Number? = null

    fun entityFilter(value: BehEntityFilter.() -> Unit) {
        general["entity_filter"] = BehEntityFilter().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        effectRange?.let { general["effect_range"] = it }
        mobEffect?.let { general["mob_effect"] = it }
        effectTime?.let { general["effect_time"] = it }
        return general
    }
}