package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class ComponentLookAt {
    val general = mutableMapOf<String, Any>()

    var searchRadius: Float? = null
    var setTarget: Boolean? = null
    var allowInvulnerable: Boolean? = null
    var lookEvent: String? = null

    /**
     * 0..1
     *
     *  The range for the random amount of time during which the entity is 'cooling down' and won't get angered or look for a target.
     */
    fun lookCooldown(min: Float, max: Float = min) {
        general["look_cooldown"] = arrayListOf(min, max)
    }

    fun filters(value: BehEntityFilter.() -> Unit) {
        general["filters"] = BehEntityFilter().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        searchRadius?.let { general["search_radius"] = it }
        setTarget?.let { general["set_target"] = it }
        allowInvulnerable?.let { general["allow_invulnerable"] = it }
        lookEvent?.let { general["look_event"] = it }
        return general
    }
}