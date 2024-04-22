package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class Boostable : MonsteraRawFile() {
    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null
        
    @SerializedName("duration")
    @Expose
    var duration: Number? = null
        
    @SerializedName("boost_items")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var boostItemsData: MutableList<BoostItems>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * boostItems {
     *     item = carrotOnAStick
     *     damage = 2
     *     replaceItem = fishing_rod
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun boostItems(value: BoostItems.() -> Unit) {
        boostItemsData = (boostItemsData ?: mutableListOf()).also { it.add(BoostItems().apply(value)) }
    }

    class BoostItems : MonsteraRawFile() {
        @SerializedName("item")
        @Expose
        var item: String? = null
            
        @SerializedName("damage")
        @Expose
        var damage: Number? = null
            
        @SerializedName("replace_item")
        @Expose
        var replaceItem: String? = null
    }
}
