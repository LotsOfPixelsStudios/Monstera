package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class Ageable : MonsteraRawFile() {

    @SerializedName("duration")
    @Expose
    var duration: Number? = null

    @SerializedName("feed_items")
    @Expose
    var feedItems: String? = null

    @SerializedName("transform_to_item")
    @Expose
    var transformToItem: String? = null

    @SerializedName("grow_up")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var growUpData: GrowUp? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * growUp {
     *     event = minecraft:ageable_grow_up
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun growUp(value: GrowUp.() -> Unit) {
        growUpData = (growUpData ?: GrowUp()).apply(value)
    }

    @SerializedName("drop_items")
    @Expose
    var dropItemsData: MutableList<String>? = null

    @Components.VanillaComponentMarker
    fun dropItems(vararg value: String) {
        dropItemsData = (dropItemsData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    class GrowUp : MonsteraRawFile() {

        @SerializedName("event")
        @Expose
        var event: String? = null

        @SerializedName("target")
        @Expose
        var target: Subject? = null
    }
}
