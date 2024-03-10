package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentCelebrateSurvive: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        override fun getData() = general
    }

    var priority: Int = 0
        set(value) {
            unsafe.general["priority"] = value
            field = value
        }

    fun fireworksInterval(rangeMin: Number, rangeMax: Number) {
        unsafe.general["fireworks_interval"] = mapOf(
            "range_min" to rangeMin,
            "range_max" to rangeMax
        )
    }

    var duration: Number = 0
        set(value) {
            unsafe.general["duration"] = value
            field = value
        }

    fun celebrateOnEndEvent(event: String, target: Subject = Subject.SELF) {
        unsafe.general["on_celebration_end_event"] = mapOf(
            "event" to event,
            "target" to target
        )
    }
}