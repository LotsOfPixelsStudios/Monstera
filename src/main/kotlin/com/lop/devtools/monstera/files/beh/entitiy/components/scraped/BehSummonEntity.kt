package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class BehSummonEntity : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("summon_choices")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var summonChoicesData: MutableList<SummonChoices>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * summonChoices {
     *     minActivationRange = 0.0
     *     maxActivationRange = 3.0
     *     cooldownTime = 5.0
     *     weight = 3
     *     castDuration = 2.0
     *     particleColor = #FF664D59
     *     startSoundEvent = cast.spell
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun summonChoices(value: SummonChoices.() -> Unit) {
        summonChoicesData = (summonChoicesData ?: mutableListOf()).also { it.add(SummonChoices().apply(value)) }
    }

    class SummonChoices : MonsteraRawFile() {
        @SerializedName("min_activation_range")
        @Expose
        var minActivationRange: Number? = null
            
        @SerializedName("max_activation_range")
        @Expose
        var maxActivationRange: Number? = null
            
        @SerializedName("cooldown_time")
        @Expose
        var cooldownTime: Number? = null
            
        @SerializedName("weight")
        @Expose
        var weight: Number? = null
            
        @SerializedName("cast_duration")
        @Expose
        var castDuration: Number? = null
            
        @SerializedName("particle_color")
        @Expose
        var particleColor: String? = null
            
        @SerializedName("start_sound_event")
        @Expose
        var startSoundEvent: String? = null
            
        @SerializedName("sequence")
        @Expose
        @JsonAdapter(MonsteraListFileTypeAdapter::class)
        var sequenceData: MutableList<Sequence>? = null
            @MonsteraBuildSetter set

        /**
         *
         * ```
         * sequence {
         *     shape = circle
         *     target = self
         *     baseDelay = 1.0
         *     delayPerSummon = 0.0
         *     numEntitiesSpawned = 5
         *     entityType = minecraft:evocation_fang
         *     size = 1.5
         *     entityLifespan = 1.1
         *     soundEvent = prepare.attack
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun sequence(value: Sequence.() -> Unit) {
            sequenceData = (sequenceData ?: mutableListOf()).also { it.add(Sequence().apply(value)) }
        }
    }

    class Sequence : MonsteraRawFile() {
        @SerializedName("shape")
        @Expose
        var shape: String? = null
            
        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
        @SerializedName("base_delay")
        @Expose
        var baseDelay: Number? = null
            
        @SerializedName("delay_per_summon")
        @Expose
        var delayPerSummon: Number? = null
            
        @SerializedName("num_entities_spawned")
        @Expose
        var numEntitiesSpawned: Number? = null
            
        @SerializedName("entity_type")
        @Expose
        var entityType: String? = null
            
        @SerializedName("size")
        @Expose
        var size: Number? = null
            
        @SerializedName("entity_lifespan")
        @Expose
        var entityLifespan: Number? = null
            
        @SerializedName("sound_event")
        @Expose
        var soundEvent: String? = null
    }
}
