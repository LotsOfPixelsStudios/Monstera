package com.lop.devtools.monstera.files.beh.entitiy

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.files.beh.entitiy.components.*
import com.lop.devtools.monstera.files.beh.entitiy.data.*
import com.lop.devtools.monstera.files.beh.item.comp.Slot
import com.lop.devtools.monstera.files.lang.langKey
import java.awt.Color

@Suppress("MemberVisibilityCanBePrivate")   //access of variables is public by design
class BehEntityComponents : MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        /**
         * access to all defined data
         */
        val general = mutableMapOf<String, Any>()

        val rideAble: ComponentRideable = ComponentRideable()
        val avoidBockType = ComponentAvoidBlock()

        override fun getData(): MutableMap<String, Any> {
            color?.let { general["minecraft:color"] = mutableMapOf("value" to it) }
            color2?.let { general["minecraft:color2"] = mutableMapOf("value" to it) }
            flyingSpeed?.let { general["minecraft:flying_speed"] = mutableMapOf("value" to it) }
            lavaMovement?.let { general["minecraft:lava_movement"] = mutableMapOf("value" to it) }
            movement?.let { general["minecraft:movement"] = mutableMapOf("value" to it) }
            playerExhaustion?.let { general["minecraft:player.exhaustion"] = mutableMapOf("value" to it) }
            playerExperience?.let { general["minecraft:player.experience"] = mutableMapOf("value" to it) }
            playerLevel?.let { general["minecraft:player.level"] = mutableMapOf("value" to it) }
            playerSaturation?.let { general["minecraft:player.saturation"] = mutableMapOf("value" to it) }
            scale?.let { general["minecraft:scale"] = mutableMapOf("value" to it) }
            underWaterMovement?.let { general["minecraft:underwater_movement"] = mutableMapOf("value" to it) }
            defaultLookAngle?.let { general["minecraft:default_look_angle"] = mutableMapOf("value" to it) }
            footSize?.let { general["minecraft:foot_size"] = mutableMapOf("value" to it) }
            frictionModifier?.let { general["minecraft:friction_modifier"] = mutableMapOf("value" to it) }
            groundOffset?.let { general["minecraft:ground_offset"] = mutableMapOf("value" to it) }
            pushTrough?.let { general["minecraft:push_through"] = mutableMapOf("value" to it) }
            soundVolume?.let { general["minecraft:sound_volume"] = mutableMapOf("value" to it) }
            walkAnimationSpeed?.let { general["minecraft:walk_animation_speed"] = mutableMapOf("value" to it) }
            markVariant?.let { general["minecraft:mark_variant"] = mutableMapOf("value" to it) }
            variant?.let { general["minecraft:variant"] = mutableMapOf("value" to it) }
            typeFamilies?.addAll(familyTypes ?: arrayListOf()) ?: run {
                typeFamilies = familyTypes
            }
            typeFamilies?.let { general["minecraft:type_family"] = mutableMapOf("family" to it) }
            if (rideAble.unsafe.getData().isNotEmpty()) {
                general["minecraft:rideable"] = rideAble.unsafe.getData()
            }
            return general
        }
    }

    var color: Int? = null
    var color2: Int? = null
    var flyingSpeed: Float? = null
    var lavaMovement: Float? = null
    var movement: Float? = null
    var playerExhaustion: Number? = null
    var playerExperience: Number? = null
    var playerLevel: Number? = null
    var playerSaturation: Number? = null
    var scale: Float? = null
    var underWaterMovement: Float? = null
    var defaultLookAngle: Float? = null
    var footSize: Float? = null
    var frictionModifier: Float? = null
    var groundOffset: Float? = null
    var pushTrough: Float? = null
    var soundVolume: Float? = null
    var walkAnimationSpeed: Float? = null
    var markVariant: Int? = null
    var variant: Int? = null
    var familyTypes: ArrayList<String>? = null
    var typeFamilies: ArrayList<String>? = null

    /**
     * 0..1
     *
     * @sample behAvoidBlockSample
     */
    fun behAvoidBlock(value: ComponentAvoidBlock.() -> Unit) {
        unsafe.general["minecraft:behavior.avoid_block"] = ComponentAvoidBlock().apply(value).unsafe.getData()
    }

    private fun behAvoidBlockSample() {
        behAvoidBlock {
            //hoglin
            priority = 1
            tickInterval = 5
            searchRange = 8
            searchHeight = 4
            walkSpeedModifier = 1f
            sprintSpeedModifier = 1f
            avoidBlockSound = "retreat" /* or use the sound {} api */
            soundInterval(2f, 5f)
            targetSelectionMethod = "nearest"
            targetBlocks =
                arrayListOf(
                    "minecraft:warped_fungus",
                    "minecraft:portal",
                    "minecraft:respawn_anchor"
                )
            onEscape("escaped_event", Subject.SELF)
        }
    }

    /**
     * 0..1
     *
     * @sample behAvoidMobTypeSample
     */
    fun behAvoidMobType(value: ComponentAvoidMobType.() -> Unit) {
        unsafe.general["minecraft:behavior.avoid_mob_type"] = ComponentAvoidMobType().apply(value).unsafe.getData()
    }

    private fun behAvoidMobTypeSample() {
        behAvoidMobType {
            priority = 1
            maxDist = 2f
            ignoreVisibility = true
            entityTypes {
                type {
                    filters { isFamily(value = "player", subject = Subject.OTHER) }
                    maxDist = 10
                    walkSpeedMultiplier = 0.8f
                    sprintSpeedMultiplier = 1.33f
                }
            }
        }
    }

    /**
     * 0..1
     *
     * @sample sampleHide
     */
    fun behHide(comp: ComponentHide.() -> Unit) {
        unsafe.general["minecraft:behavior.hide"] = ComponentHide().apply(comp).getData()
    }

    private fun sampleHide() {
        behHide {
            duration = 2f
            poiType = ""
            speedMultiplier = 1.2f
            timeoutCooldown = 2f
            priority = 1
        }
    }

    /**
     * 0..1
     *
     * Missing in the wiki.bedrock.dev documentation
     * @sample sampleMoveToPOI
     */
    fun behMoveToPOI(data: ComponentMoveToPOI.() -> Unit) {
        unsafe.general["minecraft:behavior.move_to_poi"] = ComponentMoveToPOI().apply(data).getData()
    }

    private fun sampleMoveToPOI() {
        behMoveToPOI {
            poiType = ""
            speedMultiplier = 1.2f
        }
    }

    /**
     * 0..1
     *
     * @param value
     * @sample sampleAdmireItem
     */
    fun behAdmireItem(
        value: ComponentAdmireItem.() -> Unit
    ) {
        unsafe.general["minecraft:behavior.admire_item"] = ComponentAdmireItem().apply(value).unsafe.getData()
    }

    private fun sampleAdmireItem() {
        behAdmireItem {
            priority = 1
            admireItemSound = "admire" /* or use the sound {} api */
            soundInterval(2.0f, 5.0f)
            onAdmireStart("admire_item_started_event", "self")
            onAdmireStop("admire_item_stopped_event", "self")
        }
    }

    /**
     * 0..1
     *
     * Enables the mob to barter for items that have been configured as barter currency. Must be used in combination with the barter component
     * @see <a href="https://github.com/bedrock-dot-dev/packs/blob/master/stable/behavior/entities/piglin.json</a>
     * @param data, set the priority
     * @sample sampleBarter
     */
    fun behBarter(data: ComponentBarter.() -> Unit) {
        unsafe.general["minecraft:behavior.barter"] = ComponentBarter().apply(data).unsafe.getData()
    }

    private fun sampleBarter() {
        behBarter {
            priority = 2
        }
    }

    /**
     * 0..1
     *
     * @param value
     * @sample sampleBehBeg
     */
    fun behBeg(
        value: ComponentBeg.() -> Unit
    ) {
        unsafe.general["minecraft:behavior.beg"] = ComponentBeg().apply(value).unsafe.getData()
    }

    private fun sampleBehBeg() {
        behBeg {
            priority = 0
            lookDistance = 8f
            lookTime(2f, 4f)
            items = arrayListOf("string", "apple")
        }
    }

    /**
     * 0..1
     *
     * Allows this mob to breed with other mobs.
     * @param data the prio of executing this component, Movement speed multiplier of the mob when using this AI Goal
     * @sample sampleBreed
     */
    fun behBreed(data: ComponentBreed.() -> Unit) {
        unsafe.general["minecraft:behavior.breed"] = ComponentBreed().apply(data).unsafe.getData()
    }

    private fun sampleBreed() {
        behBreed {
            priority = 3
            speedMultiplier = 0.2f
        }
    }

    /**
     * 0..1
     *
     * @param value
     * @sample behCelebrateSample
     */
    fun behCelebrate(value: ComponentCelebrate.() -> Unit) {
        unsafe.general["minecraft:behavior.celebrate"] = ComponentCelebrate().apply(value).unsafe.getData()
    }

    private fun behCelebrateSample() {
        behCelebrate {
            priority = 5
            celebrationSound = "celebrate" /* or use the sound {} api */
            soundInterval(2f, 7f)
            jumpInterval(1f, 3.5f)
            duration = 30f
            onCelebrationEndEvent("minecraft:stop_celebrating", "self")
        }
    }

    /**
     * 0..1
     *
     * @sample sampleChargeAttack
     */
    fun behChargeAttack(data: ComponentChargeAttack.() -> Unit) {
        unsafe.general["minecraft:behavior.charge_attack"] = ComponentChargeAttack().apply(data).unsafe.getData()
    }

    private fun sampleChargeAttack() {
        behChargeAttack {
            priority = 2
        }
    }


    @Deprecated("use singular", ReplaceWith("behChargeHeldItem(data)"))
    fun behChargeHeldItems(data: ComponentChargeHeldItems.() -> Unit) = behChargeHeldItem(data)

    /**
     * 0..1
     *
     * @sample sampleChargeHeldItems
     */
    fun behChargeHeldItem(data: ComponentChargeHeldItems.() -> Unit) {
        unsafe.general["minecraft:behavior.charge_held_item"] = ComponentChargeHeldItems().apply(data).unsafe.getData()
    }

    private fun sampleChargeHeldItems() {
        behChargeHeldItem {
            priority = 2
            items = arrayListOf("bow")
        }
    }

    /**
     * 0..1
     *
     * lets the entity circle around its target
     * used by the phantom
     *
     * note: if the entity has no target, the heightAboveTargetRange is the world level.
     * So if the heightAboveTargetRange is set to 20, the entity will literally go to y = 20 and circly there
     *
     * @sample circleAroundAnchorSample
     */
    fun circleAroundAnchor(value: ComponentCircleAroundAnchor.() -> Unit) {
        unsafe.general["minecraft:behavior.circle_around_anchor"] =
            ComponentCircleAroundAnchor().apply(value).unsafe.getData()
    }

    private fun circleAroundAnchorSample() {
        circleAroundAnchor {
            //phantom
            priority = 3
            radiusChange = 1f
            radiusAdjustmentChance = 0.004f
            heightAdjustmentChance = 0.002857f
            goalRadius = 1f
            goalRadius = 1f
            angleChange = 15f
            radiusRange(5f, 15f)
            heightOffsetRange(-4f, 5f)
            heightAboveTargetRange(20f, 40f)
        }
    }

    /**
     * 0..1
     *
     * @sample sampleControlledByPlayer
     */
    fun behControlledByPlayer(data: ComponentControlledByPlayer.() -> Unit) {
        unsafe.general["minecraft:behavior.controlled_by_player"] =
            ComponentControlledByPlayer().apply(data).unsafe.getData()
    }

    private fun sampleControlledByPlayer() {
        behControlledByPlayer {
            priority = 3
            mountSpeedMultiplier = 1.3f
        }
    }

    /**
     * 0..1
     *
     * @sample behDefendTrustedTargetSample
     */
    fun behDefendTrustedTarget(value: ComponentDefendTrustedTarget.() -> Unit) {
        unsafe.general["minecraft:behavior.defend_trusted_target"] =
            ComponentDefendTrustedTarget().apply(value).getData()
    }

    private fun behDefendTrustedTargetSample() {
        behDefendTrustedTarget {
            priority = 1
            withinRadius = 2f
            mustSee = true
            aggroSound = "sound" /* or use the sound {} api */
            soundChance = 0.02f
            onDefendStart("event", Subject.SELF)
            attackInterval = 2
            mustSeeForgetDuration = 2f
            entityTypes {
                type {

                }
            }
        }
    }

    /**
     * 0..1
     *
     * @sample sampleBehDefendVillage
     */
    fun behDefendVillage(value: ComponentDefendVillage.() -> Unit) {
        unsafe.general["minecraft:behavior.defend_village_target"] =
            ComponentDefendVillage().apply(value).getData()
    }

    private fun sampleBehDefendVillage() {
        behDefendVillage {
            //iron golem
            priority = 1
            mustReach = true
            attackChance = 0.05f
            entityTypes {
                type {
                    filters {
                        anyOf {
                            isFamily(subject = Subject.OTHER, value = "player")
                            isFamily(subject = Subject.OTHER, value = "mob")
                        }
                    }
                }
            }
        }
    }

    /**
     * 0..1
     *
     * @sample delayedAttackSample
     */
    fun delayedAttack(value: ComponentDelayedAttack.() -> Unit) {
        unsafe.general["minecraft:behavior.delayed_attack"] = ComponentDelayedAttack().apply(value).getData()
    }

    private fun delayedAttackSample() {
        delayedAttack {
            //ravager
            priority = 4
            attackOnce = false
            trackTarget = true
            requireCompletePath = false
            randomStopInterval = 0
            reachMultiplier = 1.5f
            speedMultiplier = 1f
            attackDuration = 0.75f
            hitDelayPct = 0.5f
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to open and close doors.
     * @sample doorInteractSample
     */
    fun behDoorInteract(data: ComponentDoorInteract.() -> Unit) {
        unsafe.general["minecraft:behavior.door_interact"] = ComponentDoorInteract().apply(data).getData()
    }

    private fun doorInteractSample() {
        behDoorInteract {
            priority = 3
        }
    }

    /**
     * 0..1
     *
     * Allows the dragon to go out with glory. This controls the Ender Dragon's death animation and can't be used by other mobs.
     * @sample dragonDeathSample
     */
    fun behDragonDeath(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.dragondeath"] = ComponentPriority().apply(data).getData()
    }

    private fun dragonDeathSample() {
        behDragonDeath {
            priority = 2
        }
    }

    /**
     * 0..1
     *
     * Allows the Dragon to fly around in a circle around the center podium. Can only be used by the Ender Dragon.
     * @sample sampleDragonHoldingPattern
     */
    fun behDragonHoldingPattern(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.dragonholdingpattern"] = ComponentPriority().apply(data).getData()
    }

    private fun sampleDragonHoldingPattern() {
        behDragonHoldingPattern {
            priority = 1
        }
    }

    /**
     * 0..1
     *
     * Allows the Dragon to stop flying and transition into perching mode. Can only be used by the Ender Dragon.
     * @sample sampleDragonLanding
     */
    fun behDragonLanding(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.dragonlanding"] = ComponentPriority().apply(data).getData()
    }

    private fun sampleDragonLanding() {
        behDragonLanding {
            priority = 2
        }
    }

    /**
     * 0..1
     *
     * Allows the dragon to look around for a player to attack while in perch mode. Can only be used by the Ender Dragon.
     * @sample sampleDragonScanning
     */
    fun behDragonScanning(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.dragonscanning"] = ComponentPriority().apply(data).getData()
    }

    private fun sampleDragonScanning() {
        behDragonScanning {
            priority = 2
        }
    }

    /**
     * 0..1
     *
     * Allows the dragon to leave perch mode and go back to flying around. Can only be used by the Ender Dragon.
     * @sample sampleDragonTakeOff
     */
    fun behDragonTakeOff(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.dragontakeoff"] = ComponentPriority().apply(data).getData()
    }

    private fun sampleDragonTakeOff() {
        behDragonTakeOff {
            priority = 2
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to drink potions based on specified environment conditions.
     * @sample behDrinkPotionSample
     */
    fun behDrinkPotion(value: ComponentDrinkPotion.() -> Unit) {
        unsafe.general["minecraft:behavior.drink_potion"] = ComponentDrinkPotion().apply(value).getData()
    }

    private fun behDrinkPotionSample() {
        behDrinkPotion {
            priority = 1
            speedModifier = -0.2f
            potions {
                potion {
                    id = 7
                    chance = 1f
                    filters {
                        allOf {
                            anyOf {
                                hourlyClockTime(">=", value = 18000)
                                hourlyClockTime("<", value = 12000)
                            }
                            isVisible()
                            anyOf {
                                isAvoidingMobs(value = "")
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 0..1
     *
     * @sample behDropItemForSample
     */
    fun behDropItemFor(value: ComponentDropItemFor.() -> Unit) {
        unsafe.general["minecraft:behavior.drop_item_for"] = ComponentDropItemFor().apply(value).getData()
    }

    private fun behDropItemForSample() {
        behDropItemFor {
            priority = 1
            secondsBeforePickup = 0f
            cooldown = 0.25f
            dropItemChance = 0.7f
            offeringDistance = 5f
            minimumTeleportDistance = 2f
            maxHeadLookAtHeight = 10f
            targetRange(5f, 5f, 5f)
            teleportOffset(0f, 1f, 0f)
            timeOfDayRange(0.74999f, 0.8f)
            speedMultiplier = 1f
            searchRange = 5f
            searchHeight = 2f
            searchCount = 0
            goalRadius = 1f
            entityTypes {
                type {
                    maxDist = 2f
                    mustSee = false
                    mustForgetDuration = 3f
                    sprintSpeedMultiplier = 1.2f
                    walkSpeedMultiplier = 1f
                    filters {

                    }
                }
            }
            lootTable = "loot_tables/entities/cat_gift.json"
            onDropAttempt("minecraft:cat_gifted_owner", target = "self")
        }
    }

    /**
     * 0..1
     *
     * Used by the sheep to eat grass
     * @sample behEatBlockSample
     */
    fun behEatBlock(value: ComponentEatBlock.() -> Unit) {
        unsafe.general["minecraft:behavior.eat_block"] = ComponentEatBlock().apply(value).getData()
    }

    private fun behEatBlockSample() {
        behEatBlock {
            priority = 6
            successChance = "query.is_baby ? 0.02 : 0.001"
            timeUntilEat = 1.8f
            eatAndReplaceBlockPairs {
                entry("grass", "dirt")
                entry("tallgrass", "air")
            }
            onEat("minecraft:on_eat_block", "self")
        }
    }

    /**
     * 0..1
     *
     * If the mob is carrying a food item, the mob will eat it and the effects will be applied to the mob.
     *
     * -> delayBeforeEating Time in seconds the mob should wait before eating the item.
     * @sample eatCarriedItemSample
     */
    fun behEatCarriedItem(data: ComponentEatCarriedItem.() -> Unit) {
        unsafe.general["minecraft:behavior.eat_carried_item"] = ComponentEatCarriedItem().apply(data).getData()
    }

    private fun eatCarriedItemSample() {
        behEatCarriedItem {
            priority = 2
            delayBeforeEating = 1f
        }
    }

    /**
     * 0..1
     *
     * Allows the enderman to drop a block they are carrying. Can only be used by Endermen.
     * @sample endermanLeaveBlockSample
     */
    fun behEndermanLeaveBlock(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.enderman_leave_block"] = ComponentPriority().apply(data).getData()
    }

    private fun endermanLeaveBlockSample() {
        behEndermanLeaveBlock {
            priority = 2
        }
    }

    /**
     * 0..1
     *
     * Allows the enderman to take a block and carry it around. Can only be used by Endermen.
     * @sample endermanTakeBlockSample
     */
    fun behEndermanTakeBlock(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.enderman_take_block"] = ComponentPriority().apply(data).getData()
    }

    private fun endermanTakeBlockSample() {
        behEndermanTakeBlock {
            priority = 2
        }
    }

    fun behEquipItem(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.equip_item"] = ComponentPriority().apply(data).getData()
    }

    /**
     * 0..1
     *
     * @sample behExploreOutskirtsSample
     */
    fun behExploreOutskirts(value: ComponentExploreOutskirts.() -> Unit) {
        unsafe.general["minecraft:behavior.explore_outskirts"] = ComponentExploreOutskirts().apply(value).getData()
    }

    private fun behExploreOutskirtsSample() {
        behExploreOutskirts {
            priority = 9
            nextXZ = 5
            nextY = 3
            minWaitTime = 3f
            maxWaitTime = 10f
            maxTravelTime = 60f
            speedMultiplier = 0.6f
            exploreDist = 6f
            minPerimeter = 1f
            minDistFromTarget = 2.5f
            timerRatio = 2f
            distFromBoundary(5f, 0f, 5f)
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to seek shade.
     * @sample findCoverSample
     */
    fun behFindCover(data: ComponentFindCover.() -> Unit) {
        unsafe.general["minecraft:behavior.find_cover"] = ComponentFindCover().apply(data).getData()
    }

    private fun findCoverSample() {
        behFindCover {
            priority = 2
            speedMultiplier = 1.2f
            coolDownTime = 2f
        }
    }

    /**
     * 0..1
     *
     * @sample behFindMountSample
     */
    fun behFindMount(value: ComponentFindMount.() -> Unit) {
        unsafe.general["minecraft:behavior.find_mount"] = ComponentFindMount().apply(value).getData()
    }

    private fun behFindMountSample() {
        behFindMount {
            //parrot
            priority = 3
            withInRadius = 16f
            avoidWater = true
            startDelay = 100
            targetNeeded = false
            mountDistance = 2f
            //piglin
            maxFailedAttempts = 20
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to move towards the nearest underwater ruin or shipwreck.
     * @sample findUnderWaterTreasureSample
     */
    fun behFindUnderWaterTreasure(data: ComponentFindUnderWaterTreasure.() -> Unit) {
        unsafe.general["minecraft:behavior.find_underwater_treasure"] =
            ComponentFindUnderWaterTreasure().apply(data).getData()
    }

    private fun findUnderWaterTreasureSample() {
        behFindUnderWaterTreasure {
            priority = 4
            speedMultiplier = 1.2f
            searchRange = 5
            stopDistance = 0.2f
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to run away from direct sunlight and seek shade.
     * @sample fleeSunSample
     */
    fun behFleeSun(data: ComponentFleeSun.() -> Unit) {
        unsafe.general["minecraft:behavior.flee_sun"] = ComponentFleeSun().apply(data).getData()
    }

    private fun fleeSunSample() {
        behFleeSun {
            priority = 1
            speedMultiplier = 1.2f
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to stay afloat while swimming.
     * @sample floatSample
     */
    fun behFloat(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.float"] = ComponentPriority().apply(data).getData()
    }

    private fun floatSample() {
        behFloat {
            priority = 3
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to float around like the Ghast.
     * @sample behFloatWanderSample
     */
    fun behFloatWander(value: ComponentFloatWander.() -> Unit) {
        unsafe.general["minecraft:behavior.float_wander"] = ComponentFloatWander().apply(value).getData()
    }

    private fun behFloatWanderSample() {
        behFloatWander {
            //bat
            xzDist = 10
            yDist = 7
            yOffset = -2f
            randomReselect = true
            floatDuration(0.1f, 0.35f)
            //ghast
            priority = 2
            mustReach = true
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to follow mobs that are in a caravan.
     * @sample behFollowCaravanSample
     */
    fun behFollowCaravan(value: ComponentFollowCaravan.() -> Unit) {
        unsafe.general["minecraft:behavior.follow_caravan"] = ComponentFollowCaravan().apply(value).getData()
    }

    private fun behFollowCaravanSample() {
        behFollowCaravan {
            //llama
            priority = 3
            speedMultiplier = 2.1f
            entityCount = 10
            entityTypes {
                type {
                    filters {
                        isFamily(subject = Subject.OTHER, value = "llama")
                    }
                }
            }
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to follow other mobs.
     * @sample followMobSample
     */
    fun behFollowMob(data: ComponentFollowEntity.() -> Unit) {
        unsafe.general["minecraft:behavior.follow_mob"] = ComponentFollowEntity().apply(data).getData()
    }

    private fun followMobSample() {
        behFollowMob {
            priority = 3
            speedMultiplier = 1.2f
            stopDistance = 3f
            searchRange = 15f
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to follow the player that owns them.
     * @sample followOwnerSample
     */
    fun behFollowOwner(data: ComponentFollowOwner.() -> Unit) {
        unsafe.general["minecraft:behavior.follow_owner"] = ComponentFollowOwner().apply(data).getData()
    }

    private fun followOwnerSample() {
        behFollowOwner {
            priority = 3
            speedMultiplier = 1.2f
            stopDistance = 10f
            startDistance = 1f
        }
    }

    /**
     * 1..0
     *
     * Allows the mob to follow their parent around.
     * @sample followParentSample
     */
    fun behFollowParent(data: ComponentFollowParent.() -> Unit) {
        unsafe.general["minecraft:behavior.follow_parent"] = ComponentFollowParent().apply(data).getData()
    }

    private fun followParentSample() {
        behFollowParent {
            priority = 3
            speedMultiplier = 1.2f
        }
    }

    /**
     * 0..1
     *
     * Allows mob to move towards its current target captain.
     * @sample followTargetCaptainSample
     */
    fun behFollowTargetCaptain(data: ComponentFollowTargetCaptain.() -> Unit) {
        unsafe.general["minecraft:behavior.follow_target_captain"] =
            ComponentFollowTargetCaptain().apply(data).getData()
    }

    private fun followTargetCaptainSample() {
        behFollowTargetCaptain {
            priority = 2
            speedMultiplier = 1.2f
            withinRadius = 5f
            followDistance = 10f
        }
    }

    /**
     * 0..1
     *
     * @sample behGoHomeSample
     */
    fun behGoHome(value: ComponentGoHome.() -> Unit) {
        unsafe.general["minecraft:behavior.go_home"] = ComponentGoHome().apply(value).getData()
    }

    private fun behGoHomeSample() {
        behGoHome {
            //bee
            priority = 4
            speedMultiplier = 1f
            interval = 1
            goalRadius = 1.2f
            onHome {
                addEvent(event = "minecraft:bee_returned_to_hive", target = "block") {
                    anyOf {
                        isBlock(subject = Subject.BLOCK, value = "minecraft:bee_nest")
                        isBlock(subject = Subject.BLOCK, value = "minecraft:beehive")
                    }
                }
                addEvent("find_hive_event", "self") {
                    allOf {
                        isBlock(operator = "!=", subject = Subject.BLOCK, value = "minecraft:bee_nest")
                        isBlock(operator = "!=", subject = Subject.BLOCK, value = "minecraft:beehive")
                    }
                }
            }
            onFailed(event = "find_hive_event", target = "self")
        }
    }

    /**
     * 0..1
     *
     * Attack of the guardian (laser beam)
     * @sample guardianAttackSample
     */
    fun behGuardianAttack(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.guardian_attack"] = ComponentPriority().apply(data).getData()
    }

    private fun guardianAttackSample() {
        behGuardianAttack {
            priority = 3
        }
    }

    /**
     * 0..1
     *
     * Allows the villager to harvest nearby farms. Can only be used by Villagers.
     * @sample harvestFarmBlockSample
     */
    fun behHarvestFarmBlock(data: ComponentHarvestFarmBlock.() -> Unit) {
        unsafe.general["minecraft:behavior.harvest_farm_block"] = ComponentHarvestFarmBlock().apply(data).getData()
    }

    private fun harvestFarmBlockSample() {
        behHarvestFarmBlock {
            priority = 5
            maxSecondsBeforeSearch = 20f
            searchCooldownMaxSeconds = 10f
            secondsUntilNewTask = 30f
            speedMultiplier = 1.2f
        }
    }

    /**
     * 0..1
     *
     * @sample behHoldGroundSample
     */
    fun behHoldGround(value: ComponentHoldGround.() -> Unit) {
        unsafe.general["minecraft:behavior.hold_ground"] = ComponentHoldGround().apply(value).getData()
    }

    private fun behHoldGroundSample() {
        behHoldGround {
            //pillager
            priority = 5
            minRadius = 10f
            broadcast = true
            broadcastRange = 8f
            withinRadiusEvent("minecraft:ranged_mode", Subject.SELF)
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to target another mob that hurts them.
     * @sample behHurtByTargetSample
     */
    fun behHurtByTarget(value: ComponentHurtByTarget.() -> Unit) {
        unsafe.general["minecraft:behavior.hurt_by_target"] = ComponentHurtByTarget().apply(value).getData()
    }

    private fun behHurtByTargetSample() {
        behHurtByTarget {
            //ravager
            priority = 2
            entityTypes {
                type {
                    filters {
                        isFamily(operator = "!=", Subject.OTHER, value = "illager")
                    }
                }
            }
            //llama
            hurtOwner = true
            //silverfish
            alertSameType = true
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to inspect bookshelves.
     * @sample inspectBookshelfSample
     */
    fun behInspectBookshelf(data: ComponentInspectBookshelf.() -> Unit) {
        unsafe.general["minecraft:behavior.inspect_bookshelf"] = ComponentInspectBookshelf().apply(data).getData()
    }

    private fun inspectBookshelfSample() {
        behInspectBookshelf {
            priority = 5
            speedMultiplier = 0.5f
            searchRange = 10f
            searchHeight = 3f
            goalRadius = 8f
            searchCount = 5
        }
    }

    /**
     * 0..1
     *
     * @sample behJumpToBlockSample
     */
    fun behJumpToBlock(value: ComponentJumpToBlock.() -> Unit) {
        unsafe.general["minecraft:behavior.jump_to_block"] = ComponentJumpToBlock().apply(value).getData()
    }

    private fun behJumpToBlockSample() {
        behJumpToBlock {
            //goat
            priority = 8
            searchWidth = 10
            searchHeight = 10
            minimumPathLength = 8
            minimumDistance = 1
            scaleFactor = 0.6f
            cooldownRange(30, 60)
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to perform a damaging knockback that affects all nearby entities.
     * @sample behKnockbackRoarSample
     */
    fun behKnockbackRoar(value: ComponentKnockbackRoar.() -> Unit) {
        unsafe.general["minecraft:behavior.knockback_roar"] = ComponentKnockbackRoar().apply(value).getData()
    }

    private fun behKnockbackRoarSample() {
        behKnockbackRoar {
            //ravager
            priority = 1
            duration = 1f
            attackTime = 0.5f
            knockbackDamage = 6
            knockbackStrength = 3
            knockbackRange = 4
            knockbackFilters { isFamily(operator = "!=", subject = Subject.OTHER, value = "ravager") }
            damageFilters { isFamily(operator = "!=", subject = Subject.OTHER, value = "illager") }
            onRoarEnd("minecraft:end_roar")
            cooldownTime = 0.1f
        }
    }

    /**
     * 0..1
     *
     * Allows mobs to lay down at times
     * -> interval A random value to determine at what intervals something can occur. This has a 1/interval chance to choose this goal
     * -> randomStopInterval a random value in which the goal can use to pull out of the behavior. This is a 1/interval chance to play the sound
     * @sample layDownSample
     */
    fun behLayDown(data: ComponentLayDown.() -> Unit) {
        unsafe.general["minecraft:behavior.inspect_bookshelf"] = ComponentLayDown().apply(data).getData()
    }

    private fun layDownSample() {
        behLayDown {
            priority = 6
            interval = 10
            randomStopInterval = 20
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to lay an egg block on a sand block if the mob is pregnant.
     * @sample layEggSample
     */
    fun behLayEgg(value: ComponentLayEgg.() -> Unit) {
        unsafe.general["minecraft:behavior.lay_egg"] = ComponentLayEgg().apply(value).getData()
    }

    private fun layEggSample() {
        behLayEgg {
            //turtle
            priority = 1
            speedMultiplier = 1f
            searchRange = 16
            searchHeight = 4
            goalRadius = 1.5f
            onLay(event = "minecraft:laid_egg", Subject.SELF)
        }
    }

    /**
     * 0..1
     *
     * -> mustBeOnGround If true, the mob will only jump at its target if its on the ground. Setting it to false will allow it to jump even if its already in the air
     * -> setPersistent Allows the actor to be set to persist upon targeting a player
     * -> yd The height in blocks the mob jumps when leaping at its target
     * @sample leapAtTargetSample
     */
    fun behLeapAtTarget(data: ComponentLeapAtTarget.() -> Unit) {
        unsafe.general["minecraft:behavior.leap_at_target"] = ComponentLeapAtTarget().apply(data).getData()
    }

    private fun leapAtTargetSample() {
        behLeapAtTarget {
            priority = 2
            targetDist = 2f
            yd = 2f
            mustBeOnGround = false
            setPersistent = false
        }
    }

    /**
     * 0..1
     *
     * @sample behLookAtEntitySample
     */
    fun behLookAtEntity(value: ComponentLookAtEntity.() -> Unit) {
        unsafe.general["minecraft:behavior.look_at_entity"] = ComponentLookAtEntity().apply(value).getData()
    }

    private fun behLookAtEntitySample() {
        behLookAtEntity {
            //evocation_illager
            priority = 10
            lookDistance = 8f
            filters { isFamily(subject = Subject.OTHER, value = "mob") }
            //vex
            probability = 0.02f
            //ravager
            angleOfViewHorizontal = 45
        }
    }

    /**
     * 0..1
     *
     * look at the player
     * @sample behLookAtPlayerSample
     */
    fun behLookAtPlayer(value: ComponentLookAtSpecEntity.() -> Unit) {
        unsafe.general["minecraft:behavior.look_at_player"] = ComponentLookAtSpecEntity().apply(value).getData()
    }

    private fun behLookAtPlayerSample() {
        behLookAtPlayer {
            //axolotl
            priority = 10
            targetDistance = 6f
            probability = 0.02f
            //cave_spider
            lookDistance = 6f

            //other
            angleOfViewHorizontal = 20
            angleOfViewVertical = 20
            lookTime = 20f
        }
    }

    /**
     * 0..1
     *
     * @sample behLookAtTargetSample
     */
    fun behLookAtTarget(value: ComponentLookAtSpecEntity.() -> Unit) {
        unsafe.general["minecraft:behavior.look_at_target"] = ComponentLookAtSpecEntity().apply(value).getData()
    }

    private fun behLookAtTargetSample() {
        behLookAtTarget {
            //axolotl
            priority = 10
            targetDistance = 6f
            probability = 0.02f
            //cave_spider
            lookDistance = 6f

            //other
            angleOfViewHorizontal = 20
            angleOfViewVertical = 20
            lookTime = 20f
        }
    }

    /**
     * 0..1
     *
     * @sample behLookAtTradingPlayerSample
     */
    fun behLookAtTradingPlayer(value: ComponentLookAtSpecEntity.() -> Unit) {
        unsafe.general["minecraft:behavior.look_at_target"] = ComponentLookAtSpecEntity().apply(value).getData()
    }

    private fun behLookAtTradingPlayerSample() {
        behLookAtTradingPlayer {
            //axolotl
            priority = 10
            targetDistance = 6f
            probability = 0.02f
            //cave_spider
            lookDistance = 6f

            //other
            angleOfViewHorizontal = 20
            angleOfViewVertical = 20
            lookTime = 20f
        }
    }

    /**
     * 0..1
     *
     * Allows the villager to look for a mate to spawn other villagers with. Can only be used by Villagers.
     * @sample makeLoveSample
     */
    fun behMakeLove(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.make_love"] = ComponentPriority().apply(data).getData()
    }

    private fun makeLoveSample() {
        behMakeLove {
            priority = 2
        }
    }

    /**
     * 0..1
     *
     * lets the entity give a melee attack
     * @sample behMeleeAttackSample
     */
    fun behMeleeAttack(value: ComponentMeleeAttack.() -> Unit) {
        unsafe.general["minecraft:behavior.melee_attack"] = ComponentMeleeAttack().apply(value).getData()
    }

    private fun behMeleeAttackSample() {
        behMeleeAttack {
            //axolotl
            priority = 4
            onKill("killed_enemy_event", Subject.SELF)
            //bee
            attackOnce = true
            speedMultiplier = 1.4f
            onAttack("countdown_to_perish_event", Subject.SELF)
            //cave spider
            randomStopInterval = 100
            reachMultiplier = 0.8f
            trackTarget = true
            //drowned
            requireCompletePath = true
        }
    }

    /**
     * 0..1
     *
     * lets the entity mingle with another
     * @sample behMingleSample
     */
    fun behMingle(value: ComponentMingle.() -> Unit) {
        unsafe.general["minecraft:behavior.mingle"] = ComponentMingle().apply(value).getData()
    }

    private fun behMingleSample() {
        behMingle {
            //villager
            priority = 7
            speedMultiplier = 0.5f
            duration = 30f
            cooldownTime = 10f
            minglePartnerType = arrayListOf("minecraft:villager_v2")
            mingleDistance = 2f
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to move around on its own while mounted seeking a target to attack.
     * @sample mountPathingSample
     */
    fun behMountPathing(data: ComponentMountPathing.() -> Unit) {
        unsafe.general["minecraft:behavior.mount_pathing"] = ComponentMountPathing().apply(data).getData()
    }

    private fun mountPathingSample() {
        behMountPathing {
            priority = 2
            speedMultiplier = 1.2f
            targetDist = 2f
            trackTarget = true
        }
    }

    /**
     * 0..1
     *
     * Can only be used by Villagers. Allows them to seek shelter indoors.
     * -> speedMultiplier Movement speed multiplier of the mob when using this AI Goal
     * -> timeoutCooldown The cooldown time in seconds before the goal can be reused after a internal failure or timeout condition
     * @sample moveIndoorsSample
     */
    fun behMoveIndoors(data: ComponentMoveIndoors.() -> Unit) {
        unsafe.general["minecraft:behavior.move_indoors"] = ComponentMoveIndoors().apply(data).getData()
    }

    private fun moveIndoorsSample() {
        behMoveIndoors {
            priority = 2
            speedMultiplier = 1.2f
            timeoutCooldown = 20f
        }
    }

    /**
     * 0..1
     *
     * Can only be used by Villagers. Allows the villagers to create paths around the village.
     * @sample moveThroughVillageSample
     */
    fun behMoveThroughVillage(data: ComponentMoveThroughVillage.() -> Unit) {
        unsafe.general["minecraft:behavior.move_through_village"] = ComponentMoveThroughVillage().apply(data).getData()
    }

    private fun moveThroughVillageSample() {
        behMoveThroughVillage {
            priority = 2
            speedMultiplier = 0.7f
            onlyAtNight = false
        }
    }

    /**
     * 0..1
     *
     * Can only be used by Villagers. Allows the villagers to create paths around the village.
     * @sample behMoveToBlockSample
     */
    fun behMoveToBlock(value: ComponentMoveToBlock.() -> Unit) {
        unsafe.general["minecraft:behavior.move_to_block"] = ComponentMoveToBlock().apply(value).getData()
    }

    private fun behMoveToBlockSample() {
        behMoveToBlock {
            //bee
            priority = 10
            tickInterval = 1
            startChance = 0.5f
            searchHeight = 4
            searchRange = 6
            goalRadius = 1f
            stayDuration = 20f
            targetSelectionMethod = "random"
            targetOffset(0f, 0.25f, 0f)
            targetBlocks =
                arrayListOf(
                    "minecraft:red_flower",
                    "minecraft:yellow_flower",
                    "minecraft:wither_rose",
                    "minecraft:double_plant:8",
                    "minecraft:double_plant:9",
                    "minecraft:double_plant:12",
                    "minecraft:double_plant:13",
                    "minecraft:flowering_azalea",
                    "minecraft:azalea_leaves_flowered"
                )
            onStayCompleted("collected_nectar", Subject.SELF)
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to move back onto land when in water.
     * @sample moveToLandSample
     */
    fun behMoveToLand(data: ComponentMoveToLand.() -> Unit) {
        unsafe.general["minecraft:behavior.move_to_land"] = ComponentMoveToLand().apply(data).getData()
    }

    private fun moveToLandSample() {
        behMoveToLand {
            priority = 1
            goalRadius = 20f
            searchCount = 3
            searchHeight = 3
            searchRange = 20
            speedMultiplier = 1.2f
        }
    }

    /**
     * 0..1
     *
     * @sample moveToLiquidSample
     */
    fun behMoveToLiquid(data: ComponentMoveToLiquid.() -> Unit) {
        unsafe.general["minecraft:behavior.move_to_liquid"] = ComponentMoveToLiquid().apply(data).getData()
    }

    private fun moveToLiquidSample() {
        behMoveToLiquid {
            priority = 2
            goalRadius = 3f
            searchCount = 3
            searchHeight = 2
            searchRange = 10
            speedMultiplier = 1.2f
        }
    }

    /**
     * 0..1
     *
     * Allows mob to move towards a random block.
     * @sample moveToRandomBLockSample
     */
    fun behMoveToRandomBlock(data: ComponentMoveToRandomBlock.() -> Unit) {
        unsafe.general["minecraft:behavior.move_to_random_block"] = ComponentMoveToRandomBlock().apply(data).getData()
    }

    private fun moveToRandomBLockSample() {
        behMoveToRandomBlock {
            priority = 2
            speedMultiplier = 0.2f
            withinRadius = 5f
            blockDistance = 2f
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to move into a random location within a village.
     * @sample moveToVillageSample
     */
    fun behMoveToVillage(data: ComponentMoveToVillage.() -> Unit) {
        unsafe.general["minecraft:behavior.move_to_village"] = ComponentMoveToVillage().apply(data).getData()
    }

    private fun moveToVillageSample() {
        behMoveToVillage {
            priority = 2
            speedMultiplier = 1.2f
            goalRadius = 20f
            coolDownTime = 10f
            searchRange = 30f
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to move back into lava when on land.
     * @sample moveToWaterSample
     */
    fun behMoveToWater(data: ComponentMoveToWater.() -> Unit) {
        unsafe.general["minecraft:behavior.move_to_water"] = ComponentMoveToWater().apply(data).getData()
    }

    private fun moveToWaterSample() {
        behMoveToWater {
            priority = 1
            speedMultiplier = 1.2f
            searchRange = 10
            searchCount = 3
            searchHeight = 3
            goalRadius = 10f
        }
    }

    /**
     * 0..1
     *
     * Allows Guardians, Iron Golems and Villagers to move within their pre-defined area that the mob should be restricted to. Other mobs don't have a restriction defined.
     * @sample moveTowardsDwellingRestrictionSample
     */
    fun behMoveTowardsDwellingRestriction(data: ComponentPrioSpeed.() -> Unit) {
        unsafe.general["minecraft:behavior.move_towards_dwelling_restriction"] =
            ComponentPrioSpeed().apply(data).getData()
    }

    private fun moveTowardsDwellingRestrictionSample() {
        behMoveTowardsDwellingRestriction {
            priority = 1
            speedMultiplier = 1.2f
        }
    }

    /**
     * 0..1
     *
     * Allows Guardians, Iron Golems and Villagers to move within their pre-defined area that the mob should be restricted to. Other mobs don't have a restriction defined.
     * @sample moveTowardsHomeRestrictionSample
     */
    fun behMoveTowardsHomeRestriction(data: ComponentPrioSpeed.() -> Unit) {
        unsafe.general["minecraft:behavior.move_towards_home_restriction"] = ComponentPrioSpeed().apply(data).getData()
    }

    private fun moveTowardsHomeRestrictionSample() {
        behMoveTowardsHomeRestriction {
            priority = 1
            speedMultiplier = 1.2f
        }
    }

    /**
     * 0..1
     *
     * Allows mob to move towards its current target.
     * @sample moveTowardsTargetSample
     */
    fun behMoveTowardsTarget(data: ComponentMoveTowardsTarget.() -> Unit) {
        unsafe.general["minecraft:behavior.move_towards_target"] = ComponentMoveTowardsTarget().apply(data).getData()
    }

    private fun moveTowardsTargetSample() {
        behMoveTowardsTarget {
            priority = 2
            speedMultiplier = 1.2f
            withinRadius = 20f
        }
    }

    /**
     * 0..1
     *
     * let's enities (like the fox) take a nap
     * @sample behNapSample
     */
    fun behNap(value: ComponentNap.() -> Unit) {
        unsafe.general["minecraft:behavior.nap"] = ComponentNap().apply(value).getData()
    }

    private fun behNapSample() {
        behNap {
            //fox
            priority = 8
            cooldownMin = 2f
            cooldownMax = 7f
            mobDetectDist = 12f
            mobDetectHeight = 6f
            canNapFilters {
                allOf {
                    inWater(value = false)
                    onGround()
                    isUnderGround()
                    weatherAtPosition(operator = "!=", value = "thunderstorm")
                }
            }
            wakeUpFilterExceptions {
                anyOf {
                    trusts(subject = Subject.OTHER)
                    isFamily(subject = Subject.OTHER, value = "fox")
                    isSneaking(subject = Subject.OTHER)
                }
            }
        }
    }

    /**
     * 0..1
     *
     * set a target for entities
     * @sample behNearestAttackableTargetSample
     */
    fun behNearestAttackableTarget(value: ComponentNearestAttackableTarget.() -> Unit) {
        unsafe.general["minecraft:behavior.nearest_attackable_target"] =
            ComponentNearestAttackableTarget().apply(value).getData()
    }

    private fun behNearestAttackableTargetSample() {
        behNearestAttackableTarget {
            //axolotl
            priority = 3
            mustSee = true
            reselectTargets = true
            withinRadius = 20f
            mustSeeForgetDuration = 17f
            entityTypes {
                type {
                    maxDist = 8
                    filters {
                        allOf { inWater() /* ... */ }
                    }
                }
                type {
                    filters {
                        /* ... */
                    }
                }
            }
            //cave spider
            attackInterval = 5
            //guardian
            attackIntervalMin = 1f
            //drowned
            persistTime = 0.5f
            //llama
            mustReach = true
            //phantom
            scanInterval = 20
            targetSearchHeight = 80f
            //other
            setPersistent = true
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to check for and pursue the nearest valid target.
     * @sample behNearestPrioritizedAttackableTargetSample
     */
    fun behNearestPrioritizedAttackableTarget(value: ComponentNearestPrioritizedAttackableTarget.() -> Unit) {
        unsafe.general["minecraft:behavior.nearest_prioritized_attackable_target"] =
            ComponentNearestPrioritizedAttackableTarget().apply(value).getData()
    }

    private fun behNearestPrioritizedAttackableTargetSample() {
        behNearestPrioritizedAttackableTarget {
            //fox
            priority = 6
            attackInterval = 2
            reselectTargets = true
            targetSearchHeight = 5f
            entityTypes {
                type {
                    maxDist = 12
                    priority = 1
                    filters { allOf { isFamily(value = "is_family") } }
                }
            }
            //piglin brute
            persistTime = 2f
            mustSee = true
            //other
            mustReach = true
            mustSeeForgetDuration = 0f
            scanInterval = 1
            setPersistent = false
            withinRadius = 2f
        }
    }

    /**
     * 0..1
     *
     * Allows to mob to be able to sit in place like the ocelot.
     * @sample ocelotSitOnBlockSample
     */
    fun behOcelotSitOnBlock(data: ComponentPrioSpeed.() -> Unit) {
        unsafe.general["minecraft:behavior.ocelot_sit_on_block"] = ComponentPrioSpeed().apply(data).getData()
    }

    private fun ocelotSitOnBlockSample() {
        behOcelotSitOnBlock {
            priority = 2
            speedMultiplier = 0.5f
        }
    }

    /**
     * 0..1
     *
     * the cat/ocelot attack
     * @sample behOcelotAttackSample
     */
    fun behOcelotAttack(value: ComponentOcelotAttack.() -> Unit) {
        unsafe.general["minecraft:behavior.ocelotattack"] = ComponentOcelotAttack().apply(value).getData()
    }

    private fun behOcelotAttackSample() {
        behOcelotAttack {
            //cat
            priority = 4
            cooldownTime = 1f
            xMaxRotation = 30f
            yMaxRotation = 30f
            maxDistance = 15f
            maxSneakRange = 15f
            maxSprintRange = 4f
            reachMultiplier = 2f
            sneakSpeedMultiplier = 0.6f
            sprintSpeedMultiplier = 1.33f
            walkSpeedMultiplier = 0.8f
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to offer the player a flower like the Iron Golem does.
     * @sample offerFlowerSample
     */
    fun behOfferFlower(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.offer_flower"] = ComponentPriority().apply(data).getData()
    }

    private fun offerFlowerSample() {
        behOfferFlower {
            priority = 2
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to open doors. Requires the mob to be able to path through doors, otherwise the mob won't even want to try opening them.
     * @sample openDoorSample
     */
    fun behOpenDoor(data: ComponentOpenDoor.() -> Unit) {
        unsafe.general["minecraft:behavior.open_door"] = ComponentOpenDoor().apply(data).getData()
    }

    private fun openDoorSample() {
        behOpenDoor {
            priority = 2
            closeDoorAfter = false
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to target another mob that hurts their owner.
     * @sample ownerHurtByTargetSample
     */
    fun behOwnerHurtByTarget(value: ComponentOwnerHurtByTarget.() -> Unit) {
        unsafe.general["minecraft:behavior.owner_hurt_by_target"] =
            ComponentOwnerHurtByTarget().apply(value).getData()
    }

    private fun ownerHurtByTargetSample() {
        behOwnerHurtByTarget {
            //wolf
            priority = 1
            //other
            entityTypes {
                type { filters { isFamily(value = "player") } }
            }
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to target a mob that is hurt by their owner.
     * @sample ownerHurtTargetSample
     */
    fun behOwnerHurtTarget(value: ComponentOwnerHurtByTarget.() -> Unit) {
        unsafe.general["minecraft:behavior.owner_hurt_target"] =
            ComponentOwnerHurtByTarget().apply(value).getData()
    }

    private fun ownerHurtTargetSample() {
        behOwnerHurtTarget {
            priority = 2
            entityTypes {
                type { }
            }
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to enter the panic state, which makes it run around and away from the damage source that made it enter this state.
     * @sample behPanicSample
     */
    fun behPanic(value: ComponentPanic.() -> Unit) {
        unsafe.general["minecraft:behavior.panic"] = ComponentPanic().apply(value).getData()
    }

    private fun behPanicSample() {
        behPanic {
            priority = 1
            speedMultiplier = 1.2f
            preferWater = false
            ignoreMobDamage = true
            force = true
            damageSources = arrayListOf(DamageType.ALL)

            //strider
            panicSound = "panic" /* or use the sound {} api */
            soundInterval(1f, 3f)
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to peek out. This is what the shulker uses to look out of its shell.
     * @sample behPeekSample
     */
    fun behPeek(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.peek"] = ComponentPriority().apply(data).getData()
    }

    private fun behPeekSample() {
        behPeek {
            priority = 2
        }
    }

    /**
     * 0..1
     *
     * Allows the pet mob to move onto a bed with it's owner while sleeping.
     * @sample petSleepWithOwnerSample
     */
    fun behPetSleepWithOwner(data: ComponentPetSleepWithOwner.() -> Unit) {
        unsafe.general["minecraft:behavior.pet_sleep_with_owner"] = ComponentPetSleepWithOwner().apply(data).getData()
    }

    private fun petSleepWithOwnerSample() {
        behPetSleepWithOwner {
            priority = 2
            speedMultiplier = 0.8f
            searchHeight = 3f
            searchRadius = 10f
            goalRadius = 10f
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to pick up items on the ground.
     * @sample behPickupItemsSample
     */
    fun behPickupItems(value: ComponentPickupItems.() -> Unit) {
        unsafe.general["minecraft:behavior.pickup_items"] = ComponentPickupItems().apply(value).getData()
    }

    private fun behPickupItemsSample() {
        behPickupItems {
            //piglin
            priority = 6
            maxDist = 10f
            goalRadius = 2f
            speedMultiplier = 0.8f
            pickupBasedOnChance = false
            canPickupAnyItem = false
            cooldownAfterBeingAttacked = 20f
            //other
            trackTarget = true
            canPickUpHandOrEquipment = true
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to play with other baby villagers. This can only be used by Villagers.
     * @sample playSample
     */
    fun behPlay(data: ComponentPrioSpeed.() -> Unit) {
        unsafe.general["minecraft:behavior.play"] = ComponentPrioSpeed().apply(data).getData()
    }

    private fun playSample() {
        behPlay {
            priority = 2
            speedMultiplier = 0.8f
        }
    }

    /**
     * 0..1
     *
     * lets the entity play dead
     * @sample behPlayDeadSample
     */
    fun behPlayDead(value: ComponentPlayDead.() -> Unit) {
        unsafe.general["minecraft:behavior.play_dead"] = value
    }

    private fun behPlayDeadSample() {
        behPlayDead {
            //axolotl
            priority = 0
            duration = 10
            forceBelowHealth = 8
            randomStartChance = 0.33f
            randomDamageRange(0, 2)
            damageSources = arrayListOf(DamageType.CONTACT, DamageType.PROJECTILE /*...*/)
            applyRegeneration = true
            filters {
                inWater()
            }
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to be ridden by the player after being tamed.
     */
    fun behPlayerRideTamed() {
        unsafe.general["minecraft:behavior.player_ride_tamed"] = mutableMapOf<Any, Any>()
    }

    /**
     * 0..1
     *
     * Allows the mob to eat/raid crops out of farms until they are full.
     * @sample behRaidGardenSample
     */
    fun behRaidGarden(value: ComponentRaidGarden.() -> Unit) {
        unsafe.general["minecraft:behavior.raid_garden"] = ComponentRaidGarden().apply(value).getData()
    }

    private fun behRaidGardenSample() {
        behRaidGarden {
            //fox
            priority = 12
            blocks =
                arrayListOf(
                    "minecraft:sweet_berry_bush",
                    "minecraft:cave_vines_head_with_berries",
                    "minecraft:cave_vines_body_with_berries"
                )
            speedMultiplier = 1.2f
            searchRange = 12
            searchHeight = 2
            goalRadius = 0.8f
            maxToEat = 0
            initialEatDelay = 2
        }
    }

    /**
     * 0..1
     *
     * lets the entity attack like a goat
     * @sample behRamAttackSample
     */
    fun behRamAttack(value: ComponentRamAttack.() -> Unit) {
        unsafe.general["minecraft:behavior.ram_attack"] = ComponentRamAttack().apply(value).getData()
    }

    private fun behRamAttackSample() {
        behRamAttack {
            //goat
            priority = 5
            runSpeed = 0.7f
            ramSpeed = 1.8f
            minRamDistance = 4
            ramDistance = 7
            knockbackForce = 2.5f
            knockbackHeight = 0.04f
            preRamSound = "pre_ram" /* or use the sound {} api */
            ramImpactSound = "ram_impact" /* or use the sound {} api */
            cooldownRange(30, 300)
            onStart("start_event", Subject.SELF)
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to randomly break surface of the water.
     * @sample randomBreachSample
     */
    fun behRandomBreach(data: ComponentRandomBreach.() -> Unit) {
        unsafe.general["minecraft:behavior.random_breach"] = ComponentRandomBreach().apply(data).getData()
    }

    private fun randomBreachSample() {
        behRandomBreach {
            priority = 2
            yDist = 3
            xzDist = 3
            interval = 2
            speedMultiplier = 1.2f
            coolDownTime = 20f
        }
    }

    /**
     * 0..1
     *
     * Allows a mob to randomly fly around.
     * @sample randomFlySample
     */
    fun behRandomFly(data: ComponentRandomFly.() -> Unit) {
        unsafe.general["minecraft:behavior.random_fly"] = ComponentRandomFly().apply(data).getData()
    }

    private fun randomFlySample() {
        behRandomFly {
            priority = 2
            xzDist = 15
            yDist = 1
            yOffset = 0f
            speedMultiplier = 1.0f
            canLandOnTrees = true
            avoidDamageBlocks = true
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to hover around randomly, close to the surface
     * @sample behRandomHoverSample
     */
    fun behRandomHover(value: ComponentRandomHover.() -> Unit) {
        unsafe.general["minecraft:behavior.random_hover"] = ComponentRandomHover().apply(value).getData()
    }

    private fun behRandomHoverSample() {
        behRandomHover {
            //bee
            priority = 12
            xzDist = 8
            yDist = 8
            yOffset = -1f
            interval = 1
            hoverHeight(1, 4)
            //other
            speedMultiplier = 1.2f
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to randomly look around.
     * @sample randomLookAroundSample
     */
    fun behRandomLookAround(value: ComponentLookAround.() -> Unit) {
        unsafe.general["minecraft:behavior.random_look_around"] = ComponentLookAround().apply(value).getData()
    }

    private fun randomLookAroundSample() {
        behRandomLookAround {
            priority = 3
            lookTime(2, 5)
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to randomly sit and look around for a duration. Note: Must have a sitting animation set up to use this.
     * @sample randomLookAroundAndSitSample
     */
    fun behRandomLookAroundAndSit(data: ComponentRandomLookAroundAndSit.() -> Unit) {
        unsafe.general["minecraft:behavior.random_look_around_and_sit"] =
            ComponentRandomLookAroundAndSit().apply(data).getData()
    }

    private fun randomLookAroundAndSitSample() {
        behRandomLookAroundAndSit {
            //fox
            priority = 12
            minLookCount = 2
            maxLookCount = 5
            minLookTime = 80
            maxLookTime = 100
            probability = 0.001f
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to randomly sit for a duration.
     * @sample randomSittingSample
     */
    fun behRandomSitting(data: ComponentRandomSitting.() -> Unit) {
        unsafe.general["minecraft:behavior.random_sitting"] = ComponentRandomSitting().apply(data).getData()
    }

    private fun randomSittingSample() {
        behRandomSitting {
            priority = 3
            startChance = 0.002f
            stopChance = 0.1f
            cooldownTime = 20f
            minSitTime = 10f
        }
    }

    /**
     * 0..1
     *
     * Allows a mob to randomly stroll around.
     * @sample randomStrollSample
     */
    fun behRandomStroll(data: ComponentRandomStroll.() -> Unit) {
        unsafe.general["minecraft:behavior.random_stroll"] = ComponentRandomStroll().apply(data).getData()
    }

    private fun randomStrollSample() {
        behRandomStroll {
            priority = 5
            interval = 20
            speedMultiplier = 0.7f
            xzDist = 10
            yDist = 3
        }
    }

    /**
     * 0..1
     *
     * @sample randomSwimSample
     */
    fun behRandomSwim(data: ComponentRandomSwim.() -> Unit) {
        unsafe.general["minecraft:behavior.random_swim"] = ComponentRandomSwim().apply(data).getData()
    }

    private fun randomSwimSample() {
        behRandomSwim {
            priority = 3
            interval = 20
            xzDist = 5
            yDist = 4
            speedMultiplier = 0.7f
            avoidSurface = false
        }
    }

    /**
     * 0..1
     *
     * lets the entity perform a ranged attack,
     * needs something to shoot like shooter()
     * @sample behRangedAttackSample
     */
    fun behRangedAttack(value: ComponentRangedAttack.() -> Unit) {
        unsafe.general["minecraft:behavior.ranged_attack"] = ComponentRangedAttack().apply(value).getData()
    }

    private fun behRangedAttackSample() {
        behRangedAttack {
            //blaze
            priority = 3
            burstShots = 3
            burstInterval = 0.3f
            chargeChargedTrigger = 0f
            chargeShootTrigger = 4f
            attackIntervalMin = 3f
            attackIntervalMax = 5f
            attackRadius = 16f
        }
    }

    /**
     * 0..1
     *
     * Allows the villager to stop so another villager can breed with it. Can only be used by a Villager.
     * @sample receiveLoveSample
     */
    fun behReceiveLove(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.receive_love"] = ComponentPriority().apply(data).getData()
    }

    private fun receiveLoveSample() {
        behReceiveLove {
            priority = 2
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to stay indoors during nighttime.
     * @sample restrictOpenDoorSample
     */
    fun behRestrictOpenDoor(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.restrict_open_door"] = ComponentPriority().apply(data).getData()
    }

    private fun restrictOpenDoorSample() {
        behRestrictOpenDoor {
            priority = 3
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to automatically start avoiding the sun when it's a clear day out.
     * @sample restrictSunSample
     */
    fun behRestrictSun(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.restrict_sun"] = ComponentPriority().apply(data).getData()
    }

    private fun restrictSunSample() {
        behRestrictSun {
            priority = 2
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to stay at a certain level when in liquid.
     * @sample riseToLiquidLevelSample
     */
    fun behRiseToLiquidLevel(data: ComponentRiseToLiquidLevel.() -> Unit) {
        unsafe.general["minecraft:behavior.rise_to_liquid_level"] = ComponentRiseToLiquidLevel().apply(data).getData()
    }

    private fun riseToLiquidLevelSample() {
        behRiseToLiquidLevel {
            priority = 3
            liquidYOffset = -0.2f
            riseDelta = 1f
            sinkDelta = 1f
        }
    }

    /**
     * 0..1
     *
     * This allows the mob to roll forward.
     * @sample rollSample
     */
    fun behRoll(data: ComponentRoll.() -> Unit) {
        unsafe.general["minecraft:behavior.roll"] = ComponentRoll().apply(data).getData()
    }

    private fun rollSample() {
        behRoll {
            priority = 1
            probability = 0.2f
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to run around aimlessly.
     * @sample runAroundLikeCrazySample
     */
    fun behRunAroundLikeCrazy(data: ComponentPrioSpeed.() -> Unit) {
        unsafe.general["minecraft:behavior.run_around_like_crazy"] = ComponentPrioSpeed().apply(data).getData()
    }

    private fun runAroundLikeCrazySample() {
        behRunAroundLikeCrazy {
            priority = 3
            speedMultiplier = 1.2f
        }
    }

    /**
     * 0..1
     *
     * @sample scaredSample
     */
    fun behScared(data: ComponentScared.() -> Unit) {
        unsafe.general["minecraft:behavior.scared"] = ComponentScared().apply(data).getData()
    }

    private fun scaredSample() {
        behScared {
            priority = 3
            soundInterval = 10
        }
    }

    /**
     * 0..1
     *
     * send an event like the evocer to make blue sheeps red
     * @sample behSendEventSample
     */
    fun behSendEvent(value: ComponentSendEvent.() -> Unit) {
        unsafe.general["minecraft:behavior.send_event"] = ComponentSendEvent().apply(value).getData()
    }

    private fun behSendEventSample() {
        behSendEvent {
            //evocation_illager
            priority = 3
            eventChoices {
                choice {
                    minActivationRange = 0f
                    maxActivationRange = 16f
                    cooldownTime = 5f
                    castDuration = 3f
                    //convert the hex string color to a rgb integer for processing
                    particleColor = Color("FFB38033".toLong(16).toInt())

                    weight = 3
                    filters {
                        allOf {
                            isFamily(value = "sheep", subject = Subject.OTHER)
                            isColor(value = ColorType.BLUE, subject = Subject.OTHER)
                        }
                    }
                    startSoundEvent = "cast.spell" /* or use the sound {} api */
                    sequence {
                        seq(2f, "wololo", "prepare.wololo")
                    }
                }
            }
        }
    }

    /**
     * 0..1
     *
     * @sample behShareItemsSample
     */
    fun behShareItems(value: ComponentShareItems.() -> Unit) {
        unsafe.general["minecraft:behavior.share_items"] = ComponentShareItems().apply(value).getData()
    }

    private fun behShareItemsSample() {
        behShareItems {
            //villager
            priority = 8
            maxDist = 3
            goalRadius = 2f
            speedMultiplier = 0.5f
            entityTypes {
                type { filters { isFamily(value = "villager", subject = Subject.OTHER) } }
            }
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to go into stone blocks like Silverfish do. Currently it can only be used by Silverfish.
     * @sample silverFishMergeWithStoneSample
     */
    fun behSilverfishMergeWithStone(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.silverfish_merge_with_stone"] = ComponentPriority().apply(data).getData()
    }

    private fun silverFishMergeWithStoneSample() {
        behSilverfishMergeWithStone {
            priority = 3
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to alert mobs in nearby blocks to come out. Currently it can only be used by Silverfish.
     * @sample silverFishWakeUpFriendsSample
     */
    fun behSilverfishWakeUpFriends(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.silverfish_wake_up_friends"] = ComponentPriority().apply(data).getData()
    }

    private fun silverFishWakeUpFriendsSample() {
        behSilverfishWakeUpFriends {
            priority = 3
        }
    }

    /**
     * 0..1
     *
     * Allows Equine mobs to be Horse Traps and be triggered like them, spawning a lightning bolt and a bunch of horses when a player is nearby.
     * Can only be used by Horses, Mules, Donkeys and Skeleton Horses.
     * @sample skeletonHorseTrapSample
     */
    fun behSkeletonHorseTrap(data: ComponentSkeletonHorseTrap.() -> Unit) {
        unsafe.general["minecraft:behavior.skeleton_horse_trap"] = ComponentSkeletonHorseTrap().apply(data).getData()
    }

    private fun skeletonHorseTrapSample() {
        behSkeletonHorseTrap {
            priority = 3
            withinRadius = 5f
            duration = 3f
        }
    }

    /**
     * 0..1
     *
     * Allows mobs that own a bed to in a village to move to and sleep in it.
     * @sample sleepSample
     */
    fun behSleep(data: ComponentSleep.() -> Unit) {
        unsafe.general["minecraft:behavior.sleep"] = ComponentSleep().apply(data).getData()
    }

    private fun sleepSample() {
        behSleep {
            //villager
            priority = 3
            goalRadius = 1.5f
            speedMultiplier = 0.6f
            sleepColliderHeight = 0.3f
            sleepColliderWidth = 1f
            sleepYOffset = 0.6f
            timeoutCooldown = 10f
            //other
            cooldownTime = 2f
        }
    }

    /**
     * 0..1
     *
     * Can only be used by Slimes and Magma Cubes. Allows the mob to use a melee attack like the slime's.
     * @sample slimeAttackSample
     */
    fun behSlimeAttack(data: ComponentSlimeAttack.() -> Unit) {
        unsafe.general["minecraft:behavior.slime_attack"] = ComponentSlimeAttack().apply(data).getData()
    }

    private fun slimeAttackSample() {
        behSlimeAttack {
            priority = 3
            setPersistent = false
        }
    }

    /**
     * 0..1
     *
     * Can only be used by Slimes and Magma Cubes. Allows the mob to float.
     * @sample slimeFloatSample
     */
    fun behSlimeFloat(data: ComponentSlimeFloat.() -> Unit) {
        unsafe.general["minecraft:behavior.slime_float"] = ComponentSlimeFloat().apply(data).getData()
    }

    private fun slimeFloatSample() {
        behSlimeFloat {
            priority = 3
            jumpChancePercentage = 0.2f
            speedMultiplier = 0.8f
        }
    }

    /**
     * 0..1
     *
     * Can only be used by Slimes and Magma Cubes.
     * @sample slimeKeepJumpingSample
     */
    fun behSlimeKeepJumping(data: ComponentPrioSpeed.() -> Unit) {
        unsafe.general["minecraft:behavior.slime_float"] = ComponentPrioSpeed().apply(data).getData()
    }

    private fun slimeKeepJumpingSample() {
        behSlimeKeepJumping {
            priority = 2
            speedMultiplier = 1f
        }
    }

    /**
     * 0..1
     *
     * Can only be used by Slimes and Magma Cubes.
     * @sample slimeRandomRotationSample
     */
    fun behSlimeRandomDirection(data: ComponentSlimeRandomRotation.() -> Unit) {
        unsafe.general["minecraft:behavior.slime_random_direction"] =
            ComponentSlimeRandomRotation().apply(data).getData()
    }

    @Deprecated("not a valid component", ReplaceWith("behSlimeRandomDirection(data)"))
    fun behSlimeRandomRotation(data: ComponentSlimeRandomRotation.() -> Unit) = behSlimeRandomDirection(data)

    private fun slimeRandomRotationSample() {
        behSlimeRandomDirection {
            priority = 2
            addRandomTimeRange = 2
            turnRange = 20
            minChangeDirectionTime = 0f
        }
    }

    /**
     * 0..1
     *
     * @sample snackingSample
     */
    fun behSnacking(data: ComponentSnacking.() -> Unit) {
        unsafe.general["minecraft:behavior.snacking"] = ComponentSnacking().apply(data).getData()
    }

    private fun snackingSample() {
        behSnacking {
            //panda
            priority = 2
            snackingCooldown = 22.5f
            snackingCooldownMin = 20f
            snackingStopChance = 0.001334f
            items = arrayListOf("bamboo", "cake")
        }
    }

    /**
     * 0..1
     *
     * @sample behSneezeSample
     */
    fun behSneeze(value: ComponentSneeze.() -> Unit) {
        unsafe.general["minecraft:behavior.sneeze"] = ComponentSneeze().apply(value).getData()
    }

    private fun behSneezeSample() {
        behSneeze {
            //panda
            priority = 7
            probability = 0.0001666f
            cooldownTime = 1f
            withinRadius = 10f
            entityTypes {
                type {
                    filters {
                        allOf {
                            hasComponent(value = "minecraft:is_baby", operator = "!=", subject = Subject.OTHER)
                            isFamily(value = "panda", subject = Subject.OTHER)
                            inWater(subject = Subject.OTHER)
                        }
                    }
                    maxDist = 10
                }
            }
            dropItemChance = 0.001f
            lootTable = "loot_tables/entities/panda_sneeze.json"
            prepareSound = "presneeze" /* or use the sound {} api */
            prepareTime = 1f
            sound = "sneeze" /* or use the sound {} api */
        }
    }

    /**
     * 0..1
     *
     * Allows the squid to dive down in water. Can only be used by the Squid.
     * @sample squidDiveSample
     */
    fun behSquidDive(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.squid_dive"] = data
    }

    private fun squidDiveSample() {
        behSquidDive {
            priority = 2
        }
    }

    /**
     * 0..1
     *
     * Allows the squid to swim away. Can only be used by the Squid.
     * @sample behSquidFlee
     */
    fun behSquidFlee(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.squid_flee"] = ComponentPriority().apply(data).getData()
    }

    private fun behSquidFlee() {
        behSquidDive {
            priority = 2
        }
    }

    /**
     * 0..1
     *
     * Allows the squid to swim in place idly. Can only be used by the Squid.
     * @sample squidIdleSample
     */
    fun behSquidIdle(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.squid_idle"] = ComponentPriority().apply(data).getData()
    }

    private fun squidIdleSample() {
        behSquidIdle {
            priority = 2
        }
    }

    /**
     * 0..1
     *
     * Allows the squid to move away from ground blocks and back to water. Can only be used by the Squid.
     * @sample squidMoveAwayFromGroundSample
     */
    fun behSquidMoveAwayFromGround(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.squid_move_away_from_ground"] = ComponentPriority().apply(data).getData()
    }

    private fun squidMoveAwayFromGroundSample() {
        behSquidIdle {
            priority = 2
        }
    }

    /**
     * 0..1
     *
     * Allows the squid to stick to the ground when outside water. Can only be used by the Squid.
     * @sample squidOutOfWaterSample
     */
    fun behSquidOutOfWater(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.squid_out_of_water"] = ComponentPriority().apply(data).getData()
    }

    private fun squidOutOfWaterSample() {
        behSquidIdle {
            priority = 2
        }
    }

    /**
     * 0..1
     *
     * Allows a mob to stalk a target, then once within range pounce onto a target,
     * on success the target will be attacked dealing damage defined by the attack component.
     * On failure, the mob will risk getting stuck
     * @sample behStalkAndPounceOnTargetSample
     */
    fun behStalkAndPounceOnTarget(value: ComponentStalkAndPounceOnTarget.() -> Unit) {
        unsafe.general["minecraft:behavior.stalk_and_pounce_on_target"] =
            ComponentStalkAndPounceOnTarget().apply(value).getData()
    }

    private fun behStalkAndPounceOnTargetSample() {
        behStalkAndPounceOnTarget {
            //fox
            priority = 7
            stalkSpeed = 1.2f
            maxStalkDistance = 12f
            leapHeight = 0.9f
            leapDistance = 0.8f
            pounceMaxDist = 5f
            interestTime = 2f
            stuckTime = 2f
            strikeDist = 2f
            struckBlocks {
                isBlock(subject = Subject.BLOCK, value = "snow_layer")
            }
            //other
            setPersistent = true
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to stay put while it is in a sitting state instead of doing something else.
     * @sample stayWhileSittingSample
     */
    fun behStayWhileSitting(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.stay_while_sitting"] = ComponentPriority().apply(data).getData()
    }

    private fun stayWhileSittingSample() {
        behStayWhileSitting {
            priority = 3
        }
    }

    /**
     * 0..1
     *
     * lets the entity attack like the polar bear
     * @sample stompAttackSample
     */
    fun behStompAttack(data: ComponentStompAttack.() -> Unit) {
        unsafe.general["minecraft:behavior.stomp_attack"] = ComponentStompAttack().apply(data).getData()
    }

    private fun stompAttackSample() {
        behStompAttack {
            priority = 2
            trackTarget = false
            requireCompletePath = false
            stompRangeMultiplier = 2f
            noDamageRangeMultiplier = 1f
        }
    }

    /**
     * 0..1
     *
     * Allows this mob to stomp turtle eggs
     * @sample stompTurtleEggsSample
     */
    fun behStompTurtleEggs(data: ComponentStompTurtleEggs.() -> Unit) {
        unsafe.general["minecraft:behavior.stomp_turtle_egg"] = ComponentStompTurtleEggs().apply(data).getData()
    }

    private fun stompTurtleEggsSample() {
        behStompTurtleEggs {
            priority = 2
            speedMultiplier = 0.9f
            searchRange = 15f
            searchHeight = 3f
            goalRadius = 10f
            interval = 20
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to move into a random location within a village within the search range.
     * @sample strollTowardsVillageSample
     */
    fun behStrollTowardsVillage(data: ComponentStrollTowardsVillage.() -> Unit) {
        unsafe.general["minecraft:behavior.stroll_towards_village"] =
            ComponentStrollTowardsVillage().apply(data).getData()
    }

    private fun strollTowardsVillageSample() {
        behStrollTowardsVillage {
            priority = 4
            speedMultiplier = 0.8f
            searchRange = 20f
            coolDownTime = 10f
            goalRadius = 15f
            startChance = 0.02f
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to attack the player by summoning other entities.
     * @sample behSummonEntitySample
     */
    fun behSummonEntity(value: ComponentSummonEntity.() -> Unit) {
        unsafe.general["minecraft:behavior.summon_entity"] = ComponentSummonEntity().apply(value).getData()
    }

    private fun behSummonEntitySample() {
        behSummonEntity {
            //evocation_illager
            priority = 2
            summonChoices {
                summonChoice {
                    minActivationRange = 0f
                    maxActivationRange = 3f
                    cooldownTime = 5f
                    weight = 3
                    castDuration = 2f
                    particleColor = Color("FF664D59".toLong(16).toInt())
                    startSoundEvent = "cast.spell" /* or use the sound {} api */
                    sequences {
                        sequence {
                            shape = "circle"
                            target = Subject.SELF
                            baseDelay = 1f
                            delayPerSummon = 0f
                            numEntitiesSpawned = 5
                            entityType = "minecraft:evocation_fang"
                            size = 1.5f
                            entityLifeSpan = 1.1f
                            soundEvent = "prepare.attack" /* or use the sound {} api */
                        }
                    }
                    //other
                    doCasting = true
                }
            }
        }
    }

    /**
     * 0..1
     *
     * @sample swellSample
     */
    fun behSwell(data: ComponentSwell.() -> Unit) {
        unsafe.general["minecraft:behavior.stroll_towards_village"] = ComponentSwell().apply(data).getData()
    }

    private fun swellSample() {
        behSwell {
            priority = 2
            startDistance = 2f
            stopDistance = 3f
        }
    }

    /**
     * 0..1
     *
     * @sample swimIdleSample
     */
    fun behSwimIdle(data: ComponentSwimIdle.() -> Unit) {
        unsafe.general["minecraft:behavior.swim_idle"] = ComponentSwimIdle().apply(data).getData()
    }

    private fun swimIdleSample() {
        behSwimIdle {
            priority = 3
            idleTime = 3f
            successRate = 0.2f
        }
    }

    /**
     * 0..1
     *
     * @sample swimWanderSample
     */
    fun behSwimWander(data: ComponentSwimWander.() -> Unit) {
        unsafe.general["minecraft:behavior.swim_wander"] = ComponentSwimWander().apply(data).getData()
    }

    private fun swimWanderSample() {
        behSwimWander {
            priority = 3
            interval = 10
            lookAhead = 5f
            speedMultiplier = 1f
            wanderTime = 10f
        }
    }

    /**
     * 0..1
     *
     * @sample behSwimWithEntitySample
     */
    fun behSwimWithEntity(value: ComponentSwimWithEntity.() -> Unit) {
        unsafe.general["minecraft:behavior.swim_with_entity"] =
            ComponentSwimWithEntity().apply(value).getData()
    }

    private fun behSwimWithEntitySample() {
        behSwimWithEntity {
            //dolphin
            priority = 4
            successRate = 0.1f
            chanceToStop = 0.0333f
            stateCheckInterval = 0.5f
            catchUpThreshold = 12f
            matchDirectionThreshold = 2f
            catchUpMultiplier = 2.5f
            speedMultiplier = 1.5f
            searchRange = 20f
            stopDistance = 5f
            entityTypes {
                type { filters { isFamily(value = "player", subject = Subject.OTHER) } }
            }
        }
    }

    /**
     * 0..1
     *
     * used by the phantom to attack
     * @sample swoopAttackSample
     */
    fun behSwoopAttack(value: ComponentSwoopAttack.() -> Unit) {
        unsafe.general["minecraft:behavior.swoop_attack"] = ComponentSwoopAttack().apply(value).getData()
    }

    private fun swoopAttackSample() {
        behSwoopAttack {
            priority = 2
            damageReach = 0.2f
            speedMultiplier = 1f
            delayRange(10f, 20f)
        }
    }

    /**
     * 0..1
     *
     * Can only be used by Villagers. Allows the mob to accept flowers from Iron Golems.
     * @sample takeFlowerSample
     */
    fun behTakeFlower(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.take_flower"] = ComponentPriority().apply(data).getData()
    }

    private fun takeFlowerSample() {
        behTakeFlower {
            priority = 2
        }
    }

    /**
     * 0..1
     *
     * @sample behTargetWhenPushedSample
     */
    fun behTargetWhenPushed(value: ComponentTargetWhenPushed.() -> Unit) {
        unsafe.general["minecraft:behavior.target_when_pushed"] = ComponentTargetWhenPushed().apply(value).getData()
    }

    private fun behTargetWhenPushedSample() {
        behTargetWhenPushed {
            //iron golem
            priority = 1
            percentChance = 5f
            entityTypes {
                type {
                    filters {
                        allOf {
                            isFamily(value = "monster", subject = Subject.OTHER)
                            isFamily(value = "creeper", operator = "!=", subject = Subject.OTHER)
                        }
                    }
                }
            }
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to be tempted by food they like.
     * @sample temptSample
     */
    fun behTempt(data: ComponentTempt.() -> Unit) {
        unsafe.general["minecraft:behavior.tempt"] = ComponentTempt().apply(data).getData()
    }

    private fun temptSample() {
        behTempt {
            priority = 2
            canGetScared = true
            items = arrayListOf("fish")
            speedMultiplier = 1.2f
            withinRadius = 10f
            canTemptVertically = true
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to look at a player that is holding a tradable item.
     * @sample tradeInterestSample
     */
    fun behTradeInterest(data: ComponentTradeInterest.() -> Unit) {
        unsafe.general["minecraft:behavior.trade_interest"] = ComponentTradeInterest().apply(data).getData()
    }

    private fun tradeInterestSample() {
        behTradeInterest {
            priority = 2
            interestTime = 10f
            removeItemTime = 10f
            carriedItemSwitchTime = 2f
            withinRadius = 5f
            cooldownTime = 2f
            //other
            cooldown = 2f
        }
    }

    /**
     * 0..1
     *
     * Allows the player to trade with this mob.
     * @sample tradeWithPlayerSample
     */
    fun behTradeWithPlayer(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.trade_with_player"] = ComponentPriority().apply(data).getData()
    }

    private fun tradeWithPlayerSample() {
        behTradeWithPlayer {
            priority = 2
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to target the same entity its owner is targeting.
     * call entityType()
     */
    fun behVexCopyOwnerTarget(value: BehEntityTypes.() -> Unit) {
        unsafe.general["minecraft:behavior.vex_copy_owner_target"] = BehEntityTypes().apply(value).getData()
    }

    /**
     * 0..1
     *
     * Allows the mob to move around randomly like the Vex.
     * @sample vexRandomAttackPosGoalSample
     */
    fun behVexRandomAttackPosGoal(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.vex_random_move"] = ComponentPriority().apply(data).getData()
    }

    private fun vexRandomAttackPosGoalSample() {
        behVexRandomAttackPosGoal {
            priority = 2
        }
    }

    /**
     * 0..1
     *
     * Allows the wither to launch random attacks. Can only be used by the Wither Boss.
     * @sample behWitherRandomAttackPosGoalSample
     */
    fun behWitherRandomAttackPosGoal(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.wither_random_attack_pos_goal"] = ComponentPriority().apply(data).getData()
    }

    private fun behWitherRandomAttackPosGoalSample() {
        behWitherRandomAttackPosGoal {
            priority = 2
        }
    }

    /**
     * 0..1
     *
     * @sample witherTargetHighestDamageSample
     */
    fun behWitherTargetHighestDamage(data: ComponentWitherTargetHighestDamage.() -> Unit) {
        unsafe.general["minecraft:behavior.wither_random_attack_pos_goal"] =
            ComponentWitherTargetHighestDamage().apply(data).getData()
    }

    private fun witherTargetHighestDamageSample() {
        behWitherTargetHighestDamage {
            priority = 1
            entityTypes {

            }
        }
    }

    /**
     * 0..1
     *
     * @sample workSample
     */
    fun behWork(data: ComponentWork.() -> Unit) {
        unsafe.general["minecraft:behavior.work"] = ComponentWork().apply(data).getData()
    }

    private fun workSample() {
        behWork {
            //villager
            priority = 7
            activeTime = 250
            speedMultiplier = 0.5f
            goalCooldown = 200
            soundDelayMin = 100
            soundDelayMax = 200
            canWorkInRain = false
            workInRainTolerance = 100
            onArrival("minecraft:resupply_trades", Subject.SELF)
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //Attributes
    //------------------------------------------------------------------------------------------------------------------
    /**
     * 0..1
     *
     * Defines the entity's strength to carry items.
     * @sample strengthSample
     */
    fun strength(data: ComponentStrength.() -> Unit) {
        unsafe.general["minecraft:strength"] = ComponentStrength().apply(data).getData()
    }

    private fun strengthSample() {
        strength {
            //llama
            value = 2
            max = 5
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //Components
    //------------------------------------------------------------------------------------------------------------------

    /**
     * 0..1
     *
     * Adds a rider to the entity. Requires minecraft:rideable.
     * @sample addRiderSample
     */
    fun addRider(data: ComponentAddRider.() -> Unit) {
        unsafe.general["minecraft:addrider"] = ComponentAddRider().apply(data).unsafe.getData()
    }

    private fun addRiderSample() {
        addRider {
            //cave_spider
            entityType = "minecraft:skeleton"
            //other
            spawnEvent = "ride"
        }
    }

    /**
     * 0..1
     *
     * Causes the mob to ignore attackable targets for a given duration.
     * @sample admireItemSample
     */
    fun admireItem(data: ComponentAdmItemComp.() -> Unit) {
        unsafe.general["minecraft:admire_item"] = ComponentAdmItemComp().apply(data).unsafe.getData()
    }

    private fun admireItemSample() {
        admireItem {
            //piglin
            duration = 8
            cooldownAfterBeingAttacked = 20
        }
    }

    /**
     * 0..1
     *
     * Adds a timer for the entity to grow up. It can be accelerated by giving the entity the items it likes as defined by feedItems.
     * @sample ageableSample
     */
    fun ageable(value: ComponentAgeable.() -> Unit) {
        unsafe.general["minecraft:ageable"] = ComponentAgeable().apply(value).unsafe.getData()
    }

    private fun ageableSample() {
        ageable {
            //axolotl
            duration = 1200
            feedItems = arrayListOf("tropical_fish_bucket")
            //or
            feedItemsGrow {
                item("stick", 0.02f)
                item("apple", 0.02f)
            }
            transformToItem = "water_bucket:0"
            growUp("minecraft:ageable_grow_up", Subject.SELF)
            //other
            dropItems = arrayListOf("stick")
        }
    }

    /**
     * 0..1
     *
     * Sets the entity's delay between playing its ambient sound.
     * @sample ambientSoundIntervalSample
     */
    fun ambientSoundInterval(data: ComponentAmbientSoundInterval.() -> Unit) {
        unsafe.general["minecraft:ambient_sound_interval"] =
            ComponentAmbientSoundInterval().apply(data).unsafe.getData()
    }

    private fun ambientSoundIntervalSample() {
        ambientSoundInterval {
            //bee
            eventName = "ambient.pollinate"
            range = 3f
            value = 2f
        }
    }

    /**
     * 0..1
     *
     * Defines the entity's 'angry' state using a timer.
     * @sample angrySample
     */
    fun angry(settings: ComponentAngry.() -> Unit) {
        unsafe.general["minecraft:angry"] = ComponentAngry().apply(settings).unsafe.getData()
    }

    private fun angrySample() {
        angry {
            angrySound = "a" /* or use the sound {} api */
            broadcastAnger = true
            broadCastAngerOnAttack = true
            broadcastAngerOnBeingAttacked = true
            broadcastFilter { }
            broadcastRange = 40
            broadCastTargets = arrayListOf("mob")
            calmEvent("@s calm")
            duration = 25
            durationDelta = 1
            filters { }
            soundInterval(1, 2)
        }
    }

    /**
     * 0..1
     *
     * Allows the mob to break doors
     * @sample breakDoorSample
     */
    fun annBreakDoor(data: ComponentBreakDoor.() -> Unit) {
        unsafe.general["minecraft:annotation.break_door"] = ComponentBreakDoor().apply(data).unsafe.getData()
    }

    private fun breakDoorSample() {
        annBreakDoor {
            breakTime = 2f
            minDifficulty = DifficultyType.NORMAL
        }
    }

    /**
     * 0..1
     *
     * Allows the actor to open doors assuming that that flags set up for the component to use in navigation
     */
    fun annOpenDoor() {
        unsafe.general.apply { put("minecraft:annotation.open_door", mutableMapOf<Any, Any>()) }
    }

    /**
     * 0..1
     *
     * A component that does damage to entities that get within range.
     * @sample areaAttackSample
     */
    fun areaAttack(value: ComponentAreaAttack.() -> Unit) {
        unsafe.general["minecraft:area_attack"] = ComponentAreaAttack().apply(value).unsafe.getData()
    }

    private fun areaAttackSample() {
        areaAttack {
            //pufferfish
            damageRange = 0.2f
            damagePerTick = 2
            cause = DamageType.CONTACT
            entityFilter {
                anyOf {
                    isFamily(value = "player", subject = Subject.OTHER)
                    isFamily(value = "monster", subject = Subject.OTHER)
                }
            }
        }
    }

    /**
     * 0..1
     *
     * Defines an entity's melee attack and any additional effects on it.
     * @sample attackSample
     */
    fun attack(data: ComponentAttack.() -> Unit) {
        unsafe.general["minecraft:attack"] = ComponentAttack().apply(data).unsafe.getData()
    }

    private fun attackSample() {
        attack {
            //either
            damage = 3
            //or
            damageRange = arrayListOf(1, 3)

            //cave spider
            effectName = "poison"
            effectDuration = 0f
        }
    }

    /**
     * 0..1
     *
     * Adds a cooldown to a mob. The intention of this cooldown is to be used to prevent the mob from attempting to aquire new attack targets.
     * @sample attackCooldownSample
     */
    fun attackCooldown(value: ComponentAttackCooldown.() -> Unit) {
        unsafe.general["minecraft:attack_cooldown"] = ComponentAttackCooldown().apply(value).unsafe.getData()
    }

    private fun attackCooldownSample() {
        attackCooldown {
            attackCooldownTime(30f, 120f)
            onComplete("attack_cooldown_complete_event", Subject.SELF)

            //or
            attackCooldownTime = 20f
        }
    }

    /**
     * used by entities that are missing attack(), like the cat
     * @sample attackDamageSample
     */
    fun attackDamage(data: ComponentAttackDamage.() -> Unit) {
        unsafe.general["minecraft:attack_damage"] = ComponentAttackDamage().apply(data).unsafe.getData()
    }

    private fun attackDamageSample() {
        attackDamage {
            value = 5
        }
    }

    /**
     * 0..1
     *
     * lets the entity glide down like a chicken
     * @sample balloonableSample
     */
    fun balloonable(data: ComponentBalloonable.() -> Unit) {
        unsafe.general["minecraft:balloonable"] = ComponentBalloonable().apply(data).unsafe.getData()
    }

    private fun balloonableSample() {
        balloonable {
            mass = 0.5f
        }
    }

    /**
     * 0..1
     *
     * Enables the component to drop an item as a barter exchange.
     * @sample barterSample
     */
    fun barter(data: ComponentBarterComp.() -> Unit) {
        unsafe.general["minecraft:barter"] = ComponentBarterComp().apply(data).unsafe.getData()
    }

    private fun barterSample() {
        barter {
            barterTable = "loot_tables/entities/piglin_barter.json"
            cooldownAfterBeingAttacked = 20
        }
    }

    /**
     * 0..1
     *
     * used by endermite, fox, player and rabbit
     */
    fun blockClimber() {
        unsafe.general["minecraft:block_climber"] = mutableMapOf<String, String>()
    }

    /**
     * 0..1
     *
     * trigger an event if a block is broken
     * @sample blockSensorSample
     */
    fun blockSensor(value: ComponentBlockSensor.() -> Unit) {
        unsafe.general["minecraft:block_sensor"] = ComponentBlockSensor().apply(value).unsafe.getData()
    }

    private fun blockSensorSample() {
        blockSensor {
            //bee
            sensorRadius = 16f
            onBreak {
                entry {
                    blocksList = arrayListOf("minecraft:beehive")
                    onBlockBroken = "hive_destroyed"
                }
                //or
                group(
                    arrayListOf(
                        "minecraft:beehive",
                        "minecraft:bee_nest"
                    ),
                    "hive_destroyed"
                )
            }
        }
    }

    /**
     * 0..1
     *
     * Defines the conditions and behavior of a rideable entity's boost.
     * @sample boostAbleSample
     */
    fun boostAble(value: ComponentBoostItems.() -> Unit) {
        unsafe.general["minecraft:boostable"] = ComponentBoostItems().apply(value).unsafe.getData()
    }

    private fun boostAbleSample() {
        boostAble {
            //pig
            speedMultiplier = 2f
            duration = 3f
            boostItems {
                item {
                    item = "carrotOnAStick"
                    damage = 2
                    replaceItem = "fishing_rod"
                }
                //or
                item("carrotOnAStick", 2, "fishing_rod")
            }
        }
    }

    /**
     * 0..1
     *
     * set the bossbar for an entity
     * @sample bossSample
     */
    fun boss(data: ComponentBoss.() -> Unit) {
        unsafe.general["minecraft:boss"] = ComponentBoss().apply(data).unsafe.getData()
    }

    private fun bossSample() {
        boss {
            shouldDarkenSky = true
            hudRange = 55
            langKey("ender.boss.bar", "Ender dragon")
        }
    }

    /**
     * 0..1
     *
     * Specifies the blocks that this entity can break as it moves around.
     * @sample breakBlocksSample
     */
    fun breakBlocks(data: ComponentBreakBlocks.() -> Unit) {
        unsafe.general["minecraft:break_blocks"] = ComponentBreakBlocks().apply(data).unsafe.getData()
    }

    private fun breakBlocksSample() {
        breakBlocks {
            breakableBlocks = arrayListOf("bamboo")
        }
    }

    /**
     * 0..1
     *
     * Defines what blocks this entity can breathe in and gives them the ability to suffocate.
     * @sample breathableSample
     */
    fun breathable(value: ComponentBreathable.() -> Unit) {
        unsafe.general["minecraft:breathable"] = ComponentBreathable().apply(value).unsafe.getData()
    }

    private fun breathableSample() {
        breathable {
            //axolotl
            totalSupply = 15
            suffocateTime = 0
            breathsWater = true
            breathsAir = true
            generatesBubbles = false
            //other
            breathsLava = true
            breathsSolids = true
            inhaleTime = 2f
            breathBlocks = arrayListOf("minecraft:sand")
            nonBreathBlocks = arrayListOf("minecraft:gravel")
        }
    }

    /**
     * 0..1
     *
     * @param value Defines the way an entity can get into the 'love' state.
     * @sample breedableSample
     */
    fun breedable(value: ComponentBreedable.() -> Unit) {
        unsafe.general["minecraft:breedable"] = ComponentBreedable().apply(value).unsafe.getData()
    }

    private fun breedableSample() {
        breedable {
            //axolotl
            requireTame = false
            breedItems = arrayListOf("tropical_fish_bucket")
            transformToItem = "water_bucket:0"
            breedsWith {
                mateType = "minecraft:axolotl"
                babyType = "minecraft:axolotl"
                breedEvent("minecraft:entity_born", Subject.BABY)
            }
            mutationFactor {
                variant = 0.00083f
            }

            //other
            allowSitting = true
            blendAttributes = true
            breedCooldown = 10f
            causesPregnancy = true
            denyParentsVariant { }
            environmentRequirements { }
            extraBabyChance = 0.2f
            inheritTamed = true
            loveFilters { }
            mutationFactor { }
            requireFullHealth = true
            requireTame = true
        }
    }

    /**
     * 0..1
     *
     * @sample bribeableSample
     */
    fun bribeable(data: ComponentBribeable.() -> Unit) {
        unsafe.general["minecraft:bribeable"] = ComponentBribeable().apply(data).unsafe.getData()
    }

    private fun bribeableSample() {
        bribeable {
            bribeCooldown = 0.2f
            bribeItems = arrayListOf("fish")
        }
    }

    /**
     * 0..1
     *
     * Enables an entity to float on the specified liquid blocks.
     * @sample buoyantSample
     */
    fun buoyant(data: ComponentBuoyant.() -> Unit) {
        unsafe.general["minecraft:buoyant"] = ComponentBuoyant().apply(data).unsafe.getData()
    }

    private fun buoyantSample() {
        buoyant {
            applyGravity = true
            baseBuoyancy = 2f
            bigWaveProbability = 0.2f
            bigWaveSpeed = 1f
            dragDownOnBuoyancyRemoved = 0.2f
            liquidBlocks = arrayListOf("water")
            simulateWave = true
        }
    }

    /**
     * 0..1
     *
     * Specifies if/how a mob burns in daylight.
     */
    fun burnsInDayLight(value: Boolean? = null) {
        if (value != null)
            unsafe.general["minecraft:burns_in_daylight"] = value
        else
            unsafe.general["minecraft:burns_in_daylight"] = mutableMapOf<Any, Any>()
    }

    /**
     * 0..1
     *
     * Allows this entity to climb up ladders.
     */
    fun canClimb() {
        unsafe.general["minecraft:can_climb"] = mutableMapOf<Any, Any>()
    }

    /**
     * 0..1
     *
     * Marks the entity as being able to fly, the pathfinder won't be restricted to paths where a solid block is required underneath it.
     */
    fun canFly() {
        unsafe.general["minecraft:can_fly"] = mutableMapOf<Any, Any>()
    }

    /**
     * 0..1
     *
     * Allows the entity to power jump like the horse does in vanilla.
     */
    fun canPowerJump() {
        unsafe.general["minecraft:can_power_jump"] = mutableMapOf<Any, Any>()
    }

    /**
     * 0..1
     *
     * @sample celebrateHuntSample
     */
    fun celebrateHunt(value: ComponentCelebrateHunt.() -> Unit) {
        unsafe.general["minecraft:celebrate_hunt"] = ComponentCelebrateHunt().apply(value).unsafe.getData()
    }

    private fun celebrateHuntSample() {
        celebrateHunt {
            //piglin
            celebrationTargets { isFamily(value = "hoglin", subject = Subject.OTHER) }
            broadcast = true
            duration = 10
            celebrateSound = "celebrate" /* or use the sound {} api */
            soundInterval(2f, 5f)
            radius = 16
        }
    }

    /**
     * 0..1
     *
     * Sets the width and height of the Entity's collision box.
     * @sample collisionBoxSample
     */
    fun collisionBox(data: ComponentCollisionBox.() -> Unit) {
        unsafe.general["minecraft:collision_box"] = ComponentCollisionBox().apply(data).unsafe.getData()
    }

    private fun collisionBoxSample() {
        collisionBox {
            height = 1f
            width = 1f
        }
    }

    /**
     * 0..1
     *
     * Defines the entity's color. Only works on vanilla entities that have predefined color values (sheep, llama, shulker).
     * @sample colorSample
     */
    fun color(data: ComponentValue.() -> Unit) {
        unsafe.general["minecraft:color"] = ComponentValue().apply(data).getData()
    }

    private fun colorSample() {
        color {
            value = 2
        }
    }

    /**
     * 0..1
     *
     * Defines the entity's color. Only works on vanilla entities that have predefined color values (sheep, llama, shulker).
     * @sample colorSample
     */
    fun color2(data: ComponentValue.() -> Unit) {
        unsafe.general["minecraft:color2"] = ComponentValue().apply(data).getData()
    }

    /**
     * 0..1
     *
     * used by the axolotl to regenerate within a fight
     */
    fun combatRegeneration() {
        unsafe.general["minecraft:combat_regeneration"] = mutableMapOf<Any, Any>()
    }

    /**
     * 0..1
     *
     * @sample conditionalBandwidthOptimizationSample
     */
    fun conditionalBandwidthOptimization(value: ComponentConditionalBandwidthOptimization.() -> Unit) {
        unsafe.general["minecraft:conditional_bandwidth_optimization"] =
            ComponentConditionalBandwidthOptimization().apply(value).unsafe.getData()
    }

    private fun conditionalBandwidthOptimizationSample() {
        conditionalBandwidthOptimization {
            //boat
            defaultValues(maxOptimizedDistance = 60f, maxDroppedTicks = 20, useMotionPrediction = true)
            conditionalValues {
                entry {
                    maxOptimizedDistance = 0f
                    maxDroppedTicks = 0
                    useMotionPredictionHints = true
                    conditionalValues { isMoving() }    //can be called multiple times if filter breaks with allOf/anyOf
                }
            }
        }
    }

    /**
     * 0..1
     *
     * List of hitboxes for melee and ranged hits against the entity.
     * @sample customHitTestSample
     */
    fun customHitTest(value: ComponentCustomHitTest.() -> Unit) {
        unsafe.general.apply {
            put("minecraft:custom_hit_test", ComponentCustomHitTest().apply(value).unsafe.getData())
        }
    }

    private fun customHitTestSample() {
        customHitTest {
            //hoglin
            addHitBox {
                width = 1f
                height = 0.85f
                pivot(0f, 0.5f, 0f)
            }
        }
    }

    /**
     * 0..1
     *
     * Applies defined amount of damage to the entity at specified intervals.
     * @sample damageOverTimeSample
     */
    fun damageOverTime(data: ComponentDamageOverTime.() -> Unit) {
        unsafe.general["minecraft:damage_over_time"] = ComponentDamageOverTime().apply(data).unsafe.getData()
    }

    private fun damageOverTimeSample() {
        damageOverTime {
            damagePerHurt = 2
            timeBetweenHurt = 1.5f
        }
    }

    /**
     * 0..1
     *
     * Defines what events to call when this entity is damaged by specific entities or items.
     * @sample damageSensorSample
     */
    fun damageSensor(value: ComponentDamageSensors.() -> Unit) {
        unsafe.general["minecraft:damage_sensor"] =
            mutableMapOf("triggers" to ComponentDamageSensors().apply(value).getData())
    }

    private fun damageSensorSample() {
        damageSensor {
            trigger {
                //no damage
                cause = DamageType.ALL
                dealsDamage = false

                //axolotl
                cause = DamageType.LIGHTING
                dealsDamage = true
                damageMultiplier = 2000f

                //other
                onDamageSoundEvent = "sound" /* or use the sound {} api */
                onDamage("event") {
                    isFamily(value = "lightning", subject = Subject.OTHER)
                }
                //or
                onDamage {
                    event = "event"
                    filters {
                        isFamily(value = "lightning", subject = Subject.OTHER)
                    }
                }
            }
        }
    }

    /**
     * 0..1
     *
     * Despawns the Actor when the despawn rules or optional filters evaluate to true.
     * @sample despawnSample
     */
    fun despawn(value: ComponentDespawn.() -> Unit) {
        unsafe.general["minecraft:despawn"] = ComponentDespawn().apply(value).getData()
    }

    private fun despawnSample() {
        despawn {
            //fish
            despawnFromDistance(32, 40)
            //other
            despawnFromChance(true, 2000)
            despawnFromInactivity(true, 2000)
            despawnFromSimulationEdge = true
            filters { }
            removeChildEntities = true
        }
    }

    /**
     * 0..1
     *
     * @sample dryingOutTimerSample
     */
    fun dryingOutTimer(value: ComponentDryingOutTimer.() -> Unit) {
        unsafe.general["minecraft:drying_out_timer"] = ComponentDryingOutTimer().apply(value).getData()
    }

    private fun dryingOutTimerSample() {
        dryingOutTimer {
            //dolphin
            totalTime = 120
            waterBottleRefillTime = 0
            driedOutEvent("died_out")
            stoppedDryingOutEvent("stop_dryingout")
            recoverAfterDriedOutEvent("recover_after_dried_out")
        }
    }

    /**
     * 0..1
     *
     * let's the entity dwell like the cat in a village
     * @sample dwellerSample
     */
    fun dweller(data: ComponentDweller.() -> Unit) {
        unsafe.general["minecraft:dweller"] = ComponentDweller().apply(data).getData()
    }

    private fun dwellerSample() {
        dweller {
            //cat
            dwellingType = "village"
            dwellerRole = "passive"
            updateIntervalBase = 60
            updateIntervalVariant = 40
            canFindPoi = false
            canMigrate = true
            firstFoundingReward = 0
        }
    }

    /**
     * 0..1
     *
     * @sample economyTradeTableSample
     */
    fun economyTradeTable(value: ComponentEconomyTradeTable.() -> Unit) {
        unsafe.general["minecraft:economy_trade_table"] = ComponentEconomyTradeTable().apply(value).getData()
    }

    private fun economyTradeTableSample() {
        economyTradeTable {
            //villager
            displayName("entity.villager.farmer", "Farmer")
            table = "trading/economy_trades/farmer_trades.json"
            newScreen = true
            persistTrades = true
            curedDiscount(-100)
            maxCuredDiscount(-500)
            //other
            convertTradesEconomy = true
            heroDemandDiscount = -4
            nearbyCuredDiscount = -25
            showTradeScreen = true

        }
    }

    /**
     * 0..1
     *
     * A component that fires an event when a set of conditions are met by other entities within the defined range.
     * @sample entitySensorSample
     */
    fun entitySensor(value: ComponentEntitySensor.() -> Unit) {
        unsafe.general["minecraft:entity_sensor"] = ComponentEntitySensor().apply(value).getData()
    }

    private fun entitySensorSample() {
        entitySensor {
            //check for player absence
            sensorRange = 10f
            relativeRange = false
            minimumCount = 1     //at least 1 as the sensor works not properly without
            maximumCount = -1    //infinity
            requireAll = true    //look at all entities!
            eventFilters {
                isFamily("!=", Subject.OTHER, "player")
            }
            event = "event_to_trigger"
        }
    }

    /**
     * 0..1
     *
     * Creates a trigger based on environment conditions.
     * @sample environmentSensorSample
     */
    fun environmentSensor(settings: ComponentEnvSensorTriggers.() -> Unit) {
        unsafe.general["minecraft:environment_sensor"] =
            mutableMapOf("triggers" to ComponentEnvSensorTriggers().apply(settings).getData())
    }

    private fun environmentSensorSample() {
        environmentSensor {
            trigger("event_to_trigger") {
                hasTag(value = "test1")
            }
            trigger("event_to_trigger2") {
                allOf {
                    hasTarget()
                    hasTag(value = "test2")
                }
            }
        }
    }

    /**
     * 0..1
     *
     * Sets the Equipment table to use for this Entity.
     * @sample equipmentSample
     */
    fun equipment(value: ComponentEquipment.() -> Unit) {
        unsafe.general["minecraft:equipment"] = ComponentEquipment().apply(value).getData()
    }

    private fun equipmentSample() {
        equipment {
            //drowned
            table = "loot_tables/entities/drowned_ranged_equipment.json"
            slotDropChance(Slot.OFF_HAND, dropChance = 1f)
            //other
            table(Addon.active!!, "equiment") {
                //define equimpent here
                pool { }
            }
        }
    }

    /**
     * 0..1
     *
     * @param value List of slots and the item that can be equipped., call slot()
     * @sample equippableSample
     */
    fun equippable(value: ComponentEquippable.() -> Unit) {
        unsafe.general["minecraft:equippable"] = ComponentEquippable().apply(value).getData()
    }

    private fun equippableSample() {
        equippable {
            //donkey
            slot(
                arrayListOf("saddle"),
                slot = 0,
                item = "saddle",
                onEquip = "minecraft:donkey_saddled",
                onUnequip = "minecraft:donkey_unsaddled"
            )
            //horse
            slot {
                slot = 0
                item = "saddle"
                acceptedItems = arrayListOf("saddle")
                onEquip = "minecraft:horse_saddled"
                onUnequip = "minecraft:horse_unsaddled"
            }
            slot(
                slot = 1, item = "horsearmoriron", acceptedItems = arrayListOf(
                    "horsearmorleather",
                    "horsearmoriron",
                    "horsearmorgold",
                    "horsearmordiamond"
                )
            )
        }
    }

    /**
     * 0..1
     *
     * sample query: "query.last_hit_by_player ? Math.Random(1,3) : 0"
     * @sample experienceRewardSample
     */
    fun experienceReward(data: ComponentExperienceReward.() -> Unit) {
        unsafe.general["minecraft:experience_reward"] = ComponentExperienceReward().apply(data).getData()
    }

    private fun experienceRewardSample() {
        experienceReward {
            //axolotl
            onBred = "Math.Random(1,7)"
            onDeath = "query.last_hit_by_player ? Math.Random(1,3) : 0"
        }
    }

    /**
     * 0..1
     *
     * @sample explodeSample
     */
    fun explode(value: ComponentExplode.() -> Unit) {
        unsafe.general["minecraft:explode"] = ComponentExplode().apply(value).getData()
    }

    private fun explodeSample() {
        explode {
            //creeper
            fuseLength(1.5f)
            fuseLit = true
            power = 3f
            causeFire = false
            destroyAffectedByGriefing = true
            //other
            breakBlocks = false
            fireAffectedByGriefing = false
            maxResistance = 2f
        }
    }

    /**
     * 0..1
     *
     * used by the blaze
     */
    fun fireImmune(value: Boolean = true) {
        unsafe.general["minecraft:fire_immune"] = value
    }

    /**
     * 0..1
     *
     * @sample flockingSample
     */
    fun flocking(value: ComponentFlocking.() -> Unit) {
        unsafe.general["minecraft:flocking"] = ComponentFlocking().apply(value).getData()
    }

    private fun flockingSample() {
        flocking {
            //dolphin
            inWater = false
            matchVariants = false
            useCenterOfMass = false
            lowFlockLimit = 4
            highFlockLimit = 8
            goalWeight = 2f
            lonerChance = 0.1f
            influenceRadius = 6f
            breachInfluence = 0f
            separationWeight = 1.75f
            separationThreshold = 3f
            cohesionWeight = 1.85f
            cohesionThreshold = 3.5f
            innerCohesionThreshold = 3.5f
            minHeight = 4f
            maxHeight = 4f
            blockDistance = 1f
            blockWeight = 0f
        }
    }

    /**
     * 0..1
     *
     * @sample flyingSpeedSample
     */
    fun flyingSpeed(data: ComponentValue.() -> Unit) {
        unsafe.general["minecraft:flying_speed"] = ComponentValue().apply(data).getData()
    }

    private fun flyingSpeedSample() {
        flyingSpeed {
            value = 0.15f
        }
    }

    /**
     * 0..1
     *
     * used by phantom to follow a target
     * @sample followRangeSample
     */
    fun followRange(data: ComponentFollowRange.() -> Unit) {
        unsafe.general["minecraft:follow_range"] = ComponentFollowRange().apply(data).getData()
    }

    private fun followRangeSample() {
        followRange {
            value = 3
            max = 5
        }
    }

    /**
     * 0..1
     *
     * Defines the way a mob's genes and alleles are passed on to it's offspring, and how those traits manifest in the child. Compatible parent genes are crossed together, the alleles are handed down from the parents to the child, and any matching genetic variants fire off JSON events to modify the child and express the traits.
     * @sample geneticsSample
     */
    fun genetics(mutationRate: Float? = null, settings: ComponentGenetics.() -> Unit) {
        val data = mutableMapOf<String, Any>()
        if (mutationRate != null)
            data["mutation_rate"] = mutationRate
        data["genes"] = ComponentGenetics().apply(settings).getData()
        unsafe.general["minecraft:genetics"] = data
    }

    private fun geneticsSample() {
        genetics(0.2f) {
            //goat
            gene {
                name = "goat_variant"
                useSimplifiedBreeding = true
                alleleRange(1, 100)
                geneticVariants {
                    genVar {
                        mainAllele(1, 2)
                        birthEvent("minecraft:born_screamer", Subject.SELF)
                    }
                    genVar {
                        mainAllele(3, 100)
                        birthEvent("minecraft:born_default", Subject.SELF)
                    }
                }
            }
            //other
            gene {
                geneticVariants {
                    genVar {
                        hiddenAllele(3, 100)
                        eitherAllele(3, 100)
                    }
                }
            }
        }
    }

    /**
     * 0..1
     *
     * @sample giveableSample
     */
    fun giveable(value: ComponentGiveable.() -> Unit) {
        unsafe.general["minecraft:giveable"] = mutableMapOf("triggers" to ComponentGiveable().apply(value).getData())
    }

    private fun giveableSample() {
        giveable {
            cooldown = 3f
            items = arrayListOf("bamboo", "cake")
            onGive("minecraft:on_calm", target = Subject.SELF)
        }
    }

    /**
     * 0..1
     *
     * @sample groupSizeSample
     */
    fun groupSize(data: ComponentGroupSize.() -> Unit) {
        unsafe.general["minecraft:group_size"] = ComponentGroupSize().apply(data).getData()
    }

    private fun groupSizeSample() {
        groupSize {
            radius = 32f
            filters {
                allOf {
                    hasComponent("!=", value = "minecraft:is_baby")
                    isFamily(value = "hoglin")
                }
            }
        }
    }

    /**
     * 0..1
     *
     * @sample growsCropSample
     */
    fun growsCrop(data: ComponentGrowsCrop.() -> Unit) {
        unsafe.general["minecraft:grows_crop"] = ComponentGrowsCrop().apply(data).getData()
    }

    private fun growsCropSample() {
        growsCrop {
            chance = 0.2f
            charges = 3
        }
    }

    /**
     * 0..1
     *
     * Defines the interactions with this entity for healing it.
     * @sample healableSample
     */
    fun healable(settings: ComponentHealable.() -> Unit) {
        unsafe.general["minecraft:healable"] = ComponentHealable().apply(settings).getData()
    }

    private fun healableSample() {
        healable {
            //cat
            items {
                item(2, "fish")
                item(2, "salmon")
            }
            //other
            forceUse = true
            filters { }
        }
    }

    /**
     * 0..1
     *
     * healt of the entity
     * @sample healthSample
     */
    fun health(data: ComponentHealth.() -> Unit) {
        unsafe.general["minecraft:health"] = ComponentHealth().apply(data).getData()
    }

    private fun healthSample() {
        health {
            value = 20
            max = 20
            min = 20    //can not die
        }
    }

    /**
     * 0..1
     *
     * used by the villager
     */
    fun hide() {
        unsafe.general["minecraft:hide"] = mutableMapOf<Any, Any>()
    }

    /**
     * 0..1
     *
     * Saves a home pos for when the the entity is spawned.
     * @sample homeSample
     */
    fun home(data: ComponentHome.() -> Unit) {
        unsafe.general["minecraft:home"] = ComponentHome().apply(data).getData()
    }

    private fun homeSample() {
        home {
            restrictionRadius = 22
            homeBlockList = arrayListOf("minecraft:bee_nest", "minecraft:beehive")
        }
    }

    /**
     * 0..1
     *
     * @sample horseJumpStrengthSample
     */
    fun horseJumpStrength(data: ComponentHorseJumpStrength.() -> Unit) {
        unsafe.general["minecraft:horse.jump_strength"] = ComponentHorseJumpStrength().apply(data).getData()
    }

    private fun horseJumpStrengthSample() {
        horseJumpStrength {
            value = 0.5f
        }
    }

    /**
     * 0..1
     *
     * Defines a set of conditions under which an entity should take damage.
     * @sample hurtOnConditionSample
     */
    fun hurtOnCondition(settings: ComponentHurtOnDamage.() -> Unit) {
        unsafe.general["minecraft:hurt_on_condition"] =
            mutableMapOf("damage_conditions" to ComponentHurtOnDamage().apply(settings).getData())
    }

    private fun hurtOnConditionSample() {
        hurtOnCondition {
            //armor_stand
            condition {
                filters {
                    inLava()
                }
                cause = DamageType.LAVA
                damagePerTick = 4
            }
        }
    }

    /**
     * 0..1
     *
     * When configured as a rideable entity, the entity will be controlled using WASD controls.
     */
    fun inputGroundControlled() {
        unsafe.general["minecraft:input_ground_controlled"] = mutableMapOf<Any, Any>()
    }

    /**
     * 0..1
     *
     * When configured as a rideable entity, the entity will be controlled using WASD controls.
     */
    @Deprecated("spelling", ReplaceWith("inputGroundControlled()"))
    fun inputGroundController() {
        inputGroundControlled()
    }

    /**
     * 0..1
     *
     * @sample insideBlockNotifierSample
     */
    fun insideBlockNotifier(value: ComponentInsideBlockNotifier.() -> Unit) {
        unsafe.general["minecraft:inside_block_notifier"] = ComponentInsideBlockNotifier().apply(value).getData()
    }

    private fun insideBlockNotifierSample() {
        insideBlockNotifier {
            //boat
            block {
                blockName = "minecraft:bubble_column"
                blockState(dragDown = true)
                enteredBlockEvent("minecraft:entered_bubble_column_down")
                exitedBlockEvent("minecraft:exited_bubble_column")
            }
        }
    }

    /**
     * 0..1
     *
     * player has 3 days until phantoms can spawn on the player
     * @sample insomniaSample
     */
    fun insomnia(data: ComponentInsomnia.() -> Unit) {
        unsafe.general["minecraft:insomnia"] = ComponentInsomnia().apply(data).getData()
    }

    private fun insomniaSample() {
        insomnia {
            daysUntilInsomnia = 3f
        }
    }

    /**
     * 0..1
     *
     * Despawns the Actor immediately.
     * @sample instantDespawnSample
     */
    fun instantDespawn(data: ComponentInstantDespawn.() -> Unit) {
        unsafe.general["minecraft:instant_despawn"] = ComponentInstantDespawn().apply(data).getData()
    }

    private fun instantDespawnSample() {
        instantDespawn {
            removeChildEntities = true
        }
    }

    /**
     * 0..1
     *
     * Defines interactions with this entity.
     * @sample interactSample
     */
    fun interact(settings: ComponentInteractions.() -> Unit) {
        unsafe.general["minecraft:interact"] = ComponentInteractions().apply(settings).getData()
    }

    private fun interactSample() {
        interact {
            interactGroup {
                addItems = "loot_table"
                cooldown = 2f
                cooldownAfterBeingAttacked = 2f
                hurtItem = 2
                text("action.interact.sample", "sample")
                onInteract {
                    event("minecraft:start_exploding_forced", Subject.SELF)
                    filters {
                        allOf { }
                    }
                }
                particleOnStart()
                playSound = "sound" /* or use the sound {} api */
                spawnEntities = "zombie"
                spawnItems = "loot_table"
                swing = true
                transformToItem = "stick"
                useItem = true
            }
            interactGroup {
                //...
            }
        }
    }

    /**
     * 0..1
     *
     * @sample inventorySample
     */
    fun inventory(value: ComponentInventory.() -> Unit) {
        unsafe.general["minecraft:inventory"] = ComponentInventory().apply(value).getData()
    }

    private fun inventorySample() {
        inventory {
            //minecart
            containerType = "minecart_chest"    //also inventory, minecart_hopper
            inventorySize = 27
            canBeSiphonedFrom = true
            //hopper
            containerType = "minecart_hopper"
            inventorySize = 5
            canBeSiphonedFrom = true
            //other
            containerName("My Container", "entity_name")
            restrictToOwner = false
            additionalSlotPerStrength = 2
        }
    }

    /**
     * 0..1
     *
     * Sets that this entity is a baby.
     */
    fun isBaby() {
        unsafe.general.apply {
            put("minecraft:is_baby", mutableMapOf<Any, Any>())
        }
    }

    /**
     * 0..1
     *
     * Sets that this entity is charged.
     */
    fun isCharged() {
        unsafe.general["minecraft:is_charged"] = mutableMapOf<Any, Any>()
    }

    /**
     * 0..1
     *
     * Sets that this entity is currently carrying a chest.
     */
    fun isChested() {
        unsafe.general["minecraft:is_chested"] = mutableMapOf<Any, Any>()
    }


    /**
     * 0..1
     *
     * Allows dyes to be used on this entity to change its color.
     *
     * ```
     * isDyeAble {
     *     interactText = "action.interact.dye"
     *     //or
     *     interactText("action.interact.dye", display = "Dye Sheep")
     * }
     * ```
     */
    fun isDyeable(data: ComponentIsDyeAble.() -> Unit) {
        unsafe.general["minecraft:is_dyeable"] = ComponentIsDyeAble().apply(data).getData()
    }

    /**
     * 0..1
     *
     * Allows dyes to be used on this entity to change its color.
     */
    @Deprecated("spelling", ReplaceWith("isDyeable(data)"))
    fun isDyeAble(data: ComponentIsDyeAble.() -> Unit) {
        isDyeable(data)
    }

    /**
     * 0..1
     *
     * Sets that this entity can hide from hostile mobs while invisible.
     */
    fun isHiddenWhenInvisible() {
        unsafe.general["minecraft:is_hidden_when_invisible"] = mutableMapOf<Any, Any>()
    }

    /**
     * 0..1
     *
     * Sets that this entity is currently on fire.
     */
    fun isIgnited() {
        unsafe.general["minecraft:is_ignited"] = mutableMapOf<Any, Any>()
    }

    /**
     * 0..1
     *
     * Sets that this entity is an illager captain.
     */
    fun isIllagerCaptain() {
        unsafe.general["minecraft:is_illager_captain"] = mutableMapOf<Any, Any>()
    }

    /**
     * 0..1
     *
     * Sets that this entity is currently saddled.
     */
    fun isSaddled() {
        unsafe.general["minecraft:is_saddled"] = mutableMapOf<Any, Any>()
    }

    /**
     * 0..1
     *
     * Sets that this entity is currently shaking.
     */
    fun isShaking() {
        unsafe.general["minecraft:is_shaking"] = mutableMapOf<Any, Any>()
    }

    /**
     * 0..1
     *
     * Sets that this entity is currently sheared.
     */
    fun isSheared() {
        unsafe.general["minecraft:is_sheared"] = mutableMapOf<Any, Any>()
    }

    /**
     * 0..1
     *
     * Sets that this entity can be stacked
     */
    fun isStackable() {
        unsafe.general["minecraft:is_stackable"] = mutableMapOf<Any, Any>()
    }

    /**
     * 0..1
     *
     * Sets that this entity is currently stunned.
     */
    fun isStunned() {
        unsafe.general["minecraft:is_stunned"] = mutableMapOf<Any, Any>()
    }

    /**
     * 0..1
     *
     * Sets that this entity is currently tamed.
     */
    fun isTamed() {
        unsafe.general["minecraft:is_tamed"] = mutableMapOf<Any, Any>()
    }

    /**
     * 0..1
     *
     * Defines what items can be used to control this entity while ridden
     * @sample itemControllableSample
     */
    fun itemControllable(data: ComponentItemControllable.() -> Unit) {
        unsafe.general["minecraft:item_controllable"] = ComponentItemControllable().apply(data).getData()
    }

    private fun itemControllableSample() {
        itemControllable {
            controlItems = arrayListOf("carrotOnAStick")
        }
    }

    /**
     * 0..1
     *
     * Determines that this entity is an item hopper.
     */
    fun itemHopper() {
        unsafe.general["minecraft:item_hopper"] = mutableMapOf<Any, Any>()
    }

    /**
     * 0..1
     *
     * Defines a dynamic type jump control that will change jump  based on the speed modifier of the mob.
     */
    fun jumpDynamic() {
        unsafe.general["minecraft:jump.dynamic"] = mutableMapOf<Any, Any>()
    }

    /**
     * 0..1
     *
     * Gives the entity the ability to jump.
     * @sample jumpStaticSample
     */
    fun jumpStatic(data: ComponentJumpStatic.() -> Unit) {
        unsafe.general["minecraft:jump.static"] = ComponentJumpStatic().apply(data).getData()
    }

    private fun jumpStaticSample() {
        jumpStatic {
            jumpPower = 0.2f
        }
    }

    /**
     * 0..1
     *
     * @sample knockbackResistanceSample
     */
    fun knockbackResistance(data: ComponentKnockbackResistance.() -> Unit) {
        unsafe.general["minecraft:knockback_resistance"] = ComponentKnockbackResistance().apply(data).getData()
    }

    private fun knockbackResistanceSample() {
        knockbackResistance {
            //armorstand
            value = 1f
            //ender dragon
            valOfMax(100, max = 100)
        }
    }

    /**
     * 0..1
     *
     * lets the entity move in lava like the strider
     * @sample lavaMovementSample
     */
    fun lavaMovement(data: ComponentValue.() -> Unit) {
        unsafe.general["minecraft:lava_movement"] = ComponentValue().apply(data).getData()
    }

    private fun lavaMovementSample() {
        lavaMovement {
            value = 0.32f
        }
    }

    /**
     * 0..1
     *
     * Allows this entity to be leashed and defines the conditions and events for this entity when is leashed.
     * @sample leashableSample
     */
    fun leashable(value: ComponentLeashable.() -> Unit) {
        unsafe.general["minecraft:leashable"] = ComponentLeashable().apply(value).getData()
    }

    private fun leashableSample() {
        leashable {
            //axolotl
            softDistance = 4f
            hardDistance = 6f
            maxDistance = 10f
            //other
            canBeStolen = true
            onLeash = "leashed"
            onUnLeash = "unleashed"
        }
    }

    /**
     * 0..1
     *
     * Defines the behavior when another entity looks at this entity.
     * @sample lookAtSample
     */
    fun lookAt(value: ComponentLookAt.() -> Unit) {
        unsafe.general["minecraft:lookat"] = ComponentLookAt().apply(value).getData()
    }

    private fun lookAtSample() {
        lookAt {
            //enderman
            searchRadius = 64f
            setTarget = true
            lookCooldown(5f)
            filters {
                allOf {
                    isFamily(subject = Subject.OTHER, value = "player")
                    hasEquipment(
                        domain = SlotDomain.HEAD,
                        subject = Subject.OTHER,
                        operator = "!=",
                        value = "carved_pumpkin"
                    )
                }
            }
            //other
            allowInvulnerable = false
            lookEvent = "event"
        }
    }

    /**
     * 0..1
     *
     * Sets the loot table for what items this entity drops upon death.
     * @sample lootSample
     */
    fun loot(data: ComponentLoot.() -> Unit) {
        unsafe.general["minecraft:loot"] = ComponentLoot().apply(data).getData()
    }

    private fun lootSample() {
        loot {
            table = "loot_tables/entities/armor_stand.json"
            //or
            genTable("armor_stand", Addon.active!!) {
                pool { }
            }
        }
    }

    /**
     * 0..1
     *
     * This component is used to implement part of the Wandering Trader behavior
     */
    fun managedWanderingTrader() {
        unsafe.general["minecraft:managed_wandering_trader"] = mutableMapOf<Any, Any>()
    }

    /**
     * 0..1
     *
     * Additional variant value. Can be used to further differentiate variants.
     * @param value The ID of the variant. By convention, 0 is the ID of the base entity
     */
    fun markVariant(value: Int) {
        unsafe.general["minecraft:mark_variant"] = mutableMapOf("value" to value)
    }

    /**
     * 0..1
     *
     * A component that applies a mob effect to entities that get within range.
     * @sample mobEffectSample
     */
    fun mobEffect(value: ComponentMobEffect.() -> Unit) {
        unsafe.general["minecraft:mob_effect"] = ComponentMobEffect().apply(value).getData()
    }

    private fun mobEffectSample() {
        mobEffect {
            //pufferfish
            effectRange = 0.1f
            mobEffect = "poison"
            effectTime = 10
            entityFilter {
                anyOf {
                    isFamily(value = "player", subject = Subject.OTHER)
                    isFamily(value = "monster", subject = Subject.OTHER)
                }
            }
        }
    }

    /**
     * 0..1
     *
     * movement speed, a normal speed is 0.2f
     * @sample movementSample
     */
    fun movement(data: ComponentValue.() -> Unit) {
        unsafe.general["minecraft:movement"] = ComponentValue().apply(data).getData()
    }

    private fun movementSample() {
        movement {
            value = 0.1f
        }
    }

    /**
     * 0..1
     *
     * This move control allows the mob to swim in water and walk on land.
     * @sample movementGneSample
     */
    fun movementAmphibious(data: ComponentMaxTurn.() -> Unit) {
        unsafe.general["minecraft:movement.amphibious"] = ComponentMaxTurn().apply(data).getData()
    }

    /**
     * 0..1
     *
     * This component accents the movement of an entity.
     * @sample movementGneSample
     */
    fun movementBasic(data: ComponentMaxTurn.() -> Unit) {
        unsafe.general["minecraft:movement.basic"] = ComponentMaxTurn().apply(data).getData()
    }

    /**
     * 0..1
     *
     * This move control causes the mob to fly.
     * @sample movementGneSample
     */
    fun movementFly(data: ComponentMaxTurn.() -> Unit) {
        unsafe.general["minecraft:movement.fly"] = ComponentMaxTurn().apply(data).getData()
    }

    /**
     * 0..1
     *
     * This move control allows a mob to fly, swim, climb, etc.
     * @sample movementGneSample
     */
    fun movementGeneric(data: ComponentMaxTurn.() -> Unit) {
        unsafe.general["minecraft:movement.generic"] = ComponentMaxTurn().apply(data).getData()
    }

    /**
     * 0..1
     *
     * @sample movementGlideSample
     */
    fun movementGlide(data: ComponentMovementGlide.() -> Unit) {
        unsafe.general["minecraft:movement.glide"] = ComponentMovementGlide().apply(data).getData()
    }

    private fun movementGlideSample() {
        movementGlide {
            startSpeed = 0.1f
            speedWhenTurning = 0.2f
            //other
            maxTurn = 15f
        }
    }

    /**
     * 0..1
     *
     * This move control causes the mob to hover.
     * @sample movementGneSample
     */
    fun movementHover(data: ComponentMaxTurn.() -> Unit) {
        unsafe.general["minecraft:movement.hover"] = ComponentMaxTurn().apply(data).getData()
    }

    /**
     * 0..1
     *
     * Move control that causes the mob to jump as it moves with a specified delay between jumps.
     * @sample movementJumpSample
     */
    fun movementJump(value: ComponentMovementJump.() -> Unit) {
        unsafe.general["minecraft:movement.jump"] = ComponentMovementJump().apply(value).getData()
    }

    private fun movementJumpSample() {
        movementJump {
            maxTurn = 15f
            jumpDelay(2f, 6f)
        }
    }

    /**
     * 0..1
     *
     * This move control causes the mob to hop as it moves.
     * @sample movementGneSample
     */
    fun movementSkip(data: ComponentMaxTurn.() -> Unit) {
        unsafe.general["minecraft:movement.skip"] = ComponentMaxTurn().apply(data).getData()
    }

    private fun movementGneSample() {
        movementBasic {
            maxTurn = 15f
        }
    }

    /**
     * 0..1
     *
     * This move control causes the mob to sway side to side giving the impression it is swimming.
     * @sample movementSwaySample
     */
    fun movementSway(data: ComponentMovementSway.() -> Unit) {
        unsafe.general["minecraft:movement.sway"] = ComponentMovementSway().apply(data).getData()
    }

    private fun movementSwaySample() {
        movementSway {
            maxTurn = 15f
            swayAmplitude = 0f
        }
    }

    /**
     * Allows this entity to be named (e.g. using a name tag).
     *
     * ```
     * nameable {
     *     //vindicator
     *     defaultTrigger("minecraft:stop_johnny", Subject.SELF)
     *     nameActions {
     *         nameAction("Johnny", "minecraft:start_johnny", Subject.SELF)
     *     }
     *     //other
     *     allowNameTagRenaming = true
     *     alwaysShow = true
     * }
     * ```
     */
    fun nameable(value: ComponentNameable.() -> Unit) {
        unsafe.general["minecraft:nameable"] = ComponentNameable().apply(value).getData()
    }

    private fun navigationSample() {
        navigationClimb {
            avoidPortals = true
            avoidSun = true
            avoidWater = true
            canBreakDoors = true
            canOpenDoors = true
            canOpenIronDoors = true
            canPassDoors = true
            canPathFromAir = true
            canPathOverWater = true
            canPathOverLava = true
            canSink = true
            canWalkInLava = true
            isAmphibious = true
            canSwim = true
            canWalk = true
            avoidDamageBlocks = true
            canBreach = true
            canFloat = true
            blocksToAvoid = arrayListOf("minecraft:sand")
        }
    }

    /**
     * 0..1
     *
     * Allows this entity to generate paths that include vertical walls like the vanilla Spiders do.
     * @sample navigationSample
     */
    fun navigationClimb(value: ComponentNavigation.() -> Unit) {
        unsafe.general["minecraft:navigation.climb"] = ComponentNavigation().apply(value).getData()
    }

    /**
     * 0..1
     *
     * Allows this entity to generate paths by flying around the air like the regular Ghast.
     * @sample navigationSample
     */
    fun navigationFloat(
        value: ComponentNavigation.() -> Unit
    ) {
        unsafe.general["minecraft:navigation.float"] = ComponentNavigation().apply(value).getData()
    }

    /**
     * 0..1
     *
     * Allows this entity to generate paths in the air like the vanilla Parrots do.
     * @sample navigationSample
     */
    fun navigationFly(value: ComponentNavigation.() -> Unit) {
        unsafe.general["minecraft:navigation.fly"] = ComponentNavigation().apply(value).getData()
    }

    /**
     * 0..1
     *
     * Allows this entity to generate paths by walking, swimming, flying and/or climbing around and jumping up and down a block.
     * @sample navigationSample
     */
    fun navigationGeneric(value: ComponentNavigation.() -> Unit) {
        unsafe.general["minecraft:navigation.generic"] = ComponentNavigation().apply(value).getData()
    }

    /**
     * 0..1
     *
     * Allows this entity to generate paths in the air like the vanilla Bees do. Keeps them from falling out of the skies and doing predictive movement.
     * @sample navigationSample
     */
    fun navigationHover(value: ComponentNavigation.() -> Unit) {
        unsafe.general["minecraft:navigation.hover"] = ComponentNavigation().apply(value).getData()
    }

    /**
     * 0..1
     *
     * Allows this entity to generate paths that include water.
     * @sample navigationSample
     */
    fun navigationSwim(value: ComponentNavigation.() -> Unit) {
        unsafe.general["minecraft:navigation.swim"] = ComponentNavigation().apply(value).getData()
    }

    /**
     * 0..1
     *
     * Allows this entity to generate paths by walking around and jumping up and down a block like regular mobs.
     * @sample navigationSample
     */
    fun navigationWalk(value: ComponentNavigation.() -> Unit) {
        unsafe.general["minecraft:navigation.walk"] = ComponentNavigation().apply(value).getData()
    }

    /**
     * 0..1
     *
     * @sample npcSample
     */
    fun npc(npcData: ComponentNpc.() -> Unit) {
        unsafe.general["minecraft:npc"] = mutableMapOf("npc_data" to ComponentNpc().apply(npcData).getData())
    }

    private fun npcSample() {
        npc {
            //npc
            portraitOffsets(translate = Triple(-7, 50, 0), scale = Triple(1.75f, 1.75f, 1.75f))
            pickerOffsets(translate = Triple(0, 20, 0), scale = Triple(1.7f, 1.7f, 1.7f))
            skinList {
                addVariant(0)
                addVariant(1)
                addVariant(2)
                addVariant(3)
                addVariant(4)
                //...
                addVariant(19)
            }
        }
    }

    /**
     * 0..1
     *
     * Only usable by the Ender Dragon. Adds a trigger to call on this entity's death.
     * @sample onEventSample
     */
    fun onDeath(data: ComponentOnEvent.() -> Unit) {
        unsafe.general["minecraft:on_death"] = ComponentOnEvent().apply(data).getData()
    }

    /**
     * 0..1
     *
     * Adds a trigger that will run when a nearby entity of the same type as this entity becomes Angry.
     * @sample onEventSample
     */
    fun onFriendlyAnger(data: ComponentOnEvent.() -> Unit) {
        unsafe.general["minecraft:on_friendly_anger"] = ComponentOnEvent().apply(data).getData()
    }

    /**
     * 0..1
     *
     * Adds a trigger to call when this entity takes damage.
     * @sample onEventSample
     */
    fun onHurt(data: ComponentOnEvent.() -> Unit) {
        unsafe.general["minecraft:on_hurt"] = ComponentOnEvent().apply(data).getData()
    }

    /**
     * 0..1
     *
     * Adds a trigger to call when this entity is attacked by the player.
     * @sample onEventSample
     */
    fun onHurtByPlayer(data: ComponentOnEvent.() -> Unit) {
        unsafe.general["minecraft:on_hurt_by_player"] = ComponentOnEvent().apply(data).getData()
    }

    /**
     * 0..1
     *
     * Adds a trigger to call when this entity is set on fire
     * @sample onEventSample
     */
    fun onIgnite(data: ComponentOnEvent.() -> Unit) {
        unsafe.general["minecraft:on_ignite"] = ComponentOnEvent().apply(data).getData()
    }

    /**
     * 0..1
     *
     * Only usable by the Ender Dragon. Adds a trigger to call when this entity lands.
     * @sample onEventSample
     */
    fun onStartLanding(data: ComponentOnEvent.() -> Unit) {
        unsafe.general["minecraft:on_start_landing"] = ComponentOnEvent().apply(data).getData()
    }

    /**
     * 0..1
     *
     * Only usable by the Ender Dragon. Adds a trigger to call when this entity starts flying.
     * @sample onEventSample
     */
    fun onStartTakeoff(data: ComponentOnEvent.() -> Unit) {
        unsafe.general["minecraft:on_start_takeoff"] = ComponentOnEvent().apply(data).getData()
    }

    /**
     * 0..1
     *
     * Adds a trigger to call when this entity finds a target.
     * @sample onEventSample
     */
    fun onTargetAcquired(data: ComponentOnEvent.() -> Unit) {
        unsafe.general["minecraft:on_target_acquired"] = ComponentOnEvent().apply(data).getData()
    }

    /**
     * 0..1
     *
     * Adds a trigger to call when this entity loses the target it currently has.
     * @sample onEventSample
     */
    fun onTargetEscape(data: ComponentOnEvent.() -> Unit) {
        unsafe.general["minecraft:on_target_escape"] = ComponentOnEvent().apply(data).getData()
    }

    private fun onEventSample() {
        onTargetEscape {
            event = "event"
            target = Subject.SELF
            filter { }
        }
    }

    /**
     * 0..1
     *
     * Adds a trigger to call when this pet's owner awakes after sleeping with the pet.
     */
    fun onWakeWithOwner(data: ComponentOnEvent.() -> Unit) {
        unsafe.general["minecraft:on_wake_with_owner"] = ComponentOnEvent().apply(data).getData()
    }

    /**
     * 0..1
     *
     * used by boat
     */
    fun outOfControl() {
        unsafe.general["minecraft:out_of_control"] = mutableMapOf<Any, Any>()
    }

    /**
     * 0..1
     *
     * @sample peekSample
     */
    fun peek(value: ComponentPeek.() -> Unit) {
        unsafe.general["minecraft:peek"] = value
    }

    private fun peekSample() {
        peek {
            //shulker
            onOpen = "minecraft:on_open"
            onClose = "minecraft:on_close"
            onTargetOpen = "minecraft:on_open"
        }
    }

    /**
     * 0..1
     *
     * Defines whether an entity should be persistent in the game world.
     */
    fun persistent() {
        unsafe.general["minecraft:persistent"] = mutableMapOf<Any, Any>()
    }

    /**
     * 0..1
     *
     * Defines a dynamic type jump control that will change jump  based on the speed modifier of the mob.
     * @sample physicsSample
     */
    fun physics(data: ComponentPhysics.() -> Unit) {
        unsafe.general["minecraft:physics"] = ComponentPhysics().apply(data).getData()
    }

    private fun physicsSample() {
        physics {
            hasGravity = true
            hasCollision = true
        }
    }

    /**
     * 0..1
     *
     * used by player
     * @sample playerCompSample
     */
    fun playerExhaustion(data: ComponentValMax.() -> Unit) {
        unsafe.general["minecraft:player.exhaustion"] = ComponentValMax().apply(data).getData()
    }

    fun exhaustionValues(data: ComponentExhaustionValues.() -> Unit) {
        unsafe.general["minecraft:exhaustion_values"] = ComponentExhaustionValues().apply(data).unsafe.getData()
    }

    /**
     * 0..1
     *
     * used by player
     * @sample playerCompSample
     */
    fun playerExperience(data: ComponentValMax.() -> Unit) {
        unsafe.general["minecraft:player.experience"] = ComponentValMax().apply(data).getData()
    }

    /**
     * 0..1
     *
     * used by player
     * @sample playerCompSample
     */
    fun playerLevel(data: ComponentValMax.() -> Unit) {
        unsafe.general["minecraft:player.level"] = ComponentValMax().apply(data).getData()
    }

    private fun playerCompSample() {
        playerLevel {
            value = 0
            max = 100
        }
    }

    /**
     * 0..1
     *
     * used by player
     * @sample playerSaturationSample
     */
    fun playerSaturation(data: ComponentValue.() -> Unit) {
        unsafe.general["minecraft:player.saturation"] = ComponentValue().apply(data).getData()
    }

    private fun playerSaturationSample() {
        playerSaturation {
            value = 20
        }
    }

    /**
     * 0..1
     *
     * @sample preferredPathSample
     */
    fun preferredPath(value: ComponentPreferredPath.() -> Unit) {
        unsafe.general["minecraft:preferred_path"] = ComponentPreferredPath().apply(value).getData()
    }

    private fun preferredPathSample() {
        preferredPath {
            //iron golem
            maxFallBlocks = 1
            jumpCost = 5
            defaultBlockCost = 1.5f
            preferredPathBlocks {
                block(0, "grass_path")
                blocks(
                    1, arrayListOf(
                        "cobblestone",
                        "stone",
                        "stonebrick",
                        "sandstone",
                        "mossy_cobblestone",
                        "stone_slab",
                        "stone_slab2",
                        "stone_slab3",
                        "stone_slab4",
                        "double_stone_slab",
                        "double_stone_slab2",
                        "double_stone_slab3",
                        //...
                    )
                )
                blocks(
                    50, arrayListOf(
                        "bed",
                        "lectern",
                        "composter",
                        "grindstone",
                        "blast_furnace",
                        "smoker",
                        "fletching_table",
                        "cartography_table",
                        "brewing_stand",
                        "smithing_table",
                        "cauldron",
                        "barrel",
                        "loom",
                        "stonecutter"
                    )
                )
            }
        }
    }

    /**
     * 0..1
     *
     * Allows the entity to be a thrown entity.
     * @sample projectileSample
     */
    fun projectile(settings: ComponentProjectile.() -> Unit) {
        unsafe.general["minecraft:projectile"] = ComponentProjectile().apply(settings).getData()
    }

    private fun projectileSample() {
        projectile {
            anchor = 1
            angleOffset = 1f
            catchFire = true
            critParticleOnHurt = true
            destroyOnHurt = true
            filter { }
            fireAffectedByGriefing = true
            gravity = 0f
            hitGroundSound = "sound" /* or use the sound {} api */
            hitSound = "sound" /* or use the sound {} api */
            homing = true
            inertia = 1f
            isDangerous = true
            knockback = false
            lightning = true
            liquidInertia = 0.1f
            multipleTargets = false
            offset(1f, 1f, 1f)
            onFireTime = 6f
            onHit {
                //arrow
                impactDamage {
                    damage(1, 1)
                    knockback = true
                    semiRandomDiffDamage = true
                    destroyOnHit = true
                    maxCriticalDamage = 10
                    minCriticalDamage = 9
                    powerMultiplier = 0.97f
                }
                stickInGround {
                    shakeTime = 0.35f
                }
                arrowEffect()
                //ender dragon
                spawnAoeCloud {
                    radius = 6f
                    radiusOnUse = 0
                    potion = 23
                    particle = "dragonbreath"
                    duration = 120
                    color = Color(220, 0, 239)
                    affectOwner = false
                    reapplicationDelay = 20
                }
                //egg
                particleOnHit {
                    particleType = "iconcrack"
                    numParticles = 6
                    onEntityHit = true
                    onOtherHit = true
                }
                spawnChance {
                    firstSpawnChance = 8
                    secondSpawnChance = 32
                    firstSpawnCount = 1
                    secondSpawnCount = 4
                    spawnDefinition = "minecraft:chicken"
                    spawnBaby = true
                }
                //enderpearl
                teleportOwner()
                removeOnHit()
                //fireball
                definitionEvent(
                    affect_projectile = true,
                    eventTriggerEvent = "minecraft:explode",
                    eventTriggerTarget = Subject.SELF
                )
                //lingering pot
                douseFire()
                //shulker
                impactDamage { shouldBounce = true }
                mobEffect {
                    mobEffect = "levitation"
                    durationEasy = 200
                    durationHard = 200
                    durationNormal = 200
                    amplifier = 1
                    filter {

                    }
                }
                //small fire ball
                ignite()
                //splash pot
                thrownPotionEffect()
                //trident
                stickInGround { shakeTime = 0f }

            }
            particle = "iconcrack"
            potionEffect = 1
            power = 1.2f
            reflectOnHurt = true
            semiRandomDiffDamage = true
            shootSound = "sound" /* or use the sound {} api */
            shootTarget = false
            shouldBounce = true
            splashPotion = true
            splashRange = 5f
            uncertaintyBase = 1f
            uncertaintyMultiplier = 2f
        }
    }

    /**
     * 0..1
     *
     * Defines what can push an entity between other entities and pistons.
     * @sample pushableSample
     */
    fun pushable(data: ComponentPushable.() -> Unit) {
        unsafe.general["minecraft:pushable"] = ComponentPushable().apply(data).getData()
    }

    private fun pushableSample() {
        pushable {
            isPushable = true
            isPushableByPiston = true
        }
    }

    /**
     * 0..1
     *
     * Attempts to trigger a raid at the entity's location.
     * @sample raidTriggerSample
     */
    fun raidTrigger(data: ComponentRaidTrigger.() -> Unit) {
        unsafe.general["minecraft:raid_trigger"] = ComponentRaidTrigger().apply(data).getData()
    }

    private fun raidTriggerSample() {
        raidTrigger {
            triggeredEvent = "event"
            target = Subject.SELF
        }
    }

    /**
     * 0..1
     *
     * Defines the entity's movement on the rails. An entity with this component is only allowed to move on the rail.
     * @sample railMovementSample
     */
    fun railMovement(data: ComponentRailMovement.() -> Unit) {
        unsafe.general["minecraft:rail_movement"] = ComponentRailMovement().apply(data).getData()
    }

    private fun railMovementSample() {
        railMovement {
            maxSpeed = 5f
        }
    }

    /**
     * 0..1
     *
     * @sample railSensorSample
     */
    fun railSensor(value: ComponentRailSensor.() -> Unit) {
        unsafe.general["minecraft:rail_sensor"] = ComponentRailSensor().apply(value).getData()
    }

    private fun railSensorSample() {
        railSensor {
            //command block minecart
            checkBlockTypes = true
            ejectOnActivate = false
            ejectOnDeactivate = false
            tickCommandBlockOnDeactivate = false
            tickCommandBlockOnActivate = true
            onDeactivate = "minecraft:command_block_deactivate"
            //other
            onActivate = "event"
        }
    }

    /**
     * 0..1
     *
     * Defines the ravager's response to their melee attack being blocked.
     * @sample ravagerBlockedSample
     */
    fun ravagerBlocked(value: ComponentRavagerBlocked.() -> Unit) {
        unsafe.general["minecraft:ravager_blocked"] = ComponentRavagerBlocked().apply(value).getData()
    }

    private fun ravagerBlockedSample() {
        ravagerBlocked {
            //ravager
            knockbackStrength = 3f
            reactionChoices {
                choice(1, "minecraft:become_stunned", Subject.SELF)
                choice(1)
            }
        }
    }

    /**
     * 0..1
     *
     * @sample rideableSample
     */
    fun rideable(value: ComponentRideable.() -> Unit) {
        unsafe.rideAble.apply(value)
    }

    private fun rideableSample() {
        rideable {
            //boat
            seatCount = 2
            interactText(key = "action.interact.ride.boat")
            pullInEntities = true
            seats {
                //with one entity occupied
                seat {
                    position(0f, -0.2f, 0f)
                    minRiderCount = 0
                    maxRiderCount = 1
                    rotateRiderBy = -90
                    lockRiderRotation = 90
                }
                //with 2 entities occupied
                seat {
                    position(0.2f, -0.2f, 0f)
                    minRiderCount = 2
                    maxRiderCount = 2
                    rotateRiderByQuery =
                        "query.has_any_family('blaze', 'creeper', 'enderman', 'illager', 'magmacube', 'piglin', 'player', 'skeleton', 'slime', 'villager', 'wandering_trader', 'witch', 'zombie', 'zombie_pigman') ? -90 : 0"
                    lockRiderRotation = 90
                }
                seat {
                    position(-0.6f, -0.2f, 0f)
                    minRiderCount = 2
                    maxRiderCount = 2
                    rotateRiderByQuery =
                        "query.has_any_family('blaze', 'creeper', 'enderman', 'illager', 'magmacube', 'piglin', 'player', 'skeleton', 'slime', 'villager', 'wandering_trader', 'witch', 'zombie', 'zombie_pigman') ? -90 : 0"
                    lockRiderRotation = 90
                }
            }

            //other
            exitText("crouch to ext", "action.hint.exit.boat")
            crouchingSkipInteract = true
            controllingSeat = 1
            familyTypes = arrayListOf("player")
            riderCanInteract = true
        }
    }

    /**
     * 0..1
     *
     * Sets the entity's visual size.
     * @sample scaleSample
     */
    fun scale(data: ComponentValue.() -> Unit) {
        unsafe.general["minecraft:scale"] = ComponentValue().apply(data).getData()
    }

    private fun scaleSample() {
        scale {
            value = 1f
        }
    }

    /**
     * 0..1
     *
     * Allows the player to detect and manuever on the scaffolding block.
     */
    fun scaffoldingClimber() {
        unsafe.general.apply {
            put("minecraft:scaffolding_climber", mutableMapOf<Any, Any>())
        }
    }

    /**
     * 0..1
     *
     * @sample scaleByAgeSample
     */
    fun scaleByAge(data: ComponentScaleByAge.() -> Unit) {
        unsafe.general["minecraft:scale_by_age"] = ComponentScaleByAge().apply(data).getData()
    }

    private fun scaleByAgeSample() {
        scaleByAge {
            endScale = 20f
            startScale = 10f
        }
    }

    /**
     * 0..1
     *
     * Fires off scheduled mob events at time of day events.
     * @sample schedulerSample
     */
    fun scheduler(value: ComponentScheduler.() -> Unit) {
        unsafe.general["minecraft:scheduler"] = ComponentScheduler().apply(value).getData()
    }

    private fun schedulerSample() {
        scheduler {
            //fox
            minDelaySecs = 0
            maxDelaySecs = 0
            scheduledEvents {
                event("minecraft:ambient_sleep") {
                    isSleeping()
                }
                event("minecraft:ambient_night") {
                    allOf {
                        isDayTime(value = false)
                        distanceToNearestPlayer(value = 16f, operator = ">")
                    }
                }
            }
        }
    }

    /**
     * 0..1
     *
     * Defines a list of items the mob wants to share or pick up. Each item must have the following parameters:
     * @sample shareAblesSample
     */
    fun shareAbles(value: ComponentShareables.() -> Unit) {
        unsafe.general["minecraft:shareables"] = ComponentShareables().apply(value).getData()
    }

    private fun shareAblesSample() {
        shareAbles {
            //drowned
            items {
                item {
                    itemId = "minecraft:nautilus_shell"
                    wantAmount = 1
                    surplusAmount = 1
                    priority = 0
                }
                item {
                    itemId = "minecraft:trident"
                    wantAmount = 1
                    surplusAmount = 1
                    priority = 1
                }
            }
            //other
            allItems = true
            allItemsMaxAmount = 1
            allItemsSurplusAmount = 1
            allItemsWantAmount = 1

            items {
                item {
                    admire = true
                    barter = true
                    consumeItem = true
                    craftInto = "stick"
                    maxAmount = 1
                    pickupLimit = 2
                    storeInInventory = true
                }
            }
        }
    }

    /**
     * 0..1
     *
     * @sample shooterSample
     */
    fun shooter(data: ComponentShooter.() -> Unit) {
        unsafe.general["minecraft:shooter"] = ComponentShooter().apply(data).getData()
    }

    private fun shooterSample() {
        shooter {
            def = "minecraft:small_fireball"
            type = "dragonfireball"
            auxVal = 0
        }
    }

    /**
     * 0..1
     *
     * Defines the entity's 'sit' state.
     * @sample sittAbleSample
     */
    fun sittAble(data: ComponentSittAble.() -> Unit) {
        unsafe.general["minecraft:sittable"] = ComponentSittAble().apply(data).getData()
    }

    private fun sittAbleSample() {
        sittAble {
            sitEvent = ""
            standEvent = ""
        }
    }

    /**
     * 0..1
     *
     * Skin ID value. Can be used to differentiate skins, such as base skins for villagers.
     * @param value The ID of the skin. By convention, 0 is the ID of the base skin
     */
    fun skinId(value: Int) {
        unsafe.general["minecraft:skin_id"] = mutableMapOf("value" to value)
    }

    /**
     * 0..1
     *
     * @sample spawnEntitiesSample
     */
    fun spawnEntities(settings: ComponentSpawnEntities.() -> Unit) {
        unsafe.general["minecraft:spawn_entity"] = ComponentSpawnEntities().apply(settings).getData()
    }

    private fun spawnEntitiesSample() {
        spawnEntities {
            //chicken
            spawnEntity {
                minWaitTime = 300
                maxWaitTime = 600
                spawnSound = "plop" /* or use the sound {} api */
                spawnItem = "egg"
                filters {
                    riderCount(value = 0)
                }
            }
            //wandering trader
            spawnEntity {
                minWaitTime = 0
                maxWaitTime = 0
                spawnEntity = "llama"
                spawnEvent = "minecraft:from_wandering_trader"
                singleUse = true
                numToSpawn = 2
                shouldLeash = true
            }
            //other
            spawnEntity {
                spawnMethod = ""
            }
        }
    }

    /**
     * 0..1
     *
     * Defines what mob effects to add and remove to the entity when adding this component.
     * @sample spellEffectsSample
     */
    fun spellEffects(value: ComponentSpellEffects.() -> Unit) {
        unsafe.general["minecraft:spell_effects"] = ComponentSpellEffects().apply(value).getData()
    }

    private fun spellEffectsSample() {
        spellEffects {
            //player
            addEffects {
                effect {
                    effectId = "bad_omen"
                    duration = 6000
                    displayOnScreenAnimation = true
                }
            }
            removeEffects = arrayListOf("bad_omen")
            //other
            addEffects {
                effect {
                    amplifier = 1
                }
            }
        }
    }

    /**
     * 0..1
     *
     * Defines the rules for a mob to be tamed by the player.
     * @sample tameAbleSample
     */
    fun tameAble(value: ComponentTameable.() -> Unit) {
        unsafe.general["minecraft:tameable"] = ComponentTameable().apply(value).getData()
    }

    private fun tameAbleSample() {
        tameAble {
            //cat
            probability = 0.33f
            tameItems =
                arrayListOf(
                    "fish",
                    "salmon"
                )

            tameEvent("minecraft:on_tame", Subject.SELF)
        }
    }

    /**
     * 0..1
     *
     * Allows the Entity to be tamed by mounting it.
     * @sample tameMountSample
     */
    fun tameMount(settings: ComponentTameMount.() -> Unit) {
        unsafe.general["minecraft:tamemount"] = ComponentTameMount().apply(settings).getData()
    }

    private fun tameMountSample() {
        tameMount {
            //donkey
            minTemper = 0
            maxTemper = 100
            feedText(langKey = "action.interact.feed")
            rideText(langKey = "action.interact.mount")
            feedItems {
                feedItem("wheat", 3)
                feedItem("sugar", 3)
                //..
            }
            autoRejectItems = arrayListOf("horsearmorleather", "horsearmoriron" /* ... */)
            tameEvent = "minecraft:on_tame"
        }
    }

    /**
     * 0..1
     *
     * Defines the entity's range within which it can see or sense other entities to target them.
     * @sample targetNearbySensorSample
     */
    fun targetNearbySensor(value: ComponentTargetNearbySensor.() -> Unit) {
        unsafe.general["minecraft:target_nearby_sensor"] = ComponentTargetNearbySensor().apply(value).getData()
    }

    private fun targetNearbySensorSample() {
        targetNearbySensor {
            //creeper
            insideRange = 2.5f
            outSideRange = 6f
            mustSee = true
            onInsideRange("minecraft:start_exploding", Subject.SELF)
            onOutsideRange("minecraft:stop_exploding", Subject.SELF)
            onVisionLostInsideRange("minecraft:stop_exploding", Subject.SELF)
        }
    }

    /**
     * 0..1
     *
     * Defines an entity's teleporting behavior.
     * @sample teleportSample
     */
    fun teleport(value: ComponentTeleport.() -> Unit) {
        unsafe.general["minecraft:teleport"] = ComponentTeleport().apply(value).getData()
    }

    private fun teleportSample() {
        teleport {
            //enderman
            randomTeleports = true
            maxRandomTeleportTime = 30f
            randomTeleportCube(32f, 32f, 32f)
            targetDistance = 16f
            targetTeleportChance = 0.5f
            lightTeleportChance = 0.05f
            //other
            darkTeleportChance = 1f
            minRandomTeleportTime = 2f
        }
    }

    /**
     * 0..1
     *
     * Defines if the entity ticks the world and the radius around it to tick.
     * @sample tickWorldSample
     */
    fun tickWorld(data: ComponentTickWorld.() -> Unit) {
        unsafe.general["minecraft:tick_world"] = ComponentTickWorld().apply(data).getData()
    }

    private fun tickWorldSample() {
        tickWorld {
            distanceToPlayer = 20f
            neverDespawn = true
            radius = 1
        }
    }

    /**
     * 0..1
     *
     * Adds a timer after which an event will fire.
     * @sample timerSample
     */
    fun timer(value: ComponentTimer.() -> Unit) {
        unsafe.general["minecraft:timer"] = ComponentTimer().apply(value).getData()
    }

    private fun timerSample() {
        timer {
            //wandering trader
            looping = false
            randomTimeChoice {
                choice(50, 2400)
                choice(50, 3600)
            }
            timeDownEvent("minecraft:start_despawn", Subject.SELF)
            //other
            time(20, 20)
            randomInterval = true
        }
    }

    /**
     * 0..1
     *
     * used by the villager
     */
    fun tradeResupply() {
        unsafe.general["minecraft:trade_resupply"] = mutableMapOf<Any, Any>()
    }

    /**
     * 0..1
     *
     * Defines this entity's ability to trade with players.
     * @sample tradeTableSample
     */
    fun tradeTable(value: ComponentTradeTable.() -> Unit) {
        unsafe.general["minecraft:trade_table"] = ComponentTradeTable().apply(value).getData()
    }

    private fun tradeTableSample() {
        tradeTable {
            //villager
            displayName(key = "entity.villager.farmer")
            table = "trading/farmer_trades.json"
            convertTradesEconomy = true
            newScreen = true
            //other
            persistTrades = true
        }
    }

    /**
     * 0..1
     *
     * Causes an entity to leave a trail of blocks as it moves about the world.
     * @sample trailSample
     */
    fun trail(value: ComponentTrail.() -> Unit) {
        unsafe.general["minecraft:trail"] = ComponentTrail().apply(value).getData()
    }

    private fun trailSample() {
        trail {
            //snow golem
            blockType = "minecraft:snow_layer"
            spawnFilters {
                isTemperatureType(operator = "<", value = BiomeTemperatureType.COLD)
            }
            //other
            spawnOffset(2f, 2f, 2f)
        }
    }

    /**
     * 0..1
     *
     * Defines an entity's transformation from the current definition into another
     * @sample transformationSample
     */
    fun transformation(settings: ComponentTransformation.() -> Unit) {
        unsafe.general["minecraft:transformation"] = ComponentTransformation().apply(settings).getData()
    }

    private fun transformationSample() {
        transformation {
            addComponentGroups = arrayListOf("comp_group")
            beginTransformationSound = "sound" /* or use the sound {} api */
            delay {
                value = 1f
                blockAssistChance = 0f
                blockMax = 0
                blockRadius = 0
                blockTypes = arrayListOf("minecraft:sand")
            }
            dropEquipment = true
            into = "minecraft:zombie"
            keepLevel = true
            keepOwner = true
            transformationSound = "sound" /* or use the sound {} api */
        }
    }

    /**
     * 0..1
     *
     * used by fox
     */
    fun trust() {
        unsafe.general["minecraft:trust"] = mutableMapOf<String, Any>()
    }

    /**
     * 0..1
     *
     * Defines the rules for a mob to trust players.
     * @sample trustingSample
     */
    fun trusting(value: ComponentTrusting.() -> Unit) {
        unsafe.general["minecraft:trusting"] = ComponentTrusting().apply(value).getData()
    }

    private fun trustingSample() {
        trusting {
            //ocelot
            probability = 0.33f
            trustItems = arrayListOf(
                "fish",
                "salmon"
            )
            trustEvent("minecraft:on_trust", Subject.SELF)
        }
    }

    /**
     * 0..1
     *
     * Defines the families this entity belongs to.
     * @param family List of family names
     */
    fun typeFamily(family: ArrayList<String>) {
        typeFamilies?.addAll(family) ?: run {
            typeFamilies = family
        }
    }


    /**
     * ```
     * underwaterMovement {
     *     value = 0.2f
     * }
     * ```
     */
    fun underwaterMovement(data: ComponentValue.() -> Unit) {
        unsafe.general["minecraft:underwater_movement"] = ComponentValue().apply(data).getData()
    }

    /**
     * 0..1
     */
    @Deprecated("spelling", ReplaceWith("underwaterMovement(data)"))
    fun underWaterMovement(data: ComponentValue.() -> Unit) {
        underwaterMovement(data)
    }

    /**
     * 0..1
     *
     * @sample waterMovementSample
     */
    fun waterMovement(data: ComponentWaterMovement.() -> Unit) {
        unsafe.general["minecraft:water_movement"] = ComponentWaterMovement().apply(data).getData()
    }

    private fun waterMovementSample() {
        waterMovement {
            dragFactor = 0.98f
        }
    }

    /**
     * 0..1
     *
     * Used to differentiate the component group of a variant of an entity from others (e.g. ocelot, villager)
     * @param value The ID of the variant. By convention, 0 is the ID of the base entity
     */
    fun variant(value: Int) {
        unsafe.general["minecraft:variant"] = mutableMapOf("value" to value)
    }

    //------------------------------------------------------------------------------------------------------------------
    //
    //------------------------------------------------------------------------------------------------------------------

    /**
     * 0..1
     *
     * Sets this entity's default head rotation angle.
     * @sample defaultLookAngleSample
     */
    fun defaultLookAngle(data: ComponentValue.() -> Unit) {
        unsafe.general["minecraft:default_look_angle"] = ComponentValue().apply(data).getData()
    }

    private fun defaultLookAngleSample() {
        defaultLookAngle {
            value = 0f
        }
    }

    /**
     * 0..1
     *
     * Sets that this entity can float in liquid blocks.
     */
    fun floatsInLiquid() {
        unsafe.general.apply {
            put("minecraft:floats_in_liquid", mutableMapOf<Any, Any>())
        }
    }

    /**
     * 0..1
     *
     * Sets the number of blocks the entity can step without jumping.
     * @sample footSizeSample
     */
    fun footSize(data: ComponentValue.() -> Unit) {
        unsafe.general["minecraft:foot_size"] = ComponentValue().apply(data).getData()
    }

    private fun footSizeSample() {
        footSize {
            value = 0.5f
        }
    }

    /**
     * 0..1
     *
     * Defines how much does friction affect this entity.
     * @sample frictionModifierSample
     */
    fun frictionModifier(data: ComponentValue.() -> Unit) {
        unsafe.general["minecraft:friction_modifier"] = ComponentValue().apply(data).getData()
    }

    private fun frictionModifierSample() {
        frictionModifier {
            value = 0.5f
        }
    }

    /**
     * 0..1
     *
     * Sets the offset from the ground that the entity is actually at.
     * @sample groundOffsetSample
     */
    fun groundOffset(data: ComponentValue.() -> Unit) {
        unsafe.general["minecraft:ground_offset"] = ComponentValue().apply(data).getData()
    }

    private fun groundOffsetSample() {
        groundOffset {
            value = 0.5f
        }
    }

    /**
     * 0..1
     *
     * Sets the distance through which the entity can push through.
     * @sample pushTroughSample
     */
    fun pushTrough(data: ComponentValue.() -> Unit) {
        unsafe.general["minecraft:push_through"] = ComponentValue().apply(data).getData()
    }

    private fun pushTroughSample() {
        pushTrough {
            value = 0.5f
        }
    }

    /**
     * 0..1
     *
     * Sets the entity's base volume for sound effects.
     * @sample soundVolumeSample
     */
    fun soundVolume(data: ComponentValue.() -> Unit) {
        unsafe.general["minecraft:sound_volume"] = ComponentValue().apply(data).getData()
    }

    private fun soundVolumeSample() {
        soundVolume {
            value = 0.5f
        }
    }

    /**
     * 0..1
     *
     *  Sets the speed multiplier for this entity's walk animation speed.
     * @sample walkAnimationSpeedSample
     */
    fun walkAnimationSpeed(data: ComponentValue.() -> Unit) {
        unsafe.general["minecraft:walk_animation_speed"] = ComponentValue().apply(data).getData()
    }

    private fun walkAnimationSpeedSample() {
        walkAnimationSpeed {
            value = 0.5f
        }
    }

    /**
     * 0..1
     *
     * Sets that this entity wants to become a jockey.
     */
    fun wantsJockey() {
        unsafe.general["minecraft:wants_jockey"] = mutableMapOf<Any, Any>()
    }

    fun vibrationListener() {
        unsafe.general["minecraft:vibration_listener"] = mutableMapOf<Any, Any>()
    }

    /**
     * used by the allay
     *
     * ```
     * behGoAndGiveItemsToNoteblock {
     *     priority = 2
     *     runSpeed = 6
     *     throwSound = "item_thrown" /* or use the sound {} api */
     *     onItemThrown(event = "pickup_item_delay", target = Subject.SELF)
     * }
     * ```
     */
    fun behGoAndGiveItemsToNoteblock(data: ComponentGoAndGiveItemsToNoteblock.() -> Unit) {
        unsafe.general["minecraft:behavior.go_and_give_items_to_noteblock"] =
            ComponentGoAndGiveItemsToNoteblock().apply(data).unsafe.getData()
    }

    /**
     * used by the allay
     *
     * ```
     * behGoAndGiveItemsToNoteblock {
     *     priority = 2
     *     runSpeed = 6
     *     throwSound = "item_thrown" /* or use the sound {} api */
     *     onItemThrown(event = "pickup_item_delay", target = Subject.SELF)
     * }
     * ```
     */
    fun behGoAndGiveItemsToOwner(data: ComponentGoAndGiveItemsToNoteblock.() -> Unit) {
        unsafe.general["minecraft:behavior.go_and_give_items_to_owner"] =
            ComponentGoAndGiveItemsToNoteblock().apply(data).unsafe.getData()
    }

    /**
     * used by the allay
     *
     * ```
     * behStayNearNoteblock {
     *     priority = 5
     *     speed = 8
     *     startDistance = 16
     *     stopDistance = 4
     * }
     * ```
     */
    fun behStayNearNoteblock(data: ComponentStayNearNoteblock.() -> Unit) {
        unsafe.general["minecraft:behavior.stay_near_noteblock"] =
            ComponentStayNearNoteblock().apply(data).unsafe.getData()
    }

    /**
     * used by the camel
     *
     * ```
     * dash {
     *     cooldownTime = 2.75
     *     horizontalMomentum = 20.0
     *     verticalMomentum = 0.6
     * }
     * ```
     */
    fun dash(data: ComponentDash.() -> Unit) {
        unsafe.general["minecraft:dash"] = ComponentDash().apply(data).unsafe.getData()
    }

    /**
     * used by the camel
     */
    fun persistSit() {
        unsafe.general["minecraft:persist_sit"] = mutableMapOf<String, String>()
    }

    /**
     * used by the camel, enderman
     *
     * ```
     * variableMaxAutoStep {
     *     baseValue = 1.0625
     *     controlledValue = 1.5625
     *     jumpPreventedValue = 0.5625
     * }
     * ```
     */
    fun variableMaxAutoStep(data: ComponentVariableMaxAutoStepMonsteraFile.() -> Unit) {
        unsafe.general["minecraft:variable_max_auto_step"] =
            ComponentVariableMaxAutoStepMonsteraFile().apply(data).unsafe.getData()
    }

    /**
     * used by drowned, fox
     */
    fun equipItem() {
        unsafe.general["minecraft:equip_item"] = mutableMapOf<String, String>()
    }

    /**
     * used by the frog
     *
     * ```
     * behEatMob {
     *     priority = 7
     *     runSpeed = 2
     *     eatAnimationTime = 0.3
     *     pullInForce = 0.75
     *     reachMobDistance = 1.75
     *     eatMobSound = "tounge" /* or use the sound {} api */
     *     lootTable = "loot_tables/entities/frog.json"
     * }
     * ```
     */
    fun behEatMob(data: ComponentEatMob.() -> Unit) {
        unsafe.general["minecraft:behavior.eat_mob"] = ComponentEatMob().apply(data).unsafe.getData()
    }

    /**
     * used by frog
     *
     * ```
     * behCroak {
     *     priority = 9
     *     interval(10, 20)
     *     duration = 4.5
     *     filters {
     *         allOf { /*...*/}
     *     }
     * }
     * ```
     */
    fun behCroak(data: ComponentCroak.() -> Unit) {
        unsafe.general["minecraft:behavior.croak"] = ComponentCroak().apply(data).unsafe.getData()
    }

    /**
     * used by the sniffer
     *
     * ```
     * behFeelingHappy {
     *     priority = 5
     *     cooldownRange = 0
     *     durationRange(2, 5)
     *     onEnd("on_feeling_happy_end", Subject.SELF)
     * }
     * ```
     */
    fun behFeelingHappy(data: ComponentFeelingHappy.() -> Unit) {
        unsafe.general["minecraft:behavior.feeling_happy"] = ComponentFeelingHappy().apply(data).unsafe.getData()
    }

    /**
     * used by the sniffer
     *
     * ```
     * behRising {
     *     priority = 2
     *     cooldownRange = 0
     *     durationRange(2, 5)
     *     onEnd("on_rising_end", Subject.SELF)
     * }
     * ```
     */
    fun behRising(data: ComponentFeelingHappy.() -> Unit) {
        unsafe.general["minecraft:behavior.rising"] = ComponentFeelingHappy().apply(data).unsafe.getData()
    }

    /**
     * used by the sniffer
     */
    fun isPregnant() {
        unsafe.general["minecraft:is_pregnant"] = mutableMapOf<String, String>()
    }

    /**
     * used by the sniffer
     *
     * ```
     * priority = 5
     * speedMultiplier = 1.25
     * findValidPositionRetries = 5
     * goalRadius = 2
     * searchRangeXz = 20
     * searchRangeY = 3
     * cooldownRange = 0
     * diggingDurationRange(8, 10)
     * items("torchflower_seeds")
     * ```
     */
    fun behRandomSearchAndDig(data: ComponentRandomSearchAndDig.() -> Unit) {
        unsafe.general["minecraft:behavior.random_search_and_dig"] =
            ComponentRandomSearchAndDig().apply(data).unsafe.getData()
    }

    /**
     * used by the sniffer
     *
     * ```
     * behScenting {
     *     priority = 6
     *     cooldownRange(400, 500)
     *     durationRange = 2
     *     onEnd(event = "on_scenting_success", target = Subject.SELF)
     * }
     * ```
     */
    fun behScenting(data: ComponentScenting.() -> Unit) {
        unsafe.general["minecraft:behavior.scenting"] = ComponentScenting().apply(data).unsafe.getData()
    }

    /**
     * used by the villager
     *
     * ```
     * behCelebrateSurvive {
     *     priority = 5
     *     fireworksInterval(2, 7)
     *     duration = 30
     *     onCelebrationEndEvent("minecraft:stop_celebrating", Subject.SELF)
     * }
     * ```
     */
    fun behCelebrateSurvive(data: ComponentCelebrateSurvive.() -> Unit) {
        unsafe.general["minecraft:behavior.celebrate_survive"] =
            ComponentCelebrateSurvive().apply(data).unsafe.getData()
    }

    /**
     * used by the villager
     *
     * ```
     * behMoveOutdoors {
     *     priority = 2
     *     speedMultiplier = 0.8
     *     timeoutCooldown = 8
     * }
     * ```
     */
    fun behMoveOutdoors(data: ComponentMoveOutdoors.() -> Unit) {
        unsafe.general["minecraft:behavior.move_outdoors"] = ComponentMoveOutdoors().apply(data).unsafe.getData()
    }

    /**
     * used by the villager
     *
     * ```
     * behWorkComposter {
     *     priority = 7
     *     activeTime = 250
     *     speedMultiplier = 0.5f
     *     goalCooldown = 200
     *     soundDelayMin = 100
     *     soundDelayMax = 200
     *     canWorkInRain = false
     *     workInRainTolerance = 100
     *     onArrival("minecraft:resupply_trades", Subject.SELF)
     * }
     * ```
     */
    fun behWorkComposter(data: ComponentWork.() -> Unit) {
        unsafe.general["minecraft:behavior.work_composter"] = ComponentWork().apply(data).getData()
    }

    /**
     * used by the villager
     *
     * ```
     * behFertilizeFarmBlock {
     *     priority = 8
     * }
     * ```
     */
    fun behFertilizeFarmBlock(data: ComponentPriority.() -> Unit) {
        unsafe.general["minecraft:behavior.fertilize_farm_block"] = ComponentPriority().apply(data).getData()
    }

    /**
     * used by the wandering trader
     */
    fun behDrinkMilk(data: ComponentDrinkMilk.() -> Unit) {
        unsafe.general["minecraft:behavior.drink_milk"] = ComponentDrinkMilk().apply(data).unsafe.getData()
    }

    /**
     * used by the warden
     *
     * ```
     * behEmerge {
     *     duration = 7
     *     onDone("minecraft:emerge", Subject.SELF)
     * }
     * ```
     */
    fun behEmerge(data: ComponentEmerge.() -> Unit) {
        unsafe.general["minecraft:behavior.emerge"] = ComponentEmerge().apply(data).unsafe.getData()
    }

    /**
     * used by the warden
     *
     * ```
     * movementSoundDistanceOffset {
     *     value = 0.55
     * }
     * ```
     */
    fun movementSoundDistanceOffset(data: ComponentValue.() -> Unit) {
        unsafe.general["minecraft:movement_sound_distance_offset"] = ComponentValue().apply(data).getData()
    }

    /**
     * used by the warden
     */
    fun vibrationDamper() {
        unsafe.general["minecraft:vibration_damper"] = mapOf<String, String>()
    }

    /**
     * used by the warden
     */
    fun suspectTracking() {
        unsafe.general["minecraft:suspect_tracking"] = mapOf<String, String>()
    }

    /**
     * used by the warden
     *
     * ```
     * angerLevel {
     *     maxAnger = 150
     *     angryThreshold = 80
     *     removeTargetsBelowAngryThreshold = true
     *     angryBoost = 20
     *     angerDecrementInterval = 1
     *     defaultAnnoyingness = 35
     *     defaultProjectileAnnoyingness = 10
     *     onIncreaseSounds(
     *         "listening_angry" to Query.angerLevel("this") beq 40,
     *         "listening" to Query.angerLevel("this") beq 0
     *     )
     *     nuisanceFilter {
     *         allOf {
     *             isFamily()
     *         }
     *     }
     * }
     * ```
     */
    fun angerLevel(data: ComponentAngerLevel.() -> Unit) {
        unsafe.general["minecraft:anger_level"] = ComponentAngerLevel().apply(data).unsafe.getData()
    }

    /**
     * used by the warden
     *
     * ```
     * heartbeat(MoMath.clamp())
     * ```
     */
    fun heartbeat(interval: Molang) {
        heartbeat(interval.data)
    }

    /**
     * ```
     * interval = "2.0 - math.clamp(query.anger_level / 80 * 1.5, 0, 1.5)"
     * ```
     */
    fun heartbeat(interval: String) {
        unsafe.general["minecraft:heartbeat"] = mapOf(
            "interval" to interval
        )
    }

    /**
     * used by the warden
     *
     * ```
     * behDig {
     *     priority = 1
     *     duration = 5.5
     *     idleTime = 60
     *     vibrationIsDisturbance = true
     *     suspicionIsDisturbance = true
     *     digsInDaylight = false
     *
     *     onStart("on_digging_event", Subject.SELF)
     * }
     * ```
     */
    fun behDig(data: ComponentDig.() -> Unit) {
        unsafe.general["minecraft:behavior.dig"] = ComponentDig().apply(data).unsafe.getData()
    }

    /**
     * used by the warden
     *
     * ```
     * behRoar {
     *     priority = 2
     *     duration = 4.2
     * }
     * ```
     */
    fun behRoar(data: ComponentRoar.() -> Unit) {
        unsafe.general["minecraft:behavior.roar"] = ComponentRoar().apply(data).unsafe.getData()
    }

    /**
     * used by the warden
     *
     * ```
     * behSonicBoom {
     *     priority = 3
     *     duration = 3
     *     speedMultiplier = 1.2
     *     attackDamage = 10
     *     attackRangeHorizontal = 15
     *     attackRangeVertical = 20
     *     attackCooldown = 2
     *     knockbackHorizontalStrength = 2.5
     *     knockbackVerticalStrength = 0.5
     *     knockbackHeightCap = 0.5
     *     durationUntilAttackSound = 1.7
     *     chargeSound = "sonic_charge" /* or use the sound {} api */
     *     attack_sound = "sonic_boom" /* or use the sound {} api */
     * }
     * ```
     */
    fun behSonicBoom(data: ComponentSonicBoom.() -> Unit) {
        unsafe.general["minecraft:behavior.sonic_boom"] = ComponentSonicBoom().apply(data).unsafe.getData()
    }

    /**
     * used by the warden
     *
     * ```
     * behInvestigateSuspiciousLocation {
     *     priority = 5
     *     speedMultiplier = 0.7
     * }
     * ```
     */
    fun behInvestigateSuspiciousLocation(data: ComponentPrioSpeed.() -> Unit) {
        unsafe.general["minecraft:behavior.investigate_suspicious_location"] =
            ComponentPrioSpeed().apply(data).getData()
    }

    /**
     * used by the warden
     */
    fun behSniff(data: ComponentSniff.() -> Unit) {
        unsafe.general["minecraft:behavior.sniff"] = ComponentSniff().apply(data).unsafe.getData()
    }
}