package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class EquipItem : MonsteraRawFile() {
    @SerializedName("excluded_items")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var excludedItemsData: MutableList<ExcludedItems>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * excludedItems {
     *     item = minecraft:banner:15
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun excludedItems(value: ExcludedItems.() -> Unit) {
        excludedItemsData = (excludedItemsData ?: mutableListOf()).also { it.add(ExcludedItems().apply(value)) }
    }

    class ExcludedItems : MonsteraRawFile() {
        @SerializedName("item")
        @Expose
        var item: String? = null
    }
}
