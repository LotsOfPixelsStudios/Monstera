package com.lop.devtools.monstera.files

import com.google.gson.GsonBuilder
import com.lop.devtools.monstera.files.beh.entitiy.BehEntity
import org.skyscreamer.jsonassert.JSONAssert
import kotlin.test.Test

class ComponentTest {
    @Test
    fun basicEntityTest() {
        val entity = BehEntity()
        with(entity) {
            description {
                identifier = "test"
                isSpawnable = false
                isSummonable = false
                isExperimental = false
            }
            components {
                physics {
                    hasGravity = false
                }
            }
        }

        val target = mutableMapOf(
            "description" to mutableMapOf(
                "identifier" to "test",
                "is_spawnable" to false,
                "is_summonable" to false,
                "is_experimental" to false
            ),
            "components" to mutableMapOf(
                "minecraft:physics" to mutableMapOf(
                    "has_gravity" to false
                )
            )
        )

        val gson = GsonBuilder().setPrettyPrinting().create()
        JSONAssert.assertEquals(gson.toJson(target), gson.toJson(entity), false)
    }
}