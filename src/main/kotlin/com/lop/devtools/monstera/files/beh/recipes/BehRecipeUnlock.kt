package com.lop.devtools.monstera.files.beh.recipes

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeList
import com.lop.devtools.monstera.addon.molang.Molang

class BehRecipeUnlock : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeList {
        val general = mutableListOf<Any>()

        override fun getData(): List<Any> {
            return general
        }
    }

    /**
     * @param identifier tells the recipe what item the player needs in their inventory in order for this recipe to be
     * unlocked
     */
    fun item(identifier: String) {
        unsafe.general.add(mutableMapOf("item" to identifier))
    }

    /**
     * @param identifier tells the recipe what item the player needs in their inventory in order for this recipe to be
     * unlocked
     * @param count the count of the item the player needs in their inventory
     */
    fun item(identifier: String, count: Int) {
        unsafe.general.add(
            mutableMapOf(
                "item" to identifier,
                "count" to count
            )
        )
    }

    /**
     * @param identifier tells the recipe what item the player needs in their inventory in order for this recipe to be
     * unlocked
     * @param count the count of the item the player needs in their inventory
     * @param data the data id of the item, can also be applied to the identifier like 'minecraft:planks:2'
     */
    fun item(identifier: String, count: Int, data: Int) {
        unsafe.general.add(
            mutableMapOf(
                "item" to identifier,
                "data" to data,
                "count" to count
            )
        )
    }

    /**
     * @param identifier tells the recipe what item the player needs in their inventory in order for this recipe to be
     * unlocked
     * @param count the count of the item the player needs in their inventory
     * @param data the data id of the item, can also be applied to the identifier like 'minecraft:planks:2' as a query
     * only known query is 'q.get_actor_info_id('minecraft:chicken')'
     */
    fun item(identifier: String, count: Int, data: String) {
        unsafe.general.add(
            mutableMapOf(
                "item" to identifier,
                "data" to data,
                "count" to count
            )
        )
    }

    /**
     * @param identifier tells the recipe what item the player needs in their inventory in order for this recipe to be
     * unlocked
     * @param count the count of the item the player needs in their inventory
     * @param data the data id of the item, can also be applied to the identifier like 'minecraft:planks:2' as a query
     * only known query is 'q.get_actor_info_id('minecraft:chicken')'
     */
    fun item(identifier: String, count: Int, data: Molang) {
        unsafe.general.add(
            mutableMapOf(
                "item" to identifier,
                "data" to data,
                "count" to count
            )
        )
    }

    /**
     * @param event is used to determine what event unlocks this recipe. "PlayerInWater" will unlock this recipe when
     * the player enters water. This is also the only known context for recipes.
     */
    fun context(event: String = "PlayerInWater") {
        unsafe.general.add(mutableMapOf("context" to event))
    }
}