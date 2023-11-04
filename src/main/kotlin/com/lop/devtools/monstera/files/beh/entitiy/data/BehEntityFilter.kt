package com.lop.devtools.monstera.files.beh.entitiy.data

class BehEntityFilter {
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
    fun clockTime(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Float
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "clock_time")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Compares the current time with a float value in the range (0.0, 1.0). 0.0= Noon 0.25= Sunset 0.5= Midnight 0.75= Sunrise
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) A floating point value.
     */
    fun distanceToNearestPlayer(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Float
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "distance_to_nearest_player")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true when the subject entity has the named ability.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The Ability type to test
     */
    fun hasAbility(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: BehEntityFilterAbilityFly
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "has_ability")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value.toString().lowercase())
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Tests whether the biome the subject is in has the specified tag.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The tag to look for
     */
    fun hasBiomeTag(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "has_biome_tag")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true when the subject entity contains the named component.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The component name to look for
     */
    fun hasComponent(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "has_component")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true when the subject Player entity has opened a container.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun hasContainerOpen(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "has_container_open")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true when the subject entity receives the named damage type.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun hasDamage(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: DamageType
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "has_damage")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value.toString().lowercase())
        }
        general.putAll(thisData)
        generalList.add(thisData)
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
    fun hasEquipment(
        domain: SlotDomain = SlotDomain.ANY,
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "has_equipment")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("domain", domain.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Tests whether the Subject has the specified mob effect.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) A string value.
     */
    fun hasMobEffect(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String? = null
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "has_mob_effect")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            if (value != null)
                put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true when the subject entity is holding a ranged weapon like a bow or crossbow.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun hasRangedWeapon(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "has_ranged_weapon")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity has the tag provided.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) A string value.
     */
    fun hasTag(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String? = null
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "has_tag")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            value?.let { put("value", it) }
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity has a valid target.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun hasTarget(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "has_target")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Tests whether the target has any trade supply left. Will return false if the target cannot be traded with.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun hasTargetSupply(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "has_trade_supply")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Compares the current 24 hour time with an int value in the range[0, 24000]
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) An integer value.
     */
    fun hourlyClockTime(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Int
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "hourly_clock_time")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is in a caravan.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun inCaravan(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "in_caravan")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true when the subject entity is in the clouds.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun inClouds(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "in_clouds")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true when the subject entity is in lava.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun inLava(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "in_lava")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true when the subject entity is in Nether.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun inNether(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "in_nether")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true when the subject entity is in water.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun inWater(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "in_water")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true when the subject entity is in water or rain.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun inWaterOrRain(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "in_water_or_rain")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Tests if the specified duration in seconds of inactivity for despawning has been reached.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) An integer value.
     */
    fun inactivityTimer(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Int
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "inactivity_timer")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Tests the current altitude against a provided value. 0= bedrock elevation.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The altitude value to compare with
     */
    fun isAltitude(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Int
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_altitude")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is fleeing from other mobs.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value string.
     */
    fun isAvoidingMobs(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_avoiding_mobs")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Tests whether the Subject is currently in the named biome.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The Biome type to test
     */
    fun isBiome(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: BiomeType
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_biome")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value.toString().lowercase())
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true when the block has the given name.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The Family name to look for
     */
    fun isBlock(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_block")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Tests the current brightness against a provided value in the range (0.0f, 1.0f).
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The brightness value to compare with.
     */
    fun isBrightness(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Float
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_brightness")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is climbing.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun isClimbing(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_climbing")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is the named color.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The Palette Color to test
     */
    fun isColor(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: ColorType
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_color")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value.toString().lowercase())
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is climbing.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun isDayTime(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_daytime")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Tests the current difficulty level of the game.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The game's difficulty level to test
     */
    fun isDifficulty(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: DifficultyType
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_difficulty")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value.toString().lowercase())
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Tests the current difficulty level of the game.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The Family name to look for
     */
    fun isFamily(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_family")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
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
    fun isGameRule(
        domain: String,
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_game_rule")
            put("domain", domain)
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Tests whether the Subject is in an area with humidity
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun isHumid(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_humid")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is immobile. An entity is immobile if it lacks AI goals, has just changed dimensions or if it is a mob and has no health.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun isImmobile(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_immobile")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Tests whether the Subject is inside the bounds of a village.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun isInVillage(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_in_village")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is leashed.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun isLeashed(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_leashed")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity leashed to the calling entity.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun isLeashedTo(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_leashed_to")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is the mark variant number provided.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) An integer value.
     */
    fun isMarkVariant(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Int
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_mark_variant")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is moving.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun isMoving(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_moving")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is the owner of the calling entity.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun isOwner(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_owner")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Tests if the subject's persistence matches the bool value passed in.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun isPersistent(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_persistent")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is riding on another entity.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun isRiding(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_riding")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is the skin id number provided.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) An integer value.
     */
    fun isSkinId(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Int
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_skin_id")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Tests whether the Subject is sleeping.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun isSleeping(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_sleeping")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Tests whether the Subject is sleeping.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun isSneaking(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_sneaking")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Tests whether the Subject is in an area with snow cover
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun isSnowCovered(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_snow_covered")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is the target of the calling entity.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun isTarget(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_target")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Tests whether the current temperature is a given type.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The Biome temperature catagory to test
     */
    fun isTemperatureType(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: BiomeTemperatureType
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_temperature_type")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value.toString().lowercase())
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Tests the current temperature against a provided value in the range (0.0, 1.0) where 0.0f is the coldest temp and 1.0f is the hottest.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The Biome temperature value to compare with.
     */
    fun isTemperatureValue(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Float
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_temperature_value")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true when the subject entity is underground. An entity is considered underground if there are non-solid blocks above it.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun isUnderGround(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_underground")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true when the subject entity is under water. An entity is considered underwater if it is completely submerged in water blocks.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun isUnderWater(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_underwater")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is the variant number provided.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) An integer value.
     */
    fun isVariant(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Int
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_variant")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true if the subject entity is visible.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun isVisible(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_visible")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * DEPRECATED
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The Family name to look for
     */
    @Deprecated("docu says so")
    fun isWeather(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "is_weather")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Tests is the mob is outside of the specified light level range (0, 16).
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) An integer value.
     */
    fun lightLevel(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Int
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "light_level")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Compares the current moon intensity with a float value in the range (0.0, 1.0)
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) A floating point value.
     */
    fun moonIntensity(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Float
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "moon_intensity")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Compares the current moon phase with an integer value in the range (0, 7).
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) An integer value.
     */
    fun moonPhase(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Int
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "moon_phase")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Compares the current moon phase with an integer value in the range (0, 7).
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun onGround(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "on_ground")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true when the subject entity is on a ladder.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun onLadder(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "on_ladder")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true if the random chance rolls 0 out of a specified max range.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) An integer value.
     */
    fun randomChance(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Int
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "random_chance")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns the number of riders on this entity.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) An integer value.
     */
    fun riderCount(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Int
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "rider_count")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Tests if the subject is a surface mob.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun surfaceMob(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "surface_mob")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Returns true if the subject is trusted by entity.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Optional) true or false.
     */
    fun trusts(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "trusts")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Tests the current weather in the dimension against a provided weather value.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) The Family name to look for
     */
    fun weather(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "weather")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    /**
     * 0..1
     *
     * Tests the current weather, at the actor's position, against a provided weather value.
     * @param operator (Optional) The comparison to apply with 'value'.
     * @param subject (Optional) The subject of this filter test.
     * @param value (Required) like thunderstorm, rain.
     */
    fun weatherAtPosition(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "weather_at_position")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
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
    fun intProperty(
        domain: String,
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Int
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "int_property")
            put("domain", domain)
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
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
    fun floatProperty(
        domain: String,
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Int
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "float_property")
            put("domain", domain)
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
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
    fun boolProperty(
        domain: String,
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: Boolean = true
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "bool_property")
            put("domain", domain)
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
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
    fun enumProperty(
        domain: String,
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "enum_property")
            put("domain", domain)
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
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
    fun hasProperty(
        operator: String = "equals",
        subject: Subject = Subject.SELF,
        value: String
    ) {
        val thisData = mutableMapOf<String,Any>()
        thisData.apply {
            put("test", "int_property")
            put("operator", operator)
            put("subject", subject.toString().lowercase())
            put("value", value)
        }
        general.putAll(thisData)
        generalList.add(thisData)
    }

    fun allOf(settings: BehEntityFilter.() -> Unit) {
        val behEntityFilter = BehEntityFilter().apply{ settings(this) }

        general.apply {
            put("all_of", behEntityFilter.getDataList())
        }
    }

    fun anyOf(settings: BehEntityFilter.() -> Unit) {
        val behEntityFilter = BehEntityFilter().apply{ settings(this) }

        general.apply {
            put("any_of", behEntityFilter.getDataList())
        }
    }

    fun noneOf(settings: BehEntityFilter.() -> Unit) {
        val behEntityFilter = BehEntityFilter().apply{ settings(this) }

        general.apply {
            put("none_of", behEntityFilter.getDataList())
        }
    }

    fun getData(): MutableMap<String, Any> {
        return general
    }

    private fun getDataList(): ArrayList<Any> {
        return generalList
    }
}