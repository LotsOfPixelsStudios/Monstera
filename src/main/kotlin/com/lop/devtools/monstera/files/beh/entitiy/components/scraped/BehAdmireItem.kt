package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class BehAdmireItem : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("admire_item_sound")
    @Expose
    var admireItemSound: String? = null
        
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

    @SerializedName("on_admire_item_start")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var onAdmireItemStartData: OnAdmireItemStart? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onAdmireItemStart {
     *     event = admire_item_started_event
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onAdmireItemStart(value: OnAdmireItemStart.() -> Unit) {
        onAdmireItemStartData = (onAdmireItemStartData ?: OnAdmireItemStart()).apply(value)
    }

    @SerializedName("on_admire_item_stop")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var onAdmireItemStopData: OnAdmireItemStop? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onAdmireItemStop {
     *     event = admire_item_stopped_event
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onAdmireItemStop(value: OnAdmireItemStop.() -> Unit) {
        onAdmireItemStopData = (onAdmireItemStopData ?: OnAdmireItemStop()).apply(value)
    }

    class SoundInterval : MonsteraRawFile() {
        @SerializedName("range_min")
        @Expose
        var rangeMin: Number? = null

        @SerializedName("range_max")
        @Expose
        var rangeMax: Number? = null
    }

    class OnAdmireItemStart : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null
            
        @SerializedName("target")
        @Expose
        var target: Subject? = null
    }

    class OnAdmireItemStop : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null
            
        @SerializedName("target")
        @Expose
        var target: Subject? = null
    }
}
