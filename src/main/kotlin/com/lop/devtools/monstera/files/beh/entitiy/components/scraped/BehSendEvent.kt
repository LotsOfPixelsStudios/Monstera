package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class BehSendEvent : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("event_choices")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var eventChoicesData: MutableList<EventChoices>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * eventChoices {
     *     minActivationRange = 0.0
     *     maxActivationRange = 16.0
     *     cooldownTime = 5.0
     *     castDuration = 3.0
     *     particleColor = #FFB38033
     *     weight = 3
     *     startSoundEvent = cast.spell
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun eventChoices(value: EventChoices.() -> Unit) {
        eventChoicesData = (eventChoicesData ?: mutableListOf()).also { it.add(EventChoices().apply(value)) }
    }

    class EventChoices : MonsteraRawFile() {
        @SerializedName("min_activation_range")
        @Expose
        var minActivationRange: Number? = null
            
        @SerializedName("max_activation_range")
        @Expose
        var maxActivationRange: Number? = null
            
        @SerializedName("cooldown_time")
        @Expose
        var cooldownTime: Number? = null
            
        @SerializedName("cast_duration")
        @Expose
        var castDuration: Number? = null
            
        @SerializedName("particle_color")
        @Expose
        var particleColor: String? = null
            
        @SerializedName("weight")
        @Expose
        var weight: Number? = null
            
        @SerializedName("filters")
        @Expose
        var filtersData: BehEntityFilter? = null
            @MonsteraBuildSetter set

        /**
         * com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.Filters allow data objects to specify test criteria which allows their use.
         * ```
         * filters {
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun filters(value: BehEntityFilter.() -> Unit) {
            filtersData = (filtersData ?: BehEntityFilter()).apply(value)
        }

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
         *     baseDelay = 2.0
         *     event = wololo
         *     soundEvent = prepare.wololo
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
        @SerializedName("base_delay")
        @Expose
        var baseDelay: Number? = null
            
        @SerializedName("event")
        @Expose
        var event: String? = null
            
        @SerializedName("sound_event")
        @Expose
        var soundEvent: String? = null
    }
}
