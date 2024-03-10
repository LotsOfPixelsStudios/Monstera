package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class ComponentCelebrateHunt : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            broadcast?.let { general["broadcast"] = it }
            duration?.let { general["duration"] = it }
            celebrateSound?.let { general["celebrate_sound"] = it }
            radius?.let { general["radius"] = it }
            return general
        }
    }
    var broadcast: Boolean? = null
    var duration: Number? = null
    var celebrateSound: String? = null

    var radius: Number? = null

    fun celebrationTargets(value: BehEntityFilter.() -> Unit) {
        unsafe.general["celebration_targets"] = BehEntityFilter().apply(value).getData()
    }

    /**
     * 0..1
     *
     * The range of time in seconds to randomly wait before playing the sound again
     */
    fun soundInterval(min: Float, max: Float) {
        unsafe.general["sound_interval"] = arrayListOf(min, max)
    }
}