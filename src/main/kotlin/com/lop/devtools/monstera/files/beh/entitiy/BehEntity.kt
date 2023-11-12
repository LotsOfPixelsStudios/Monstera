package com.lop.devtools.monstera.files.beh.entitiy

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.MonsteraBuilder
import com.lop.devtools.monstera.files.beh.entitiy.description.BehEntityDescription
import com.lop.devtools.monstera.files.beh.entitiy.events.BehEntityEvents
import com.lop.devtools.monstera.getMonsteraLogger
import org.slf4j.LoggerFactory
import java.nio.file.Path

/**
 * Create an Entity with a description, componentGroups, components and events
 */
class BehEntity: MonsteraFile {
    private val logger = getMonsteraLogger("BehEntity File")
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        var description = BehEntityDescription()
        var components = BehEntityComponents()
        var componentGroups = BehEntityComponentGroups()
        var events = BehEntityEvents()

        override fun getData(): MutableMap<String, Any> {
            val descriptionData = description.unsafe.getData()
            val componentsData = components.unsafe.getData()
            val groupsData = componentGroups.unsafe.getData()
            val eventData = events.unsafe.getData()

            if (descriptionData.isNotEmpty()) general["description"] = descriptionData
            if (componentsData.isNotEmpty()) general["components"] = componentsData
            if (groupsData.isNotEmpty()) general["component_groups"] = groupsData
            if (eventData.isNotEmpty()) general["events"] = eventData

            //Debug
            //check if there are events with no matching group
            events.unsafe.debugAddedGroups.forEach {
                if (!groupsData.keys.contains(it)) {
                    logger.warn("ComponentGroup '$it' was added in a event but does not exist")
                }
            }
            //check if there are groups that were not added
            groupsData.keys.forEach {
                if (!events.unsafe.debugAddedGroups.contains(it)) {
                    logger.warn("ComponentGroup '$it' was never added with events")
                }
            }

            return general
        }

        fun build(
            filename: String,
            path: Path,
            version: String
        ) {
            val sanFile = filename
                .removeSuffix(".json")
                .replace("-", "_")
                .replace(" ", "_")
            MonsteraBuilder.buildTo(
                path, "$sanFile.json", mutableMapOf(
                    "format_version" to version,
                    "minecraft:entity" to getData()
                )
            )
        }
    }

    /**
     * 1 instance required
     * 1 instance allowed
     *
     * ```
     * description {
     *     identifier = "pi:test"
     *     isSpawnable = true
     *     isSummonable = true
     *     isExperimental = false
     *     runtimeIdentifier = RuntimeIdentifier.armor_stand
     *     scripts {
     *         //...
     *     }
     *     animations {
     *         //...
     *     }
     *     properties {
     *         //...
     *     }
     * }
     * ```
     *
     * @see BehEntityDescription data class
     */
    fun description(settings: BehEntityDescription.() -> Unit) {
        unsafe.description.apply(settings)
    }

    /**
     * 0..1 instance allowed
     *
     * @see BehEntityComponentGroups
     * @sample sampleComponentGroups
     */
    fun componentGroups(settings: BehEntityComponentGroups.() -> Unit) {
        unsafe.componentGroups.apply(settings)
    }

    /**
     * 0..1 instance allowed
     *
     * @see BehEntityComponents
     * @sample sampleComponents
     */
    fun components(setting: BehEntityComponents.() -> Unit) {
        unsafe.components.apply(setting)
    }

    /**
     * 0..1 instance allowed
     *
     * @see BehEntityEvents
     * @sample sampleEvent
     */
    fun events(setting: BehEntityEvents.() -> Unit) {
        unsafe.events.apply(setting)
    }

    private fun sampleComponentGroups() {
        componentGroups {
            componentGroup("timer") {
                components {
                    //...
                }
            }
            componentGroup("test") {
                //...
            }
        }
    }

    private fun sampleComponents() {
        components {
            //AI_GOALS (behavior:)
            behAvoidBlock { }
            behAvoidMobType { }
            behHide { }
            behMoveToPOI {}
            behScared { }
            behAdmireItem { }
            behBarter { }
            behBeg { }
            behBreed { }
            behCelebrate { }
            behChargeAttack { }
            behChargeHeldItem { }
            behControlledByPlayer { }
            behDefendTrustedTarget { }
            behDefendVillage { }
            behDoorInteract { }
            behDragonDeath { }
            behDragonHoldingPattern { }
            behDragonLanding { }
            behDragonScanning { }
            behDragonTakeOff { }
            behDrinkPotion { }
            behDropItemFor { }
            behEatBlock { }
            behEatCarriedItem { }
            behEndermanLeaveBlock { }
            behEndermanTakeBlock { }
            behExploreOutskirts { }
            behFindCover { }
            behFindMount { }
            behFindUnderWaterTreasure { }
            behFleeSun { }
            behFloat { }
            behFloatWander { }
            behFollowCaravan { }
            behFollowMob { }
            behFollowOwner { }
            behFollowParent { }
            behFollowTargetCaptain { }
            behGoHome { }
            behGuardianAttack { }
            behHarvestFarmBlock { }
            behHoldGround { }
            behHurtByTarget { }
            behInspectBookshelf { }
            behJumpToBlock { }
            behKnockbackRoar { }
            behLayDown { }
            behLayEgg { }
            behLeapAtTarget { }
            behLookAtEntity { }
            behLookAtPlayer { }
            behLookAtTarget { }
            behLookAtTradingPlayer { }
            behMakeLove { }
            behMeleeAttack { }
            behMingle { }
            behMountPathing { }
            behMoveIndoors { }
            behMoveThroughVillage { }
            behMoveToBlock { }
            behMoveToLand { }
            behMoveToLiquid { }
            behMoveToRandomBlock { }
            behMoveToVillage { }
            behMoveToWater { }
            behMoveTowardsDwellingRestriction { }
            behMoveTowardsHomeRestriction { }
            behMoveTowardsTarget { }
            behNap { }
            behNearestAttackableTarget { }
            behNearestPrioritizedAttackableTarget { }
            behOcelotSitOnBlock { }
            behOcelotAttack { }
            behOfferFlower { }
            behOpenDoor { }
            behOwnerHurtByTarget { }
            behOwnerHurtTarget { }
            behPanic { }
            behPeek { }
            behPetSleepWithOwner { }
            behPickupItems { }
            behPlay { }
            behPlayDead { }
            behPlayerRideTamed()
            behRaidGarden { }
            behRamAttack { }
            behRandomBreach { }
            behRandomFly { }
            behRandomHover { }
            behRandomLookAround { }
            behRandomLookAroundAndSit { }
            behRandomSitting { }
            behRandomStroll { }
            behRandomSwim { }
            behRangedAttack { }
            behRandomStroll { }
            behReceiveLove { }
            behRestrictOpenDoor { }
            behRestrictSun { }
            behRiseToLiquidLevel { }
            behRoll { }
            behRunAroundLikeCrazy { }
            behSendEvent { }
            behShareItems { }
            behSilverfishMergeWithStone { }
            behSilverfishWakeUpFriends { }
            behSkeletonHorseTrap { }
            behSleep { }
            behSlimeAttack { }
            behSlimeFloat { }
            behSlimeKeepJumping { }
            behSlimeRandomDirection {}
            behSnacking { }
            behSneeze { }
            behSquidDive { }
            behSquidFlee { }
            behSquidIdle { }
            behSquidMoveAwayFromGround { }
            behSquidOutOfWater { }
            behStalkAndPounceOnTarget { }
            behStayWhileSitting { }
            behStompAttack { }
            behStompTurtleEggs { }
            behStrollTowardsVillage { }
            behSummonEntity { }
            behSwell { }
            behSwimIdle { }
            behSwimWander { }
            behSwimWithEntity { }
            behSwoopAttack { }
            behTakeFlower { }
            behTargetWhenPushed { }
            behTempt { }
            behTradeInterest { }
            behTradeWithPlayer { }
            behVexCopyOwnerTarget { type { } }
            behVexRandomAttackPosGoal { }
            behWitherRandomAttackPosGoal { }
            behWitherTargetHighestDamage { }
            behWork { }
            //attributes
            attack { }
            attackDamage { }
            spellEffects { }
            strength { }
            //components
            addRider { }
            admireItem { }
            ageable { }
            angry { }
            annBreakDoor { }
            annOpenDoor()
            areaAttack { }
            attackCooldown { }
            balloonable { }
            blockClimber()
            barter { }
            blockSensor { }
            boostAble { }
            boss { }
            breakBlocks { }
            breathable { }
            breedable { }
            bribeable { }
            buoyant { }
            burnsInDayLight()
            celebrateHunt { }
            combatRegeneration()
            customHitTest { }
            damageOverTime { }
            damageSensor { }
            despawn { }
            dryingOutTimer { }
            dweller { }
            economyTradeTable { }
            entitySensor { }
            environmentSensor { }
            behEquipItem { }
            equippable { slot() }
            experienceReward { }
            explode { }
            fireImmune()
            flocking { }
            flyingSpeed { }
            genetics { }
            giveable { }
            groupSize { }
            growsCrop { }
            healable { }
            hurtOnCondition { }
            home { }
            horseJumpStrength { }
            insideBlockNotifier { }
            insomnia { }
            instantDespawn { }
            interact { }
            inventory { }
            isIllagerCaptain()
            lavaMovement { }
            itemHopper()
            jumpDynamic()
            jumpStatic { }
            leashable { }
            lookAt { }
            managedWanderingTrader()
            mobEffect { }
            movementAmphibious { }
            movementBasic { }
            movementFly { }
            movementGeneric { }
            movementGlide { }
            movementHover { }
            movementJump { }
            movementSkip { }
            movementSway { }
            nameable { }
            navigationClimb { }
            navigationFloat { }
            navigationFly { }
            navigationGeneric { }
            navigationHover { }
            navigationSwim { }
            navigationWalk { }
            npc { }
            outOfControl()
            peek { }
            persistent()
            physics { }
            playerExhaustion { }
            exhaustionValues { }
            playerExperience { }
            playerLevel { }
            playerExperience { }
            playerSaturation { }
            preferredPath { }
            projectile { }
            pushable { }
            raidTrigger { }
            railMovement { }
            railSensor { }
            ravagerBlocked { }
            rideable { }
            scaffoldingClimber()
            scaleByAge { }
            scheduler { }
            shareAbles { }
            shooter { }
            sittAble { }
            spawnEntities { }
            strength { }
            tameAble { }
            tameMount { }
            targetNearbySensor { }
            teleport { }
            tickWorld { }
            timer { }
            tradeResupply()
            tradeTable { }
            trail { }
            transformation { }
            trust()
            trusting { }
            waterMovement { }
            underwaterMovement { }
            //Properties
            ambientSoundInterval { }
            canClimb()
            canFly()
            canPowerJump()
            collisionBox { }
            color { }
            color2 { }
            defaultLookAngle { }
            equipment { }
            fireImmune()
            floatsInLiquid()
            flyingSpeed { }
            footSize { }
            followRange { }
            frictionModifier { }
            groundOffset { }
            inputGroundControlled()
            isBaby()
            isCharged()
            isChested()
            isDyeable { }
            isHiddenWhenInvisible()
            isIgnited()
            isSaddled()
            isShaking()
            isSheared()
            isStackable()
            isStunned()
            isTamed()
            itemControllable { }
            loot { }
            markVariant(2)
            pushTrough { }
            scale { }
            skinId(2)
            soundVolume { }
            typeFamily(arrayListOf("player"))
            variant(2)
            walkAnimationSpeed { }
            wantsJockey()
            onDeath { }
            onFriendlyAnger { }
            onHurt { }
            onHurtByPlayer { }
            onIgnite { }
            onStartLanding { }
            onStartTakeoff { }
            onTargetAcquired { }
            onTargetEscape { }
            onWakeWithOwner { }
            //attack
            delayedAttack { }
            health { }
            behGuardianAttack { }
            knockbackResistance { }
            behAvoidMobType { }
            circleAroundAnchor { }
            hide()

            //new components 1.19/20
            vibrationListener()
            behGoAndGiveItemsToNoteblock { }
            behGoAndGiveItemsToOwner { }
            behStayNearNoteblock { }
            dash { }
            persistSit()
            variableMaxAutoStep { }
            equipItem()
            behEatMob { }
            behCroak { }
            behFeelingHappy { }
            behRising { }
            isPregnant()
            behRandomSearchAndDig { }
            behScenting { }
            behCelebrateSurvive { }
            behMoveOutdoors { }
            behWorkComposter { }
            behFertilizeFarmBlock { }
            behDrinkMilk { }
            behEmerge { }
            movementSoundDistanceOffset { }
            vibrationDamper()
            suspectTracking()
            angerLevel { }
            heartbeat("")
            behDig { }
            behRoar { }
            behSonicBoom { }
            behInvestigateSuspiciousLocation { }
            behSniff { }
        }
    }

    private fun sampleEvent() {
        events {
            event("test") { }
            defaultBornEvent { }
            defaultSpawnedEvent { }
            defaultTransformedEvent { }
            defaultOnPrimeEvent { }
        }
    }
}