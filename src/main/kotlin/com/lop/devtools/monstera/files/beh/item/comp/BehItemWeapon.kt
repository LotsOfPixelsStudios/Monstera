package com.lop.devtools.monstera.files.beh.item.comp

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class BehItemWeapon: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        override fun getData(): MutableMap<String, Any> {
            return general
        }
    }

    /**
     * Trigger for letting you know when this item is used to hit a block
     */
    fun onHitBlock(value: Any) {
        unsafe.general.apply {
            put("on_hit_block", value)
        }
    }

    /**
     * Trigger for letting you know when this item is used to hurt another mob
     */
    fun onHurtEntity(value: Any) {
        unsafe.general.apply {
            put("on_hurt_entity", value)
        }
    }

    /**
     * Trigger for letting you know when this item hit another actor, but didn't do damage
     */
    fun onNotHurtEntity(value: Any) {
        unsafe.general.apply {
            put("on_not_hurt_entity", value)
        }
    }
}