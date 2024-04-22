package com.lop.devtools.monstera.files.beh.item.comp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class ItemWearable : MonsteraRawFile() {
    @SerializedName("dispensable")
    @Expose
    var dispensable: Boolean? = null

    @SerializedName("slot")
    @Expose
    var slot: Slot? = null

    enum class Slot(private val s: String) {
        @SerializedName("slot.weapon.mainhand")
        MAIN_HAND("slot.weapon.mainhand"),

        @SerializedName("slot.weapon.offhand")
        OFF_HAND("slot.weapon.offhand"),

        @SerializedName("slot.armor.head")
        ARMOR_HEAD("slot.armor.head"),

        @SerializedName("slot.armor.chest")
        ARMOR_CHEST("slot.armor.chest"),

        @SerializedName("slot.armor.legs")
        ARMOR_LEGS("slot.armor.legs"),

        @SerializedName("slot.armor.feet")
        ARMOR_FEET("slot.armor.feet"),

        @SerializedName("slot.hotbar")
        HOT_BAR("slot.hotbar"),

        @SerializedName("slot.inventory")
        INVENTORY("slot.inventory"),

        @SerializedName("slot.enderchest")
        ENDER_CHEST("slot.enderchest"),

        @SerializedName("slot.saddle")
        SADDLE("slot.saddle"),

        @SerializedName("slot.armor")
        ARMOR("slot.armor"),

        @SerializedName("slot.chest")
        CHEST("slot.chest"),

        @SerializedName("slot.equippable")
        EQUIPPABLE("slot.equippable");

        override fun toString(): String {
            return s
        }
    }
}