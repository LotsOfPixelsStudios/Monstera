package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentDig : MonsteraFile {
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

    var duration: Number = 0
        set(value) {
            unsafe.general["duration"] = value
            field = value
        }

    var idleTime: Number = 0
        set(value) {
            unsafe.general["idle_time"] = value
            field = value
        }

    var vibrationIsDisturbance: Boolean = false
        set(value) {
            unsafe.general["vibration_is_disturbance"] = value
            field = value
        }

    var suspicionIsDisturbance: Boolean = false
        set(value) {
            unsafe.general["suspicion_is_disturbance"] = value
            field = value
        }

    var digsInDaylight: Boolean = false
        set(value) {
            unsafe.general["digs_in_daylight"] = value
            field = value
        }

    fun onStart(event: String, target: Subject = Subject.SELF) {
        unsafe.general["on_start"] = mapOf(
            "event" to event,
            "target" to target.toString().lowercase()
        )
    }
}