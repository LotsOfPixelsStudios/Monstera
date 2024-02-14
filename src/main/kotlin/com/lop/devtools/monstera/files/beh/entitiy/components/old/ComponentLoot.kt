package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.files.beh.tables.loot.BehLootTables

class ComponentLoot {
    val general = mutableMapOf<String, Any>()

    var table: String? = null

    fun genTable(
        tableName: String,
        addon: Addon,
        table: BehLootTables.() -> Unit
    ) {
        general["table"] = "loot_tables/entities/$tableName.json"
        BehLootTables().apply(table).unsafe.build(tableName, addon.config.paths.lootTableEntity)
    }

    fun getData(): MutableMap<String, Any> {
        table?.let { general["table"] = it }
        return general
    }
}