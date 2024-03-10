package com.lop.devtools.monstera.files.beh.tables.loot

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.api.MonsteraBuildableFile
import com.lop.devtools.monstera.files.MonsteraBuilder
import com.lop.devtools.monstera.files.beh.tables.shared.BehTableFun
import com.lop.devtools.monstera.getMonsteraLogger
import java.nio.file.Path

class BehLootTables {
    class Block(val data: BehLootTables) : MonsteraBuildableFile {
        override fun build(filename: String, path: Path?, version: String?) {
            val sanFile = filename
                .removeSuffix(".json")
                .replace("-", "_")
                .replace(" ", "_")
            val selPath = path ?: Addon.active?.config?.paths?.lootTableBlock ?: run {
                getMonsteraLogger(this.javaClass.name).error("Could not Resolve a path for block loot file '$sanFile' as no addon was initialized!")
                return
            }

            MonsteraBuilder.buildTo(
                selPath,
                "$sanFile.json",
                data
            )
        }
    }

    class Entity(val data: BehLootTables) : MonsteraBuildableFile {
        override fun build(filename: String, path: Path?, version: String?) {
            val sanFile = filename
                .removeSuffix(".json")
                .replace("-", "_")
                .replace(" ", "_")
            val selPath = path ?: Addon.active?.config?.paths?.lootTableEntity ?: run {
                getMonsteraLogger(this.javaClass.name).error("Could not Resolve a path for entity loot file '$sanFile' as no addon was initialized!")
                return
            }

            MonsteraBuilder.buildTo(
                selPath,
                "$sanFile.json",
                data
            )
        }
    }

    /**
     * returns true if no pool is defined
     */
    fun isEmpty() = poolsData.isNullOrEmpty()


    @SerializedName("pools")
    @Expose
    var poolsData: MutableList<Pool>? = null
        @MonsteraBuildSetter set

    /**
     * add a pool, can be called multiple times
     *
     * ```
     * pool {
     *     rolls(2)
     *     //or
     *     rolls(1, 2)
     *
     *     entry { }
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun pool(data: Pool.() -> Unit) {
        poolsData = (poolsData ?: mutableListOf()).apply { add(Pool().apply(data)) }
    }

    class Pool {
        @SerializedName("rolls")
        @Expose
        var rollsData: Any? = null
            @MonsteraBuildSetter set

        @OptIn(MonsteraBuildSetter::class)
        fun rolls(number: Int) {
            rollsData = number
        }

        @OptIn(MonsteraBuildSetter::class)
        fun rolls(min: Int, max: Int) {
            rollsData = Rolls().apply {
                this.min = min
                this.max = max
            }
        }

        @SerializedName("entries")
        @Expose
        var entryData: MutableList<Entry>? = null
            @MonsteraBuildSetter set

        /**
         * can be called multiple times
         *
         * ```
         * entry {
         *     type = "item" //or block
         *     identifier = "minecraft:golden_apple"    //or minecraft:spawn_egg
         *     weight = 20
         *     functions { }
         * }
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun entry(data: Entry.() -> Unit) {
            entryData = (entryData ?: mutableListOf()).apply { add(Entry().apply(data)) }
        }
    }

    class Rolls {
        @SerializedName("min")
        @Expose
        var min: Int? = null

        @SerializedName("max")
        @Expose
        var max: Int? = null
    }

    class Entry {
        @SerializedName("type")
        @Expose
        var type: String? = null

        @SerializedName("name")
        @Expose
        var identifier: String? = null

        @SerializedName("weight")
        @Expose
        var weight: Int? = null

        @SerializedName("functions")
        @Expose
        var functionData: MutableList<Any>? = null
            @MonsteraBuildSetter set

        /**
         * ```
         * functions {
         *     functionEnchantBook(2, 3, 4, 2)
         *     functionEnchantRandomGear(0.2f)
         *     functionEnchantRandomly(true)
         *     functionEnchantWithLevels(true, 1, 3)
         *     functionSpecificEnchants {
         *         enchant("unbreaking", 2)
         *         enchant("looting", 3)
         *     }
         *     functionLootingEnchant(1, 2)
         *     functionRandomBlockState("wood_type", valueMax = 1, valueMin = 2)
         *     functionRandomAuxValue(2, 3)
         *     functionSetActorId("minecraft:zombie")
         *     functionSetBannerDetails(2)
         *     functionSetBookContents("12Build", "Lore", arrayListOf("bla", "bla"))
         *     functionSetCount(2)
         *     functionSetCounts(1, 2)
         *     functionSetDamage(4.5f)
         *     functionSetDamages(0.5f, 1f)
         *     functionSetData(2)
         *     functionSetDataFromColorIndex()
         *     functionSetLore(arrayListOf("bla", "bla"))
         *     functionSetName("test")
         *     functionFillContainer("loot_tables/chests/simple_dungeon.json")
         *     functionExplorationMap("fortress")
         *     functionFurnaceSmelt()
         * }
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun functions(data: BehTableFun.() -> Unit) {
            functionData = (functionData ?: mutableListOf()).apply { addAll(BehTableFun().apply(data).functionData) }
        }
    }
}