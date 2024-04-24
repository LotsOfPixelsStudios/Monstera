package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class Trusting : MonsteraRawFile() {
    @SerializedName("probability")
    @Expose
    var probability: Number? = null
        
    @SerializedName("trust_items")
    @Expose
    var trustItemsData: MutableList<String>? = null
        
    fun trustItems(vararg value: String) {
        trustItemsData = (trustItemsData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("trust_event")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var trustEventData: TrustEvent? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * trustEvent {
     *     event = minecraft:on_trust
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun trustEvent(value: TrustEvent.() -> Unit) {
        trustEventData = (trustEventData ?: TrustEvent()).apply(value)
    }

    class TrustEvent : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null
            
        @SerializedName("target")
        @Expose
        var target: Subject? = null
    }
}
