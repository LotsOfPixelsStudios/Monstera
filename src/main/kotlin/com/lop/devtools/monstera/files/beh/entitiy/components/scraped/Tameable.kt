package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class Tameable : MonsteraRawFile() {
    @SerializedName("probability")
    @Expose
    var probability: Number? = null
        
    @SerializedName("tame_items")
    @Expose
    var tameItemsData: MutableList<String>? = null
        
    fun tameItems(vararg value: String) {
        tameItemsData = (tameItemsData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("tame_event")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var tameEventData: TameEvent? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * tameEvent {
     *     event = minecraft:on_tame
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun tameEvent(value: TameEvent.() -> Unit) {
        tameEventData = (tameEventData ?: TameEvent()).apply(value)
    }

    class TameEvent : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null
            
        @SerializedName("target")
        @Expose
        var target: Subject? = null
    }
}
