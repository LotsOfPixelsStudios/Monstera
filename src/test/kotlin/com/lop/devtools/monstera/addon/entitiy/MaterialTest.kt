package com.lop.devtools.monstera.addon.entitiy

import com.lop.devtools.monstera.addon.buildTestAddon
import com.lop.devtools.monstera.addon.testAddon
import kotlin.test.AfterTest
import kotlin.test.Test

class MaterialTest {
    @AfterTest
    fun buildTask() {
        buildTestAddon()
    }

    @Test
    fun basicMaterialTest() = testAddon {
        val entityId = "material_test"
        entity("material_test", "Material Entity") {
            behaviour {
                components {
                    physics { }
                }
            }
            resource {
                defaultRenderPart {
                    material("alpha_test", "*")
                    material("parrot", "my_bone")
                    material("slime", "my_other_bone")
                }
            }
        }

        withJsonFile(config.paths.resRenderControllers.resolve("${entityId}.json")) {
            assert(containsKey("format_version"))
            assert(containsKeyChain("render_controllers", "controller.render${config.namespace}.$entityId.default"))
            assert(
                containsKeyChainValue(
                    value = "Material.default_my_bone",
                    "render_controllers",
                    "controller.render${config.namespace}.$entityId.default",
                    "materials",
                    "my_bone"
                )
            )
            assert(
                containsKeyChainValue(
                    value = "Material.default_my_other_bone",
                    "render_controllers",
                    "controller.render.${config.namespace}.$entityId.default",
                    "materials",
                    "my_other_bone"
                )
            )
            assert(!containsKey("not_a_key"))
        }
    }
}