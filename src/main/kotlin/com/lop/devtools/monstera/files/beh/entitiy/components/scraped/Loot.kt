package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.tables.loot.BehLootTables
import com.lop.devtools.monstera.getMonsteraLogger

class Loot : MonsteraRawFile() {
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
}