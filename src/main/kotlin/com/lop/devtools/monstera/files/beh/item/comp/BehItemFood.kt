package com.lop.devtools.monstera.files.beh.item.comp

import com.lop.devtools.monstera.files.beh.item.comp.food.Effects
import com.lop.devtools.monstera.files.beh.item.comp.food.Saturation

class BehItemFood {
    val general = mutableMapOf<String,Any>()

    /**
     * 0..1
     */
    fun nutrition(value: Int) {
        general.apply {
            put("nutrition", value)
        }
    }

    /**
     * 0..1
     */
    fun saturation(value: Saturation) {
        general.apply {
            put("saturation_modifier", value)
        }
    }

    /**
     * 0..1
     */
    fun useConvertTo(value: String) {
        general.apply {
            put("using_converts_to", value)
        }
    }

    /**
     * 0..1
     */
    fun alwaysEat(value: Boolean) {
        general.apply {
            put("can_always_eat", value)
        }
    }

    /**
     * 0..1
     */
    fun effects(settings: Effects.() -> Unit) {
        val effects = Effects().apply { settings(this) }
        general.apply {
            put("effects", effects.getData())
        }
    }

    fun getData(): MutableMap<String,Any> {
        return general
    }
}