package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentSpellEffects {
    val general = mutableMapOf<String, Any>()

    var removeEffects: List<String>? = null

    /**
     * 0..1
     *
     * List of effects to add to this entity after adding this component
     */
    fun addEffects(value: CompAddSpellEffects.() -> Unit) {
        general["add_effects"] = CompAddSpellEffects().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        removeEffects?.let { general["remove_effects"] = it }
        return general
    }
}

class CompAddSpellEffects {
    val general = arrayListOf<Any>()

    fun effect(value: CompAddSpellEffect.() -> Unit) {
        general.add(CompAddSpellEffect().apply(value).getData())
    }

    fun getData(): ArrayList<Any> {
        return general
    }
}

class CompAddSpellEffect {
    val general = mutableMapOf<String, Any>()

    var effectId: String? = null
    var duration: Number? = null
    var amplifier: Int? = null
    var displayOnScreenAnimation: Boolean? = null
    var visible: Boolean? = null

    fun getData(): MutableMap<String, Any> {
        effectId?.let { general["effect"] = it }
        duration?.let { general["duration"] = it }
        amplifier?.let { general["amplifier"] = it }
        displayOnScreenAnimation?.let { general["display_on_screen_animation"] = it }
        visible?.let { general["visible"] = it }
        return general
    }
}