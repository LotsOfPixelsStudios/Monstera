package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentTimer {
    val general = mutableMapOf<String, Any>()

    var looping: Boolean? = null
    var randomInterval: Boolean? = null

    /**
     * 0..1
     *
     * Amount of time in seconds for the timer. Can be specified as a single number arrayListOf(value) or a pair of numbers arrayListOf(min,max). Incompatible with random_time_choices.
     */
    fun time(min: Number, max: Number = min) {
        general["time"] = arrayListOf(min, max)
    }

    /**
     * 0..1
     *
     * Event to fire when the time on the timer runs out.
     */
    fun timeDownEvent(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if(target != null)
            data["target"] = target.toString().lowercase()
        general["time_down_event"] = data
    }

    /**
     * 0..1
     *
     * This is a list of objects, representing one value in seconds that can be picked before firing the event and an optional weight. Incompatible with time.
     */
    fun randomTimeChoice(value: CompRandomTimeChoices.() -> Unit) {
        general["random_time_choices"] = CompRandomTimeChoices().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        looping?.let { general["looping"] = it }
        randomInterval?.let { general["randomInterval"] = it }
        return general
    }
}

class CompRandomTimeChoices {
    val general = arrayListOf<Any>()

    /**
     * 1..n
     */
    fun choice(weight: Number? = null, value: Number) {
        val data = mutableMapOf("value" to value)
        if(weight != null)
            data["weight"] = weight
        general.add(data)
    }

    fun getData(): ArrayList<Any> {
        return general
    }
}