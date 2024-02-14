package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class Equipment {
    @SerializedName("table")
    @Expose
    var table: String? = null
        

    @SerializedName("slot_drop_chance")
    @Expose
    var slotDropChanceData: MutableList<SlotDropChance>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * slotDropChance {
     *     slot = slot.weapon.offhand
     *     dropChance = 1.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun slotDropChance(value: SlotDropChance.() -> Unit) {
        slotDropChanceData = (slotDropChanceData ?: mutableListOf()).also { it.add(SlotDropChance().apply(value)) }
    }

    class SlotDropChance {
        @SerializedName("slot")
        @Expose
        var slot: String? = null
            

        @SerializedName("drop_chance")
        @Expose
        var dropChance: Number? = null
            
    }
}
