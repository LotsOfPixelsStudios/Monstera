@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.lop.devtools.monstera.files.beh.spawnrules

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.api.MonsteraBuildableFile
import com.lop.devtools.monstera.files.*
import com.lop.devtools.monstera.files.beh.spawnrules.conditions.*
import com.lop.devtools.monstera.files.sanetiseFilename
import com.lop.devtools.monstera.getMonsteraLogger
import java.lang.Error
import java.nio.file.Path

class BehSpawnRules : MonsteraBuildableFile, MonsteraRawFile() {
    override fun build(filename: String, path: Path?, version: String?): Result<Path> {
        val selPath = path ?: Addon.active?.config?.paths?.behBlocks ?: run {
            getMonsteraLogger(this.javaClass.name).error("Could not Resolve a path for spawn rule file '$filename' as no addon was initialized!")
            return Result.failure(Error("Could not Resolve a path for spawn rule file '$filename' as no addon was initialized!"))
        }

        val target = MonsteraBuilder.buildTo(
            selPath,
            sanetiseFilename(filename, "json"),
            FileHeader(
                version ?: Addon.active?.config?.formatVersions?.behSpawnRules ?: "1.8.0",
                this
            )
        )
        return Result.success(target)
    }

    data class FileHeader(
        @SerializedName("format_version")
        @Expose
        var formatVersion: String = "1.8.0",

        @SerializedName("minecraft:spawn_rules")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var block: BehSpawnRules
    ) : MonsteraRawFile()

    @SerializedName("description")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var descriptionData: Description? = null
        @MonsteraBuildSetter set

    /**
     * ```
     * description {
     *     identifier = "minecraft:zombie"
     *     populationControl = PopulationControl.MONSTER
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun description(data: Description.() -> Unit) {
        descriptionData = (descriptionData ?: Description()).apply(data)
    }

    class Description : MonsteraRawFile() {
        @SerializedName("identifier")
        @Expose
        var identifier: String? = null

        @SerializedName("population_control")
        @Expose
        var populationControl: PopulationControl? = null
    }

    @SerializedName("conditions")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var conditionsData: MutableList<Condition>? = null
        @MonsteraBuildSetter set

    /**
     * add a spawn condition, can be called multiple times
     *
     * ```
     * condition {
     *     weight(100)
     *     densityLimit { }
     *     spawnsOnBlockFilter = mutableListOf("minecraft:sand")
     *     spawnsOnBlockPreventedFilterData = mutableListOf("minecraft:sand")
     *     spawnsAboveBlockFilterData { }
     *     herd { }
     *     permuteType { }
     *     brightnessFilter { }
     *     heightFilter { }
     *     spawnsOnSurface()
     *     spawnsUnderground()
     *     spawnsUnderwater()
     *     disallowSpawnsInBubble()
     *     spawnsLava()
     *     biomeFilter { }
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun condition(data: Condition.() -> Unit) {
        conditionsData = (conditionsData ?: mutableListOf()).apply { add(Condition().apply(data)) }
    }

    open class Condition : MonsteraRawFile() {
        @SerializedName("minecraft:weight")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var weightData: SpawnWeight? = null
            @MonsteraBuildSetter set

        @OptIn(MonsteraBuildSetter::class)
        fun weight(default: Number) {
            weightData = SpawnWeight().apply { this.default = default }
        }

        @SerializedName("minecraft:density_limit")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var densityLimitData: SpawnDensityLimit? = null
            @MonsteraBuildSetter set

        /**
         * ```
         * densityLimit {
         *     surface = 10
         *     underground = 50
         * }
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun densityLimit(data: SpawnDensityLimit.() -> Unit) {
            densityLimitData = (densityLimitData ?: SpawnDensityLimit()).apply(data)
        }

        @SerializedName("minecraft:spawns_on_block_filter")
        @Expose
        var spawnsOnBlockFilter: MutableList<String>? = null

        @SerializedName("minecraft:spawns_on_block_prevented_filter")
        @Expose
        var spawnsOnBlockPreventedFilterData: MutableList<String>? = null

        @SerializedName("minecraft:spawns_above_block_filter")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var spawnsAboveBlockFilterData: SpawnAboveBlockFilter? = null
            @MonsteraBuildSetter set

        /**
         * minecraft:spawns_above_block_filter will detect blocks within a set distance vertically, and if the condition
         * is met, the entity will spawn successfully.
         *
         * ```
         * spawnAboveBlockFilter {
         *     blocks = mutableListOf("minecraft:sand")
         *     distance = 10
         * }
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun spawnAboveBlockFilter(data: SpawnAboveBlockFilter.() -> Unit) {
            spawnsAboveBlockFilterData = (spawnsAboveBlockFilterData ?: SpawnAboveBlockFilter()).apply(data)
        }

        @SerializedName("minecraft:herd")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var herdData: SpawnHerd? = null
            @MonsteraBuildSetter set

        /**
         * ```
         * herd {
         *     minSize = 3
         *     maxSize = 6
         *     event = "minecraft:entity_born"
         *     eventSkipCount = 2
         * }
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun herd(data: SpawnHerd.() -> Unit) {
            herdData = (herdData ?: SpawnHerd()).apply(data)
        }

        @SerializedName("minecraft:permute_type")
        @Expose
        @JsonAdapter(MonsteraListFileTypeAdapter::class)
        var permuteTypeData: MutableList<SpawnPermuteType>? = null
            @MonsteraBuildSetter set

        /**
         * should be called multiple times
         * ```
         * permuteType {
         *     weight = 95
         * }
         * permuteType {
         *     weight = 95
         *     entityType = "minecraft:zombie_villager_v2"
         * }
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun permuteType(data: SpawnPermuteType.() -> Unit) {
            permuteTypeData = (permuteTypeData ?: mutableListOf()).apply { add(SpawnPermuteType().apply(data)) }
        }

        @SerializedName("minecraft:brightness_filter")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var brightnessFilterData: SpawnBrightnessFilter? = null
            @MonsteraBuildSetter set

        /**
         * ```
         * brightnessFilter {
         *     min = 0
         *     max = 7
         *     adjustForWeather = true
         * }
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun brightnessFilter(data: SpawnBrightnessFilter.() -> Unit) {
            brightnessFilterData = (brightnessFilterData ?: SpawnBrightnessFilter()).apply(data)
        }

        @SerializedName("minecraft:height_filter")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var heightFilterData: SpawnHeightFilter? = null
            @MonsteraBuildSetter set

        /**
         * ```
         * heightFilter {
         *     min = 0
         *     max = 64
         * }
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun heightFilter(data: SpawnHeightFilter.() -> Unit) {
            heightFilterData = (heightFilterData ?: SpawnHeightFilter()).apply(data)
        }

        @SerializedName("minecraft:spawns_on_surface")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var spawnsOnSurfaceData: SpawnsOnSurface? = null
            @MonsteraBuildSetter set

        @OptIn(MonsteraBuildSetter::class)
        fun spawnsOnSurface() {
            spawnsOnSurfaceData = SpawnsOnSurface()
        }

        @SerializedName("minecraft:spawns_underground")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var spawnsUndergroundData: SpawnsUnderground? = null
            @MonsteraBuildSetter set

        @OptIn(MonsteraBuildSetter::class)
        fun spawnsUnderground() {
            spawnsUndergroundData = SpawnsUnderground()
        }

        @SerializedName("minecraft:spawns_underwater")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var spawnsUnderwaterData: SpawnsUnderwater? = null
            @MonsteraBuildSetter set

        @OptIn(MonsteraBuildSetter::class)
        fun spawnsUnderwater() {
            spawnsUnderwaterData = SpawnsUnderwater()
        }

        @SerializedName("minecraft:disallow_spawns_in_bubble")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var disallowSpawnsInBubbleData: SpawnDisallowSpawnsInBubble? = null
            @MonsteraBuildSetter set

        @OptIn(MonsteraBuildSetter::class)
        fun disallowSpawnsInBubble() {
            disallowSpawnsInBubbleData = SpawnDisallowSpawnsInBubble()
        }

        @SerializedName("minecraft:spawns_lava")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var spawnsLavaData: SpawnsLava? = null
            @MonsteraBuildSetter set

        @OptIn(MonsteraBuildSetter::class)
        fun spawnsLava() {
            spawnsLavaData = SpawnsLava()
        }

        @SerializedName("minecraft:biome_filter")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var biomeFilterData: SpawnBiomeFilter? = null
            @MonsteraBuildSetter set

        /**
         * ```
         * biomeFilter {
         *     filterEntry(BiomeTag.MONSTER)
         *     //or
         *     anyOf{ }
         *     allOf{ }
         * }
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun biomeFilter(data: SpawnBiomeFilter.() -> Unit) {
            biomeFilterData = (biomeFilterData ?: SpawnBiomeFilter()).apply(data)
        }

        @SerializedName("minecraft:difficulty_filter")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var difficultyFilterData: SpawnDifficultyFilter? = null
            @MonsteraBuildSetter set

        /**
         * ```
         * difficultyFilter {
         *     min = "easy"
         *     max = "hard"
         * }
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun difficultyFilter(data: SpawnDifficultyFilter.() -> Unit) {
            difficultyFilterData = (difficultyFilterData ?: SpawnDifficultyFilter()).apply(data)
        }

        @SerializedName("minecraft:distance_filter")
        @Expose
        var distanceFilterData: Any? = null
            @MonsteraBuildSetter set

        @SerializedName("minecraft:is_experimental")
        @Expose
        var isExperimentalData: Any? = null
            @MonsteraBuildSetter set

        @SerializedName("minecraft:world_age_filter")
        @Expose
        var worldAgeFilterData: Any? = null
            @MonsteraBuildSetter set

        @SerializedName("minecraft:delay_filter")
        @Expose
        var delayFilterData: Any? = null
            @MonsteraBuildSetter set

        @SerializedName("minecraft:mob_event_filter")
        @Expose
        var mobEventFilterData: Any? = null
            @MonsteraBuildSetter set

        @SerializedName("minecraft:is_persistent")
        @Expose
        var isPersistentData: Any? = null
            @MonsteraBuildSetter set

        @SerializedName("minecraft:player_in_village_filter")
        @Expose
        var playerInVillageFilterData: Any? = null
            @MonsteraBuildSetter set
    }
}

enum class PopulationControl {
    @SerializedName("animal")
    ANIMAL,

    @SerializedName("monster")
    MONSTER,

    @SerializedName("water_animal")
    WATER_ANIMAL,

    @SerializedName("ambient")
    AMBIENT;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}