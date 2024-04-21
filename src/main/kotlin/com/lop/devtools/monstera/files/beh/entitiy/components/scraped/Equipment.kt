package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.tables.loot.BehLootTables
import com.lop.devtools.monstera.getMonsteraLogger

class Equipment : MonsteraRawFile() {
    @SerializedName("table")
    @Expose
    var table: String? = null

    /**
     * ```
     * table("name") {
     *     pool {  }
     * }
     * ```
     */
    fun table(tableName: String, data: BehLootTables.() -> Unit) {
        val lootTables = BehLootTables().apply(data)
        val target = BehLootTables.Entity(lootTables).build(tableName)
        target.fold({
            table = BehLootTables.resolveRelative(it)
        }, {
            getMonsteraLogger(this.javaClass.name).warn("Equipment table not added!")
        })
    }


    @SerializedName("slot_drop_chance")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
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

    class SlotDropChance : MonsteraRawFile() {
        @SerializedName("slot")
        @Expose
        var slot: String? = null
            
        @SerializedName("drop_chance")
        @Expose
        var dropChance: Number? = null
    }
}
