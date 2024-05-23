package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class Healable : MonsteraRawFile() {
    @SerializedName("items")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var itemsData: MutableList<Items>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * items {
     *     item = cactus
     *     healAmount = 2
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun items(value: Items.() -> Unit) {
        itemsData = (itemsData ?: mutableListOf()).also { it.add(Items().apply(value)) }
    }

    @SerializedName("force_use")
    @Expose
    var forceUse: Boolean? = null
        

    @SerializedName("filters")
    @Expose
    var filtersData: BehEntityFilter? = null
        @MonsteraBuildSetter set

    /**
     * com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.Filters allow data objects to specify test criteria which allows their use.
     * ```
     * filters {
     *     test = is_riding
     *     operator = !=
     *     value = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun filters(value: BehEntityFilter.() -> Unit) {
        filtersData = (filtersData ?: BehEntityFilter()).apply(value)
    }

    class Items : MonsteraRawFile() {
        @SerializedName("item")
        @Expose
        var item: String? = null
            
        @SerializedName("heal_amount")
        @Expose
        var healAmount: Number? = null
    }
}