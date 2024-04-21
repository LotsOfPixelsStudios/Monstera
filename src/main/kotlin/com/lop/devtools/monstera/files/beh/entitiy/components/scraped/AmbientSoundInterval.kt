package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class AmbientSoundInterval : MonsteraRawFile() {
    @SerializedName("value")
    @Expose
    var value: Number? = null

    @SerializedName("range")
    @Expose
    var range: Number? = null

    @SerializedName("event_name")
    @Expose
    var eventName: String? = null

    @SerializedName("event_names")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var eventNamesData: MutableList<EventNames>? = null
        @MonsteraBuildSetter set


    /**
     *
     * ```
     * eventNames {
     *     eventName = ambient.tame
     *     condition = query.is_using_item
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun eventNames(value: EventNames.() -> Unit) {
        eventNamesData = (eventNamesData ?: mutableListOf()).also { it.add(EventNames().apply(value)) }
    }

    class EventNames : MonsteraRawFile() {
        @SerializedName("event_name")
        @Expose
        var eventName: String? = null

        @SerializedName("condition")
        @Expose
        var condition: String? = null
    }
}
