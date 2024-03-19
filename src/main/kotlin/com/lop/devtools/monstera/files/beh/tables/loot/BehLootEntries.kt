package com.lop.devtools.monstera.files.beh.tables.loot

import com.lop.devtools.monstera.files.beh.tables.shared.BehTableFun

class BehLootEntries {
    var general = ArrayList<Any>()

    /**
     * 1..*
     *
     * @param type item or block
     * @param name for example "minecraft:spawn_egg"
     * @param weight probability the entry is selected
     * @param settings see sampleBehTableFun() in [BehTableFun]
     * @sample sampleEntry
     */
    fun entry(type: String = "item", name: String = "minecraft:spawn_egg", weight: Int = 1, settings: BehTableFun.() -> Unit) {
        val behTableFun = BehTableFun().apply { settings(this) }
        val behEcoFunData = behTableFun.getData()

        if(behEcoFunData.size == 0) {
            general.add(
                mutableMapOf(
                    "type" to type,
                    "name" to name,
                    "weight" to weight
                )
            )
        } else {
            general.add(
                mutableMapOf(
                    "type" to type,
                    "name" to name,
                    "weight" to weight,
                    "functions" to behEcoFunData
                )
            )
        }
    }

    fun getData(): ArrayList<Any> {
        return general
    }

    private fun sampleEntry() {
        entry {
            functionEnchantBook(2, 3, 4, 2)
            functionEnchantRandomGear(0.2f)
            functionEnchantRandomly(true)
            functionEnchantWithLevels(true, 1, 3)
            functionSpecificEnchants {
                enchant("unbreaking", 2)
                enchant("looting", 3)
            }
            functionLootingEnchant(1, 2)
            functionRandomBlockState("wood_type", valueMax = 1, valueMin = 2)
            functionRandomAuxValue(2, 3)
            functionSetActorId("minecraft:zombie")
            functionSetBannerDetails(2)
            functionSetBookContents("12Build", "Lore", arrayListOf("bla", "bla"))
            functionSetCount(2)
            functionSetCounts(1, 2)
            functionSetDamage(4.5f)
            functionSetDamages(0.5f, 1f)
            functionSetData(2)
            functionSetDataFromColorIndex()
            functionSetLore(arrayListOf("bla", "bla"))
            functionSetName("test")
            functionFillContainer("loot_tables/chests/simple_dungeon.json")
            functionExplorationMap("fortress")
            functionFurnaceSmelt()
        }
    }
}