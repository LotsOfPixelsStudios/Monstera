@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.lop.devtools.monstera.files.beh.tables.trading

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.api.MonsteraBuildableFile
import com.lop.devtools.monstera.files.MonsteraBuilder
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.tables.shared.BehTableFun
import com.lop.devtools.monstera.getMonsteraLogger
import java.lang.Error
import java.nio.file.Path

class BehEconomyTrades: MonsteraBuildableFile, MonsteraRawFile() {
    companion object {
        fun resolveRelative(path: Path): String {
            val sub = path.toString().split("trading").last().replace("\\", "/")
            return "trading$sub"
        }
    }

    override fun build(filename: String, path: Path?, version: String?): Result<Path> {
        val sanFile = filename
            .removeSuffix(".json")
            .replace("-", "_")
            .replace(" ", "_")
        val selPath = path ?: Addon.active?.config?.paths?.behTrading ?: run {
            getMonsteraLogger(this.javaClass.name).error("Could not Resolve a path for tade table file '$sanFile' as no addon was initialized!")
            return Result.failure(Error("Could not Resolve a path for tade table file '$sanFile' as no addon was initialized!"))
        }

        val target = MonsteraBuilder.buildTo(
            selPath,
            "$sanFile.json",
            this
        )
        return Result.success(target)
    }

    @SerializedName("tiers")
    @Expose
    var tierData: MutableList<Tier>? = null
        @MonsteraBuildSetter set

    /**
     * adds a trading tier, can be called multiple times
     *
     * note: group and trade can't exit both in a tier, pick group if you want multiple trades in one tier
     * ```
     * tier {
     *     group { }
     * }
     * tier {
     *     totalExpRequired = 28
     *     trade { }
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun tier(data: Tier.() -> Unit) {
        tierData = (tierData ?: mutableListOf()).apply { add(Tier().apply(data)) }
    }

    open class Tier {
        @SerializedName("groups")
        @Expose
        var groupsData: MutableList<Group>? = null
            @MonsteraBuildSetter set

        /**
         * adds a group in the current tier,
         * a group lets you pick randomly between multiple trades in a tier or
         *
         * ```
         * group {
         *     numToSelect = 2  //picks 2 trades randomly form the defined trades below
         *
         *     trade { ... }
         *     trade { ... }
         *     trade { ... }
         * }
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun group(data: Group.() -> Unit) {
            groupsData = (groupsData ?: mutableListOf()).apply { add(Group().apply(data)) }
        }

        @SerializedName("total_exp_required")
        @Expose
        var totalExpRequired: Number? = null

        @SerializedName("trades")
        @Expose
        var tradesData: MutableList<Trade>? = null
            @MonsteraBuildSetter set

        /**
         * skips the group and applies a trade for that tier, can be called multiple times
         *
         * ```
         * trade {
         *     want { }
         *     give { }
         *     maxUses = 7
         *     traderExp = 3
         *     rewardExp = true
         * }
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun trade(data: Trade.() -> Unit) {
            tradesData = (tradesData ?: mutableListOf()).apply { add(Trade().apply(data)) }
        }
    }

    open class Group {
        @SerializedName("num_to_select")
        @Expose
        var numToSelect: Number? = null

        @SerializedName("trades")
        @Expose
        var tradesData: MutableList<Trade>? = null
            @MonsteraBuildSetter set

        /**
         * a trade for that group, can be called multiple times
         *
         * ```
         * trade {
         *     want { }
         *     give { }
         *     maxUses = 7
         *     traderExp = 3
         *     rewardExp = true
         * }
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun trade(data: Trade.() -> Unit) {
            tradesData = (tradesData ?: mutableListOf()).apply { add(Trade().apply(data)) }
        }
    }

    open class Trade {
        @SerializedName("wants")
        @Expose
        var wantsData: MutableList<Want>? = null
            @MonsteraBuildSetter set

        /**
         * declare items the trader wants, can be called multiple times (if it makes sense) like the librarian a book
         * and emeralds for an enchanted book
         *
         * ```
         * want {
         *     item = "minecraft:book"
         * }
         * want {
         *     item = "minecraft:emerald"
         *     quantity(11, 52)
         *     priceMultiplier = 0.5
         * }
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun want(data: Want.() -> Unit) {
            wantsData = (wantsData ?: mutableListOf()).apply { add(Want().apply(data)) }
        }

        @SerializedName("gives")
        @Expose
        var givesData: MutableList<Give>? = null
            @MonsteraBuildSetter set

        /**
         * declare items the trader gives in return, can be called multiple times (if it makes sense)
         *
         * ```
         * give {
         *     item = "minecraft:enchanted_book"
         *     functions {
         *         functionEnchantBook(2, 3, 4, 2)
         *     }
         * }
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun give(data: Give.() -> Unit) {
            givesData = (givesData ?: mutableListOf()).apply { add(Give().apply(data)) }
        }

        @SerializedName("max_uses")
        @Expose
        var maxUses: Number? = null

        @SerializedName("trader_exp")
        @Expose
        var traderExp: Number? = null

        /**
         * if the player should receive xp orbs when trading
         */
        @SerializedName("reward_exp")
        @Expose
        var rewardExp: Boolean? = null
    }

    open class Want {
        @SerializedName("item")
        @Expose
        var item: String? = null

        @SerializedName("quantity")
        @Expose
        var quantityData: Any? = null
            @MonsteraBuildSetter set

        @OptIn(MonsteraBuildSetter::class)
        fun quantity(value: Int) {
            quantityData = value
        }

        @OptIn(MonsteraBuildSetter::class)
        fun quantity(min: Int, max: Int) {
            quantityData = mutableMapOf("min" to min, "max" to max)
        }

        @SerializedName("price_multiplier")
        @Expose
        var priceMultiplier: Number? = null
    }

    open class Give {
        @SerializedName("item")
        @Expose
        var item: String? = null

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