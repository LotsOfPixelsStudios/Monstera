package com.lop.devtools.monstera.addon.item

import com.lop.devtools.monstera.addon.buildTestAddon
import com.lop.devtools.monstera.addon.testAddon
import kotlin.test.AfterTest
import kotlin.test.Test

class Recipe {
    @AfterTest
    fun buildTask() {
        buildTestAddon()
    }

    @Test
    fun recipeTest() = testAddon {
        item("test") {
            craftingRecipe {
                val g = "minecraft:gold_ingot"
                val r = "minecraft:redstone"

                craftingPattern(
                    t(g, g, g),
                    t(g, r, g),
                    t(g, r, g)
                )

                unlockRequirement {
                    item = g
                }
            }
        }
    }
}