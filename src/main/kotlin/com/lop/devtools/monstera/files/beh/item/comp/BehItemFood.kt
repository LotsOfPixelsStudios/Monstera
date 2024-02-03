package com.lop.devtools.monstera.files.beh.item.comp

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.beh.item.comp.food.Effects
import com.lop.devtools.monstera.files.beh.item.comp.food.Saturation

class BehItemFood: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String,Any>()

        val effects = Effects()

        override fun getData(): MutableMap<String, Any> {
            val effectsData = effects.getData()
            if(effectsData.isNotEmpty())
                general["effects"] = effectsData
            return general
        }
    }

    /**
     * 0..1
     */
    fun nutrition(value: Int) {
        unsafe.general.apply {
            put("nutrition", value)
        }
    }

    /**
     * 0..1
     */
    fun saturation(value: Saturation) {
        unsafe.general.apply {
            put("saturation_modifier", value)
        }
    }

    /**
     * 0..1
     */
    fun useConvertTo(value: String) {
        unsafe.general.apply {
            put("using_converts_to", value)
        }
    }

    /**
     * 0..1
     */
    fun alwaysEat(value: Boolean) {
        unsafe.general.apply {
            put("can_always_eat", value)
        }
    }

    /**
     * 0..1
     */
    fun effects(settings: Effects.() -> Unit) {
        unsafe.effects.apply(settings)
    }
}