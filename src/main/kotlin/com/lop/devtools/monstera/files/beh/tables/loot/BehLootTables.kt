@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.lop.devtools.monstera.files.beh.tables.loot

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.api.MonsteraBuildableFile
import com.lop.devtools.monstera.files.MonsteraBuilder
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.tables.shared.BehTableFun
import com.lop.devtools.monstera.files.sanetiseFilename
import com.lop.devtools.monstera.getMonsteraLogger
import java.nio.file.Path

class BehLootTables: MonsteraRawFile() {
    companion object {
        fun resolveRelative(path: Path): String {
            val sub = path.toString().split("loot_tables").last().replace("\\", "/")
            return "loot_tables$sub"
        }
        private val logger = getMonsteraLogger(this::class.simpleName ?: "BehLootTables")
    }
    class Block(val data: BehLootTables) : MonsteraBuildableFile {
        override fun build(filename: String, path: Path?, version: String?): Result<Path> {
            val selPath = path ?: Addon.active?.config?.paths?.lootTableBlock ?: run {
                logger.error("Could not Resolve a path for block loot file '$filename' as no addon was initialized!")
                return Result.failure(Error("Could not Resolve a path for block loot file '$filename' as no addon was initialized!"))
            }
            val target = MonsteraBuilder.buildTo(
                selPath,
                sanetiseFilename(filename, "json"),
                data
            )
            return Result.success(target)
        }
    }

    class Entity(val data: BehLootTables) : MonsteraBuildableFile {
        override fun build(filename: String, path: Path?, version: String?): Result<Path> {
            val sanFile = filename
                .removeSuffix(".json")
                .replace("-", "_")
                .replace(" ", "_")
            val selPath = path ?: Addon.active?.config?.paths?.lootTableEntity ?: run {
                logger.error("Could not Resolve a path for entity loot file '$sanFile' as no addon was initialized!")
                return Result.failure(Error("Could not Resolve a path for entity loot file '$sanFile' as no addon was initialized!"))
            }

            val target = MonsteraBuilder.buildTo(
                selPath,
                "$sanFile.json",
                data
            )
            return Result.success(target)
        }
    }

    /**
     * returns true if no pool is defined
     */
    fun isEmpty() = poolsData.isNullOrEmpty()

    @SerializedName("pools")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
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

    open class Pool : MonsteraRawFile() {
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
        @JsonAdapter(MonsteraListFileTypeAdapter::class)
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

    open class Entry : MonsteraRawFile() {
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
         * in older monstera version the identifier field was known as "name"
         */
        @Deprecated("", ReplaceWith("identifier"))
        var name: String? = null

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