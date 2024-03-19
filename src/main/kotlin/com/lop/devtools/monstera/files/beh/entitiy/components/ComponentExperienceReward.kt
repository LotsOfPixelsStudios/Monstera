package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentExperienceReward {
    val general = mutableMapOf<String, Any>()

    var onBred: String? = null
    var onDeath: String? = null

    fun getData(): MutableMap<String, Any> {
        onBred?.let { general["on_bred"] = it }
        onDeath?.let { general["on_death"] = it }
        return general
    }
}