package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class Equippable : MonsteraRawFile() {
    @SerializedName("slots")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var slotsData: MutableList<Slots>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * slots {
     *     slot = 0
     *     item = saddle
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun slots(value: Slots.() -> Unit) {
        slotsData = (slotsData ?: mutableListOf()).also { it.add(Slots().apply(value)) }
    }

    class Slots : MonsteraRawFile() {
        @SerializedName("slot")
        @Expose
        var slot: Number? = null
            
        @SerializedName("item")
        @Expose
        var item: String? = null
            
        @SerializedName("accepted_items")
        @Expose
        var acceptedItemsData: MutableList<String>? = null
            
        fun acceptedItems(vararg value: String) {
            acceptedItemsData = (acceptedItemsData ?: mutableListOf()).also { it.addAll(value.toList()) }
        }

        @SerializedName("on_equip")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var onEquipData: OnEquip? = null
            @MonsteraBuildSetter set

        /**
         *
         * ```
         * onEquip {
         *     event = minecraft:camel_saddled
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun onEquip(value: OnEquip.() -> Unit) {
            onEquipData = (onEquipData ?: OnEquip()).apply(value)
        }

        @SerializedName("on_unequip")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var onUnequipData: OnUnequip? = null
            @MonsteraBuildSetter set
            

        /**
         *
         * ```
         * onUnequip {
         *     event = minecraft:camel_unsaddled
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun onUnequip(value: OnUnequip.() -> Unit) {
            onUnequipData = (onUnequipData ?: OnUnequip()).apply(value)
        }
    }

    class OnEquip : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null
    }

    class OnUnequip : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null
    }
}
