package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentRamAttack {
    val general = mutableMapOf<String, Any>()
    val onStart = arrayListOf<MutableMap<String, String>>()

    var priority: Int? = null
    var runSpeed: Float? = null
    var ramSpeed: Float? = null
    var minRamDistance: Number? = null
    var ramDistance: Number? = null
    var knockbackForce: Float? = null
    var knockbackHeight: Float? = null
    var preRamSound: String? = null
    var ramImpactSound: String? = null

    /**
     * 0..1
     */
    fun cooldownRange(lower: Int, higher: Int) {
        general["cooldown_range"] = arrayListOf(lower, higher)
    }

    /**
     * 0..n
     *
     * @param event the event to trigger
     * @param target like Subject.SELF
     */
    fun onStart(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if(target != null)
            data["target"] = target.toString().lowercase()
        onStart.add(data)
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        runSpeed?.let { general["run_speed"] = it }
        ramSpeed?.let { general["ram_speed"] = it }
        minRamDistance?.let { general["min_ram_distance"] = it }
        ramDistance?.let { general["ram_distance"] = it }
        knockbackForce?.let { general["knockback_force"] = it }
        knockbackHeight?.let { general["knockback_height"] = it }
        preRamSound?.let { general["pre_ram_sound"] = it }
        ramImpactSound?.let { general["ram_impact_sound"] = it }
        general["on_start"] = onStart
        return general
    }
}