package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class ComponentNap {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var cooldownMin: Float? = null
    var cooldownMax: Float? = null
    var mobDetectDist: Float? = null
    var mobDetectHeight: Float? = null

    fun canNapFilters(filter: BehEntityFilter.() -> Unit) {
        general["can_nap_filters"] = BehEntityFilter().apply(filter).getData()
    }

    fun wakeUpFilterExceptions(filter: BehEntityFilter.() -> Unit) {
        general["wake_mob_exceptions"] = BehEntityFilter().apply(filter).getData()
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        cooldownMin?.let { general["cooldown_min"] = it }
        cooldownMax?.let { general["cooldown_max"] = it }
        mobDetectDist?.let { general["mob_detect_dist"] = it }
        mobDetectHeight?.let { general["mob_detect_height"] = it }
        return general
    }
}