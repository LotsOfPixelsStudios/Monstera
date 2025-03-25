@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.lop.devtools.monstera.files.beh.entitiy

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.DebugMarker
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.api.MonsteraBuildableFile
import com.lop.devtools.monstera.files.*
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.description.BehEntityDescription
import com.lop.devtools.monstera.files.beh.entitiy.events.BehEntityEvent
import com.lop.devtools.monstera.files.sanitiseFilename
import com.lop.devtools.monstera.getMonsteraLogger
import java.nio.file.Path

/**
 * Create an Entity with a description, componentGroups, components and events
 */
class BehEntity : MonsteraBuildableFile, MonsteraRawFile() {
    private fun logger() = getMonsteraLogger(this.javaClass.name)

    @SerializedName("description")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var descriptionData: BehEntityDescription? = null
        @MonsteraBuildSetter set

    @SerializedName("components")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var componentsData: Components? = null
        @MonsteraBuildSetter set

    @SerializedName("component_groups")
    @Expose
    @JsonAdapter(MonsteraMapFileTypeAdapter::class)
    var componentGroupsData: MutableMap<String, Components>? = null
        @MonsteraBuildSetter set

    @SerializedName("events")
    @Expose
    @JsonAdapter(MonsteraMapFileTypeAdapter::class)
    var eventsData: MutableMap<String, BehEntityEvent>? = null
        @MonsteraBuildSetter set

    override fun build(filename: String, path: Path?, version: String?): Result<Path> {
        val selPath = path ?: Addon.active?.config?.paths?.behEntity ?: run {
            logger().error("Could not Resolve a path for entity file '$filename' as no addon was initialized!")
            return Result.failure(Error("Could not Resolve a path for entity file '$filename' as no addon was initialized!"))
        }

        val target = MonsteraBuilder.buildTo(
            selPath,
            sanitiseFilename(filename, "json"),
            FileHeader(
                version ?: Addon.active?.config?.formatVersions?.behEntity ?: "1.20.50",
                this
            )
        )
        return Result.success(target)
    }

    /**
     * load json blocks with this class
     */
    data class FileHeader(
        @SerializedName("format_version")
        @Expose
        var formatVersion: String = "1.20.50",

        @SerializedName("minecraft:entity")
        @Expose
        var entity: BehEntity
    ): MonsteraRawFile()

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
    @OptIn(MonsteraBuildSetter::class)
    fun description(settings: BehEntityDescription.() -> Unit) {
        descriptionData = (descriptionData ?: BehEntityDescription()).apply(settings)
    }

    /**
     * 0..1 instance allowed
     *
     * ```
     * componentGroups {
     *     componentGroup("timer") {
     *         components {
     *             //...
     *         }
     *     }
     *     componentGroup("test") {
     *         //...
     *     }
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun componentGroup(name: String, components: Components.() -> Unit) {
        componentGroupsData = (componentGroupsData ?: mutableMapOf()).also { groupsMap ->
            groupsMap[name]?.apply(components) ?: run {
                groupsMap.put(name, Components().apply(components))
            }
        }
    }

    /**
     * 0..1 instance allowed
     *
     * ```
     * components {
     *     //AI_GOALS (behavior:)
     *     behAvoidBlock { }
     *     behAvoidMobType { }
     *     behHide { }
     *     behMoveToPOI {}
     *     behScared { }
     *     behAdmireItem { }
     *     behBarter { }
     *     behBeg { }
     *     behBreed { }
     *     behCelebrate { }
     *     behChargeAttack { }
     *     behChargeHeldItem { }
     *     behControlledByPlayer { }
     *     behDefendTrustedTarget { }
     *     behDefendVillage { }
     *     behDoorInteract { }
     *     behDragonDeath { }
     *     behDragonHoldingPattern { }
     *     behDragonLanding { }
     *     behDragonScanning { }
     *     behDragonTakeOff { }
     *     behDrinkPotion { }
     *     behDropItemFor { }
     *     behEatBlock { }
     *     behEatCarriedItem { }
     *     behEndermanLeaveBlock { }
     *     behEndermanTakeBlock { }
     *     behExploreOutskirts { }
     *     behFindCover { }
     *     behFindMount { }
     *     behFindUnderWaterTreasure { }
     *     behFleeSun { }
     *     behFloat { }
     *     behFloatWander { }
     *     behFollowCaravan { }
     *     behFollowMob { }
     *     behFollowOwner { }
     *     behFollowParent { }
     *     behFollowTargetCaptain { }
     *     behGoHome { }
     *     behGuardianAttack { }
     *     behHarvestFarmBlock { }
     *     behHoldGround { }
     *     behHurtByTarget { }
     *     behInspectBookshelf { }
     *     behJumpToBlock { }
     *     behKnockbackRoar { }
     *     behLayDown { }
     *     behLayEgg { }
     *     behLeapAtTarget { }
     *     behLookAtEntity { }
     *     behLookAtPlayer { }
     *     behLookAtTarget { }
     *     behLookAtTradingPlayer { }
     *     behMakeLove { }
     *     behMeleeAttack { }
     *     behMingle { }
     *     behMountPathing { }
     *     behMoveIndoors { }
     *     behMoveThroughVillage { }
     *     behMoveToBlock { }
     *     behMoveToLand { }
     *     behMoveToLiquid { }
     *     behMoveToRandomBlock { }
     *     behMoveToVillage { }
     *     behMoveToWater { }
     *     behMoveTowardsDwellingRestriction { }
     *     behMoveTowardsHomeRestriction { }
     *     behMoveTowardsTarget { }
     *     behNap { }
     *     behNearestAttackableTarget { }
     *     behNearestPrioritizedAttackableTarget { }
     *     behOcelotSitOnBlock { }
     *     behOcelotAttack { }
     *     behOfferFlower { }
     *     behOpenDoor { }
     *     behOwnerHurtByTarget { }
     *     behOwnerHurtTarget { }
     *     behPanic { }
     *     behPeek { }
     *     behPetSleepWithOwner { }
     *     behPickupItems { }
     *     behPlay { }
     *     behPlayDead { }
     *     behPlayerRideTamed()
     *     behRaidGarden { }
     *     behRamAttack { }
     *     behRandomBreach { }
     *     behRandomFly { }
     *     behRandomHover { }
     *     behRandomLookAround { }
     *     behRandomLookAroundAndSit { }
     *     behRandomSitting { }
     *     behRandomStroll { }
     *     behRandomSwim { }
     *     behRangedAttack { }
     *     behRandomStroll { }
     *     behReceiveLove { }
     *     behRestrictOpenDoor { }
     *     behRestrictSun { }
     *     behRiseToLiquidLevel { }
     *     behRoll { }
     *     behRunAroundLikeCrazy { }
     *     behSendEvent { }
     *     behShareItems { }
     *     behSilverfishMergeWithStone { }
     *     behSilverfishWakeUpFriends { }
     *     behSkeletonHorseTrap { }
     *     behSleep { }
     *     behSlimeAttack { }
     *     behSlimeFloat { }
     *     behSlimeKeepJumping { }
     *     behSlimeRandomDirection {}
     *     behSnacking { }
     *     behSneeze { }
     *     behSquidDive { }
     *     behSquidFlee { }
     *     behSquidIdle { }
     *     behSquidMoveAwayFromGround { }
     *     behSquidOutOfWater { }
     *     behStalkAndPounceOnTarget { }
     *     behStayWhileSitting { }
     *     behStompAttack { }
     *     behStompTurtleEggs { }
     *     behStrollTowardsVillage { }
     *     behSummonEntity { }
     *     behSwell { }
     *     behSwimIdle { }
     *     behSwimWander { }
     *     behSwimWithEntity { }
     *     behSwoopAttack { }
     *     behTakeFlower { }
     *     behTargetWhenPushed { }
     *     behTempt { }
     *     behTradeInterest { }
     *     behTradeWithPlayer { }
     *     behVexCopyOwnerTarget { type { } }
     *     behVexRandomAttackPosGoal { }
     *     behWitherRandomAttackPosGoal { }
     *     behWitherTargetHighestDamage { }
     *     behWork { }
     *     //attributes
     *     attack { }
     *     attackDamage { }
     *     spellEffects { }
     *     strength { }
     *     //components
     *     addRider { }
     *     admireItem { }
     *     ageable { }
     *     angry { }
     *     annBreakDoor { }
     *     annOpenDoor()
     *     areaAttack { }
     *     attackCooldown { }
     *     balloonable { }
     *     blockClimber()
     *     barter { }
     *     blockSensor { }
     *     boostAble { }
     *     boss { }
     *     breakBlocks { }
     *     breathable { }
     *     breedable { }
     *     bribeable { }
     *     buoyant { }
     *     burnsInDayLight()
     *     celebrateHunt { }
     *     combatRegeneration()
     *     customHitTest { }
     *     damageOverTime { }
     *     damageSensor { }
     *     despawn { }
     *     dryingOutTimer { }
     *     dweller { }
     *     economyTradeTable { }
     *     entitySensor { }
     *     environmentSensor { }
     *     behEquipItem { }
     *     equippable { slot() }
     *     experienceReward { }
     *     explode { }
     *     fireImmune()
     *     flocking { }
     *     flyingSpeed { }
     *     genetics { }
     *     giveable { }
     *     groupSize { }
     *     growsCrop { }
     *     healable { }
     *     hurtOnCondition { }
     *     home { }
     *     horseJumpStrength { }
     *     insideBlockNotifier { }
     *     insomnia { }
     *     instantDespawn { }
     *     interact { }
     *     inventory { }
     *     isIllagerCaptain()
     *     lavaMovement { }
     *     itemHopper()
     *     jumpDynamic()
     *     jumpStatic { }
     *     leashable { }
     *     lookAt { }
     *     managedWanderingTrader()
     *     mobEffect { }
     *     movementAmphibious { }
     *     movementBasic { }
     *     movementFly { }
     *     movementGeneric { }
     *     movementGlide { }
     *     movementHover { }
     *     movementJump { }
     *     movementSkip { }
     *     movementSway { }
     *     nameable { }
     *     navigationClimb { }
     *     navigationFloat { }
     *     navigationFly { }
     *     navigationGeneric { }
     *     navigationHover { }
     *     navigationSwim { }
     *     navigationWalk { }
     *     npc { }
     *     outOfControl()
     *     peek { }
     *     persistent()
     *     physics { }
     *     playerExhaustion { }
     *     exhaustionValues { }
     *     playerExperience { }
     *     playerLevel { }
     *     playerExperience { }
     *     playerSaturation { }
     *     preferredPath { }
     *     projectile { }
     *     pushable { }
     *     raidTrigger { }
     *     railMovement { }
     *     railSensor { }
     *     ravagerBlocked { }
     *     rideable { }
     *     scaffoldingClimber()
     *     scaleByAge { }
     *     scheduler { }
     *     shareAbles { }
     *     shooter { }
     *     sittAble { }
     *     spawnEntities { }
     *     strength { }
     *     tameAble { }
     *     tameMount { }
     *     targetNearbySensor { }
     *     teleport { }
     *     tickWorld { }
     *     timer { }
     *     tradeResupply()
     *     tradeTable { }
     *     trail { }
     *     transformation { }
     *     trust()
     *     trusting { }
     *     waterMovement { }
     *     underwaterMovement { }
     *     //Properties
     *     ambientSoundInterval { }
     *     canClimb()
     *     canFly()
     *     canPowerJump()
     *     collisionBox { }
     *     color { }
     *     color2 { }
     *     defaultLookAngle { }
     *     equipment { }
     *     fireImmune()
     *     floatsInLiquid()
     *     flyingSpeed { }
     *     footSize { }
     *     followRange { }
     *     frictionModifier { }
     *     groundOffset { }
     *     inputGroundControlled()
     *     isBaby()
     *     isCharged()
     *     isChested()
     *     isDyeable { }
     *     isHiddenWhenInvisible()
     *     isIgnited()
     *     isSaddled()
     *     isShaking()
     *     isSheared()
     *     isStackable()
     *     isStunned()
     *     isTamed()
     *     itemControllable { }
     *     loot { }
     *     markVariant(2)
     *     pushTrough { }
     *     scale { }
     *     skinId(2)
     *     soundVolume { }
     *     typeFamily(arrayListOf("player"))
     *     variant(2)
     *     walkAnimationSpeed { }
     *     wantsJockey()
     *     onDeath { }
     *     onFriendlyAnger { }
     *     onHurt { }
     *     onHurtByPlayer { }
     *     onIgnite { }
     *     onStartLanding { }
     *     onStartTakeoff { }
     *     onTargetAcquired { }
     *     onTargetEscape { }
     *     onWakeWithOwner { }
     *     //attack
     *     delayedAttack { }
     *     health { }
     *     behGuardianAttack { }
     *     knockbackResistance { }
     *     behAvoidMobType { }
     *     circleAroundAnchor { }
     *     hide()
     *
     *     //new components 1.19/20
     *     vibrationListener()
     *     behGoAndGiveItemsToNoteblock { }
     *     behGoAndGiveItemsToOwner { }
     *     behStayNearNoteblock { }
     *     dash { }
     *     persistSit()
     *     variableMaxAutoStep { }
     *     equipItem()
     *     behEatMob { }
     *     behCroak { }
     *     behFeelingHappy { }
     *     behRising { }
     *     isPregnant()
     *     behRandomSearchAndDig { }
     *     behScenting { }
     *     behCelebrateSurvive { }
     *     behMoveOutdoors { }
     *     behWorkComposter { }
     *     behFertilizeFarmBlock { }
     *     behDrinkMilk { }
     *     behEmerge { }
     *     movementSoundDistanceOffset { }
     *     vibrationDamper()
     *     suspectTracking()
     *     angerLevel { }
     *     heartbeat("")
     *     behDig { }
     *     behRoar { }
     *     behSonicBoom { }
     *     behInvestigateSuspiciousLocation { }
     *     behSniff { }
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun components(setting: Components.() -> Unit) {
        componentsData = (componentsData ?: Components()).apply(setting)
    }

    /**
     * 0..1 instance allowed
     *
     * ```
     * event("test") {
     *     add { }
     *     remove { }
     *     sequence { }
     *     randomize { }
     *     setProperty("myProp", true, Addon.active!!)
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun event(name: String, data: BehEntityEvent.() -> Unit) {
        eventsData = (eventsData ?: mutableMapOf()).also { groupsMap ->
            groupsMap[name]?.apply(data) ?: run {
                groupsMap.put(name, BehEntityEvent().apply(data))
            }
        }
    }

    @OptIn(MonsteraBuildSetter::class)
    fun eventBorn(data: BehEntityEvent.() -> Unit) {
        eventsData = (eventsData ?: mutableMapOf()).also { groupsMap ->
            groupsMap["minecraft:entity_born"]?.apply(data) ?: run {
                groupsMap.put("minecraft:entity_born", BehEntityEvent().apply(data))
            }
        }
    }

    @OptIn(MonsteraBuildSetter::class)
    fun eventSpawned(data: BehEntityEvent.() -> Unit) {
        eventsData = (eventsData ?: mutableMapOf()).also { groupsMap ->
            groupsMap["minecraft:entity_spawned"]?.apply(data) ?: run {
                groupsMap.put("minecraft:entity_spawned", BehEntityEvent().apply(data))
            }
        }
    }

    @OptIn(MonsteraBuildSetter::class)
    fun eventOnPrime(data: BehEntityEvent.() -> Unit) {
        eventsData = (eventsData ?: mutableMapOf()).also { groupsMap ->
            groupsMap["minecraft:on_prime"]?.apply(data) ?: run {
                groupsMap.put("minecraft:on_prime", BehEntityEvent().apply(data))
            }
        }
    }

    @OptIn(MonsteraBuildSetter::class)
    fun eventTransformed(data: BehEntityEvent.() -> Unit) {
        eventsData = (eventsData ?: mutableMapOf()).also { groupsMap ->
            groupsMap["minecraft:entity_transformed"]?.apply(data) ?: run {
                groupsMap.put("minecraft:entity_transformed", BehEntityEvent().apply(data))
            }
        }
    }

    @DebugMarker
    fun debugEntity() {
        if (descriptionData == null) {
            logger().error("Beh Entity has no description!")
            return
        }
        if (descriptionData?.identifier == null) {
            logger().error("Beh Entity has no identifier!")
            return
        }

        //check if all component groups were called with events
        val componentGroupNames = componentGroupsData?.keys ?: mutableSetOf()
        val addedGroupNames = eventsData?.flatMap { it.value.getAddedGroups() } ?: mutableSetOf()

        val notAddedGroups = componentGroupNames.filter {
            !addedGroupNames.contains(it)
        }

        val nonExistentGroups = addedGroupNames.filter {
            !componentGroupNames.contains(it)
        }

        if (notAddedGroups.isNotEmpty()) {
            logger().warn(
                "The following groups where not added withe events: ${notAddedGroups.joinToString(", ")}"
            )
        }
        if (nonExistentGroups.isNotEmpty()) {
            logger().warn(
                """
                    The following groups where added withe events but do not exist: 
                    ${nonExistentGroups.joinToString(", ")}
                """.trimIndent()
            )
        }

        //check properties constrains
        descriptionData?.debugLogProperties()
    }

    /**
     * load json entities with this class
     */
    data class Loader(
        @SerializedName("format_version")
        @Expose
        val formatVersion: String = "",

        @SerializedName("minecraft:entity")
        @Expose
        val entity: BehEntity
    )
}