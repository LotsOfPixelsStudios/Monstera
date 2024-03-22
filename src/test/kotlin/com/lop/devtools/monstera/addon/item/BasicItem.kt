package com.lop.devtools.monstera.addon.item

import com.lop.devtools.monstera.addon.buildTestAddon
import com.lop.devtools.monstera.addon.testAddon
import com.lop.devtools.monstera.files.beh.item.BehItem
import com.lop.devtools.monstera.files.getResource
import kotlin.test.AfterTest
import kotlin.test.Test

class BasicItem {
    @AfterTest
    fun buildTask() {
        buildTestAddon()
    }
    
    @Test
    fun basicItemTest() = testAddon {
        buildToMcFolder = true
        item("basic_item", "My basic Item") {
            menuCategory(BehItem.Description.Category.CONSTRUCTION)
            components {
                damage(10)
                durability {
                    maxDurability = 36
                }
                handEquipped(true)
            }
            texture(getResource("item/attachable/custom_chestplate.png"))
        }
    }
}