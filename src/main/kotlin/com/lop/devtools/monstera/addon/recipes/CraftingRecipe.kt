package com.lop.devtools.monstera.addon.recipes

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafe
import com.lop.devtools.monstera.files.beh.recipes.BehRecipe
import com.lop.devtools.monstera.files.beh.recipes.BehRecipeShaped
import com.lop.devtools.monstera.files.beh.recipes.RecipeTags

class CraftingRecipe: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafe {
        val rawRecipe = BehRecipeShaped()
        val keyPattern = mutableMapOf<String, String>()

        fun isEmpty() = keyPattern.isEmpty()

        fun build(name: String, identifier: String, addon: Addon, resultItem: String = identifier) {
            rawRecipe.data.description(identifier)
            rawRecipe.data.result { item = resultItem }
            keyPattern.forEach { (id, key) ->
                rawRecipe.data.addKey(key, id)
            }

            rawRecipe.build(name, addon.config.paths.behRecipe, addon.config.formatVersions.behRecipe)
        }
    }


    /**
     * add a pattern for the crafting table to the recipe
     *
     * ```
     * val d = "minecraft:diamond"
     * val s = "minecraft:stick"
     * val e = ""
     *
     * craftingPattern(
     *     t(e,d,d),
     *     t(e,s,e),
     *     t(e,s,e)
     * )
     * ```
     */
    fun craftingPattern(
        t1: Triple<String, String, String>,
        t2: Triple<String, String, String>,
        t3: Triple<String, String, String>,
    ) {
        with(unsafe.rawRecipe.data) {
            tags = mutableListOf(RecipeTags.CRAFTING_TABLE)
            pattern = mutableListOf(
                tripleToPattern(t1),
                tripleToPattern(t2),
                tripleToPattern(t3)
            )
        }
    }

    /**
     * @param data define how this item will be unlocked in the recipe book
     *
     * ```
     * unlockRequirement {
     *     item("minecraft:wood", count = 3, data = 2)
     *     context()
     * }
     * ```
     */
    fun unlockRequirement(data: BehRecipe.ItemInfo.() -> Unit) {
        unsafe.rawRecipe.data.unlockRequirement(data)
    }

    @Deprecated("old", ReplaceWith("unlockRequirement(data)"),)
    fun unlock(data: BehRecipe.ItemInfo.() -> Unit) = unlockRequirement(data)

    fun t(i1: String, i2: String, i3: String) = Triple(i1, i2, i3)

    private fun tripleToPattern(t: Triple<String, String, String>): String {
        var patternString = ""
        patternString += compilePattern(t.first)
        patternString += compilePattern(t.second)
        patternString += compilePattern(t.third)
        return patternString
    }

    private fun compilePattern(t: String): String {
        if(t.isEmpty() || t == " ")
            return " "
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