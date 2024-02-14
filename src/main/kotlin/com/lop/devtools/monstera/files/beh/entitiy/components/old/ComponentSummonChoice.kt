package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeList
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject
import java.awt.Color

class ComponentSummonEntity : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            return general
        }
    }

    var priority: Int = 0
        set(value) {
            unsafe.general["priority"] = value
            field = value
        }

    fun summonChoices(value: BehEntityCompSummonChoices.() -> Unit) {
        unsafe.general["summon_choices"] = BehEntityCompSummonChoices().apply(value).unsafe.getData()
    }
}

class BehEntityCompSummonChoices : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeList {
        val general = mutableListOf<Any>()

        override fun getData(): MutableList<Any> {
            return general
        }
    }

    fun summonChoice(value: SummonChoice.() -> Unit) {
        unsafe.general.add(SummonChoice().apply { value(this) }.unsafe.getData())
    }
}

class SummonChoice : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            return general
        }
    }

    var castDuration: Number = 0
        set(value) {
            unsafe.general["cast_duration"] = value
            field = value
        }
    var cooldownTime: Number = 0
        set(value) {
            unsafe.general["cooldown_time"] = value
            field = value
        }
    var doCasting: Boolean = false
        set(value) {
            unsafe.general["do_casting"] = value
            field = value
        }
    var maxActivationRange: Number = 0
        set(value) {
            unsafe.general["max_activation_range"] = value
            field = value
        }
    var minActivationRange: Number = 0
        set(value) {
            unsafe.general["min_activation_range"] = value
            field = value
        }
    var particleColor: Color = Color.BLACK
        set(value) {
            unsafe.general["particle_color"] = "#${Integer.toHexString(value.rgb)}"
            field = value
        }
    var startSoundEvent: String = ""
        set(value) {
            unsafe.general["start_sound_event"] = value
            field = value
        }
    var weight: Int = 1
        set(value) {
            unsafe.general["weight"] = value
            field = value
        }

    /**
     * 0..1
     *
     * @param value what to spawn with which weight and delay, shape and form
     */
    fun sequences(value: SummonSequence.() -> Unit) {
        unsafe.general.apply {
            put("sequence", SummonSequence().apply { value(this) }.unsafe.getData())
        }
    }

    /**
     * 0..1
     *
     * @param value The weight of this spell. Controls how likely the mob is to choose this spell when casting one
     */
    @Deprecated("Use variable", ReplaceWith("weight = value"))
    fun weight(value: Int) {
        unsafe.general.apply {
            put("weight", value)
        }
    }

    /**
     * 0..1
     *
     * @param filters entity filter
     */
    fun filters(filters: BehEntityFilter.() -> Unit) {
        unsafe.general.apply {
            put("filters", BehEntityFilter().apply { filters(this) }.getData())
        }
    }
}

class SummonSequence : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeList {
        val general = mutableListOf<Any>()

        override fun getData(): MutableList<Any> {
            return general
        }
    }

    /**
     * ```
     * sequence {
     *     shape = "circle"
     *     target = Subject.SELF
     *     baseDelay = 1f
     *     delayPerSummon = 0f
     *     numEntitiesSpawned = 5
     *     entityType = "minecraft:evocation_fang"
     *     size = 1.5f
     *     entityLifeSpan = 1.1f
     *     soundEvent = "prepare.attack" /* or use the sound {} api */
     *     summonCap = 3
     *     summonCapRadius = 16
     * }
     * ```
     */
    fun sequence(data: SummonSeqCom.() -> Unit) {
        unsafe.general.add(SummonSeqCom().apply(data).unsafe.getData())
    }
}

class SummonSeqCom : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            return general
        }
    }

    var shape: String = ""
        set(value) {
            unsafe.general["shape"] = value
            field = value
        }
    var target: Subject = Subject.SELF
        set(value) {
            unsafe.general["target"] = value.toString().lowercase()
            field = value
        }
    var baseDelay: Number = 0
        set(value) {
            unsafe.general["base_delay"] = value
            field = value
        }
    var delayPerSummon: Number = 0
        set(value) {
            unsafe.general["delay_per_summon"] = value
            field = value
        }
    var numEntitiesSpawned: Int = 0
        set(value) {
            unsafe.general["num_entities_spawned"] = value
            field = value
        }
    var entityType: String = ""
        set(value) {
            unsafe.general["entity_type"] = value
            field = value
        }
    var size: Number = 0
        set(value) {
            unsafe.general["size"] = value
            field = value
        }
    var entityLifeSpan: Number = 0
        set(value) {
            unsafe.general["entity_lifespan"] = value
            field = value
        }
    var soundEvent: String = ""
        set(value) {
            unsafe.general["sound_event"] = value
            field = value
        }

    var summonCap: Int = 0
        set(value) {
            unsafe.general["summon_cap"] = value
            field = value
        }
    var summonCapRadius: Number = 0
        set(value) {
            unsafe.general["summon_cap_radius"] = value
            field = value
        }
}