package com.lop.devtools.monstera.files.beh.entitiy.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

open class BehEntityFilter {
    private val calledFilters = mutableListOf<BehEntityFilter>()

    @OptIn(MonsteraBuildSetter::class)
    fun getCopy(): BehEntityFilter {
        return BehEntityFilter().also {
            it.testData = testData
            it.operatorData = operatorData
            it.domainData = domainData
            it.subjectData = subjectData
            it.valueData = valueData
            it.anyOfData = anyOfData
            it.allOfData = allOfData
            it.noneOfData = noneOfData
        }
    }

    @SerializedName("test")
    @Expose
    var testData: String? = null
        @MonsteraBuildSetter set

    @SerializedName("operator")
    @Expose
    var operatorData: String? = null
        @MonsteraBuildSetter set

    @SerializedName("domain")
    @Expose
    var domainData: String? = null
        @MonsteraBuildSetter set

    @SerializedName("subject")
    @Expose
    var subjectData: Subject? = null
        @MonsteraBuildSetter set

    @SerializedName("value")
    @Expose
    var valueData: Any? = null
        @MonsteraBuildSetter set

    @SerializedName("any_of")
    @Expose
    var anyOfData: MutableList<BehEntityFilter>? = null
        @MonsteraBuildSetter set

    @SerializedName("all_of")
    @Expose
    var allOfData: MutableList<BehEntityFilter>? = null
        @MonsteraBuildSetter set

    @SerializedName("none_of")
    @Expose
    var noneOfData: MutableList<BehEntityFilter>? = null
        @MonsteraBuildSetter set

    val general = mutableMapOf<String, Any>()
    val generalList = arrayListOf<Any>()

    /**
     * 0..1
     *
     * Compares the current time with a float value in the range (0.0, 1.0). 0.0= Noon 0.25= Sunset 0.5= Midnight 0.75= Sunrise
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) A floating point value.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun clockTime(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Float
    ) {
        testData = "clock_time"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Compares the current time with a float value in the range (0.0, 1.0). 0.0= Noon 0.25= Sunset 0.5= Midnight 0.75= Sunrise
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) A floating point value.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun distanceToNearestPlayer(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Float
    ) {
        testData = "distance_to_nearest_player"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true when the subject entity has the named ability.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The Ability type to test
     */
    @OptIn(MonsteraBuildSetter::class)
    fun hasAbility(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: BehEntityFilterAbilityFly
    ) {
        testData = "has_ability"
        operatorData = operator
        subjectData = subject
        valueData = value.toString()
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests whether the biome the subject is in has the specified tag.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The tag to look for
     */
    @OptIn(MonsteraBuildSetter::class)
    fun hasBiomeTag(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String
    ) {
        testData = "has_biome_tag"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true when the subject entity contains the named component.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The component name to look for
     */
    @OptIn(MonsteraBuildSetter::class)
    fun hasComponent(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String
    ) {
        testData = "has_component"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true when the subject Player entity has opened a container.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun hasContainerOpen(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "has_container_open"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true when the subject entity receives the named damage type.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun hasDamage(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: DamageType
    ) {
        testData = "has_damage"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests for the presence of a named item in the designated slot of the subject entity.
     * @param domain (Optional) The equipment location to test
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The item name to look for
     */
    @OptIn(MonsteraBuildSetter::class)
    fun hasEquipment(
        domain: SlotDomain = SlotDomain.ANY,
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String
    ) {
        testData = "has_equipment"
        operatorData = operator
        subjectData = subject
        valueData = value
        domainData = domain.toString()
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests whether the Subject has the specified mob effect.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) A string value.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun hasMobEffect(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String? = null
    ) {
        testData = "has_mob_effect"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true when the subject entity is holding a ranged weapon like a bow or crossbow.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun hasRangedWeapon(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "has_ranged_weapon"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity has the tag provided.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) A string value.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun hasTag(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String? = null
    ) {
        testData = "has_tag"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity has a valid target.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun hasTarget(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "has_target"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests whether the target has any trade supply left. Will return false if the target cannot be traded with.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun hasTargetSupply(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "has_trade_supply"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Compares the current 24 hour time with an int value in the range[0, 24000]
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) An integer value.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun hourlyClockTime(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Int
    ) {
        testData = "hourly_clock_time"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is in a caravan.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun inCaravan(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "in_caravan"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true when the subject entity is in the clouds.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun inClouds(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "in_clouds"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true when the subject entity is in lava.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun inLava(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "in_lava"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true when the subject entity is in Nether.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun inNether(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "in_nether"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true when the subject entity is in water.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun inWater(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "in_water"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true when the subject entity is in water or rain.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun inWaterOrRain(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "in_water_or_rain"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests if the specified duration in seconds of inactivity for despawning has been reached.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) An integer value.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun inactivityTimer(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Int
    ) {
        testData = "inactivity_timer"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests the current altitude against a provided value. 0= bedrock elevation.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The altitude value to compare with
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isAltitude(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Int
    ) {
        testData = "is_altitude"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is fleeing from other mobs.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value string.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isAvoidingMobs(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String
    ) {
        testData = "is_avoiding_mobs"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests whether the Subject is currently in the named biome.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The Biome type to test
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isBiome(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: BiomeType
    ) {
        testData = "is_biome"
        operatorData = operator
        subjectData = subject
        valueData = value.toString()
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true when the block has the given name.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The Family name to look for
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isBlock(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String
    ) {
        testData = "is_block"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests the current brightness against a provided value in the range (0.0f, 1.0f).
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The brightness value to compare with.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isBrightness(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Float
    ) {
        testData = "is_brightness"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is climbing.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isClimbing(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "is_climbing"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is the named color.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The Palette Color to test
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isColor(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: ColorType
    ) {
        testData = "is_color"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is climbing.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isDayTime(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "is_daytime"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests the current difficulty level of the game.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The game's difficulty level to test
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isDifficulty(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: DifficultyType
    ) {
        testData = "is_difficulty"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests the current difficulty level of the game.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The Family name to look for
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isFamily(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String
    ) {
        testData = "is_family"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests whether a named game rule is active.
     * @param domain (Required) The Game Rule to test.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The Family name to look for
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isGameRule(
        domain: String,
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "is_game_rule"
        operatorData = operator
        subjectData = subject
        valueData = value
        domainData = domain
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests whether the Subject is in an area with humidity
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isHumid(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "is_humid"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is immobile. An entity is immobile if it lacks AI goals, has just changed dimensions or if it is a mob and has no health.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isImmobile(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "is_immobile"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests whether the Subject is inside the bounds of a village.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isInVillage(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "is_in_village"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is leashed.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isLeashed(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "is_leashed"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity leashed to the calling entity.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isLeashedTo(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "is_leashed_to"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is the mark variant number provided.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) An integer value.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isMarkVariant(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Int
    ) {
        testData = "is_mark_variant"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is moving.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isMoving(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "is_moving"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is the owner of the calling entity.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isOwner(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "is_owner"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests if the subject's persistence matches the bool value passed in.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isPersistent(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "is_persistent"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is riding on another entity.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isRiding(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "is_persistent"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is the skin id number provided.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) An integer value.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isSkinId(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Int
    ) {
        testData = "is_skin_id"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests whether the Subject is sleeping.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isSleeping(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "is_sleeping"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests whether the Subject is sleeping.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isSneaking(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "is_sneaking"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests whether the Subject is in an area with snow cover
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isSnowCovered(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "is_snow_covered"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is the target of the calling entity.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isTarget(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "is_target"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests whether the current temperature is a given type.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The Biome temperature catagory to test
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isTemperatureType(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: BiomeTemperatureType
    ) {
        testData = "is_temperature_type"
        operatorData = operator
        subjectData = subject
        valueData = value.toString()
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests the current temperature against a provided value in the range (0.0, 1.0) where 0.0f is the coldest temp and 1.0f is the hottest.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The Biome temperature value to compare with.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isTemperatureValue(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Float
    ) {
        testData = "is_temperature_value"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true when the subject entity is underground. An entity is considered underground if there are non-solid blocks above it.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isUnderGround(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "is_underground"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true when the subject entity is under water. An entity is considered underwater if it is completely submerged in water blocks.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isUnderWater(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "is_underwater"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is the variant number provided.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) An integer value.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isVariant(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Int
    ) {
        testData = "is_variant"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is visible.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun isVisible(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "is_visible"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * DEPRECATED
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The Family name to look for
     */
    @OptIn(MonsteraBuildSetter::class)
    @Deprecated("docu says so")
    fun isWeather(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String
    ) {
        testData = "is_weather"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests is the mob is outside of the specified light level range (0, 16).
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) An integer value.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun lightLevel(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Int
    ) {
        testData = "light_level"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Compares the current moon intensity with a float value in the range (0.0, 1.0)
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) A floating point value.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun moonIntensity(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Float
    ) {
        testData = "moon_intensity"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Compares the current moon phase with an integer value in the range (0, 7).
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) An integer value.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun moonPhase(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Int
    ) {
        testData = "moon_phase"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Compares the current moon phase with an integer value in the range (0, 7).
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun onGround(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "on_ground"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true when the subject entity is on a ladder.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun onLadder(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "on_ladder"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true if the random chance rolls 0 out of a specified max range.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) An integer value.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun randomChance(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Int
    ) {
        testData = "random_chance"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns the number of riders on this entity.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) An integer value.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun riderCount(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Int
    ) {
        testData = "rider_count"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests if the subject is a surface mob.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun surfaceMob(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "surface_mob"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Returns true if the subject is trusted by entity.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun trusts(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "trusts"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests the current weather in the dimension against a provided weather value.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The Family name to look for
     */
    @OptIn(MonsteraBuildSetter::class)
    fun weather(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String
    ) {
        testData = "weather"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests the current weather, at the actor's position, against a provided weather value.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) like thunderstorm, rain.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun weatherAtPosition(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String
    ) {
        testData = "weather_at_position"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests for an int property
     *
     * @param domain (Required) the property name
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) like thunderstorm, rain.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun intProperty(
        domain: String,
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Int
    ) {
        testData = "int_property"
        operatorData = operator
        subjectData = subject
        valueData = value
        domainData = domain
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests for a float property
     *
     * @param domain (Required) the property name
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) like thunderstorm, rain.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun floatProperty(
        domain: String,
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Int
    ) {
        testData = "float_property"
        operatorData = operator
        subjectData = subject
        valueData = value
        domainData = domain
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests for a bool property
     *
     * @param domain (Required) the property name
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) like thunderstorm, rain.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun boolProperty(
        domain: String,
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        testData = "bool_property"
        operatorData = operator
        subjectData = subject
        valueData = value
        domainData = domain
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests for an enum property
     *
     * @param domain (Required) the property name
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) like thunderstorm, rain.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun enumProperty(
        domain: String,
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String
    ) {
        testData = "enum_property"
        operatorData = operator
        subjectData = subject
        valueData = value
        domainData = domain
        calledFilters.add(getCopy())
    }

    /**
     * 0..1
     *
     * Tests for a property
     *
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) like thunderstorm, rain.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun hasProperty(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String
    ) {
        testData = "has_property"
        operatorData = operator
        subjectData = subject
        valueData = value
        calledFilters.add(getCopy())
    }

    @OptIn(MonsteraBuildSetter::class)
    fun allOf(settings: BehEntityFilter.() -> Unit) {
        allOfData = (allOfData ?: mutableListOf()).also { it.addAll(BehEntityFilter().apply(settings).calledFilters) }
        calledFilters.add(getCopy())
    }

    @OptIn(MonsteraBuildSetter::class)
    fun anyOf(settings: BehEntityFilter.() -> Unit) {
        anyOfData = (anyOfData ?: mutableListOf()).also { it.addAll(BehEntityFilter().apply(settings).calledFilters) }
        calledFilters.add(getCopy())
    }

    @OptIn(MonsteraBuildSetter::class)
    fun noneOf(settings: BehEntityFilter.() -> Unit) {
        noneOfData = (noneOfData ?: mutableListOf()).also { it.addAll(BehEntityFilter().apply(settings).calledFilters) }
        calledFilters.add(getCopy())
    }
}