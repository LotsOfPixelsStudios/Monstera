package com.lop.devtools.monstera.addon.item

import com.lop.devtools.monstera.addon.buildTestAddon
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.addon.testAddon
import com.lop.devtools.monstera.files.beh.item.comp.EnchantableSlot
import com.lop.devtools.monstera.files.beh.item.comp.ItemWearable
import com.lop.devtools.monstera.files.getResource
import kotlin.test.AfterTest
import kotlin.test.Test

class AttachableTest {
    @AfterTest
    fun buildTask() {
        buildTestAddon()
    }
    
    @Test
    fun basicAttachableTest() = testAddon {
        buildToMcFolder = true
        item("test_attachable", "Test Attachable") {
            category("equipment")
            texture(getResource("item/attachable/custom_chestplate.png"))
            components {
                creativeCategory {
                    parent = "itemGroup.name.chestplate"
                }
                maxStackSize(1)
                enchantable {
                    value = 10
                    slot = EnchantableSlot.ARMOR_TORSO
                }
                repairable {
                    repairItem {
                        items("minecraft:stick")
                        repairAmount(Query("context.other->q.remaining_durability + 0.05 * context.other->q.max_durability"))
                    }
                }
                wearable {
                    slot = ItemWearable.Slot.ARMOR_CHEST
                }
                durability {
                    maxDurability = 200
                }
            }
            attachable {
                loadArmorPreset()
                texture(getResource("item/attachable/custom_chest.png"))
                geometry("geometry.player.armor.chestplate")
                scripts {
                    parentSetup = "v.chest_layer_visible = 0.0;"
                }
            }
        }
    }
}