package com.lop.devtools.monstera.files.beh.item.comp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemEnchantable {
    @SerializedName("value")
    @Expose
    var value: Number? = null

    @SerializedName("slot")
    @Expose
    var slot: EnchantableSlot? = null
}

enum class EnchantableSlot {
    ARMOR_FEET,
    ARMOR_TORSO,
    ARMOR_HEAD,
    ARMOR_LEGS,
    AXE,
    BOW,
    COSMETIC_HEAD,
    CROSSBOW,
    ELYTRA,
    FISHING_ROD,
    FLINTSTEEL,
    HOE,
    PICKAXE,
    SHEARS,
    SHIELD,
    SHOVEL,
    SWORD,
    ALL;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}