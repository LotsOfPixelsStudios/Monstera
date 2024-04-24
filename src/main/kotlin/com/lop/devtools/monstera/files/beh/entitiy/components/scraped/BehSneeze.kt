package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.entitiy.components.BehEntityTypes
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.tables.loot.BehLootTables
import com.lop.devtools.monstera.getMonsteraLogger

class BehSneeze : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null

    @SerializedName("probability")
    @Expose
    var probability: Number? = null

    @SerializedName("cooldown_time")
    @Expose
    var cooldownTime: Number? = null

    @SerializedName("within_radius")
    @Expose
    var withinRadius: Number? = null

    @SerializedName("entity_types")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var entityTypesData: MutableList<BehEntityTypes>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * entityTypes {
     *     maxDist = 10
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun entityTypes(value: BehEntityTypes.() -> Unit) {
        entityTypesData = (entityTypesData ?: mutableListOf()).also { it.add(BehEntityTypes().apply(value)) }
    }

    @SerializedName("drop_item_chance")
    @Expose
    var dropItemChance: Number? = null

    @SerializedName("loot_table")
    @Expose
    var lootTable: String? = null

    /**
     * ```
     * table("name") {
     *     pool {  }
     * }
     * ```
     */
    fun lootTable(tableName: String, data: BehLootTables.() -> Unit) {
        val lootTables = BehLootTables().apply(data)
        val target = BehLootTables.Entity(lootTables).build(tableName)
        target.fold({
            lootTable = BehLootTables.resolveRelative(it)
        }, {
            getMonsteraLogger(this::class.simpleName ?: "Entity Sneeze").warn("Equipment table not added!")
        })
    }

    @SerializedName("prepare_sound")
    @Expose
    var prepareSound: String? = null

    @SerializedName("prepare_time")
    @Expose
    var prepareTime: Number? = null

    @SerializedName("sound")
    @Expose
    var sound: String? = null

}