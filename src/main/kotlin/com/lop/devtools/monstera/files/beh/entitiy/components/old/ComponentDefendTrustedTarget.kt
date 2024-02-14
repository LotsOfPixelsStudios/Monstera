package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentDefendTrustedTarget {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var withinRadius: Float? = null
    var mustSee: Boolean? = null
    var aggroSound: String? = null
    var soundChance: Float? = null
    var attackInterval: Int? = null
    var mustSeeForgetDuration: Float? = null

    fun onDefendStart(event: String, target: String? = null) {
        val data = mutableMapOf("event" to event)
        if (target != null) {
            data["target"] = target
        }
        general["on_defend_start"] = data
    }

    fun onDefendStart(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if (target != null) {
            data["target"] = target.toString().lowercase()
        }
        general["on_defend_start"] = data
    }

    /**
     * 0..1
     *
     * @sample sampleEntityTypes
     */
    fun entityTypes(value: BehEntityTypes.() -> Unit) {
        general["entity_types"] = BehEntityTypes().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        withinRadius?.let { general["within_radius"] = it }
        mustSee?.let { general["must_see"] = it }
        aggroSound?.let { general["aggro_sound"] = it }
        soundChance?.let { general["sound_chance"] = it }
        attackInterval?.let { general["attack_interval"] = it }
        mustSeeForgetDuration?.let { general["must_see_forget_duration"] = it }

        return general
    }

    private fun sampleEntityTypes() {
        entityTypes {
            type {
                maxDist = 2f
                mustSee = true
                mustForgetDuration = 5f
                sprintSpeedMultiplier = 1.2f
                walkSpeedMultiplier = 0.2f

                filters {

                }
            }
        }
    }
}