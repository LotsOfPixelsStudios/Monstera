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
                           @SerializedName("armor_feet")
    ARMOR_FEET,
    @SerializedName("armor_torso")
    ARMOR_TORSO,
    @SerializedName("armor_head")
    ARMOR_HEAD,
    @SerializedName("armor_legs")
    ARMOR_LEGS,
    @SerializedName("armor_axe")
    AXE,
    @SerializedName("armor_bow")
    BOW,
    @SerializedName("cosmetic_head")
    COSMETIC_HEAD,
    @SerializedName("crossbow")
    CROSSBOW,
    @SerializedName("elytra")
    ELYTRA,
    @SerializedName("fishing_rod")
    FISHING_ROD,
    @SerializedName("flintsteel")
    FLINTSTEEL,
    @SerializedName("hoe")
    HOE,
    @SerializedName("pickaxe")
    PICKAXE,
    @SerializedName("shears")
    SHEARS,
    @SerializedName("shield")
    SHIELD,
    @SerializedName("shovel")
    SHOVEL,
    @SerializedName("sword")
    SWORD,
    @SerializedName("all")
    ALL;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}