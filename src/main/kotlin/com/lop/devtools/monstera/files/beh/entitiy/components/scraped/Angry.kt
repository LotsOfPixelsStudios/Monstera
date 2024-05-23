package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class Angry : MonsteraRawFile() {
    @SerializedName("duration")
    @Expose
    var duration: Number? = null

    @SerializedName("broadcastAnger")
    @Expose
    var broadcastAnger: Boolean? = null

    @SerializedName("broadcastRange")
    @Expose
    var broadcastRange: Number? = null

    @SerializedName("broadcast_filters")
    @Expose
    var broadcastFiltersData: BehEntityFilter? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * broadcastFilters {
     *     test = is_family
     *     operator = !=
     *     value = pacified
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun broadcastFilters(value: BehEntityFilter.() -> Unit) {
        broadcastFiltersData = (broadcastFiltersData ?: BehEntityFilter()).apply(value)
    }

    @SerializedName("calm_event")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var calmEventData: CalmEvent? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * calmEvent {
     *     event = calmed_down
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun calmEvent(value: CalmEvent.() -> Unit) {
        calmEventData = (calmEventData ?: CalmEvent()).apply(value)
    }

    @SerializedName("duration_delta")
    @Expose
    var durationDelta: Number? = null

    @SerializedName("angry_sound")
    @Expose
    var angrySound: String? = null

    @SerializedName("sound_interval")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
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

    @SerializedName("broadcast_anger_on_attack")
    @Expose
    var broadcastAngerOnAttack: Boolean? = null

    @SerializedName("broadcast_anger_on_being_attacked")
    @Expose
    var broadcastAngerOnBeingAttacked: Boolean? = null

    @SerializedName("broadcast_targets")
    @Expose
    var broadcastTargetsData: MutableList<String>? = null

    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun broadcastTargets(vararg value: String) {
        broadcastTargetsData = (broadcastTargetsData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

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

    class CalmEvent : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null

        @SerializedName("target")
        @Expose
        var target: Subject? = null
    }

    class SoundInterval : MonsteraRawFile() {
        @SerializedName("range_min")
        @Expose
        var rangeMin: Number? = null

        @SerializedName("range_max")
        @Expose
        var rangeMax: Number? = null
    }
}
