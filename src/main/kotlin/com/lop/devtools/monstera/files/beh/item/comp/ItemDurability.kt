package com.lop.devtools.monstera.files.beh.item.comp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class ItemDurability {
    @SerializedName("damage_chance")
    @Expose
    var damageChanceData: DamageChance? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    fun damageChance(min: Number, max: Number) {
        damageChanceData = DamageChance().apply {
            this.min = min
            this.max = max
        }
    }

    @SerializedName("max_durability")
    @Expose
    var maxDurability: Number? = null

    class DamageChance {
        @SerializedName("min")
        @Expose
        var min: Number? = null

        @SerializedName("max")
        @Expose
        var max: Number? = null
    }
}