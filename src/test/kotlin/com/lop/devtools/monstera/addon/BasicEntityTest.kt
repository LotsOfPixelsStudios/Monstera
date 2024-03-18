package com.lop.devtools.monstera.addon

import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.files.getResource
import java.awt.Color
import kotlin.test.Test

class BasicEntityTest {
    @Test
    fun basicEntityTest() {
        TestAddon.lazyAddon.entity("soldior_test") {
            resource {
                components {
                    spawnEgg {
                        eggByColor(Color.BLACK, Color.BLUE)
                    }
                }
                textureLayer(arrayListOf(
                    getResource("entity/textures/soldier_npc_0.png"),
                    getResource("entity/textures/soldier_npc_1.png"),
                    getResource("entity/textures/soldier_npc_2.png"),
                    getResource("entity/textures/soldier_npc_3.png"),
                ), Query.variant)
                geometryLayer(getResource("entity/geometries/soldier_npc.geo.json"))
                animation(getResource("entity/animations/soldier_npc.animation.json"))

                animationController("general") {
                    state("default") {
                        animations("base_pose", "move")
                    }
                }
            }
            behaviour {
                components {
                    walkMovement(0.2) {
                        avoidWater = true
                    }
                }
            }
        }
    }
}