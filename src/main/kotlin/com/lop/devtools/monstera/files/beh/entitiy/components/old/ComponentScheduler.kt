package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class ComponentScheduler {
    val general = mutableMapOf<String, Any>()

    var minDelaySecs: Number? = null
    var maxDelaySecs: Number? = null


    /**
     * 0..1
     *
     * The list of triggers that fire when the conditions match the given filter criteria.
     * If any filter criteria overlap the first defined event will be picked.
     */
    fun scheduledEvents(value: CompScheduledEvents.() -> Unit) {
        general["scheduled_events"] = CompScheduledEvents().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        minDelaySecs?.let { general["min_delay_secs"] = it }
        maxDelaySecs?.let { general["max_delay_secs"] = it }
        return general
    }
}

class CompScheduledEvents {
    val general = arrayListOf<Any>()

    fun event(event: String, filter: BehEntityFilter.() -> Unit) {
        general.add(mutableMapOf<String, Any>("event" to event).apply {
            val data = BehEntityFilter().apply(filter).getData()
            if (data.isNotEmpty())
                put("filters", data)
        })
    }

    fun getData(): ArrayList<Any> {
        return general
    }
}