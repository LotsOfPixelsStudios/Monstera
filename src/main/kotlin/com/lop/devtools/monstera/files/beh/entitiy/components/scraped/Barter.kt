package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.tables.loot.BehLootTables
import com.lop.devtools.monstera.files.beh.tables.loot.BehLootTables.Companion.resolveRelative
import com.lop.devtools.monstera.getMonsteraLogger

class Barter : MonsteraRawFile() {

    @SerializedName("barter_table")
    @Expose
    var barterTable: String? = null

    fun table(tableName: String, data: BehLootTables.() -> Unit) {
        val lootTables = BehLootTables().apply(data)
        lootTables.debug(tableName)
        val target = BehLootTables.Entity(lootTables).build(tableName)
        target.fold({
            barterTable = resolveRelative(it)
        }, {
            getMonsteraLogger(this.javaClass.name).warn("Equipment table not added!")
        })
    }


    @SerializedName("cooldown_after_being_attacked")
    @Expose
    var cooldownAfterBeingAttacked: Number? = null
}