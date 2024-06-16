package com.lop.devtools.monstera.addon.entitiy

import com.lop.devtools.monstera.addon.buildTestAddon
import com.lop.devtools.monstera.addon.testAddon
import com.lop.devtools.monstera.files.getResource
import java.awt.Color
import kotlin.test.AfterTest
import kotlin.test.Test

class EquipmentEntityTest {
    @AfterTest
    fun buildTask() {
        buildTestAddon()
    }

    @Test
    fun equipmentEntityTest() = testAddon {
        entity("eq_test_ent", "My Equipment Test Entity") {
            behaviour {
                components {
                    physics {  }
                    walkMovement(0.2) { }
                    equipment {
                        table("my_eq_table") {
                            pool {
                                rolls(1)
                                entry {
                                    type = "item"
                                    identifier = "minecraft:iron_axe"
                                    weight = 1
                                    functions {
                                        functionSetCount(1)
                                    }
                                }
                            }
                        }
                    }
                    equipItem {  }
                }
            }
            resource {
                geometryLayer(getResource("entity/geometries/soldier_npc.geo.json"))
                textureLayer(getResource("entity/textures/soldier_npc_0.png"))
                animation(getResource("entity/animations/soldier_npc.animation.json"))
                animationController("general") {
                    initialState = "default"
                    state("default") {
                        animations("base_pose", "default", "move")
                    }
                }
                components {
                    scripts {
                        preAnimationEntry("variable.tcos0 = (Math.cos(query.modified_distance_moved * 38.17) * query.modified_move_speed / variable.gliding_speed_value) * 57.3;")
                    }
                    spawnEgg {
                        eggByColor(Color.BLACK, Color.GRAY)
                    }
                    enableAttachment = true
                }
            }
        }
    }
}