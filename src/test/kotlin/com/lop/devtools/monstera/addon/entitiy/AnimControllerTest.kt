package com.lop.devtools.monstera.addon.entitiy

import com.lop.devtools.monstera.addon.api.MonsteraExperimental
import com.lop.devtools.monstera.addon.buildTestAddon
import com.lop.devtools.monstera.addon.molang.Math
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.addon.molang.Variable
import com.lop.devtools.monstera.addon.molang.eq
import com.lop.devtools.monstera.addon.testAddon
import java.awt.Color
import kotlin.test.AfterTest
import kotlin.test.Test

class AnimControllerTest {
    @AfterTest
    fun buildTask() {
        buildTestAddon()
    }

    @OptIn(MonsteraExperimental::class)
    @Test
    fun basicAnimControllerTest() = testAddon {
        entity("my_anim_test", "Anim Test") {
            behaviour {
                components {
                    physics { }
                }
                animation("test") {
                    timeline {  }
                    animLength = 2f
                }
                animController("my_anim_controller", Query.isBaby) {
                    initialState = "default"
                    variables {
                        set(Variable.new("my_var" to Math.random(0, 1)))
                    }
                    state("default") {
                        transition("success", Variable["my_var"] eq 0)
                    }
                    state("success") {
                        onEntry = mutableListOf("/say success")
                    }
                }
            }
            resource {
                components {
                    spawnEgg {
                        eggByColor(Color.BLACK, Color.GRAY)
                    }
                }
            }
        }
        withJsonFile(config.paths.behEntity.resolve("my_anim_test.json")) {
            assert(
                containsKeyChainValue(
                    value = "query.is_baby",
                    "minecraft:entity",
                    "description",
                    "scripts",
                    "animate",
                    "my_anim_controller"
                )
            )
            assert(
                containsKeyChainValue(
                    value = "controller.animation.${config.namespace}.my_anim_test.my_anim_controller",
                    "minecraft:entity",
                    "description",
                    "animations",
                    "my_anim_controller"
                )
            )
        }
        withJsonFile(config.paths.behAnimController.resolve("my_anim_test.json")) {
            assert(
                containsKeyChain(
                    "animation_controllers",
                    "controller.animation.${config.namespace}.my_anim_test.my_anim_controller",
                    "initial_state"
                )
            )
            assert(
                containsKeyChainValue(
                    value = "(variable.my_var == 0)",
                    "animation_controllers",
                    "controller.animation.${config.namespace}.my_anim_test.my_anim_controller",
                    "states",
                    "default",
                    "transitions",
                    "success"
                )
            )
            assert(
                containsKeyChain(
                    "animation_controllers",
                    "controller.animation.${config.namespace}.my_anim_test.my_anim_controller",
                    "states",
                    "success",
                    "on_entry"
                )
            )
        }
    }
}