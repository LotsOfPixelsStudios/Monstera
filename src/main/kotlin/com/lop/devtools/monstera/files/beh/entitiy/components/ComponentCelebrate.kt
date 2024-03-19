package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentCelebrate : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            priority?.let { general["priority"] = it }
            celebrationSound?.let { general["celebration_sound"] = it }
            duration?.let { general["duration"] = it }

            return general
        }
    }
    var priority: Int? = null
    var celebrationSound: String? = null

    var duration: Float? = null

    /**
     * The range of time in seconds to randomly wait before playing the sound again.
     */
    fun soundInterval(rangeMin: Float? = null, rangeMax: Float? = null) {
        unsafe.general["sound_interval"] = sharedInterval(rangeMin, rangeMax)
    }

    /**
     * The range of time in seconds to randomly wait before jumping again.
     */
    fun jumpInterval(rangeMin: Float? = null, rangeMax: Float? = null) {
        unsafe.general["jump_interval"] = sharedInterval(rangeMin, rangeMax)
    }

    private fun sharedInterval(rangeMin: Float? = null, rangeMax: Float? = null): MutableMap<String, Any> {
        val data = mutableMapOf<String, Any>()

        if(rangeMin != null) {
            data["range_min"] = rangeMin
        }
        if(rangeMax != null) {
            data["range_max"] = rangeMax
        }
        return data
    }

    /**
     * The event to trigger when the goal's duration expires.
     */
    fun onCelebrationEndEvent(event: String, target: String? = null) {
        val data = mutableMapOf("event" to event)
        if(target != null) {
            data["target"] = target
        }
        unsafe.general["on_celebration_end_event"] = data
    }
}