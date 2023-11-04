package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentRavagerBlocked {
    val general = mutableMapOf<String, Any>()

    var knockbackStrength: Float? = null

    fun reactionChoices(value: ReactionChoices.() -> Unit) {
        general["reaction_choices"] = ReactionChoices().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        knockbackStrength?.let { general["knockback_strength"] = it }
        return general
    }
}

class ReactionChoices {
    val general = arrayListOf<Any>()

    fun choice(weight: Int, event: String? = null, target: Subject? = null) {
        val data = mutableMapOf<String, Any>()
        if(event != null)
            data["event"] = event
        if(target != null)
            data["target"] = target
        general.add(
            mutableMapOf<String, Any>("weight" to weight).apply {
                if(data.isNotEmpty())
                    put("value", data)
            }
        )
    }

    fun getData(): ArrayList<Any> {
        return general
    }
}