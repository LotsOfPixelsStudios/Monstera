package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentGoHome {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var speedMultiplier: Float? = null
    var interval: Int? = null
    var goalRadius: Float? = null

    fun onHome(value: CompOnHome.() -> Unit) {
        general["on_home"] = CompOnHome().apply(value).getData()
    }

    /**
     * event to trigger if no home found
     * @param event the event to trigger
     * @param target (self/other/block)
     */
    fun onFailed(event: String, target: String? = null) {
        val data = mutableMapOf("event" to event)
        if (target != null)
            data["target"] = target
        general["on_failed"] = data
    }

    /**
     * event to trigger if no home found
     * @param event the event to trigger
     * @param target
     */
    fun onFailed(event: String, target: Subject) {
        val data = mutableMapOf(
            "event" to event,
            "target" to target.toString().lowercase()
        )
        general["on_failed"] = data
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        interval?.let { general["interval"] = it }
        goalRadius?.let { general["goal_radius"] = it }
        return general
    }
}

class CompOnHome {
    val general = arrayListOf<MutableMap<String, Any>>()

    fun addEvent(event: String, target: String? = null, filter: BehEntityFilter.() -> Unit) {
        val data = mutableMapOf<String, Any>("event" to event)
        if (target != null)
            data["target"] = target
        if (BehEntityFilter().apply(filter).getData().isNotEmpty()) {
            data["filters"] = BehEntityFilter().apply(filter).getData()
        }
    }

    fun addEvent(event: String, target: Subject, filter: BehEntityFilter.() -> Unit) {
        val data = mutableMapOf<String, Any>(
            "event" to event,
            "target" to target.toString().lowercase()
        )
        if (BehEntityFilter().apply(filter).getData().isNotEmpty()) {
            data["filters"] = BehEntityFilter().apply(filter).getData()
        }
    }

    fun getData(): ArrayList<MutableMap<String, Any>> {
        return general
    }
}