package com.lop.devtools.monstera.files.beh.recipes

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class BehRecipe: MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        /**
         * access to all defined data
         */
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            identifier?.let { unsafe.general["description"] = mutableMapOf("identifier" to it) }
            tags?.let {
                val tags = arrayListOf<String>()
                it.forEach { tag -> tags.add(tag.toString().lowercase()) }
                unsafe.general["tags"] = tags
            }
            pattern?.let { unsafe.general["pattern"] = it }
            inputBrewingStand?.let { unsafe.general["input"] = it }
            reagent?.let { unsafe.general["reagent"] = it }
            return unsafe.general
        }
    }
    var identifier: String? = null
    var tags: ArrayList<RecipeTags>? = null
    var pattern: ArrayList<String>? = null
    var inputBrewingStand: String? = null

    var reagent: String? = null

    fun description(identifier: String) {
        unsafe.general.apply { put("identifier", identifier) }
    }

    /**
     * 1
     *
     * only used for tag: crafting_table
     */
    fun keys(setting: BehRecipeKey.() -> Unit) {
        val behRecipeKey = BehRecipeKey().apply { setting(this) }

        unsafe.general.apply {
            put("key", behRecipeKey.unsafe.getData())
        }
    }

    /**
     * 1
     *
     * only used for tag: crafting_table
     */
    fun result(item: String, data: Int = 0) {
        unsafe.general.apply {
            put(
                "result", mutableMapOf(
                    "item" to item,
                    "data" to data
                )
            )
        }
    }

    /**
     * 1
     *
     * only used by tags: "furnace", "smoker", "campfire", "soul_campfire"
     */
    fun inputFurnace(item: String, data: Int = 0, count: Int) {
        unsafe.general.apply {
            put(
                "input", mutableMapOf(
                    "item" to item,
                    "data" to data,
                    "count" to count
                )
            )
        }
    }

    /**
     * 1
     *
     * only used by tags: "furnace", "smoker", "campfire", "soul_campfire" and "brewing_stand"
     */
    fun output(item: String, count: Int = 1) {
        unsafe.general.apply {
            put(
                "output", mutableMapOf(
                    "item" to item,
                    "count" to count
                )
            )
        }
    }
}

enum class RecipeTags {
    FURNACE,
    BLAST_FURNACE,
    SMOKER,
    CAMPFIRE,
    SOUL_CAMPFIRE,
    CRAFTING_TABLE,
    STONECUTTER,
    SMITHING_TABLE,
    BREWING_STAND
}