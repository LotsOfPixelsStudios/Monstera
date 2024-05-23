package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class DryingOutTimer : MonsteraRawFile() {
    @SerializedName("total_time")
    @Expose
    var totalTime: Number? = null
        
    @SerializedName("water_bottle_refill_time")
    @Expose
    var waterBottleRefillTime: Number? = null
        
    @SerializedName("dried_out_event")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var driedOutEventData: DriedOutEvent? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * driedOutEvent {
     *     event = dried_out
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun driedOutEvent(value: DriedOutEvent.() -> Unit) {
        driedOutEventData = (driedOutEventData ?: DriedOutEvent()).apply(value)
    }

    @SerializedName("stopped_drying_out_event")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var stoppedDryingOutEventData: StoppedDryingOutEvent? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * stoppedDryingOutEvent {
     *     event = stop_drying_out
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun stoppedDryingOutEvent(value: StoppedDryingOutEvent.() -> Unit) {
        stoppedDryingOutEventData = (stoppedDryingOutEventData ?: StoppedDryingOutEvent()).apply(value)
    }

    @SerializedName("recover_after_dried_out_event")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var recoverAfterDriedOutEventData: RecoverAfterDriedOutEvent? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * recoverAfterDriedOutEvent {
     *     event = recover_after_dried_out
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun recoverAfterDriedOutEvent(value: RecoverAfterDriedOutEvent.() -> Unit) {
        recoverAfterDriedOutEventData = (recoverAfterDriedOutEventData ?: RecoverAfterDriedOutEvent()).apply(value)
    }

    class DriedOutEvent : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null
    }

    class StoppedDryingOutEvent : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null
    }

    class RecoverAfterDriedOutEvent : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null
    }
}
