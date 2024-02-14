package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentAdmireItem : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        override fun getData(): MutableMap<String, Any> {
            priority?.let { general["priority"] = it }
            admireItemSound?.let { general["admire_item_sound"] = it }
            return general
        }
    }

    var priority: Int? = null
    var admireItemSound: String? = null

    /**
     * The range of time in seconds to randomly wait before playing the sound again.
     */
    fun soundInterval(rangeMin: Float, rangeMax: Float) {
        unsafe.general["sound_interval"] = mutableMapOf(
            "range_min" to rangeMin,
            "range_max" to rangeMax
        )
    }

    fun onAdmireStart(event: String, target: String = "self") {
        unsafe.general["on_admire_item_start"] = mutableMapOf(
            "event" to event,
            "target" to target
        )
    }

    fun onAdmireStop(event: String, target: String = "self") {
        unsafe.general["on_admire_item_stop"] = mutableMapOf(
            "event" to event,
            "target" to target
        )
    }
}

class ComponentAdmItemComp : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        override fun getData(): MutableMap<String, Any> {
            duration?.let { general["duration"] }
            cooldownAfterBeingAttacked?.let { general["cooldown_after_being_attacked"] }
            return general
        }
    }

    var duration: Number? = null
    var cooldownAfterBeingAttacked: Number? = null
}