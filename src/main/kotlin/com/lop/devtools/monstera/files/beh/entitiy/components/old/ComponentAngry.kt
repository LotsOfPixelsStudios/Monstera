package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentAngry: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            angrySound?.let { general["angry_sound"] = it }
            broadcastAnger?.let { general["broadcast_anger"] = it }
            broadCastAngerOnAttack?.let { general["broadcast_anger_on_attack"] = it }
            broadcastAngerOnBeingAttacked?.let { general["broadcast_anger_on_being_attacked"] = it }
            broadcastRange?.let { general["broadcast_range"] = it }
            broadCastTargets?.let { general["broadcast_targets"] = it }
            duration?.let { general["duration"] = it }
            durationDelta?.let { general["duration_delta"] = it }
            return general
        }
    }



    var angrySound: String? = null
    var broadcastAnger: Boolean? = null
    var broadCastAngerOnAttack: Boolean? = null
    var broadcastAngerOnBeingAttacked: Boolean? = null
    var broadcastRange: Number? = null
    var broadCastTargets: ArrayList<String>? = null
    var duration: Number? = null
    var durationDelta: Number? = null

    /**
     * 0..1
     *
     * 	Conditions that make this entry in the list valid
     */
    fun broadcastFilter(settings: BehEntityFilter.() -> Unit) {
        unsafe.general["broadcast_filters"] = BehEntityFilter().apply(settings).getData()
    }

    /**
     * 0..1
     *
     * Event to run after the number of seconds specified in duration expires (when the entity stops being 'angry')
     */
    fun calmEvent(event: String, target: Subject = Subject.SELF) {
        unsafe.general["calm_event"] = mutableMapOf(
            "event" to event,
            "target" to target.toString().lowercase()
        )
    }

    /**
     * 0..1
     *
     * Filter out mob types that it should not attack while angry (other Piglins)
     */
    fun filters(settings: BehEntityFilter.() -> Unit) {
        unsafe.general["filters"] = BehEntityFilter().apply(settings).getData()
    }

    /**
     * 0..1
     *
     * The range of time in seconds to randomly wait before playing the sound again
     */
    fun soundInterval(lower: Number, higher: Number) {
        unsafe.general["sound_interval"] = listOf(lower, higher)
    }
}