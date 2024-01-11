package com.lop.devtools.monstera.addon.recipes

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafe
import com.lop.devtools.monstera.files.beh.recipes.BehRecipe
import com.lop.devtools.monstera.files.beh.recipes.BehRecipeUnlock
import com.lop.devtools.monstera.files.beh.recipes.RecipeTags

class CraftingRecipe: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafe {
        val rawRecipe = BehRecipe()
        val keyPattern = mutableMapOf<String, String>()

        fun isEmpty() = keyPattern.isEmpty()

        fun build(name: String, identifier: String, addon: Addon, resultItem: String = identifier) {
            rawRecipe.identifier = identifier
            rawRecipe.result(resultItem)
            rawRecipe.keys {
                keyPattern.forEach { (id, key) ->
                    key(key, id)
                }
            }
            rawRecipe.unsafe.build(name, addon.config.paths.behRecipe)
        }
    }


    /**
     * add a pattern for the crafting table to the recipe
     *
     * ```
     * craftingPattern(
     *     t("","minecraft:diamond","minecraft:diamond"),
     *     t("","minecraft:diamond",""),
     *     t("","minecraft:stick","")
     * )
     * ```
     */
    fun craftingPattern(
        t1: Triple<String, String, String>,
        t2: Triple<String, String, String>,
        t3: Triple<String, String, String>,
    ) {
        unsafe.rawRecipe.tags = arrayListOf(RecipeTags.CRAFTING_TABLE)
        unsafe.rawRecipe.pattern = arrayListOf(
            tripleToPattern(t1),
            tripleToPattern(t2),
            tripleToPattern(t3)
        )
    }

    /**
     * @param data define how this item will be unlocked in the recipe book
     *
     * ```
     * unlock {
     *     item("minecraft:wood", count = 3, data = 2)
     *     context()
     * }
     * ```
     */
    fun unlock(data: BehRecipeUnlock.() -> Unit) {
        unsafe.rawRecipe.unlock(data)
    }

    fun t(i1: String, i2: String, i3: String) = Triple(i1, i2, i3)

    private fun tripleToPattern(t: Triple<String, String, String>): String {
        var patternString = ""
        patternString += compilePattern(t.first)
        patternString += compilePattern(t.second)
        patternString += compilePattern(t.third)
        return patternString
    }

    private fun compilePattern(t: String): String {
        if(unsafe.keyPattern.containsKey(t))
            return unsafe.keyPattern[t]!!

        val ret = when(unsafe.keyPattern.size) {
            0 -> "F"
            1 -> "G"
            2 -> "H"
            3 -> "I"
            4 -> "J"
            5 -> "K"
            6 -> "L"
            7 -> "M"
            8 -> "N"
            else -> "O"
        }

        unsafe.keyPattern[t] = ret

        return ret
    }
}