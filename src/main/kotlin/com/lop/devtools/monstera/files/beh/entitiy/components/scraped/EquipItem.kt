package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class EquipItem {
    @SerializedName("excluded_items")
    @Expose
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

    class ExcludedItems {
        @SerializedName("item")
        @Expose
        var item: String? = null
            
    }
}
