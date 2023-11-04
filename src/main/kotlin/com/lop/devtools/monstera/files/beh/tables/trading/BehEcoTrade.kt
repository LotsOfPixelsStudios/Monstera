package com.lop.devtools.monstera.files.beh.tables.trading

import com.lop.devtools.monstera.files.beh.tables.shared.BehTableFun

@Suppress("MemberVisibilityCanBePrivate")
class BehEcoTrade {
    var general = mutableMapOf<String,Any>()
    var wantsList = ArrayList<Any>()
    var giveList = ArrayList<Any>()

    var maxUses: Int? = null
    var rewardExp: Boolean? = null

    /**
     * 1
     */
    fun wants(item: String, quantity: Int) {
        wantsList.add(
            mutableMapOf(
                "item" to item,
                "quantity" to quantity
            )
        )
    }

    /**
     * 1
     *
     * @sample sampleGives
     */
    fun gives(item: String, quantity: Int = 1, functions: BehTableFun.() -> Unit) {
        val behEcoFun = BehTableFun().apply(functions).getData()

        if(behEcoFun.size == 0) {
            giveList.add(
                mutableMapOf(
                    "item" to item,
                    "quantity" to quantity
                )
            )
        } else {
            giveList.add(
                mutableMapOf(
                    "item" to item,
                    "quantity" to quantity,
                    "functions" to behEcoFun
                )
            )
        }
    }

    fun getData(): MutableMap<String, Any> {
        maxUses?.let { general["max_uses"] = it }
        rewardExp?.let { general["reward_exp"] = it }
        general.apply {
            put("wants", wantsList)
            put("gives", giveList)
        }
        return general
    }

    private fun sampleGives() {
        gives("spawn_egg", 1) {
            functionEnchantBook(2, 3, 4, 2)
            functionEnchantRandomGear(0.3f)
            functionEnchantRandomly(true)
            functionEnchantWithLevels(true, 1, 3)
            functionSpecificEnchants {
                enchant("unbreaking", 2)
                enchant("looting", 4)
            }
            functionLootingEnchant(1, 2)
            functionRandomBlockState("wood_type", valueMax = 1, valueMin = 2)
            functionRandomAuxValue(2, 3)
            functionSetActorId("minecraft:zombie")
            functionSetBannerDetails(3)
            functionSetBookContents("12Build", "Lore", arrayListOf("bla", "bla"))
            functionSetCount(2)
            functionSetCounts(1, 3)
            functionSetDamage(4.5f)
            functionSetDamages(0.5f, 1f)
            functionSetData(3)
            functionSetDataFromColorIndex()
            functionSetLore(arrayListOf("bla", "bla"))
            functionSetName("test")
            functionFillContainer("loot_tables/chests/simple_dungeon.json")
            functionExplorationMap("fortress")
            functionFurnaceSmelt()
        }
    }
}