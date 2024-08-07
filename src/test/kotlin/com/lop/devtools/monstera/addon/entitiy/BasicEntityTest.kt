package com.lop.devtools.monstera.addon.entitiy

import com.lop.devtools.monstera.addon.buildTestAddon
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.addon.testAddon
import com.lop.devtools.monstera.files.getResource
import kotlin.test.AfterTest
import kotlin.test.Test

class BasicEntityTest {
    @AfterTest
    fun buildTask() {
        buildTestAddon()
    }

    @Test
    fun basicEntityTest() = testAddon {
        entity("soldier_test") {
            resource {
                textureLayer(
                    arrayListOf(
                        getResource("entity/textures/soldier_npc_0.png"),
                        getResource("entity/textures/soldier_npc_1.png"),
                        getResource("entity/textures/soldier_npc_2.png"),
                        getResource("entity/textures/soldier_npc_3.png"),
                    ), Query.variant
                )
                assert(unsafeRawEntity.description?.texturesData != null)
                //geometryLayer(getResource("entity/geometries/soldier_npc.geo.json"))
                //animation(getResource("entity/animations/soldier_npc.animation.json"))

                animationController("general") {
                    state("default") {
                        animations("base_pose", "move")
                    }
                }
            }

            behaviour {
                properties {
                    bool("test") {
                        default = true
                        clientSync = true
                    }
                }
                componentGroup("test") {
                    isBaby {  }
                    additionalKeys = mapOf("my_component" to "my_value")
                }
                components {
                    walkMovement(0.2) {
                        avoidWater = true
                    }
                    additionalKeys = mapOf("my_component" to "my_value")

                    rideable {
                        familyTypes("player")
                        seat {
                            position(0, 0, 2)
                        }
                        seatCountFromSeats()
                    }
                    inputGroundControlled {  }
                    itemControllable {  }
                }
            }
        }
    }
}