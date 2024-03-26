package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.BehEntityTypes
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class BehAvoidMobType {

    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("entity_types")
    @Expose
    var entityTypesData: MutableList<BehEntityTypes>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * entityTypes {
     *     maxDist = 10
     *     walkSpeedMultiplier = 0.8
     *     sprintSpeedMultiplier = 1.33
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun entityTypes(value: BehEntityTypes.() -> Unit) {
        entityTypesData = (entityTypesData ?: mutableListOf()).also { it.add(BehEntityTypes().apply(value)) }
    }

    @SerializedName("probability_per_strength")
    @Expose
    var probabilityPerStrength: Number? = null
        

    @SerializedName("remove_target")
    @Expose
    var removeTarget: Boolean? = null
        

    @SerializedName("avoid_mob_sound")
    @Expose
    var avoidMobSound: String? = null
        

    @SerializedName("sound_interval")
    @Expose
    var soundIntervalData: SoundInterval? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * soundInterval {
     *     rangeMin = 2.0
     *     rangeMax = 5.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun soundInterval(value: SoundInterval.() -> Unit) {
        soundIntervalData = (soundIntervalData ?: SoundInterval()).apply(value)
    }

    @SerializedName("max_dist")
    @Expose
    var maxDist: Number? = null
        

    @SerializedName("max_flee")
    @Expose
    var maxFlee: Number? = null
        

    @SerializedName("ignore_visibility")
    @Expose
    var ignoreVisibility: Boolean? = null
        

    @SerializedName("on_escape_event")
    @Expose
    var onEscapeEventData: OnEscapeEvent? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onEscapeEvent {
     *     event = become_calm_event
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onEscapeEvent(value: OnEscapeEvent.() -> Unit) {
        onEscapeEventData = (onEscapeEventData ?: OnEscapeEvent()).apply(value)
    }

    class SoundInterval {
        @SerializedName("range_min")
        @Expose
        var rangeMin: Number? = null
            

        @SerializedName("range_max")
        @Expose
        var rangeMax: Number? = null
            
    }

    class OnEscapeEvent {
        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }
}
