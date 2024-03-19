package com.lop.devtools.monstera.files.beh.spawnrules

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.MonsteraBuilder
import com.lop.devtools.monstera.files.beh.spawnrules.conditions.BehSpawnRulesCondition
import com.lop.devtools.monstera.files.beh.spawnrules.conditions.BiomeTag
import java.nio.file.Path

class BehSpawnRules: MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        /**
         * access to all defined data
         */
        val general = mutableMapOf<String, Any>()

        /**
         * raw conditions (also mutable maps)
         */
        val condList = arrayListOf<Any>()

        override fun getData(): MutableMap<String, Any> {
            unsafe.general.apply {
                put("conditions", unsafe.condList)
            }
            return unsafe.general
        }

        fun build(
            filename: String,
            path: Path,
            version: String = "1.8.0"
        ) {
            val sanFile = filename
                .removeSuffix(".json")
                .replace("-", "_")
                .replace(" ", "_")
            MonsteraBuilder.buildTo(
                path, "$sanFile.json", mutableMapOf(
                    "format_version" to version,
                    "minecraft:spawn_rules" to getData()
                )
            )
        }
    }

    /**
     * 1
     *
     * @param identifier projectShort:name
     * @param populationControl: "monster" or "animal", Spawncap
     */
    fun description(identifier: String, populationControl: PopulationControl = PopulationControl.MONSTER) {
        unsafe.general.apply {
            put("description", mutableMapOf(
                "identifier" to identifier,
                "population_control" to populationControl.toString().lowercase()
            ))
        }
    }

    /**
     * 1..*
     *
     * @sample sampleCond
     */
    fun condition(settings: BehSpawnRulesCondition.() -> Unit) {
        val behSpawnRulesConditions = BehSpawnRulesCondition().apply { settings(this) }
        unsafe.condList.add(behSpawnRulesConditions.getData())
    }

    private fun sampleCond() {
        condition {
            biomeFilter("==", BiomeTag.cold)
            brightnessFilter(true)
            densityLimit(0,10)
            difficultyFilter("easy", "hard")
            herd{
                //...
            }
            spawnOnSurface()
            spawnUnderWater()
            weight(1)
        }
    }
}

enum class PopulationControl{
    ANIMAL,
    MONSTER,
    WATER_ANIMAL
}