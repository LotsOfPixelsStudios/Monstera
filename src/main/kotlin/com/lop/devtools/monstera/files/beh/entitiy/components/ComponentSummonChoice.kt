package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject
import java.awt.Color

class ComponentSummonEntity {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null

    fun summonChoices(value: BehEntityCompSummonChoices.() -> Unit) {
        general["summon_choices"] = BehEntityCompSummonChoices().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        return general
    }
}

class BehEntityCompSummonChoices {
    val general = arrayListOf<Any>()

    fun summonChoice(value: SummonChoice.() -> Unit) {
        general.add(SummonChoice().apply { value(this) }.getData())
    }

    fun getData(): ArrayList<Any> {
        return general
    }
}

class SummonChoice {
    val general = mutableMapOf<String, Any>()

    var castDuration: Float? = null
    var cooldownTime: Float? = null
    var doCasting: Boolean? = null
    var maxActivationRange: Float? = null
    var minActivationRange: Float? = null
    var particleColor: Color? = null
    var startSoundEvent: String? = null
    var weight: Int? = null

    /**
     * 0..1
     *
     * @param value what to spawn with which weight and delay, shape and form
     */
    fun sequences(value: SummonSequence.() -> Unit) {
        general.apply {
            put("sequence", SummonSequence().apply { value(this) }.getData())
        }
    }

    /**
     * 0..1
     *
     * @param value The weight of this spell. Controls how likely the mob is to choose this spell when casting one
     */
    fun weight(value: Int) {
        general.apply {
            put("weight", value)
        }
    }

    /**
     * 0..1
     *
     * @param filters entity filter
     */
    fun filters(filters: BehEntityFilter.() -> Unit) {
        general.apply {
            put("filters", BehEntityFilter().apply { filters(this) }.getData())
        }
    }

    fun getData(): MutableMap<String, Any> {
        castDuration?.let { general["cast_duration"] = it }
        cooldownTime?.let { general["cooldown_time"] = it }
        doCasting?.let { general["do_casting"] = it }
        maxActivationRange?.let { general["max_activation_range"] = it }
        minActivationRange?.let { general["min_activation_range"] = it }
        particleColor?.let { general["particle_color"] = "#${Integer.toHexString(it.rgb)}" }
        startSoundEvent?.let { general["start_sound_event"] = it }
        weight?.let { general["weight"] = it }
        return general
    }
}

class SummonSequence {
    val general = arrayListOf<MutableMap<String, Any>>()

    fun sequence(data: SummonSeqCom.() -> Unit) {
        general.add(SummonSeqCom().apply(data).getData())
    }

    fun getData(): ArrayList<MutableMap<String, Any>> {
        return general
    }
}

class SummonSeqCom {
    val general = mutableMapOf<String, Any>()

    var shape: String? = null
    var target: Subject? = null
    var baseDelay: Float? = null
    var delayPerSummon: Float? = null
    var numEntitiesSpawned: Int? = null
    var entityType: String? = null
    var size: Float? = null
    var entityLifeSpan: Float? = null
    var soundEvent: String? = null

    fun getData(): MutableMap<String, Any> {
        shape?.let { general["shape"] = it }
        target?.let { general["target"] = it }
        baseDelay?.let { general["base_delay"] = it }
        delayPerSummon?.let { general["delay_per_summon"] = it }
        numEntitiesSpawned?.let { general["num_entities_spawned"] = it }
        entityType?.let { general["entity_type"] = it }
        size?.let { general["size"] = it }
        entityLifeSpan?.let { general["entity_lifespan"] = it }
        soundEvent?.let { general["sound_event"] = it }
        return general
    }
}