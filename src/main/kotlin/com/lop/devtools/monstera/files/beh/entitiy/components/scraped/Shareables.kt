package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class Shareables {
    @SerializedName("singular_pickup")
    @Expose
    var singularPickup: Boolean? = null
        

    @SerializedName("items")
    @Expose
    var itemsData: MutableList<Items>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * items {
     *     item = minecraft:nautilus_shell
     *     wantAmount = 1
     *     surplusAmount = 1
     *     priority = 0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun items(value: Items.() -> Unit) {
        itemsData = (itemsData ?: mutableListOf()).also { it.add(Items().apply(value)) }
    }

    @SerializedName("all_items")
    @Expose
    var allItems: Boolean? = null
        

    @SerializedName("all_items_max_amount")
    @Expose
    var allItemsMaxAmount: Number? = null
        

    class Items {
        @SerializedName("item")
        @Expose
        var item: String? = null
            

        @SerializedName("want_amount")
        @Expose
        var wantAmount: Number? = null
            

        @SerializedName("surplus_amount")
        @Expose
        var surplusAmount: Number? = null
            

        @SerializedName("priority")
        @Expose
        var priority: Number? = null
            
    }
}
