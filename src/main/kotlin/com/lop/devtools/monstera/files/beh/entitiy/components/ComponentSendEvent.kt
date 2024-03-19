package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter
import java.awt.Color

class ComponentSendEvent {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null


    fun eventChoices(value: EventChoices.() -> Unit) {
        general["event_choices"] = EventChoices().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        return general
    }
}

class EventChoices {
    val general = arrayListOf<MutableMap<String, Any>>()

    fun choice(value: EventChoice.() -> Unit) {
        general.add(EventChoice().apply(value).getData())
    }

    fun getData(): ArrayList<MutableMap<String, Any>> {
        return general
    }
}

class EventChoice {
    val general = mutableMapOf<String, Any>()

    var minActivationRange: Float? = null
    var maxActivationRange: Float? = null
    var cooldownTime: Float? = null
    var castDuration: Float? = null
    var particleColor: Color? = null
    var weight: Int? = null
    var startSoundEvent: String? = null

    fun filters(value: BehEntityFilter.() -> Unit) {
        general["filters"] = BehEntityFilter().apply(value).getData()
    }

    fun sequence(value: CompSequence.() -> Unit) {
        general["sequence"] = CompSequence().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        minActivationRange?.let { general["min_activation_range"] = it }
        maxActivationRange?.let { general["max_activation_range"] = it }
        cooldownTime?.let { general["cooldown_time"] = it }
        castDuration?.let { general["cast_duration"] = it }
        particleColor?.let { general["particle_color"] = "#${Integer.toHexString(it.rgb)}" }
        weight?.let { general["weight"] = it }
        startSoundEvent?.let { general["start_sound_event"] = it }
        return general
    }
}

class CompSequence {
    val general = arrayListOf<MutableMap<String, Any>>()

    fun seq(baseDelay: Float? = null, event: String? = null, soundEvent: String? = null) {
        val data = mutableMapOf<String, Any>()
        if(baseDelay != null)
            data["base_delay"] = baseDelay
        if(event != null)
            data["event"] = event
        if(soundEvent != null)
            data["sound_event"] = soundEvent
        general.add(data)
    }

    fun getData(): ArrayList<MutableMap<String, Any>> {
        return general
    }
}