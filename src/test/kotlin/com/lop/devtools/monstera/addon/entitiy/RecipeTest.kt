package com.lop.devtools.monstera.addon.entitiy

import com.lop.devtools.monstera.addon.buildTestAddon
import com.lop.devtools.monstera.addon.testAddon
import kotlin.test.AfterTest
import kotlin.test.Test

class RecipeTest {
    @AfterTest
    fun buildTask() {
        buildTestAddon()
    }

    @Test
    fun basicEntityRecipeTest() = testAddon {
        entity("test_recipe") {
            behaviour {
                components {
                    physics { }
                }
                craftingRecipe {
                    val n = ""
                    val s = "minecraft:stick"
                    val d = "minecraft:diamond"

                    craftingPattern(
                        t(n, d, n),
                        t(n, d, n),
                        t(n, s, n)
                    )

                    unlockRequirement {
                        item = d
                    }
                }
            }
        }
    }
}