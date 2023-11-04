package com.lop.devtools.monstera.files.beh.spawnrules.conditions

class BehSpawnRulesCondition {
    val general = mutableMapOf<String, Any>()

    /**
     * 0..1
     *
     * This component allows the players to specify which biomes the mob spawns in. Check
     * https://compass.minecraft.partners/hc/en-us/articles/360026720393-Entities#Tagged%20Biomes
     * to see which Biome Tags exist, and what each biome is tagged as.
     *
     * @param operator: can be `!, &&, ||, <, <=, >=, >, ==, !=`
     * @param biomeTag: [BiomeTag]
     */
    fun biomeFilter(operator: String, biomeTag: BiomeTag) {
        general.apply {
            put(
                "minecraft:biome_filter", mutableMapOf(
                    "test" to "has_biome_tag",
                    "operator" to operator,
                    "value" to biomeTag.toString()
                )
            )
        }
    }

    /**
     * 0..1
     *
     * This component allows players to set the light level range that causes the mob to spawn.
     * @param adjustForWeather This determines if weather can affect the light level conditions that cause the mob to spawn
     * (e.g. Allowing hostile mobs to spawn during the day when it rains.)
     * @param max This is the maximum light level value that allows the mob to spawn
     * @param min This is the minimum light level value that allows the mob to spawn
     */
    fun brightnessFilter(adjustForWeather: Boolean, max: Float = 15.0f, min: Float = 0.0f) {
        general.apply {
            put(
                "minecraft:brightness_filter", mutableMapOf(
                    "adjust_for_weather" to adjustForWeather,
                    "max" to max,
                    "min" to min
                )
            )
        }
    }

    /**
     * 0..1
     *
     * This component allows players to determine the density cap limits for the specified mob type.
     * @param surface: This is the maximum number of mobs of this type spawnable on the surface
     * @param underground: 	This is the maximum number of mobs of this type spawnable underground
     */
    fun densityLimit(surface: Int, underground: Int) {
        general.apply {
            put(
                "minecraft:density_limit", mutableMapOf(
                    "surface" to surface,
                    "underground" to underground
                )
            )
        }
    }

    /**
     * 0..1
     *
     * This component allows players determine what mobs spawn when certain difficulty levels are set.
     * @param max:
     * @param min: peaceful, easy, normal, hard
     */
    fun difficultyFilter(max: String, min: String) {
        general.apply {
            put(
                "minecraft:difficulty_filter", mutableMapOf(
                    "max" to max,
                    "min" to min
                )
            )
        }
    }

    /**
     * 0..1
     *
     * This component allows players to determine the herd size of animals.
     * @sample sampleHerd
     */
    fun herd(settings: BehSpawnRulesHerd.() -> Unit) {
        general["minecraft:herd"] = BehSpawnRulesHerd().apply(settings).getData()
    }

    /**
     * 0..1
     *
     * This component allows the mob to spawn on the ground. Adding the component causes it to be true, removing it causes the mob to stop spawning on the surface.
     */
    fun spawnOnSurface() {
        general.apply {
            put("minecraft:spawns_on_surface", mutableMapOf<String, Any>())
        }
    }

    /**
     * 0..1
     *
     * This component allows the mob to spawn underwater. Adding the component causes it to be true, removing it causes the mob to stop spawning underwater.
     */
    fun spawnUnderWater() {
        general.apply {
            put("minecraft:spawns_underwater", mutableMapOf<String, Any>())
        }
    }

    /**
     * 0..1
     *
     * 	This component allows players to give a priority to how often that mob should spawn.
     * 	Mobs with lower weight values have a higher chance to spawn than mobs with higher weight values.
     */
    fun weight(value: Int = 0) {
        general.apply {
            put(
                "minecraft:weight", mutableMapOf(
                    "default" to value
                )
            )
        }
    }

    /**
     * 0..1
     *
     * height filter
     */
    fun heightFilter(min: Int, max: Int = min) {
        general["minecraft:height_filter"] = mutableMapOf("min" to min, "max" to max)
    }

    /**
     * 0..1
     *
     * blocks to spawn on
     */
    fun blockFilter(blocks: ArrayList<String>) {
        general["minecraft:spawns_on_block_filter"] = blocks
    }

    fun getData(): MutableMap<String, Any> {
        return general
    }

    private fun sampleHerd() {
        herd {
            event("@e pi:test")
            eventSkipCount(2)
            maxSize(5)
            minSize(1)
        }
    }
}