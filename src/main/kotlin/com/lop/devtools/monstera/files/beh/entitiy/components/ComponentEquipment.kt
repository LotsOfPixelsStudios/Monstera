package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.files.beh.item.comp.Slot
import com.lop.devtools.monstera.files.beh.tables.loot.BehLootTables

class ComponentEquipment {
    val general = mutableMapOf<String, Any>()
    val slots = arrayListOf<Any>()

    var table: String? = null

    /**
     * 0..1
     *
     * @param tableName the name of the table, likely just the entity name
     */
    fun table(addon: Addon, tableName: String, table: BehLootTables.() -> Unit) {
        general["table"] = "loot_tables/entities/$tableName.json"
        BehLootTables().apply(table).unsafe.build(tableName, addon.config.paths.lootTableEntity)
    }

    /**
     * 0..n
     */
    fun slotDropChance(slot: Slot? = null, dropChance: Float? = null) {
        val data = mutableMapOf<String, Any>()
        if(slot != null)
            data["slot"] = slot.toString()
        if(dropChance != null)
            data["drop_chance"] = dropChance
        slots.add(data)
    }

    fun getData(): MutableMap<String, Any> {
        table?.let { general["table"] = it }
        if(slots.isNotEmpty())
            general["slot_drop_chance"] = slots
        return general
    }
}