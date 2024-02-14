package com.lop.devtools.monstera.files.beh.item.comp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemWearable {
    @SerializedName("dispensable")
    @Expose
    var dispensable: Boolean? = null

    @SerializedName("slot")
    @Expose
    var slot: Slot? = null

    enum class Slot(private val s: String) {
        MAIN_HAND("slot.weapon.mainhand"),
        OFF_HAND("slot.weapon.offhand"),
        ARMOR_HEAD("slot.armor.head"),
        ARMOR_CHEST("slot.armor.chest"),
        ARMOR_LEGS("slot.armor.legs"),
        ARMOR_FEET("slot.armor.feet"),
        HOT_BAR("slot.hotbar"),
        INVENTORY("slot.inventory"),
        ENDER_CHEST("slot.enderchest"),
        SADDLE("slot.saddle"),
        ARMOR("slot.armor"),
        CHEST("slot.chest"),
        EQUIPPABLE("slot.equippable");

        override fun toString(): String {
            return s
        }
    }
}