package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class BehDrinkPotion : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("speed_modifier")
    @Expose
    var speedModifier: Number? = null
        

    @SerializedName("potions")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var potionsData: MutableList<Potions>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * potions {
     *     id = 7
     *     chance = 1.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun potions(value: Potions.() -> Unit) {
        potionsData = (potionsData ?: mutableListOf()).also { it.add(Potions().apply(value)) }
    }

    class Potions : MonsteraRawFile() {
        @SerializedName("id")
        @Expose
        var id: Number? = null
            
        @SerializedName("chance")
        @Expose
        var chance: Number? = null
            
        @SerializedName("filters")
        @Expose
        var filtersData: BehEntityFilter? = null
            @MonsteraBuildSetter set

        /**
         * com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.Filters allow data objects to specify test criteria which allows their use.
         * ```
         * filters {
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun filters(value: BehEntityFilter.() -> Unit) {
            filtersData = (filtersData ?: BehEntityFilter()).apply(value)
        }
    }
}