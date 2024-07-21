package com.lop.devtools.monstera.addon.entitiy

import com.lop.devtools.monstera.addon.buildTestAddon
import com.lop.devtools.monstera.addon.testAddon
import com.lop.devtools.monstera.files.beh.spawnrules.PopulationControl
import com.lop.devtools.monstera.files.beh.spawnrules.conditions.BiomeTag
import kotlin.test.AfterTest
import kotlin.test.Test

class SpawnRuleTest {
    @AfterTest
    fun buildTask() {
        buildTestAddon()
    }

    @Test
    fun spawnRulesTest() = testAddon {
        entity("spawn_rule_test") {
            behaviour {
                spawnRule {
                    populationControl(PopulationControl.ANIMAL)
                    condition {
                        weight(2)
                        biomeFilter {
                            filterEntry(BiomeTag.OCEAN)
                        }
                    }
                }
            }
        }
        withJsonFile(config.paths.behSpawnRules.resolve("spawn_rule_test.json")) {
            assert(
                this.containsKeyChainValue(
                    value = "ocean",
                    "minecraft:spawn_rules",
                    "conditions",
                    "minecraft:biome_filter",
                    "value"
                )
            )
            assert(
                this.containsKeyChainValue(
                    value = 2,
                    "minecraft:spawn_rules",
                    "conditions",
                    "minecraft:weight",
                    "default"
                )
            )
        }
    }
}