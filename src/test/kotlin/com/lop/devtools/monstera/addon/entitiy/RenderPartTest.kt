package com.lop.devtools.monstera.addon.entitiy

import com.lop.devtools.monstera.addon.buildTestAddon
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.addon.testAddon
import com.lop.devtools.monstera.files.getResource
import kotlin.test.AfterTest
import kotlin.test.Test

class RenderPartTest {
    @AfterTest
    fun buildTask() {
        buildTestAddon()
    }

    @Test
    fun basicRenderPartTest() = testAddon {
        entity("render_part_ent") {
            resource {
                renderPart("init", Query.True) {
                    geometryLayer(getResource("entity/geometries/soldier_npc.geo.json"))
                    textureLayer(getResource("entity/textures/soldier_npc_0.png"))
                }
                renderPart("other", Query.canFly) {
                    geometryLayer(getResource("entity/geometries/soldier_npc.geo.json"))
                    textureLayer(getResource("entity/textures/soldier_npc_1.png"))
                }
                renderPart("missing_res", Query.isBaby) {

                }
            }
            behaviour {
                components {
                    physics {  }
                }
            }
        }
    }
}