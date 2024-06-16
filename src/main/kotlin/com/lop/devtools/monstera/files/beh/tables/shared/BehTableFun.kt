package com.lop.devtools.monstera.files.beh.tables.shared

class BehTableFun {
    var functionData = ArrayList<Any>()

    /**
     * 0..*
     *
     * only trading
     *
     * see https://compass.minecraft.partners/hc/en-us/articles/360044956673-Loot-and-Trade-Table-Functions#enchant_book_for_trading
     */
    fun functionEnchantBook(baseCost: Int, baseRandomCost: Int, perLevelRandomCost: Int, perLevelCost: Int) {
        functionData.add(
            mutableMapOf(
                "function" to "enchant_book_for_trading",
                "base_cost" to baseCost,
                "base_random_cost" to baseRandomCost,
                "per_level_random_cost" to perLevelRandomCost,
                "per_level_cost" to perLevelCost
            )
        )
    }

    /**
     * 0..*
     *
     * see https://compass.minecraft.partners/hc/en-us/articles/360044956673-Loot-and-Trade-Table-Functions#enchant_random_gear
     */
    fun functionEnchantRandomGear(chance: Float) {
        functionData.add(
            mutableMapOf(
                "function" to "enchant_random_gear",
                "chance" to chance
            )
        )
    }

    /**
     * 0..*
     *
     * see https://compass.minecraft.partners/hc/en-us/articles/360044956673-Loot-and-Trade-Table-Functions#enchant_randomly
     */
    fun functionEnchantRandomly(treasure: Boolean = true) {
        functionData.add(
            mutableMapOf(
                "function" to "enchant_randomly",
                "treasure" to treasure
            )
        )
    }

    /**
     * 0..*
     *
     * see https://compass.minecraft.partners/hc/en-us/articles/360044956673-Loot-and-Trade-Table-Functions#enchant_with_levels
     */
    fun functionEnchantWithLevels(treasure: Boolean = false, lvlMin: Int, lvlMax: Int) {
        functionData.add(
            mutableMapOf(
                "function" to "enchant_with_levels",
                "treasure" to treasure,
                "levels" to mutableMapOf(
                    "min" to lvlMin,
                    "max" to lvlMax
                )
            )
        )
    }

    /**
     * 0..*
     *
     * https://compass.minecraft.partners/hc/en-us/articles/360044956673-Loot-and-Trade-Table-Functions#specific_enchants
     */
    fun functionSpecificEnchants(settings: BehTableEnchants.() -> Unit) {
        val behEcoEnchants = BehTableEnchants().apply { settings(this) }
        functionData.add(
            mutableMapOf(
                "function" to "specific_enchants",
                "enchants" to behEcoEnchants.getData()
            )
        )
    }

    /**
     * 0..*
     *
     * loot table only
     *
     * https://compass.minecraft.partners/hc/en-us/articles/360044956673-Loot-and-Trade-Table-Functions#looting_enchant
     * @param countMin:
     * @param countMax: usually between 1 and 3
     */
    fun functionLootingEnchant(countMin: Int, countMax: Int) {
        functionData.add(
            mutableMapOf(
                "function" to "looting_enchant",
                "count" to mutableMapOf(
                    "min" to countMin,
                    "max" to countMax
                )
            )
        )
    }

    /**
     * 0..*
     *
     * https://compass.minecraft.partners/hc/en-us/articles/360044956673-Loot-and-Trade-Table-Functions#random_block_state
     *
     * @param blockState, ex. stone_type => stone (0), granite (1), polished granite (2), diorite (3), polished diorite (4), or andesite (5).
     */
    fun functionRandomBlockState(blockState: String = "stone_type", valueMin: Int, valueMax: Int) {
        functionData.add(
            mutableMapOf(
                "function" to "random_block_state",
                "block_state" to blockState,
                "values" to mutableMapOf(
                    "min" to valueMin,
                    "max" to valueMax
                )
            )
        )
    }

    /**
     * 0..*
     *
     * https://compass.minecraft.partners/hc/en-us/articles/360044956673-Loot-and-Trade-Table-Functions#random_aux_value
     */
    fun functionRandomAuxValue(valueMin: Int, valueMax: Int) {
        functionData.add(
            mutableMapOf(
                "function" to "random_aux_value",
                "values" to mutableMapOf(
                    "min" to valueMin,
                    "max" to valueMax
                )
            )
        )
    }

    /**
     * 0..*
     *
     * https://compass.minecraft.partners/hc/en-us/articles/360044956673-Loot-and-Trade-Table-Functions#set_actor_id
     *
     * @param id: entity/item/block, if null -> spawnegg from the initiator
     */
    fun functionSetActorId(id: String? = null) {
        if (id != null) {
            functionData.add(
                mutableMapOf(
                    "function" to "set_actor_id",
                    "id" to id
                )
            )
        } else {
            mutableMapOf(
                "function" to "set_actor_id"
            )
        }
    }

    /**
     * 0..*
     *
     * https://compass.minecraft.partners/hc/en-us/articles/360044956673-Loot-and-Trade-Table-Functions#set_banner_details
     */
    fun functionSetBannerDetails(type: Int) {
        functionData.add(
            mutableMapOf(
                "function" to "set_banner_details",
                "type" to type
            )
        )
    }

    /**
     * 0..*
     *
     * https://compass.minecraft.partners/hc/en-us/articles/360044956673-Loot-and-Trade-Table-Functions#set_book_contents
     */
    fun functionSetBookContents(author: String, title: String, pages: ArrayList<String>) {
        functionData.add(
            mutableMapOf(
                "function" to "set_book_contents",
                "author" to author,
                "title" to title,
                "pages" to pages
            )
        )
    }

    /**
     * 0..1
     *
     * https://compass.minecraft.partners/hc/en-us/articles/360044956673-Loot-and-Trade-Table-Functions#set_count
     * Sets the quantity of items returned
     */
    fun functionSetCount(count: Int) {
        functionData.add(
            mutableMapOf(
                "function" to "set_count",
                "count" to count
            )
        )
    }

    /**
     * 0..1
     *
     * https://compass.minecraft.partners/hc/en-us/articles/360044956673-Loot-and-Trade-Table-Functions#set_count
     * Sets the quantity of items returned
     */
    fun functionSetCounts(countMin: Int, countMax: Int) {
        functionData.add(
            mutableMapOf(
                "function" to "set_count",
                "count" to mutableMapOf(
                    "min" to countMin,
                    "max" to countMax
                )
            )
        )
    }

    /**
     * 0..*
     *
     * https://compass.minecraft.partners/hc/en-us/articles/360044956673-Loot-and-Trade-Table-Functions#set_damage
     */
    fun functionSetDamage(damage: Float) {
        functionData.add(
            mutableMapOf(
                "function" to "set_damage",
                "damage" to damage
            )
        )
    }

    /**
     * 0..*
     *
     * https://compass.minecraft.partners/hc/en-us/articles/360044956673-Loot-and-Trade-Table-Functions#set_damage
     */
    fun functionSetDamages(damageMin: Float, damageMax: Float) {
        functionData.add(
            mutableMapOf(
                "function" to "set_damage",
                "damage" to mutableMapOf(
                    "min" to damageMin,
                    "max" to damageMax
                )
            )
        )
    }

    /**
     * 0..*
     *
     * https://compass.minecraft.partners/hc/en-us/articles/360044956673-Loot-and-Trade-Table-Functions#set_data
     */
    fun functionSetData(data: Int) {
        functionData.add(
            mutableMapOf(
                "function" to "set_data",
                "data" to data
            )
        )
    }

    /**
     * 0..1
     *
     * https://compass.minecraft.partners/hc/en-us/articles/360044956673-Loot-and-Trade-Table-Functions#set_data_from_color_index
     */
    fun functionSetDataFromColorIndex() {
        functionData.add(
            mutableMapOf(
                "function" to "set_data_from_color_index"
            )
        )
    }

    /**
     * 0..1
     *
     * https://compass.minecraft.partners/hc/en-us/articles/360044956673-Loot-and-Trade-Table-Functions#set_lore
     */
    fun functionSetLore(lore: ArrayList<String>) {
        functionData.add(
            mutableMapOf(
                "function" to "set_lore",
                "lore" to lore
            )
        )
    }

    /**
     * 0..1
     *
     * https://compass.minecraft.partners/hc/en-us/articles/360044956673-Loot-and-Trade-Table-Functions#set_name
     */
    fun functionSetName(name: String) {
        functionData.add(
            mutableMapOf(
                "function" to "set_name",
                "name" to name
            )
        )
    }

    /**
     * 0..1
     *
     * https://compass.minecraft.partners/hc/en-us/articles/360044956673-Loot-and-Trade-Table-Functions#fill_container
     *
     * This function allows you to define the loot table for a chest. When the item is generated and the player places it, it will be full of the contents defined inside the referenced loot table.
     * @param lootTable: path to LootTable like: "loot_tables/chests/simple_dungeon.json"
     */
    fun functionFillContainer(lootTable: String) {
        functionData.add(
            mutableMapOf(
                "function" to "fill_container",
                "lootTable" to lootTable
            )
        )
    }

    /**
     * 0..1
     *
     * https://compass.minecraft.partners/hc/en-us/articles/360044956673-Loot-and-Trade-Table-Functions#exploration_map
     *
     * @param destination: endcity fortress mineshaft monument ruins stronghold temple village mansion shipwreck buriedtreasure pillageroutpost todo: make to Enum
     */
    fun functionExplorationMap(destination: String) {
        functionData.add(
            mutableMapOf(
                "function" to "exploration_map",
                "destination" to destination
            )
        )
    }

    /**
     * 0..1
     * loot table only
     *
     * https://compass.minecraft.partners/hc/en-us/articles/360044956673-Loot-and-Trade-Table-Functions#furnace_smelt
     */
    fun functionFurnaceSmelt() {
        functionData.add(
            mutableMapOf(
                "function" to "furnace_smelt"
            )
        )
    }

    class BehTableEnchants {
        var general = ArrayList<Any>()

        /**
         * 1..*
         *
         * @param id: enchant name like knockback, unbreaking
         * @param level: usually form 1 to 5
         */
        fun enchant(id: String, level: Int) {
            general.add(
                mutableMapOf(
                    "id" to id,
                    "level" to level
                )
            )
        }

        fun getData(): ArrayList<Any> {
            return general
        }
    }
}