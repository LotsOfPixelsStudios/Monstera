package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.beh.entitiy.components.scraped.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class Components {
    @SerializedName("minecraft:is_hidden_when_invisible")
    @Expose
    var isHiddenWhenInvisibleData: IsHiddenWhenInvisible? = null
        @MonsteraBuildSetter set

    /**
     * Sets that this entity can hide from hostile mobs while invisible.
     * ```
     * isHiddenWhenInvisible {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun isHiddenWhenInvisible(value: IsHiddenWhenInvisible.() -> Unit) {
        isHiddenWhenInvisibleData = (isHiddenWhenInvisibleData ?: IsHiddenWhenInvisible()).apply(value)
    }

    @SerializedName("minecraft:type_family")
    @Expose
    var typeFamilyData: TypeFamily? = null
        @MonsteraBuildSetter set

    /**
     * Defines the families this entity belongs to.
     * ```
     * typeFamily {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun typeFamily(value: TypeFamily.() -> Unit) {
        typeFamilyData = (typeFamilyData ?: TypeFamily()).apply(value)
    }

    @SerializedName("minecraft:collision_box")
    @Expose
    var collisionBoxData: CollisionBox? = null
        @MonsteraBuildSetter set

    /**
     * Sets the width and height of the Entity's collision box.
     * ```
     * collisionBox {
     *     width = 0.35
     *     height = 0.6
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun collisionBox(value: CollisionBox.() -> Unit) {
        collisionBoxData = (collisionBoxData ?: CollisionBox()).apply(value)
    }

    @SerializedName("minecraft:balloonable")
    @Expose
    var balloonableData: Balloonable? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * balloonable {
     *     mass = 0.5
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun balloonable(value: Balloonable.() -> Unit) {
        balloonableData = (balloonableData ?: Balloonable()).apply(value)
    }

    @SerializedName("minecraft:breathable")
    @Expose
    var breathableData: Breathable? = null
        @MonsteraBuildSetter set

    /**
     * Defines what blocks this entity can breathe in and gives them the ability to suffocate.
     * ```
     * breathable {
     *     totalSupply = 15
     *     suffocateTime = 0
     *     breathesWater = true
     *     breathesAir = true
     *     generatesBubbles = false
     *     breathesLava = true
     *     inhaleTime = 3.75
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun breathable(value: Breathable.() -> Unit) {
        breathableData = (breathableData ?: Breathable()).apply(value)
    }

    @SerializedName("minecraft:nameable")
    @Expose
    var nameableData: Nameable? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to be named (e.g. using a name tag).
     * ```
     * nameable {
     *     alwaysShow = true
     *     allowNameTagRenaming = false
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun nameable(value: Nameable.() -> Unit) {
        nameableData = (nameableData ?: Nameable()).apply(value)
    }

    @SerializedName("minecraft:leashable")
    @Expose
    var leashableData: Leashable? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to be leashed and defines the conditions and events for this entity when is leashed.
     * ```
     * leashable {
     *     softDistance = 4.0
     *     hardDistance = 6.0
     *     maxDistance = 10.0
     *     canBeStolen = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun leashable(value: Leashable.() -> Unit) {
        leashableData = (leashableData ?: Leashable()).apply(value)
    }

    @SerializedName("minecraft:health")
    @Expose
    var healthData: Health? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * health {
     *     value = 20
     *     max = 6
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun health(value: Health.() -> Unit) {
        healthData = (healthData ?: Health()).apply(value)
    }

    @SerializedName("minecraft:hurt_on_condition")
    @Expose
    var hurtOnConditionData: HurtOnCondition? = null
        @MonsteraBuildSetter set

    /**
     * Defines a set of conditions under which an entity should take damage.
     * ```
     * hurtOnCondition {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun hurtOnCondition(value: HurtOnCondition.() -> Unit) {
        hurtOnConditionData = (hurtOnConditionData ?: HurtOnCondition()).apply(value)
    }

    @SerializedName("minecraft:damage_sensor")
    @Expose
    var damageSensorData: DamageSensor? = null
        @MonsteraBuildSetter set

    /**
     * Defines what events to call when this entity is damaged by specific entities or items.
     * ```
     * damageSensor {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun damageSensor(value: DamageSensor.() -> Unit) {
        damageSensorData = (damageSensorData ?: DamageSensor()).apply(value)
    }

    @SerializedName("minecraft:movement")
    @Expose
    var movementData: ComponentValue? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    var movement: Number? = null
        set(value) {
            movementData = ComponentValue().also { it.value = value }
            field = value
        }

    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun movement(value: ComponentValue.() -> Unit) {
        movementData = (movementData ?: ComponentValue()).apply(value)
    }

    @SerializedName("minecraft:flying_speed")
    @Expose
    var flyingSpeedData: ComponentValue? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    var flyingSpeed: Number? = null
        set(value) {
            flyingSpeedData = ComponentValue().also { it.value = value }
            field = value
        }

    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun flyingSpeed(value: ComponentValue.() -> Unit) {
        flyingSpeedData = (flyingSpeedData ?: ComponentValue()).apply(value)
    }

    @SerializedName("minecraft:navigation.hover")
    @Expose
    var navigationHoverData: NavigationHover? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to generate paths in the air like the vanilla Bees do. Keeps them from falling out of the skies and doing predictive movement.
     * ```
     * navigationHover {
     *     canPathOverWater = true
     *     canSink = false
     *     canPassDoors = false
     *     canPathFromAir = true
     *     avoidWater = true
     *     avoidDamageBlocks = true
     *     avoidSun = false
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun navigationHover(value: NavigationHover.() -> Unit) {
        navigationHoverData = (navigationHoverData ?: NavigationHover()).apply(value)
    }

    @SerializedName("minecraft:movement.hover")
    @Expose
    var movementHoverData: MovementHover? = null
        @MonsteraBuildSetter set

    /**
     * This move control causes the mob to hover.
     * ```
     * movementHover {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun movementHover(value: MovementHover.() -> Unit) {
        movementHoverData = (movementHoverData ?: MovementHover()).apply(value)
    }

    @SerializedName("minecraft:follow_range")
    @Expose
    var followRangeData: FollowRange? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * followRange {
     *     value = 1024
     *     max = 48
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun followRange(value: FollowRange.() -> Unit) {
        followRangeData = (followRangeData ?: FollowRange()).apply(value)
    }

    @SerializedName("minecraft:ambient_sound_interval")
    @Expose
    var ambientSoundIntervalData: AmbientSoundInterval? = null
        @MonsteraBuildSetter set

    /**
     * Sets the entity's delay between playing its ambient sound.
     * ```
     * ambientSoundInterval {
     *     value = 5.0
     *     range = 5.0
     *     eventName = ambient
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun ambientSoundInterval(value: AmbientSoundInterval.() -> Unit) {
        ambientSoundIntervalData = (ambientSoundIntervalData ?: AmbientSoundInterval()).apply(value)
    }

    @SerializedName("minecraft:jump.static")
    @Expose
    var jumpStaticData: JumpStatic? = null
        @MonsteraBuildSetter set

    /**
     * Gives the entity the ability to jump.
     * ```
     * jumpStatic {
     *     jumpPower = 0.6
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun jumpStatic(value: JumpStatic.() -> Unit) {
        jumpStaticData = (jumpStaticData ?: JumpStatic()).apply(value)
    }

    @SerializedName("minecraft:can_fly")
    @Expose
    var canFlyData: CanFly? = null
        @MonsteraBuildSetter set

    /**
     * Marks the entity as being able to fly, the pathfinder won't be restricted to paths where a solid block is required underneath it.
     * ```
     * canFly {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun canFly(value: CanFly.() -> Unit) {
        canFlyData = (canFlyData ?: CanFly()).apply(value)
    }

    @SerializedName("minecraft:physics")
    @Expose
    var physicsData: Physics? = null
        @MonsteraBuildSetter set

    /**
     * Defines physics properties of an actor, including if it is affected by gravity or if it collides with objects.
     * ```
     * physics {
     *     hasGravity = false
     *     hasCollision = false
     *     pushTowardsClosestSpace = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun physics(value: Physics.() -> Unit) {
        physicsData = (physicsData ?: Physics()).apply(value)
    }

    @SerializedName("minecraft:pushable")
    @Expose
    var pushableData: Pushable? = null
        @MonsteraBuildSetter set

    /**
     * Defines what can push an entity between other entities and pistons.
     * ```
     * pushable {
     *     isPushable = true
     *     isPushableByPiston = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun pushable(value: Pushable.() -> Unit) {
        pushableData = (pushableData ?: Pushable()).apply(value)
    }

    @SerializedName("minecraft:vibration_listener")
    @Expose
    var vibrationListenerData: VibrationListener? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * vibrationListener {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun vibrationListener(value: VibrationListener.() -> Unit) {
        vibrationListenerData = (vibrationListenerData ?: VibrationListener()).apply(value)
    }

    @SerializedName("minecraft:conditional_bandwidth_optimization")
    @Expose
    var conditionalBandwidthOptimizationData: ConditionalBandwidthOptimization? = null
        @MonsteraBuildSetter set

    /**
     * Defines the Conditional Spatial Update Bandwidth Optimizations of this entity.
     * ```
     * conditionalBandwidthOptimization {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun conditionalBandwidthOptimization(value: ConditionalBandwidthOptimization.() -> Unit) {
        conditionalBandwidthOptimizationData =
            (conditionalBandwidthOptimizationData ?: ConditionalBandwidthOptimization()).apply(value)
    }

    @SerializedName("minecraft:game_event_movement_tracking")
    @Expose
    var gameEventMovementTrackingData: GameEventMovementTracking? = null
        @MonsteraBuildSetter set

    /**
     * Allows an entity to emit `entityMove`, `swim` and `flap` game events, depending on the block the entity is moving through. It is added by default to every mob. Add it again to override its behavior.
     * ```
     * gameEventMovementTracking {
     *     emitFlap = true
     *     emitMove = false
     *     emitSwim = false
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun gameEventMovementTracking(value: GameEventMovementTracking.() -> Unit) {
        gameEventMovementTrackingData = (gameEventMovementTrackingData ?: GameEventMovementTracking()).apply(value)
    }

    @SerializedName("minecraft:inventory")
    @Expose
    var inventoryData: Inventory? = null
        @MonsteraBuildSetter set

    /**
     * Defines this entity's inventory properties.
     * ```
     * inventory {
     *     inventorySize = 1
     *     containerType = horse
     *     canBeSiphonedFrom = true
     *     additionalSlotsPerStrength = 3
     *     private = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun inventory(value: Inventory.() -> Unit) {
        inventoryData = (inventoryData ?: Inventory()).apply(value)
    }

    @SerializedName("minecraft:interact")
    @Expose
    var interactData: Interact? = null
        @MonsteraBuildSetter set

    /**
     * Defines interactions with this entity.
     * ```
     * interact {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun interact(value: Interact.() -> Unit) {
        interactData = (interactData ?: Interact()).apply(value)
    }

    @SerializedName("minecraft:behavior.panic")
    @Expose
    var behPanicData: BehPanic? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to enter the panic state, which makes it run around and away from the damage source that made it enter this state.
     * ```
     * behPanic {
     *     priority = 1
     *     speedMultiplier = 2.0
     *     force = true
     *     ignoreMobDamage = true
     *     panicSound = panic
     *     preferWater = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behPanic(value: BehPanic.() -> Unit) {
        behPanicData = (behPanicData ?: BehPanic()).apply(value)
    }

    @SerializedName("minecraft:behavior.go_and_give_items_to_noteblock")
    @Expose
    var behGoAndGiveItemsToNoteblockData: BehGoAndGiveItemsToNoteblock? = null
        @MonsteraBuildSetter set

    /**
     * The entity will attempt to toss the items from its inventory to a nearby recently played noteblock.
     * ```
     * behGoAndGiveItemsToNoteblock {
     *     priority = 3
     *     runSpeed = 8
     *     throwSound = item_thrown
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behGoAndGiveItemsToNoteblock(value: BehGoAndGiveItemsToNoteblock.() -> Unit) {
        behGoAndGiveItemsToNoteblockData =
            (behGoAndGiveItemsToNoteblockData ?: BehGoAndGiveItemsToNoteblock()).apply(value)
    }

    @SerializedName("minecraft:behavior.go_and_give_items_to_owner")
    @Expose
    var behGoAndGiveItemsToOwnerData: BehGoAndGiveItemsToOwner? = null
        @MonsteraBuildSetter set

    /**
     * The entity will attempt to toss the items from its inventory to its owner.
     * ```
     * behGoAndGiveItemsToOwner {
     *     priority = 4
     *     runSpeed = 8
     *     throwSound = item_thrown
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behGoAndGiveItemsToOwner(value: BehGoAndGiveItemsToOwner.() -> Unit) {
        behGoAndGiveItemsToOwnerData = (behGoAndGiveItemsToOwnerData ?: BehGoAndGiveItemsToOwner()).apply(value)
    }

    @SerializedName("minecraft:behavior.stay_near_noteblock")
    @Expose
    var behStayNearNoteblockData: BehStayNearNoteblock? = null
        @MonsteraBuildSetter set

    /**
     * The entity will attempt to toss the items from its inventory to a nearby recently played noteblock.
     * ```
     * behStayNearNoteblock {
     *     priority = 5
     *     speed = 8
     *     startDistance = 16
     *     stopDistance = 4
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behStayNearNoteblock(value: BehStayNearNoteblock.() -> Unit) {
        behStayNearNoteblockData = (behStayNearNoteblockData ?: BehStayNearNoteblock()).apply(value)
    }

    @SerializedName("minecraft:behavior.follow_owner")
    @Expose
    var behFollowOwnerData: BehFollowOwner? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to follow the player that owns them.
     * ```
     * behFollowOwner {
     *     priority = 6
     *     speedMultiplier = 8
     *     startDistance = 16
     *     stopDistance = 4
     *     canTeleport = false
     *     ignoreVibration = false
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behFollowOwner(value: BehFollowOwner.() -> Unit) {
        behFollowOwnerData = (behFollowOwnerData ?: BehFollowOwner()).apply(value)
    }

    @SerializedName("minecraft:behavior.float")
    @Expose
    var behFloatData: BehFloat? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to stay afloat while swimming. Passengers will be kicked out the moment the mob's head goes underwater, which may not happen for tall mobs.
     * ```
     * behFloat {
     *     priority = 7
     *     sinkWithPassengers = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behFloat(value: BehFloat.() -> Unit) {
        behFloatData = (behFloatData ?: BehFloat()).apply(value)
    }

    @SerializedName("minecraft:behavior.look_at_player")
    @Expose
    var behLookAtPlayerData: BehLookAtPlayer? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to look at the player when the player is nearby.
     * ```
     * behLookAtPlayer {
     *     priority = 8
     *     targetDistance = 6.0
     *     probability = 0.02
     *     lookDistance = 6.0
     *     angleOfViewHorizontal = 45
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behLookAtPlayer(value: BehLookAtPlayer.() -> Unit) {
        behLookAtPlayerData = (behLookAtPlayerData ?: BehLookAtPlayer()).apply(value)
    }

    @SerializedName("minecraft:behavior.random_look_around")
    @Expose
    var behRandomLookAroundData: BehRandomLookAround? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to randomly look around.
     * ```
     * behRandomLookAround {
     *     priority = 8
     *     lookDistance = 8.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behRandomLookAround(value: BehRandomLookAround.() -> Unit) {
        behRandomLookAroundData = (behRandomLookAroundData ?: BehRandomLookAround()).apply(value)
    }

    @SerializedName("minecraft:behavior.random_hover")
    @Expose
    var behRandomHoverData: BehRandomHover? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to hover around randomly, close to the surface
     * ```
     * behRandomHover {
     *     priority = 9
     *     xzDist = 8
     *     yDist = 8
     *     yOffset = -1
     *     interval = 1
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behRandomHover(value: BehRandomHover.() -> Unit) {
        behRandomHoverData = (behRandomHoverData ?: BehRandomHover()).apply(value)
    }

    @SerializedName("minecraft:timer")
    @Expose
    var timerData: Timer? = null
        @MonsteraBuildSetter set

    /**
     * Adds a timer after which an event will fire.
     * ```
     * timer {
     *     looping = false
     *     time = 3
     *     randomInterval = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun timer(value: Timer.() -> Unit) {
        timerData = (timerData ?: Timer()).apply(value)
    }

    @SerializedName("minecraft:behavior.pickup_items")
    @Expose
    var behPickupItemsData: BehPickupItems? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to pick up items on the ground.
     * ```
     * behPickupItems {
     *     priority = 2
     *     maxDist = 32
     *     searchHeight = 32
     *     goalRadius = 2.2
     *     speedMultiplier = 6
     *     pickupBasedOnChance = false
     *     canPickupAnyItem = false
     *     canPickupToHandOrEquipment = false
     *     pickupSameItemsAsInHand = true
     *     cooldownAfterBeingAttacked = 20.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behPickupItems(value: BehPickupItems.() -> Unit) {
        behPickupItemsData = (behPickupItemsData ?: BehPickupItems()).apply(value)
    }

    @SerializedName("minecraft:knockback_resistance")
    @Expose
    var knockbackResistanceData: ComponentValue? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    var knockbackResistance: Number? = null
        set(value) {
            knockbackResistanceData = ComponentValue().also { it.value = value }
            field = value
        }

    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun knockbackResistance(value: ComponentValue.() -> Unit) {
        knockbackResistanceData = (knockbackResistanceData ?: ComponentValue()).apply(value)
    }

    @SerializedName("minecraft:loot")
    @Expose
    var lootData: Loot? = null
        @MonsteraBuildSetter set

    /**
     * Sets the loot table for what items this entity drops upon death.
     * ```
     * loot {
     *     table = loot_tables/entities/armor_stand.json
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun loot(value: Loot.() -> Unit) {
        lootData = (lootData ?: Loot()).apply(value)
    }

    @SerializedName("minecraft:persistent")
    @Expose
    var persistentData: Persistent? = null
        @MonsteraBuildSetter set

    /**
     * Defines whether an entity should be persistent in the game world.
     * ```
     * persistent {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun persistent(value: Persistent.() -> Unit) {
        persistentData = (persistentData ?: Persistent()).apply(value)
    }

    @SerializedName("minecraft:projectile")
    @Expose
    var projectileData: Projectile? = null
        @MonsteraBuildSetter set

    /**
     * Allows the entity to be a thrown entity.
     * ```
     * projectile {
     *     hitSound = bow.hit
     *     power = 1.6
     *     gravity = 0.05
     *     uncertaintyBase = 16
     *     uncertaintyMultiplier = 4
     *     anchor = 1
     *     shouldBounce = true
     *     inertia = 1
     *     semiRandomDiffDamage = true
     *     reflectOnHurt = true
     *     angleOffset = 0.0
     *     liquidInertia = 1
     *     catchFire = true
     *     destroyOnHurt = true
     *     critParticleOnHurt = true
     *     homing = true
     *     hitGroundSound = item.trident.hit_ground
     *     stopOnHurt = true
     *     multipleTargets = false
     *     shootSound = bow
     *     shootTarget = false
     *     isDangerous = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun projectile(value: Projectile.() -> Unit) {
        projectileData = (projectileData ?: Projectile()).apply(value)
    }

    @SerializedName("minecraft:navigation.generic")
    @Expose
    var navigationGenericData: NavigationGeneric? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to generate paths by walking, swimming, flying and/or climbing around and jumping up and down a block.
     * ```
     * navigationGeneric {
     *     isAmphibious = true
     *     canPathOverWater = true
     *     canSwim = true
     *     canWalk = true
     *     canSink = false
     *     avoidDamageBlocks = true
     *     canBreach = true
     *     canJump = false
     *     canBreakDoors = true
     *     avoidSun = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun navigationGeneric(value: NavigationGeneric.() -> Unit) {
        navigationGenericData = (navigationGenericData ?: NavigationGeneric()).apply(value)
    }

    @SerializedName("minecraft:movement.amphibious")
    @Expose
    var movementAmphibiousData: MovementAmphibious? = null
        @MonsteraBuildSetter set

    /**
     * This move control allows the mob to swim in water and walk on land.
     * ```
     * movementAmphibious {
     *     maxTurn = 15.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun movementAmphibious(value: MovementAmphibious.() -> Unit) {
        movementAmphibiousData = (movementAmphibiousData ?: MovementAmphibious()).apply(value)
    }

    @SerializedName("minecraft:underwater_movement")
    @Expose
    var underwaterMovementData: ComponentValue? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    var underwaterMovement: Number? = null
        set(value) {
            underwaterMovementData = ComponentValue().also { it.value = value }
            field = value
        }

    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun underwaterMovement(value: ComponentValue.() -> Unit) {
        underwaterMovementData = (underwaterMovementData ?: ComponentValue()).apply(value)
    }

    @SerializedName("minecraft:despawn")
    @Expose
    var despawnData: Despawn? = null
        @MonsteraBuildSetter set

    /**
     * Despawns the Actor when the despawn rules or optional filters evaluate to true.
     * ```
     * despawn {
     *     removeChildEntities = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun despawn(value: Despawn.() -> Unit) {
        despawnData = (despawnData ?: Despawn()).apply(value)
    }

    @SerializedName("minecraft:attack")
    @Expose
    var attackData: Attack? = null
        @MonsteraBuildSetter set

    /**
     * Defines an entity's melee attack and any additional effects on it.
     * ```
     * attack {
     *     damage = 2
     *     effectName = poison
     *     effectDuration = 10
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun attack(value: Attack.() -> Unit) {
        attackData = (attackData ?: Attack()).apply(value)
    }

    @SerializedName("minecraft:combat_regeneration")
    @Expose
    var combatRegenerationData: CombatRegeneration? = null
        @MonsteraBuildSetter set

    /**
     * Gives Regeneration I and removes Mining Fatigue from the mob that kills the Actor's attack target.
     * ```
     * combatRegeneration {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun combatRegeneration(value: CombatRegeneration.() -> Unit) {
        combatRegenerationData = (combatRegenerationData ?: CombatRegeneration()).apply(value)
    }

    @SerializedName("minecraft:behavior.play_dead")
    @Expose
    var behPlayDeadData: BehPlayDead? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to pretend to be dead to avoid being targeted by attackers.
     * ```
     * behPlayDead {
     *     priority = 0
     *     duration = 10
     *     forceBelowHealth = 8
     *     randomStartChance = 0.33
     *     applyRegeneration = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behPlayDead(value: BehPlayDead.() -> Unit) {
        behPlayDeadData = (behPlayDeadData ?: BehPlayDead()).apply(value)
    }

    @SerializedName("minecraft:behavior.tempt")
    @Expose
    var behTemptData: BehTempt? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to be tempted by food they like.
     * ```
     * behTempt {
     *     priority = 2
     *     speedMultiplier = 1.1
     *     canTemptVertically = true
     *     withinRadius = 8
     *     canGetScared = true
     *     temptSound = tempt
     *     canTemptWhileRidden = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behTempt(value: BehTempt.() -> Unit) {
        behTemptData = (behTemptData ?: BehTempt()).apply(value)
    }

    @SerializedName("minecraft:behavior.nearest_attackable_target")
    @Expose
    var behNearestAttackableTargetData: BehNearestAttackableTarget? = null
        @MonsteraBuildSetter set

    /**
     * Allows an entity to attack the closest target within a given subset of specific target types.
     * ```
     * behNearestAttackableTarget {
     *     priority = 3
     *     mustSee = true
     *     reselectTargets = true
     *     withinRadius = 20.0
     *     mustSeeForgetDuration = 17.0
     *     attackInterval = 5
     *     persistTime = 0.5
     *     attackIntervalMin = 1.0
     *     mustReach = true
     *     scanInterval = 20
     *     targetSearchHeight = 80.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behNearestAttackableTarget(value: BehNearestAttackableTarget.() -> Unit) {
        behNearestAttackableTargetData = (behNearestAttackableTargetData ?: BehNearestAttackableTarget()).apply(value)
    }

    @SerializedName("minecraft:behavior.melee_box_attack")
    @Expose
    var behMeleeBoxAttackData: BehMeleeBoxAttack? = null
        @MonsteraBuildSetter set

    /**
     * Allows an entity to deal damage through a melee attack with reach calculations based on bounding boxes.
     * ```
     * behMeleeBoxAttack {
     *     priority = 4
     *     attackOnce = true
     *     speedMultiplier = 1.4
     *     trackTarget = true
     *     randomStopInterval = 100
     *     canSpreadOnFire = true
     *     requireCompletePath = true
     *     cooldownTime = 0.75
     *     meleeFov = 360
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behMeleeBoxAttack(value: BehMeleeBoxAttack.() -> Unit) {
        behMeleeBoxAttackData = (behMeleeBoxAttackData ?: BehMeleeBoxAttack()).apply(value)
    }

    @SerializedName("minecraft:behavior.move_to_water")
    @Expose
    var behMoveToWaterData: BehMoveToWater? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to move back into water when on land.
     * ```
     * behMoveToWater {
     *     priority = 6
     *     searchRange = 16
     *     searchHeight = 5
     *     searchCount = 1
     *     goalRadius = 0.1
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behMoveToWater(value: BehMoveToWater.() -> Unit) {
        behMoveToWaterData = (behMoveToWaterData ?: BehMoveToWater()).apply(value)
    }

    @SerializedName("minecraft:behavior.swim_idle")
    @Expose
    var behSwimIdleData: BehSwimIdle? = null
        @MonsteraBuildSetter set

    /**
     * Allows the entity go idle, if swimming. Entity must be in water.
     * ```
     * behSwimIdle {
     *     priority = 7
     *     idleTime = 5.0
     *     successRate = 0.05
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behSwimIdle(value: BehSwimIdle.() -> Unit) {
        behSwimIdleData = (behSwimIdleData ?: BehSwimIdle()).apply(value)
    }

    @SerializedName("minecraft:behavior.random_swim")
    @Expose
    var behRandomSwimData: BehRandomSwim? = null
        @MonsteraBuildSetter set

    /**
     * Allows an entity to randomly move through water
     * ```
     * behRandomSwim {
     *     priority = 8
     *     interval = 0
     *     xzDist = 30
     *     yDist = 15
     *     speedMultiplier = 0.5
     *     avoidSurface = false
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behRandomSwim(value: BehRandomSwim.() -> Unit) {
        behRandomSwimData = (behRandomSwimData ?: BehRandomSwim()).apply(value)
    }

    @SerializedName("minecraft:behavior.random_stroll")
    @Expose
    var behRandomStrollData: BehRandomStroll? = null
        @MonsteraBuildSetter set

    /**
     * Allows a mob to randomly stroll around.
     * ```
     * behRandomStroll {
     *     priority = 9
     *     interval = 100
     *     speedMultiplier = 1.0
     *     xzDist = 16
     *     yDist = 1
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behRandomStroll(value: BehRandomStroll.() -> Unit) {
        behRandomStrollData = (behRandomStrollData ?: BehRandomStroll()).apply(value)
    }

    @SerializedName("minecraft:attack_cooldown")
    @Expose
    var attackCooldownData: AttackCooldown? = null
        @MonsteraBuildSetter set

    /**
     * Adds a cooldown to a mob. The intention of this cooldown is to be used to prevent the mob from attempting to aquire new attack targets.
     * ```
     * attackCooldown {
     *     attackCooldownTime = 120.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun attackCooldown(value: AttackCooldown.() -> Unit) {
        attackCooldownData = (attackCooldownData ?: AttackCooldown()).apply(value)
    }

    @SerializedName("minecraft:variant")
    @Expose
    var variantData: ComponentValue? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    var variant: Number? = null
        set(value) {
            variantData = ComponentValue().also { it.value = value }
            field = value
        }

    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun variant(value: ComponentValue.() -> Unit) {
        variantData = (variantData ?: ComponentValue()).apply(value)
    }

    @SerializedName("minecraft:is_baby")
    @Expose
    var isBabyData: IsBaby? = null
        @MonsteraBuildSetter set

    /**
     * Sets that this entity is a baby.
     * ```
     * isBaby {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun isBaby(value: IsBaby.() -> Unit) {
        isBabyData = (isBabyData ?: IsBaby()).apply(value)
    }

    @SerializedName("minecraft:scale")
    @Expose
    var scaleData: ComponentValue? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    var scale: Number? = null
        set(value) {
            scaleData = ComponentValue().also { it.value = value }
            field = value
        }

    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun scale(value: ComponentValue.() -> Unit) {
        scaleData = (scaleData ?: ComponentValue()).apply(value)
    }

    @SerializedName("minecraft:ageable")
    @Expose
    var ageableData: Ageable? = null
        @MonsteraBuildSetter set

    /**
     * Adds a timer for the entity to grow up. It can be accelerated by giving the entity the items it likes as defined by feedItems.
     * ```
     * ageable {
     *     duration = 1200
     *     feedItems = tropical_fish_bucket
     *     transformToItem = water_bucket:0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun ageable(value: Ageable.() -> Unit) {
        ageableData = (ageableData ?: Ageable()).apply(value)
    }

    @SerializedName("minecraft:behavior.follow_parent")
    @Expose
    var behFollowParentData: BehFollowParent? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to follow their parent around.
     * ```
     * behFollowParent {
     *     priority = 5
     *     speedMultiplier = 1.1
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behFollowParent(value: BehFollowParent.() -> Unit) {
        behFollowParentData = (behFollowParentData ?: BehFollowParent()).apply(value)
    }

    @SerializedName("minecraft:experience_reward")
    @Expose
    var experienceRewardData: ExperienceReward? = null
        @MonsteraBuildSetter set

    /**
     * .
     * ```
     * experienceReward {
     *     onBred = Math.Random(1,7)
     *     onDeath = query.last_hit_by_player ? Math.Random(1,3) : 0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun experienceReward(value: ExperienceReward.() -> Unit) {
        experienceRewardData = (experienceRewardData ?: ExperienceReward()).apply(value)
    }

    @SerializedName("minecraft:behavior.breed")
    @Expose
    var behBreedData: BehBreed? = null
        @MonsteraBuildSetter set

    /**
     * Allows this mob to breed with other mobs.
     * ```
     * behBreed {
     *     priority = 1
     *     speedMultiplier = 1.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behBreed(value: BehBreed.() -> Unit) {
        behBreedData = (behBreedData ?: BehBreed()).apply(value)
    }

    @SerializedName("minecraft:breedable")
    @Expose
    var breedableData: Breedable? = null
        @MonsteraBuildSetter set

    /**
     * Defines the way an entity can get into the 'love' state.
     * ```
     * breedable {
     *     requireTame = false
     *     breedItems = tropical_fish_bucket
     *     transformToItem = water_bucket:0
     *     requireFullHealth = true
     *     allowSitting = true
     *     inheritTamed = false
     *     causesPregnancy = true
     *     mutationStrategy = random
     *     blendAttributes = false
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun breedable(value: Breedable.() -> Unit) {
        breedableData = (breedableData ?: Breedable()).apply(value)
    }

    @SerializedName("minecraft:environment_sensor")
    @Expose
    var environmentSensorData: EnvironmentSensor? = null
        @MonsteraBuildSetter set

    /**
     * Creates a trigger based on environment conditions.
     * ```
     * environmentSensor {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun environmentSensor(value: EnvironmentSensor.() -> Unit) {
        environmentSensorData = (environmentSensorData ?: EnvironmentSensor()).apply(value)
    }

    @SerializedName("minecraft:damage_over_time")
    @Expose
    var damageOverTimeData: DamageOverTime? = null
        @MonsteraBuildSetter set

    /**
     * Applies defined amount of damage to the entity at specified intervals.
     * ```
     * damageOverTime {
     *     damagePerHurt = 1
     *     timeBetweenHurt = 0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun damageOverTime(value: DamageOverTime.() -> Unit) {
        damageOverTimeData = (damageOverTimeData ?: DamageOverTime()).apply(value)
    }

    @SerializedName("minecraft:drying_out_timer")
    @Expose
    var dryingOutTimerData: DryingOutTimer? = null
        @MonsteraBuildSetter set

    /**
     * Adds a timer for drying out that will count down and fire 'dried_out_event' or will stop as soon as the entity will get under rain or water and fire 'stopped_drying_out_event'
     * ```
     * dryingOutTimer {
     *     totalTime = 300
     *     waterBottleRefillTime = 90
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun dryingOutTimer(value: DryingOutTimer.() -> Unit) {
        dryingOutTimerData = (dryingOutTimerData ?: DryingOutTimer()).apply(value)
    }

    @SerializedName("minecraft:navigation.float")
    @Expose
    var navigationFloatData: NavigationFloat? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to generate paths by flying around the air like the regular Ghast.
     * ```
     * navigationFloat {
     *     canPathOverWater = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun navigationFloat(value: NavigationFloat.() -> Unit) {
        navigationFloatData = (navigationFloatData ?: NavigationFloat()).apply(value)
    }

    @SerializedName("minecraft:movement.basic")
    @Expose
    var movementBasicData: MovementBasic? = null
        @MonsteraBuildSetter set

    /**
     * This component accents the movement of an entity.
     * ```
     * movementBasic {
     *     maxTurn = 180.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun movementBasic(value: MovementBasic.() -> Unit) {
        movementBasicData = (movementBasicData ?: MovementBasic()).apply(value)
    }

    @SerializedName("minecraft:behavior.float_wander")
    @Expose
    var behFloatWanderData: BehFloatWander? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to float around like the Ghast.
     * ```
     * behFloatWander {
     *     xzDist = 10
     *     yDist = 7
     *     yOffset = -2.0
     *     randomReselect = true
     *     priority = 2
     *     mustReach = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behFloatWander(value: BehFloatWander.() -> Unit) {
        behFloatWanderData = (behFloatWanderData ?: BehFloatWander()).apply(value)
    }

    @SerializedName("minecraft:behavior.move_towards_home_restriction")
    @Expose
    var behMoveTowardsHomeRestrictionData: BehMoveTowardsHomeRestriction? = null
        @MonsteraBuildSetter set

    /**
     * Allows mobs with the home component to move toward their pre-defined area that the mob should be restricted to.
     * ```
     * behMoveTowardsHomeRestriction {
     *     priority = 9
     *     speedMultiplier = 1.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behMoveTowardsHomeRestriction(value: BehMoveTowardsHomeRestriction.() -> Unit) {
        behMoveTowardsHomeRestrictionData =
            (behMoveTowardsHomeRestrictionData ?: BehMoveTowardsHomeRestriction()).apply(value)
    }

    @SerializedName("minecraft:on_target_acquired")
    @Expose
    var onTargetAcquiredData: OnTargetAcquired? = null
        @MonsteraBuildSetter set

    /**
     * Adds a trigger to call when this entity finds a target.
     * ```
     * onTargetAcquired {
     *     event = attacked
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun onTargetAcquired(value: OnTargetAcquired.() -> Unit) {
        onTargetAcquiredData = (onTargetAcquiredData ?: OnTargetAcquired()).apply(value)
    }

    @SerializedName("minecraft:home")
    @Expose
    var homeData: Home? = null
        @MonsteraBuildSetter set

    /**
     * Saves a home pos for when the the entity is spawned.
     * ```
     * home {
     *     restrictionRadius = 22
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun home(value: Home.() -> Unit) {
        homeData = (homeData ?: Home()).apply(value)
    }

    @SerializedName("minecraft:block_sensor")
    @Expose
    var blockSensorData: BlockSensor? = null
        @MonsteraBuildSetter set

    /**
     * Fires off a specified event when a block in the block list is broken within the sensor range.
     * ```
     * blockSensor {
     *     sensorRadius = 16
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun blockSensor(value: BlockSensor.() -> Unit) {
        blockSensorData = (blockSensorData ?: BlockSensor()).apply(value)
    }

    @SerializedName("minecraft:behavior.hurt_by_target")
    @Expose
    var behHurtByTargetData: BehHurtByTarget? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to target another mob that hurts them.
     * ```
     * behHurtByTarget {
     *     priority = 1
     *     hurtOwner = true
     *     alertSameType = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behHurtByTarget(value: BehHurtByTarget.() -> Unit) {
        behHurtByTargetData = (behHurtByTargetData ?: BehHurtByTarget()).apply(value)
    }

    @SerializedName("minecraft:angry")
    @Expose
    var angryData: Angry? = null
        @MonsteraBuildSetter set

    /**
     * Defines the entity's 'angry' state using a timer.
     * ```
     * angry {
     *     duration = 25
     *     broadcastAnger = true
     *     broadcastRange = 16
     *     durationDelta = 3
     *     angrySound = angry
     *     broadcastAngerOnAttack = true
     *     broadcastAngerOnBeingAttacked = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun angry(value: Angry.() -> Unit) {
        angryData = (angryData ?: Angry()).apply(value)
    }

    @SerializedName("minecraft:mark_variant")
    @Expose
    var markVariantData: ComponentValue? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    var markVariant: Number? = null
        set(value) {
            markVariantData = ComponentValue().also { it.value = value }
            field = value
        }

    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun markVariant(value: ComponentValue.() -> Unit) {
        markVariantData = (markVariantData ?: ComponentValue()).apply(value)
    }

    @SerializedName("minecraft:behavior.move_to_block")
    @Expose
    var behMoveToBlockData: BehMoveToBlock? = null
        @MonsteraBuildSetter set

    /**
     * Allows mob to move towards a block.
     * ```
     * behMoveToBlock {
     *     priority = 10
     *     tickInterval = 1
     *     startChance = 0.5
     *     searchRange = 6
     *     searchHeight = 4
     *     goalRadius = 1.0
     *     stayDuration = 20.0
     *     targetSelectionMethod = random
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behMoveToBlock(value: BehMoveToBlock.() -> Unit) {
        behMoveToBlockData = (behMoveToBlockData ?: BehMoveToBlock()).apply(value)
    }

    @SerializedName("minecraft:grows_crop")
    @Expose
    var growsCropData: GrowsCrop? = null
        @MonsteraBuildSetter set

    /**
     * Could increase crop growth when entity walks over crop
     * ```
     * growsCrop {
     *     charges = 10
     *     chance = 0.03
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun growsCrop(value: GrowsCrop.() -> Unit) {
        growsCropData = (growsCropData ?: GrowsCrop()).apply(value)
    }

    @SerializedName("minecraft:behavior.go_home")
    @Expose
    var behGoHomeData: BehGoHome? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to move back to the position they were spawned.
     * ```
     * behGoHome {
     *     priority = 4
     *     speedMultiplier = 1.0
     *     interval = 1
     *     goalRadius = 1.2
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behGoHome(value: BehGoHome.() -> Unit) {
        behGoHomeData = (behGoHomeData ?: BehGoHome()).apply(value)
    }

    @SerializedName("minecraft:navigation.walk")
    @Expose
    var navigationWalkData: NavigationWalk? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to generate paths by walking around and jumping up and down a block like regular mobs.
     * ```
     * navigationWalk {
     *     canPathOverWater = true
     *     avoidWater = true
     *     avoidDamageBlocks = true
     *     canFloat = true
     *     canPassDoors = true
     *     canOpenDoors = true
     *     canSink = false
     *     isAmphibious = true
     *     canBreakDoors = true
     *     avoidPortals = false
     *     avoidSun = true
     *     canPathOverLava = true
     *     canWalkInLava = true
     *     canWalk = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun navigationWalk(value: NavigationWalk.() -> Unit) {
        navigationWalkData = (navigationWalkData ?: NavigationWalk()).apply(value)
    }

    @SerializedName("minecraft:can_climb")
    @Expose
    var canClimbData: CanClimb? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to climb up ladders.
     * ```
     * canClimb {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun canClimb(value: CanClimb.() -> Unit) {
        canClimbData = (canClimbData ?: CanClimb()).apply(value)
    }

    @SerializedName("minecraft:fire_immune")
    @Expose
    var fireImmuneData: FireImmune? = null
        @MonsteraBuildSetter set

    /**
     * Sets that this entity doesn't take damage from fire.
     * ```
     * fireImmune {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun fireImmune(value: FireImmune.() -> Unit) {
        fireImmuneData = (fireImmuneData ?: FireImmune()).apply(value)
    }

    @SerializedName("minecraft:on_hurt")
    @Expose
    var onHurtData: OnHurt? = null
        @MonsteraBuildSetter set

    /**
     * Adds a trigger to call when this entity takes damage.
     * ```
     * onHurt {
     *     event = minecraft:on_hurt_event
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun onHurt(value: OnHurt.() -> Unit) {
        onHurtData = (onHurtData ?: OnHurt()).apply(value)
    }

    @SerializedName("minecraft:on_hurt_by_player")
    @Expose
    var onHurtByPlayerData: OnHurtByPlayer? = null
        @MonsteraBuildSetter set

    /**
     * Adds a trigger to call when this entity is attacked by the player.
     * ```
     * onHurtByPlayer {
     *     event = minecraft:on_hurt_event
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun onHurtByPlayer(value: OnHurtByPlayer.() -> Unit) {
        onHurtByPlayerData = (onHurtByPlayerData ?: OnHurtByPlayer()).apply(value)
    }

    @SerializedName("minecraft:target_nearby_sensor")
    @Expose
    var targetNearbySensorData: TargetNearbySensor? = null
        @MonsteraBuildSetter set

    /**
     * Defines the entity's range within which it can see or sense other entities to target them.
     * ```
     * targetNearbySensor {
     *     insideRange = 2.0
     *     outsideRange = 3.0
     *     mustSee = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun targetNearbySensor(value: TargetNearbySensor.() -> Unit) {
        targetNearbySensorData = (targetNearbySensorData ?: TargetNearbySensor()).apply(value)
    }

    @SerializedName("minecraft:shooter")
    @Expose
    var shooterData: Shooter? = null
        @MonsteraBuildSetter set

    /**
     * Defines the entity's ranged attack behavior. The "minecraft:behavior.ranged_attack" goal uses this component to determine which projectiles to shoot.
     * ```
     * shooter {
     *     def = minecraft:small_fireball
     *     sound = item.trident.throw
     *     type = dragonfireball
     *     auxVal = 19
     *     power = 0.75
     *     magic = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun shooter(value: Shooter.() -> Unit) {
        shooterData = (shooterData ?: Shooter()).apply(value)
    }

    @SerializedName("minecraft:behavior.ranged_attack")
    @Expose
    var behRangedAttackData: BehRangedAttack? = null
        @MonsteraBuildSetter set

    /**
     * Allows an entity to attack by using ranged shots. "charge_shoot_trigger" must be greater than 0 to enable charged up burst-shot attacks. Requires minecraft:shooter to define projectile behaviour.
     * ```
     * behRangedAttack {
     *     priority = 3
     *     burstShots = 3
     *     burstInterval = 0.3
     *     chargeChargedTrigger = 0.0
     *     chargeShootTrigger = 4.0
     *     attackIntervalMin = 3.0
     *     attackIntervalMax = 5.0
     *     attackRadius = 16.0
     *     swing = true
     *     attackRadiusMin = 4
     *     speedMultiplier = 1.0
     *     targetInSightTime = 0.1
     *     attackInterval = 1
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behRangedAttack(value: BehRangedAttack.() -> Unit) {
        behRangedAttackData = (behRangedAttackData ?: BehRangedAttack()).apply(value)
    }

    @SerializedName("minecraft:is_stackable")
    @Expose
    var isStackableData: ComponentValue? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    var isStackable: Boolean? = null
        set(value) {
            isStackableData = ComponentValue().also { it.value = value }
            field = value
        }

    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun isStackable(value: ComponentValue.() -> Unit) {
        isStackableData = (isStackableData ?: ComponentValue()).apply(value)
    }

    @SerializedName("minecraft:buoyant")
    @Expose
    var buoyantData: Buoyant? = null
        @MonsteraBuildSetter set

    /**
     * Enables an entity to float on the specified liquid blocks.
     * ```
     * buoyant {
     *     baseBuoyancy = 1.0
     *     applyGravity = true
     *     simulateWaves = true
     *     bigWaveProbability = 0.03
     *     bigWaveSpeed = 10.0
     *     dragDownOnBuoyancyRemoved = 0.7
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun buoyant(value: Buoyant.() -> Unit) {
        buoyantData = (buoyantData ?: Buoyant()).apply(value)
    }

    @SerializedName("minecraft:inside_block_notifier")
    @Expose
    var insideBlockNotifierData: InsideBlockNotifier? = null
        @MonsteraBuildSetter set

    /**
     * Verifies whether the entity is inside any of the listed blocks.
     * ```
     * insideBlockNotifier {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun insideBlockNotifier(value: InsideBlockNotifier.() -> Unit) {
        insideBlockNotifierData = (insideBlockNotifierData ?: InsideBlockNotifier()).apply(value)
    }

    @SerializedName("minecraft:rideable")
    @Expose
    var rideableData: Rideable? = null
        @MonsteraBuildSetter set

    /**
     * Determines whether this entity can be ridden. Allows specifying the different seat positions and quantity.
     * ```
     * rideable {
     *     seatCount = 2
     *     passengerMaxWidth = 1.375
     *     interactText = action.interact.ride.boat
     *     pullInEntities = true
     *     crouchingSkipInteract = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun rideable(value: Rideable.() -> Unit) {
        rideableData = (rideableData ?: Rideable()).apply(value)
    }

    @SerializedName("minecraft:out_of_control")
    @Expose
    var outOfControlData: OutOfControl? = null
        @MonsteraBuildSetter set

    /**
     * Defines the entity's 'out of control' state.
     * ```
     * outOfControl {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun outOfControl(value: OutOfControl.() -> Unit) {
        outOfControlData = (outOfControlData ?: OutOfControl()).apply(value)
    }

    @SerializedName("minecraft:is_tamed")
    @Expose
    var isTamedData: IsTamed? = null
        @MonsteraBuildSetter set

    /**
     * Sets that this entity is currently tamed.
     * ```
     * isTamed {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun isTamed(value: IsTamed.() -> Unit) {
        isTamedData = (isTamedData ?: IsTamed()).apply(value)
    }

    @SerializedName("minecraft:healable")
    @Expose
    var healableData: Healable? = null
        @MonsteraBuildSetter set

    /**
     * Defines the interactions with this entity for healing it.
     * ```
     * healable {
     *     forceUse = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun healable(value: Healable.() -> Unit) {
        healableData = (healableData ?: Healable()).apply(value)
    }

    @SerializedName("minecraft:behavior.random_look_around_and_sit")
    @Expose
    var behRandomLookAroundAndSitData: BehRandomLookAroundAndSit? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to randomly sit and look around for a duration. Note: Must have a sitting animation set up to use this.
     * ```
     * behRandomLookAroundAndSit {
     *     priority = 4
     *     continueIfLeashed = true
     *     continueSittingOnReload = true
     *     minLookCount = 2
     *     maxLookCount = 5
     *     minLookTime = 80
     *     maxLookTime = 100
     *     minAngleOfViewHorizontal = -30
     *     maxAngleOfViewHorizontal = 30
     *     randomLookAroundCooldown = 5
     *     probability = 0.001
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behRandomLookAroundAndSit(value: BehRandomLookAroundAndSit.() -> Unit) {
        behRandomLookAroundAndSitData = (behRandomLookAroundAndSitData ?: BehRandomLookAroundAndSit()).apply(value)
    }

    @SerializedName("minecraft:variable_max_auto_step")
    @Expose
    var variableMaxAutoStepData: VariableMaxAutoStep? = null
        @MonsteraBuildSetter set

    /**
     * Entities with this component will have a maximum auto step height that is different depending on whether they are on a block that prevents jumping. Incompatible with "runtime_identifier": "minecraft:horse".
     * ```
     * variableMaxAutoStep {
     *     baseValue = 1.5625
     *     controlledValue = 1.5625
     *     jumpPreventedValue = 0.5625
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun variableMaxAutoStep(value: VariableMaxAutoStep.() -> Unit) {
        variableMaxAutoStepData = (variableMaxAutoStepData ?: VariableMaxAutoStep()).apply(value)
    }

    @SerializedName("minecraft:is_saddled")
    @Expose
    var isSaddledData: IsSaddled? = null
        @MonsteraBuildSetter set

    /**
     * Sets that this entity is currently saddled.
     * ```
     * isSaddled {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun isSaddled(value: IsSaddled.() -> Unit) {
        isSaddledData = (isSaddledData ?: IsSaddled()).apply(value)
    }

    @SerializedName("minecraft:input_ground_controlled")
    @Expose
    var inputGroundControlledData: InputGroundControlled? = null
        @MonsteraBuildSetter set

    /**
     * When configured as a rideable entity, the entity will be controlled using WASD controls. Beginning with 1.19.50 the default auto step height for rideable entities is half a block. Consider adding the `variable_max_auto_step` component to increase it.
     * ```
     * inputGroundControlled {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun inputGroundControlled(value: InputGroundControlled.() -> Unit) {
        inputGroundControlledData = (inputGroundControlledData ?: InputGroundControlled()).apply(value)
    }

    @SerializedName("minecraft:dash")
    @Expose
    var dashData: Dash? = null
        @MonsteraBuildSetter set

    /**
     * Ability for a rideable entity to dash.
     * ```
     * dash {
     *     cooldownTime = 2.75
     *     horizontalMomentum = 20.0
     *     verticalMomentum = 0.6
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun dash(value: Dash.() -> Unit) {
        dashData = (dashData ?: Dash()).apply(value)
    }

    @SerializedName("minecraft:behavior.player_ride_tamed")
    @Expose
    var behPlayerRideTamedData: BehPlayerRideTamed? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to be ridden by the player after being tamed.
     * ```
     * behPlayerRideTamed {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behPlayerRideTamed(value: BehPlayerRideTamed.() -> Unit) {
        behPlayerRideTamedData = (behPlayerRideTamedData ?: BehPlayerRideTamed()).apply(value)
    }

    @SerializedName("minecraft:equippable")
    @Expose
    var equippableData: Equippable? = null
        @MonsteraBuildSetter set

    /**
     * Defines an entity's behavior for having items equipped to it.
     * ```
     * equippable {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun equippable(value: Equippable.() -> Unit) {
        equippableData = (equippableData ?: Equippable()).apply(value)
    }

    @SerializedName("minecraft:attack_damage")
    @Expose
    var attackDamageData: ComponentValue? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    var attackDamage: Number? = null
        set(value) {
            attackDamageData = ComponentValue().also { it.value = value }
            field = value
        }

    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun attackDamage(value: ComponentValue.() -> Unit) {
        attackDamageData = (attackDamageData ?: ComponentValue()).apply(value)
    }

    @SerializedName("minecraft:dweller")
    @Expose
    var dwellerData: Dweller? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * dweller {
     *     dwellingType = village
     *     dwellerRole = passive
     *     updateIntervalBase = 60
     *     updateIntervalVariant = 40
     *     canFindPoi = false
     *     canMigrate = true
     *     firstFoundingReward = 0
     *     preferredProfession = farmer
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun dweller(value: Dweller.() -> Unit) {
        dwellerData = (dwellerData ?: Dweller()).apply(value)
    }

    @SerializedName("minecraft:behavior.mount_pathing")
    @Expose
    var behMountPathingData: BehMountPathing? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to move around on its own while mounted seeking a target to attack.
     * ```
     * behMountPathing {
     *     priority = 1
     *     speedMultiplier = 1.25
     *     targetDist = 0
     *     trackTarget = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behMountPathing(value: BehMountPathing.() -> Unit) {
        behMountPathingData = (behMountPathingData ?: BehMountPathing()).apply(value)
    }

    @SerializedName("minecraft:behavior.leap_at_target")
    @Expose
    var behLeapAtTargetData: BehLeapAtTarget? = null
        @MonsteraBuildSetter set

    /**
     * Allows monsters to jump at and attack their target. Can only be used by hostile mobs.
     * ```
     * behLeapAtTarget {
     *     priority = 3
     *     targetDist = 0.3
     *     yd = 0.4
     *     mustBeOnGround = false
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behLeapAtTarget(value: BehLeapAtTarget.() -> Unit) {
        behLeapAtTargetData = (behLeapAtTargetData ?: BehLeapAtTarget()).apply(value)
    }

    @SerializedName("minecraft:behavior.ocelotattack")
    @Expose
    var behOcelotattackData: BehOcelotattack? = null
        @MonsteraBuildSetter set

    /**
     * Allows an entity to attack by sneaking and pouncing.
     * ```
     * behOcelotattack {
     *     priority = 4
     *     cooldownTime = 1.0
     *     xMaxRotation = 30.0
     *     yMaxHeadRotation = 30.0
     *     maxDistance = 15.0
     *     maxSneakRange = 15.0
     *     maxSprintRange = 4.0
     *     reachMultiplier = 2.0
     *     sneakSpeedMultiplier = 0.6
     *     sprintSpeedMultiplier = 1.33
     *     walkSpeedMultiplier = 0.8
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behOcelotattack(value: BehOcelotattack.() -> Unit) {
        behOcelotattackData = (behOcelotattackData ?: BehOcelotattack()).apply(value)
    }

    @SerializedName("minecraft:tameable")
    @Expose
    var tameableData: Tameable? = null
        @MonsteraBuildSetter set

    /**
     * Defines the rules for a mob to be tamed by the player.
     * ```
     * tameable {
     *     probability = 0.33
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun tameable(value: Tameable.() -> Unit) {
        tameableData = (tameableData ?: Tameable()).apply(value)
    }

    @SerializedName("minecraft:behavior.avoid_mob_type")
    @Expose
    var behAvoidMobTypeData: BehAvoidMobType? = null
        @MonsteraBuildSetter set

    /**
     * Allows the entity to run away from other entities that meet the criteria specified.
     * ```
     * behAvoidMobType {
     *     priority = 6
     *     probabilityPerStrength = 0.14
     *     removeTarget = true
     *     avoidMobSound = retreat
     *     maxDist = 16
     *     maxFlee = 20
     *     ignoreVisibility = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behAvoidMobType(value: BehAvoidMobType.() -> Unit) {
        behAvoidMobTypeData = (behAvoidMobTypeData ?: BehAvoidMobType()).apply(value)
    }

    @SerializedName("minecraft:behavior.move_towards_dwelling_restriction")
    @Expose
    var behMoveTowardsDwellingRestrictionData: BehMoveTowardsDwellingRestriction? = null
        @MonsteraBuildSetter set

    /**
     * Allows mobs with the dweller component to move toward their Village area that the mob should be restricted to.
     * ```
     * behMoveTowardsDwellingRestriction {
     *     priority = 7
     *     speedMultiplier = 1
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behMoveTowardsDwellingRestriction(value: BehMoveTowardsDwellingRestriction.() -> Unit) {
        behMoveTowardsDwellingRestrictionData =
            (behMoveTowardsDwellingRestrictionData ?: BehMoveTowardsDwellingRestriction()).apply(value)
    }

    @SerializedName("minecraft:color")
    @Expose
    var colorData: ComponentValue? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    var color: Number? = null
        set(value) {
            colorData = ComponentValue().also { it.value = value }
            field = value
        }

    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun color(value: ComponentValue.() -> Unit) {
        colorData = (colorData ?: ComponentValue()).apply(value)
    }

    @SerializedName("minecraft:sittable")
    @Expose
    var sittableData: Sittable? = null
        @MonsteraBuildSetter set

    /**
     * Defines the entity's 'sit' state.
     * ```
     * sittable {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun sittable(value: Sittable.() -> Unit) {
        sittableData = (sittableData ?: Sittable()).apply(value)
    }

    @SerializedName("minecraft:is_dyeable")
    @Expose
    var isDyeableData: IsDyeable? = null
        @MonsteraBuildSetter set

    /**
     * Allows dyes to be used on this entity to change its color.
     * ```
     * isDyeable {
     *     interactText = action.interact.dye
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun isDyeable(value: IsDyeable.() -> Unit) {
        isDyeableData = (isDyeableData ?: IsDyeable()).apply(value)
    }

    @SerializedName("minecraft:behavior.stay_while_sitting")
    @Expose
    var behStayWhileSittingData: BehStayWhileSitting? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to stay put while it is in a sitting state instead of doing something else.
     * ```
     * behStayWhileSitting {
     *     priority = 3
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behStayWhileSitting(value: BehStayWhileSitting.() -> Unit) {
        behStayWhileSittingData = (behStayWhileSittingData ?: BehStayWhileSitting()).apply(value)
    }

    @SerializedName("minecraft:behavior.ocelot_sit_on_block")
    @Expose
    var behOcelotSitOnBlockData: BehOcelotSitOnBlock? = null
        @MonsteraBuildSetter set

    /**
     * Allows to mob to be able to sit in place like the ocelot.
     * ```
     * behOcelotSitOnBlock {
     *     priority = 7
     *     speedMultiplier = 1.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behOcelotSitOnBlock(value: BehOcelotSitOnBlock.() -> Unit) {
        behOcelotSitOnBlockData = (behOcelotSitOnBlockData ?: BehOcelotSitOnBlock()).apply(value)
    }

    @SerializedName("minecraft:behavior.pet_sleep_with_owner")
    @Expose
    var behPetSleepWithOwnerData: BehPetSleepWithOwner? = null
        @MonsteraBuildSetter set

    /**
     * Allows the pet mob to move onto a bed with its owner while sleeping.
     * ```
     * behPetSleepWithOwner {
     *     priority = 2
     *     speedMultiplier = 1.2
     *     searchRadius = 10
     *     searchHeight = 10
     *     goalRadius = 1.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behPetSleepWithOwner(value: BehPetSleepWithOwner.() -> Unit) {
        behPetSleepWithOwnerData = (behPetSleepWithOwnerData ?: BehPetSleepWithOwner()).apply(value)
    }

    @SerializedName("minecraft:on_wake_with_owner")
    @Expose
    var onWakeWithOwnerData: OnWakeWithOwner? = null
        @MonsteraBuildSetter set

    /**
     * Adds a trigger to call when this pet's owner awakes after sleeping with the pet.
     * ```
     * onWakeWithOwner {
     *     event = minecraft:pet_slept_with_owner
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun onWakeWithOwner(value: OnWakeWithOwner.() -> Unit) {
        onWakeWithOwnerData = (onWakeWithOwnerData ?: OnWakeWithOwner()).apply(value)
    }

    @SerializedName("minecraft:behavior.drop_item_for")
    @Expose
    var behDropItemForData: BehDropItemFor? = null
        @MonsteraBuildSetter set

    /**
     * Allows the entity to move toward a target, and drop an item near the target. This goal requires a "minecraft:navigation" to execute.
     * ```
     * behDropItemFor {
     *     priority = 1
     *     secondsBeforePickup = 0.0
     *     cooldown = 0.25
     *     dropItemChance = 0.7
     *     offeringDistance = 5.0
     *     minimumTeleportDistance = 2.0
     *     maxHeadLookAtHeight = 10.0
     *     speedMultiplier = 1.0
     *     searchRange = 5
     *     searchHeight = 2
     *     searchCount = 0
     *     goalRadius = 1.0
     *     lootTable = loot_tables/entities/cat_gift.json
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behDropItemFor(value: BehDropItemFor.() -> Unit) {
        behDropItemForData = (behDropItemForData ?: BehDropItemFor()).apply(value)
    }

    @SerializedName("minecraft:navigation.climb")
    @Expose
    var navigationClimbData: NavigationClimb? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to generate paths that include vertical walls like the vanilla Spiders do.
     * ```
     * navigationClimb {
     *     canPathOverWater = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun navigationClimb(value: NavigationClimb.() -> Unit) {
        navigationClimbData = (navigationClimbData ?: NavigationClimb()).apply(value)
    }

    @SerializedName("minecraft:addrider")
    @Expose
    var addriderData: Addrider? = null
        @MonsteraBuildSetter set

    /**
     * Adds a rider to the entity. Requires minecraft:rideable.
     * ```
     * addrider {
     *     entityType = minecraft:skeleton
     *     spawnEvent = minecraft:spawn_for_raid
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun addrider(value: Addrider.() -> Unit) {
        addriderData = (addriderData ?: Addrider()).apply(value)
    }

    @SerializedName("minecraft:rail_movement")
    @Expose
    var railMovementData: RailMovement? = null
        @MonsteraBuildSetter set

    /**
     * Defines the entity's movement on the rails. An entity with this component is only allowed to move on the rail.
     * ```
     * railMovement {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun railMovement(value: RailMovement.() -> Unit) {
        railMovementData = (railMovementData ?: RailMovement()).apply(value)
    }

    @SerializedName("minecraft:spawn_entity")
    @Expose
    var spawnEntityData: SpawnEntity? = null
        @MonsteraBuildSetter set

    /**
     * Adds a timer after which this entity will spawn another entity or item (similar to vanilla's chicken's egg-laying behavior).
     * ```
     * spawnEntity {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun spawnEntity(value: SpawnEntity.() -> Unit) {
        spawnEntityData = (spawnEntityData ?: SpawnEntity()).apply(value)
    }

    @SerializedName("minecraft:rail_sensor")
    @Expose
    var railSensorData: RailSensor? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * railSensor {
     *     checkBlockTypes = true
     *     ejectOnActivate = false
     *     ejectOnDeactivate = false
     *     tickCommandBlockOnActivate = true
     *     tickCommandBlockOnDeactivate = false
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun railSensor(value: RailSensor.() -> Unit) {
        railSensorData = (railSensorData ?: RailSensor()).apply(value)
    }

    @SerializedName("minecraft:behavior.swell")
    @Expose
    var behSwellData: BehSwell? = null
        @MonsteraBuildSetter set

    /**
     * Allows the creeper to swell up when a player is nearby. It can only be used by Creepers.
     * ```
     * behSwell {
     *     startDistance = 2.5
     *     stopDistance = 6.0
     *     priority = 2
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behSwell(value: BehSwell.() -> Unit) {
        behSwellData = (behSwellData ?: BehSwell()).apply(value)
    }

    @SerializedName("minecraft:behavior.melee_attack")
    @Expose
    var behMeleeAttackData: BehMeleeAttack? = null
        @MonsteraBuildSetter set

    /**
     * Allows an entity to deal damage through a melee attack.
     * ```
     * behMeleeAttack {
     *     priority = 4
     *     speedMultiplier = 1.25
     *     trackTarget = false
     *     reachMultiplier = 0.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behMeleeAttack(value: BehMeleeAttack.() -> Unit) {
        behMeleeAttackData = (behMeleeAttackData ?: BehMeleeAttack()).apply(value)
    }

    @SerializedName("minecraft:on_target_escape")
    @Expose
    var onTargetEscapeData: OnTargetEscape? = null
        @MonsteraBuildSetter set

    /**
     * Adds a trigger to call when this entity loses the target it currently has.
     * ```
     * onTargetEscape {
     *     event = minecraft:stop_exploding
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun onTargetEscape(value: OnTargetEscape.() -> Unit) {
        onTargetEscapeData = (onTargetEscapeData ?: OnTargetEscape()).apply(value)
    }

    @SerializedName("minecraft:explode")
    @Expose
    var explodeData: Explode? = null
        @MonsteraBuildSetter set

    /**
     * Defines how the entity explodes.
     * ```
     * explode {
     *     fuseLength = 1.5
     *     fuseLit = true
     *     power = 3
     *     causesFire = false
     *     destroyAffectedByGriefing = true
     *     fireAffectedByGriefing = true
     *     maxResistance = 4.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun explode(value: Explode.() -> Unit) {
        explodeData = (explodeData ?: Explode()).apply(value)
    }

    @SerializedName("minecraft:is_charged")
    @Expose
    var isChargedData: IsCharged? = null
        @MonsteraBuildSetter set

    /**
     * Sets that this entity is charged.
     * ```
     * isCharged {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun isCharged(value: IsCharged.() -> Unit) {
        isChargedData = (isChargedData ?: IsCharged()).apply(value)
    }

    @SerializedName("minecraft:behavior.swim_with_entity")
    @Expose
    var behSwimWithEntityData: BehSwimWithEntity? = null
        @MonsteraBuildSetter set

    /**
     * Allows the entity follow another entity. Both entities must be swimming [ie, in water].
     * ```
     * behSwimWithEntity {
     *     priority = 4
     *     successRate = 0.1
     *     chanceToStop = 0.0333
     *     stateCheckInterval = 0.5
     *     catchUpThreshold = 12.0
     *     matchDirectionThreshold = 2.0
     *     catchUpMultiplier = 2.5
     *     speedMultiplier = 1.5
     *     searchRange = 20.0
     *     stopDistance = 5.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behSwimWithEntity(value: BehSwimWithEntity.() -> Unit) {
        behSwimWithEntityData = (behSwimWithEntityData ?: BehSwimWithEntity()).apply(value)
    }

    @SerializedName("minecraft:behavior.random_breach")
    @Expose
    var behRandomBreachData: BehRandomBreach? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to randomly break surface of the water.
     * ```
     * behRandomBreach {
     *     priority = 6
     *     interval = 50
     *     xzDist = 6
     *     cooldownTime = 2.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behRandomBreach(value: BehRandomBreach.() -> Unit) {
        behRandomBreachData = (behRandomBreachData ?: BehRandomBreach()).apply(value)
    }

    @SerializedName("minecraft:behavior.find_underwater_treasure")
    @Expose
    var behFindUnderwaterTreasureData: BehFindUnderwaterTreasure? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to move towards the nearest underwater ruin or shipwreck.
     * ```
     * behFindUnderwaterTreasure {
     *     priority = 2
     *     speedMultiplier = 2.0
     *     searchRange = 30
     *     stopDistance = 50
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behFindUnderwaterTreasure(value: BehFindUnderwaterTreasure.() -> Unit) {
        behFindUnderwaterTreasureData = (behFindUnderwaterTreasureData ?: BehFindUnderwaterTreasure()).apply(value)
    }

    @SerializedName("minecraft:flocking")
    @Expose
    var flockingData: Flocking? = null
        @MonsteraBuildSetter set

    /**
     * Allows entities to flock in groups in water or not.
     * ```
     * flocking {
     *     inWater = false
     *     matchVariants = false
     *     useCenterOfMass = false
     *     lowFlockLimit = 4
     *     highFlockLimit = 8
     *     goalWeight = 2.0
     *     lonerChance = 0.1
     *     influenceRadius = 6.0
     *     breachInfluence = 0.0
     *     separationWeight = 1.75
     *     separationThreshold = 3.0
     *     cohesionWeight = 1.85
     *     cohesionThreshold = 6.5
     *     innnerCohesionThreshold = 3.5
     *     minHeight = 4.0
     *     maxHeight = 4.0
     *     blockDistance = 1.0
     *     blockWeight = 0.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun flocking(value: Flocking.() -> Unit) {
        flockingData = (flockingData ?: Flocking()).apply(value)
    }

    @SerializedName("minecraft:bribeable")
    @Expose
    var bribeableData: Bribeable? = null
        @MonsteraBuildSetter set

    /**
     * Defines the way an entity can get into the 'bribed' state.
     * ```
     * bribeable {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun bribeable(value: Bribeable.() -> Unit) {
        bribeableData = (bribeableData ?: Bribeable()).apply(value)
    }

    @SerializedName("minecraft:horse.jump_strength")
    @Expose
    var horseJumpStrengthData: ComponentValue? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    var horseJumpStrength: Number? = null
        set(value) {
            horseJumpStrengthData = ComponentValue().also { it.value = value }
            field = value
        }

    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun horseJumpStrength(value: ComponentValue.() -> Unit) {
        horseJumpStrengthData = (horseJumpStrengthData ?: ComponentValue()).apply(value)
    }

    @SerializedName("minecraft:scale_by_age")
    @Expose
    var scaleByAgeData: ScaleByAge? = null
        @MonsteraBuildSetter set

    /**
     * Defines the entity's size interpolation based on the entity's age.
     * ```
     * scaleByAge {
     *     startScale = 0.5
     *     endScale = 1.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun scaleByAge(value: ScaleByAge.() -> Unit) {
        scaleByAgeData = (scaleByAgeData ?: ScaleByAge()).apply(value)
    }

    @SerializedName("minecraft:behavior.run_around_like_crazy")
    @Expose
    var behRunAroundLikeCrazyData: BehRunAroundLikeCrazy? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to run around aimlessly.
     * ```
     * behRunAroundLikeCrazy {
     *     priority = 1
     *     speedMultiplier = 1.2
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behRunAroundLikeCrazy(value: BehRunAroundLikeCrazy.() -> Unit) {
        behRunAroundLikeCrazyData = (behRunAroundLikeCrazyData ?: BehRunAroundLikeCrazy()).apply(value)
    }

    @SerializedName("minecraft:tamemount")
    @Expose
    var tamemountData: Tamemount? = null
        @MonsteraBuildSetter set

    /**
     * Allows the Entity to be tamed by mounting it.
     * ```
     * tamemount {
     *     minTemper = 0
     *     maxTemper = 100
     *     feedText = action.interact.feed
     *     rideText = action.interact.mount
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun tamemount(value: Tamemount.() -> Unit) {
        tamemountData = (tamemountData ?: Tamemount()).apply(value)
    }

    @SerializedName("minecraft:is_chested")
    @Expose
    var isChestedData: IsChested? = null
        @MonsteraBuildSetter set

    /**
     * Sets that this entity is currently carrying a chest.
     * ```
     * isChested {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun isChested(value: IsChested.() -> Unit) {
        isChestedData = (isChestedData ?: IsChested()).apply(value)
    }

    @SerializedName("minecraft:can_power_jump")
    @Expose
    var canPowerJumpData: CanPowerJump? = null
        @MonsteraBuildSetter set

    /**
     * Allows the entity to power jump like the horse does in vanilla.
     * ```
     * canPowerJump {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun canPowerJump(value: CanPowerJump.() -> Unit) {
        canPowerJumpData = (canPowerJumpData ?: CanPowerJump()).apply(value)
    }

    @SerializedName("minecraft:equip_item")
    @Expose
    var equipItemData: EquipItem? = null
        @MonsteraBuildSetter set

    /**
     * The entity puts on the desired equipment.
     * ```
     * equipItem {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun equipItem(value: EquipItem.() -> Unit) {
        equipItemData = (equipItemData ?: EquipItem()).apply(value)
    }

    @SerializedName("minecraft:movement.generic")
    @Expose
    var movementGenericData: MovementGeneric? = null
        @MonsteraBuildSetter set

    /**
     * This move control allows a mob to fly, swim, climb, etc.
     * ```
     * movementGeneric {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun movementGeneric(value: MovementGeneric.() -> Unit) {
        movementGenericData = (movementGenericData ?: MovementGeneric()).apply(value)
    }

    @SerializedName("minecraft:burns_in_daylight")
    @Expose
    var burnsInDaylightData: BurnsInDaylight? = null
        @MonsteraBuildSetter set

    /**
     * Specifies if/how a mob burns in daylight.
     * ```
     * burnsInDaylight {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun burnsInDaylight(value: BurnsInDaylight.() -> Unit) {
        burnsInDaylightData = (burnsInDaylightData ?: BurnsInDaylight()).apply(value)
    }

    @SerializedName("minecraft:shareables")
    @Expose
    var shareablesData: Shareables? = null
        @MonsteraBuildSetter set

    /**
     * Defines a list of items the mob wants to share or pick up. Each item must have the following parameters:
     * ```
     * shareables {
     *     singularPickup = true
     *     allItems = true
     *     allItemsMaxAmount = 1
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun shareables(value: Shareables.() -> Unit) {
        shareablesData = (shareablesData ?: Shareables()).apply(value)
    }

    @SerializedName("minecraft:behavior.flee_sun")
    @Expose
    var behFleeSunData: BehFleeSun? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to run away from direct sunlight and seek shade.
     * ```
     * behFleeSun {
     *     priority = 2
     *     speedMultiplier = 1
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behFleeSun(value: BehFleeSun.() -> Unit) {
        behFleeSunData = (behFleeSunData ?: BehFleeSun()).apply(value)
    }

    @SerializedName("minecraft:behavior.equip_item")
    @Expose
    var behEquipItemData: BehEquipItem? = null
        @MonsteraBuildSetter set

    /**
     * The entity puts on the desired equipment.
     * ```
     * behEquipItem {
     *     priority = 3
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behEquipItem(value: BehEquipItem.() -> Unit) {
        behEquipItemData = (behEquipItemData ?: BehEquipItem()).apply(value)
    }

    @SerializedName("minecraft:behavior.stomp_turtle_egg")
    @Expose
    var behStompTurtleEggData: BehStompTurtleEgg? = null
        @MonsteraBuildSetter set

    /**
     * Allows this mob to stomp turtle eggs
     * ```
     * behStompTurtleEgg {
     *     priority = 4
     *     speedMultiplier = 1
     *     searchRange = 10
     *     searchHeight = 2
     *     goalRadius = 1.14
     *     interval = 20
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behStompTurtleEgg(value: BehStompTurtleEgg.() -> Unit) {
        behStompTurtleEggData = (behStompTurtleEggData ?: BehStompTurtleEgg()).apply(value)
    }

    @SerializedName("minecraft:equipment")
    @Expose
    var equipmentData: Equipment? = null
        @MonsteraBuildSetter set

    /**
     * Sets the Equipment table to use for this Entity.
     * ```
     * equipment {
     *     table = loot_tables/entities/drowned_ranged_equipment.json
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun equipment(value: Equipment.() -> Unit) {
        equipmentData = (equipmentData ?: Equipment()).apply(value)
    }

    @SerializedName("minecraft:annotation.break_door")
    @Expose
    var annotationBreakDoorData: AnnotationBreakDoor? = null
        @MonsteraBuildSetter set

    /**
     * Allows the actor to break doors assuming that that flags set up for the component to use in navigation
     * ```
     * annotationBreakDoor {
     *     breakTime = 30
     *     minDifficulty = normal
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun annotationBreakDoor(value: AnnotationBreakDoor.() -> Unit) {
        annotationBreakDoorData = (annotationBreakDoorData ?: AnnotationBreakDoor()).apply(value)
    }

    @SerializedName("minecraft:movement.sway")
    @Expose
    var movementSwayData: MovementSway? = null
        @MonsteraBuildSetter set

    /**
     * This move control causes the mob to sway side to side giving the impression it is swimming.
     * ```
     * movementSway {
     *     swayAmplitude = 0.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun movementSway(value: MovementSway.() -> Unit) {
        movementSwayData = (movementSwayData ?: MovementSway()).apply(value)
    }

    @SerializedName("minecraft:behavior.guardian_attack")
    @Expose
    var behGuardianAttackData: BehGuardianAttack? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to use a laser beam attack. Can only be used by Guardians and Elder Guardians.
     * ```
     * behGuardianAttack {
     *     priority = 4
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behGuardianAttack(value: BehGuardianAttack.() -> Unit) {
        behGuardianAttackData = (behGuardianAttackData ?: BehGuardianAttack()).apply(value)
    }

    @SerializedName("minecraft:teleport")
    @Expose
    var teleportData: Teleport? = null
        @MonsteraBuildSetter set

    /**
     * Defines an entity's teleporting behavior.
     * ```
     * teleport {
     *     randomTeleports = true
     *     maxRandomTeleportTime = 30
     *     targetDistance = 16
     *     targetTeleportChance = 0.05
     *     lightTeleportChance = 0.05
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun teleport(value: Teleport.() -> Unit) {
        teleportData = (teleportData ?: Teleport()).apply(value)
    }

    @SerializedName("minecraft:lookat")
    @Expose
    var lookatData: Lookat? = null
        @MonsteraBuildSetter set

    /**
     * Defines the behavior when another entity looks at this entity.
     * ```
     * lookat {
     *     searchRadius = 64.0
     *     setTarget = true
     *     lookCooldown = 5.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun lookat(value: Lookat.() -> Unit) {
        lookatData = (lookatData ?: Lookat()).apply(value)
    }

    @SerializedName("minecraft:behavior.enderman_leave_block")
    @Expose
    var behEndermanLeaveBlockData: BehEndermanLeaveBlock? = null
        @MonsteraBuildSetter set

    /**
     * Allows the enderman to drop a block they are carrying. Can only be used by Endermen.
     * ```
     * behEndermanLeaveBlock {
     *     priority = 10
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behEndermanLeaveBlock(value: BehEndermanLeaveBlock.() -> Unit) {
        behEndermanLeaveBlockData = (behEndermanLeaveBlockData ?: BehEndermanLeaveBlock()).apply(value)
    }

    @SerializedName("minecraft:behavior.enderman_take_block")
    @Expose
    var behEndermanTakeBlockData: BehEndermanTakeBlock? = null
        @MonsteraBuildSetter set

    /**
     * Allows the enderman to take a block and carry it around. Can only be used by Endermen.
     * ```
     * behEndermanTakeBlock {
     *     priority = 11
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behEndermanTakeBlock(value: BehEndermanTakeBlock.() -> Unit) {
        behEndermanTakeBlockData = (behEndermanTakeBlockData ?: BehEndermanTakeBlock()).apply(value)
    }

    @SerializedName("minecraft:block_climber")
    @Expose
    var blockClimberData: BlockClimber? = null
        @MonsteraBuildSetter set

    /**
     * Allows the player to detect and maneuver on the scaffolding block.
     * ```
     * blockClimber {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun blockClimber(value: BlockClimber.() -> Unit) {
        blockClimberData = (blockClimberData ?: BlockClimber()).apply(value)
    }

    @SerializedName("minecraft:boss")
    @Expose
    var bossData: Boss? = null
        @MonsteraBuildSetter set

    /**
     * The current state of the boss for updating the boss HUD.
     * ```
     * boss {
     *     shouldDarkenSky = false
     *     hudRange = 125
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun boss(value: Boss.() -> Unit) {
        bossData = (bossData ?: Boss()).apply(value)
    }

    @SerializedName("minecraft:behavior.dragonlanding")
    @Expose
    var behDragonlandingData: BehDragonlanding? = null
        @MonsteraBuildSetter set

    /**
     * Allows the Dragon to stop flying and transition into perching mode. Can only be used by the Ender Dragon.
     * ```
     * behDragonlanding {
     *     priority = 0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behDragonlanding(value: BehDragonlanding.() -> Unit) {
        behDragonlandingData = (behDragonlandingData ?: BehDragonlanding()).apply(value)
    }

    @SerializedName("minecraft:behavior.dragonflaming")
    @Expose
    var behDragonflamingData: BehDragonflaming? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to use a flame-breath attack. Can only be used by the Ender Dragon.
     * ```
     * behDragonflaming {
     *     priority = 1
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behDragonflaming(value: BehDragonflaming.() -> Unit) {
        behDragonflamingData = (behDragonflamingData ?: BehDragonflaming()).apply(value)
    }

    @SerializedName("minecraft:behavior.dragonscanning")
    @Expose
    var behDragonscanningData: BehDragonscanning? = null
        @MonsteraBuildSetter set

    /**
     * Allows the dragon to look around for a player to attack while in perch mode. Can only be used by the Ender Dragon.
     * ```
     * behDragonscanning {
     *     priority = 2
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behDragonscanning(value: BehDragonscanning.() -> Unit) {
        behDragonscanningData = (behDragonscanningData ?: BehDragonscanning()).apply(value)
    }

    @SerializedName("minecraft:behavior.dragontakeoff")
    @Expose
    var behDragontakeoffData: BehDragontakeoff? = null
        @MonsteraBuildSetter set

    /**
     * Allows the dragon to leave perch mode and go back to flying around. Can only be used by the Ender Dragon.
     * ```
     * behDragontakeoff {
     *     priority = 0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behDragontakeoff(value: BehDragontakeoff.() -> Unit) {
        behDragontakeoffData = (behDragontakeoffData ?: BehDragontakeoff()).apply(value)
    }

    @SerializedName("minecraft:behavior.dragonchargeplayer")
    @Expose
    var behDragonchargeplayerData: BehDragonchargeplayer? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to attack a player by charging at them. The player is chosen by the "minecraft:behavior.dragonscanning". Can only be used by the Ender Dragon.
     * ```
     * behDragonchargeplayer {
     *     priority = 1
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behDragonchargeplayer(value: BehDragonchargeplayer.() -> Unit) {
        behDragonchargeplayerData = (behDragonchargeplayerData ?: BehDragonchargeplayer()).apply(value)
    }

    @SerializedName("minecraft:behavior.dragonstrafeplayer")
    @Expose
    var behDragonstrafeplayerData: BehDragonstrafeplayer? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to fly around looking for a player to shoot fireballs at. Can only be used by the Ender Dragon.
     * ```
     * behDragonstrafeplayer {
     *     priority = 2
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behDragonstrafeplayer(value: BehDragonstrafeplayer.() -> Unit) {
        behDragonstrafeplayerData = (behDragonstrafeplayerData ?: BehDragonstrafeplayer()).apply(value)
    }

    @SerializedName("minecraft:behavior.dragonholdingpattern")
    @Expose
    var behDragonholdingpatternData: BehDragonholdingpattern? = null
        @MonsteraBuildSetter set

    /**
     * Allows the Dragon to fly around in a circle around the center podium. Can only be used by the Ender Dragon.
     * ```
     * behDragonholdingpattern {
     *     priority = 3
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behDragonholdingpattern(value: BehDragonholdingpattern.() -> Unit) {
        behDragonholdingpatternData = (behDragonholdingpatternData ?: BehDragonholdingpattern()).apply(value)
    }

    @SerializedName("minecraft:behavior.dragondeath")
    @Expose
    var behDragondeathData: BehDragondeath? = null
        @MonsteraBuildSetter set

    /**
     * Allows the dragon to go out with glory. This controls the Ender Dragon's death animation and can't be used by other mobs.
     * ```
     * behDragondeath {
     *     priority = 0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behDragondeath(value: BehDragondeath.() -> Unit) {
        behDragondeathData = (behDragondeathData ?: BehDragondeath()).apply(value)
    }

    @SerializedName("minecraft:behavior.summon_entity")
    @Expose
    var behSummonEntityData: BehSummonEntity? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to attack the player by summoning other entities.
     * ```
     * behSummonEntity {
     *     priority = 2
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behSummonEntity(value: BehSummonEntity.() -> Unit) {
        behSummonEntityData = (behSummonEntityData ?: BehSummonEntity()).apply(value)
    }

    @SerializedName("minecraft:behavior.send_event")
    @Expose
    var behSendEventData: BehSendEvent? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to send an event to another mob.
     * ```
     * behSendEvent {
     *     priority = 3
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behSendEvent(value: BehSendEvent.() -> Unit) {
        behSendEventData = (behSendEventData ?: BehSendEvent()).apply(value)
    }

    @SerializedName("minecraft:behavior.look_at_entity")
    @Expose
    var behLookAtEntityData: BehLookAtEntity? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to look at nearby entities.
     * ```
     * behLookAtEntity {
     *     priority = 10
     *     lookDistance = 8.0
     *     angleOfViewHorizontal = 45
     *     probability = 0.02
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behLookAtEntity(value: BehLookAtEntity.() -> Unit) {
        behLookAtEntityData = (behLookAtEntityData ?: BehLookAtEntity()).apply(value)
    }

    @SerializedName("minecraft:can_join_raid")
    @Expose
    var canJoinRaidData: CanJoinRaid? = null
        @MonsteraBuildSetter set

    /**
     * Determines that this entity can join an existing raid.
     * ```
     * canJoinRaid {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun canJoinRaid(value: CanJoinRaid.() -> Unit) {
        canJoinRaidData = (canJoinRaidData ?: CanJoinRaid()).apply(value)
    }

    @SerializedName("minecraft:behavior.celebrate")
    @Expose
    var behCelebrateData: BehCelebrate? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to celebrate surviving a raid by making celebration sounds and jumping.
     * ```
     * behCelebrate {
     *     priority = 5
     *     celebrationSound = celebrate
     *     duration = 30.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behCelebrate(value: BehCelebrate.() -> Unit) {
        behCelebrateData = (behCelebrateData ?: BehCelebrate()).apply(value)
    }

    @SerializedName("minecraft:behavior.move_to_village")
    @Expose
    var behMoveToVillageData: BehMoveToVillage? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to move into a random location within a village.
     * ```
     * behMoveToVillage {
     *     priority = 6
     *     speedMultiplier = 0.7
     *     goalRadius = 2.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behMoveToVillage(value: BehMoveToVillage.() -> Unit) {
        behMoveToVillageData = (behMoveToVillageData ?: BehMoveToVillage()).apply(value)
    }

    @SerializedName("minecraft:behavior.swim_wander")
    @Expose
    var behSwimWanderData: BehSwimWander? = null
        @MonsteraBuildSetter set

    /**
     * Allows the entity to wander around while swimming, when not path-finding.
     * ```
     * behSwimWander {
     *     priority = 4
     *     interval = 0.1
     *     lookAhead = 2.0
     *     speedMultiplier = 1.0
     *     wanderTime = 5.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behSwimWander(value: BehSwimWander.() -> Unit) {
        behSwimWanderData = (behSwimWanderData ?: BehSwimWander()).apply(value)
    }

    @SerializedName("minecraft:behavior.stalk_and_pounce_on_target")
    @Expose
    var behStalkAndPounceOnTargetData: BehStalkAndPounceOnTarget? = null
        @MonsteraBuildSetter set

    /**
     * Allows a mob to stalk a target, then once within range pounce onto a target, on success the target will be attacked dealing damage defined by the attack component. On failure, the mob will risk getting stuck
     * ```
     * behStalkAndPounceOnTarget {
     *     priority = 7
     *     stalkSpeed = 1.2
     *     maxStalkDist = 12.0
     *     leapHeight = 0.9
     *     leapDist = 0.8
     *     pounceMaxDist = 5.0
     *     interestTime = 2.0
     *     stuckTime = 2.0
     *     strikeDist = 2.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behStalkAndPounceOnTarget(value: BehStalkAndPounceOnTarget.() -> Unit) {
        behStalkAndPounceOnTargetData = (behStalkAndPounceOnTargetData ?: BehStalkAndPounceOnTarget()).apply(value)
    }

    @SerializedName("minecraft:behavior.eat_carried_item")
    @Expose
    var behEatCarriedItemData: BehEatCarriedItem? = null
        @MonsteraBuildSetter set

    /**
     * If the mob is carrying a food item, the mob will eat it and the effects will be applied to the mob.
     * ```
     * behEatCarriedItem {
     *     priority = 12
     *     delayBeforeEating = 28
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behEatCarriedItem(value: BehEatCarriedItem.() -> Unit) {
        behEatCarriedItemData = (behEatCarriedItemData ?: BehEatCarriedItem()).apply(value)
    }

    @SerializedName("minecraft:behavior.raid_garden")
    @Expose
    var behRaidGardenData: BehRaidGarden? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to eat/raid crops out of farms until they are full.
     * ```
     * behRaidGarden {
     *     priority = 12
     *     speedMultiplier = 1.2
     *     searchRange = 12
     *     searchHeight = 2
     *     goalRadius = 0.8
     *     maxToEat = 0
     *     initialEatDelay = 2
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behRaidGarden(value: BehRaidGarden.() -> Unit) {
        behRaidGardenData = (behRaidGardenData ?: BehRaidGarden()).apply(value)
    }

    @SerializedName("minecraft:scheduler")
    @Expose
    var schedulerData: Scheduler? = null
        @MonsteraBuildSetter set

    /**
     * Fires off scheduled mob events at time of day events.
     * ```
     * scheduler {
     *     minDelaySecs = 0
     *     maxDelaySecs = 0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun scheduler(value: Scheduler.() -> Unit) {
        schedulerData = (schedulerData ?: Scheduler()).apply(value)
    }

    @SerializedName("minecraft:trust")
    @Expose
    var trustData: Trust? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * trust {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun trust(value: Trust.() -> Unit) {
        trustData = (trustData ?: Trust()).apply(value)
    }

    @SerializedName("minecraft:behavior.defend_trusted_target")
    @Expose
    var behDefendTrustedTargetData: BehDefendTrustedTarget? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to target another mob that hurts an entity it trusts.
     * ```
     * behDefendTrustedTarget {
     *     priority = 0
     *     withinRadius = 25
     *     mustSee = false
     *     aggroSound = mad
     *     soundChance = 0.05
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behDefendTrustedTarget(value: BehDefendTrustedTarget.() -> Unit) {
        behDefendTrustedTargetData = (behDefendTrustedTargetData ?: BehDefendTrustedTarget()).apply(value)
    }

    @SerializedName("minecraft:behavior.nearest_prioritized_attackable_target")
    @Expose
    var behNearestPrioritizedAttackableTargetData: BehNearestPrioritizedAttackableTarget? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to check for and pursue the nearest valid target.
     * ```
     * behNearestPrioritizedAttackableTarget {
     *     priority = 6
     *     attackInterval = 2
     *     reselectTargets = true
     *     targetSearchHeight = 5
     *     withinRadius = 12.0
     *     persistTime = 2.0
     *     mustSee = true
     *     mustReach = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behNearestPrioritizedAttackableTarget(value: BehNearestPrioritizedAttackableTarget.() -> Unit) {
        behNearestPrioritizedAttackableTargetData =
            (behNearestPrioritizedAttackableTargetData ?: BehNearestPrioritizedAttackableTarget()).apply(value)
    }

    @SerializedName("minecraft:behavior.find_cover")
    @Expose
    var behFindCoverData: BehFindCover? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to seek shade.
     * ```
     * behFindCover {
     *     priority = 0
     *     speedMultiplier = 1
     *     cooldownTime = 0.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behFindCover(value: BehFindCover.() -> Unit) {
        behFindCoverData = (behFindCoverData ?: BehFindCover()).apply(value)
    }

    @SerializedName("minecraft:behavior.nap")
    @Expose
    var behNapData: BehNap? = null
        @MonsteraBuildSetter set

    /**
     * Allows mobs to occassionally stop and take a nap under certain conditions.
     * ```
     * behNap {
     *     priority = 8
     *     cooldownMin = 2.0
     *     cooldownMax = 7.0
     *     mobDetectDist = 12.0
     *     mobDetectHeight = 6.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behNap(value: BehNap.() -> Unit) {
        behNapData = (behNapData ?: BehNap()).apply(value)
    }

    @SerializedName("minecraft:behavior.stroll_towards_village")
    @Expose
    var behStrollTowardsVillageData: BehStrollTowardsVillage? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to move into a random location within a village within the search range.
     * ```
     * behStrollTowardsVillage {
     *     priority = 11
     *     speedMultiplier = 1.0
     *     goalRadius = 3.0
     *     cooldownTime = 10.0
     *     searchRange = 32
     *     startChance = 0.005
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behStrollTowardsVillage(value: BehStrollTowardsVillage.() -> Unit) {
        behStrollTowardsVillageData = (behStrollTowardsVillageData ?: BehStrollTowardsVillage()).apply(value)
    }

    @SerializedName("minecraft:behavior.move_to_land")
    @Expose
    var behMoveToLandData: BehMoveToLand? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to move back onto land when in water.
     * ```
     * behMoveToLand {
     *     priority = 6
     *     searchRange = 30
     *     searchHeight = 8
     *     searchCount = 80
     *     goalRadius = 2
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behMoveToLand(value: BehMoveToLand.() -> Unit) {
        behMoveToLandData = (behMoveToLandData ?: BehMoveToLand()).apply(value)
    }

    @SerializedName("minecraft:behavior.eat_mob")
    @Expose
    var behEatMobData: BehEatMob? = null
        @MonsteraBuildSetter set

    /**
     * Allows the entity to eat a specified Mob.
     * ```
     * behEatMob {
     *     priority = 7
     *     runSpeed = 2.0
     *     eatAnimationTime = 0.30
     *     pullInForce = 0.75
     *     reachMobDistance = 1.75
     *     eatMobSound = tongue
     *     lootTable = loot_tables/entities/frog.json
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behEatMob(value: BehEatMob.() -> Unit) {
        behEatMobData = (behEatMobData ?: BehEatMob()).apply(value)
    }

    @SerializedName("minecraft:behavior.croak")
    @Expose
    var behCroakData: BehCroak? = null
        @MonsteraBuildSetter set

    /**
     * Allows the entity to croak at a random time interval with configurable conditions.
     * ```
     * behCroak {
     *     priority = 9
     *     duration = 4.5
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behCroak(value: BehCroak.() -> Unit) {
        behCroakData = (behCroakData ?: BehCroak()).apply(value)
    }

    @SerializedName("minecraft:behavior.jump_to_block")
    @Expose
    var behJumpToBlockData: BehJumpToBlock? = null
        @MonsteraBuildSetter set

    /**
     * Allows an entity to jump to another random block.
     * ```
     * behJumpToBlock {
     *     priority = 10
     *     searchWidth = 8
     *     searchHeight = 4
     *     minimumPathLength = 2
     *     minimumDistance = 1
     *     scaleFactor = 0.6
     *     maxVelocity = 1
     *     preferredBlocksChance = 0.5
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behJumpToBlock(value: BehJumpToBlock.() -> Unit) {
        behJumpToBlockData = (behJumpToBlockData ?: BehJumpToBlock()).apply(value)
    }

    @SerializedName("minecraft:behavior.lay_egg")
    @Expose
    var behLayEggData: BehLayEgg? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to lay an egg block on certain types of blocks if the mob is pregnant.
     * ```
     * behLayEgg {
     *     priority = 2
     *     speedMultiplier = 1.0
     *     searchRange = 10
     *     searchHeight = 3
     *     goalRadius = 1.7
     *     allowLayingFromBelow = true
     *     useDefaultAnimation = false
     *     laySeconds = 2
     *     eggType = minecraft:frog_spawn
     *     layEggSound = lay_spawn
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behLayEgg(value: BehLayEgg.() -> Unit) {
        behLayEggData = (behLayEggData ?: BehLayEgg()).apply(value)
    }

    @SerializedName("minecraft:behavior.squid_move_away_from_ground")
    @Expose
    var behSquidMoveAwayFromGroundData: BehSquidMoveAwayFromGround? = null
        @MonsteraBuildSetter set

    /**
     * Allows the squid to move away from ground blocks and back to water. Can only be used by the Squid.
     * ```
     * behSquidMoveAwayFromGround {
     *     priority = 1
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behSquidMoveAwayFromGround(value: BehSquidMoveAwayFromGround.() -> Unit) {
        behSquidMoveAwayFromGroundData = (behSquidMoveAwayFromGroundData ?: BehSquidMoveAwayFromGround()).apply(value)
    }

    @SerializedName("minecraft:behavior.squid_flee")
    @Expose
    var behSquidFleeData: BehSquidFlee? = null
        @MonsteraBuildSetter set

    /**
     * Allows the squid to swim away. Can only be used by the Squid.
     * ```
     * behSquidFlee {
     *     priority = 2
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behSquidFlee(value: BehSquidFlee.() -> Unit) {
        behSquidFleeData = (behSquidFleeData ?: BehSquidFlee()).apply(value)
    }

    @SerializedName("minecraft:behavior.squid_idle")
    @Expose
    var behSquidIdleData: BehSquidIdle? = null
        @MonsteraBuildSetter set

    /**
     * Allows the squid to swim in place idly. Can only be used by the Squid.
     * ```
     * behSquidIdle {
     *     priority = 2
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behSquidIdle(value: BehSquidIdle.() -> Unit) {
        behSquidIdleData = (behSquidIdleData ?: BehSquidIdle()).apply(value)
    }

    @SerializedName("minecraft:behavior.squid_dive")
    @Expose
    var behSquidDiveData: BehSquidDive? = null
        @MonsteraBuildSetter set

    /**
     * Allows the squid to dive down in water. Can only be used by the Squid.
     * ```
     * behSquidDive {
     *     priority = 2
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behSquidDive(value: BehSquidDive.() -> Unit) {
        behSquidDiveData = (behSquidDiveData ?: BehSquidDive()).apply(value)
    }

    @SerializedName("minecraft:behavior.squid_out_of_water")
    @Expose
    var behSquidOutOfWaterData: BehSquidOutOfWater? = null
        @MonsteraBuildSetter set

    /**
     * Allows the squid to stick to the ground when outside water. Can only be used by the Squid.
     * ```
     * behSquidOutOfWater {
     *     priority = 2
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behSquidOutOfWater(value: BehSquidOutOfWater.() -> Unit) {
        behSquidOutOfWaterData = (behSquidOutOfWaterData ?: BehSquidOutOfWater()).apply(value)
    }

    @SerializedName("minecraft:genetics")
    @Expose
    var geneticsData: Genetics? = null
        @MonsteraBuildSetter set

    /**
     * Defines the way a mob's genes and alleles are passed on to its offspring, and how those traits manifest in the child. Compatible parent genes are crossed together, the alleles are handed down from the parents to the child, and any matching genetic variants fire off JSON events to modify the child and express the traits.
     * ```
     * genetics {
     *     mutationRate = 0.02
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun genetics(value: Genetics.() -> Unit) {
        geneticsData = (geneticsData ?: Genetics()).apply(value)
    }

    @SerializedName("minecraft:behavior.ram_attack")
    @Expose
    var behRamAttackData: BehRamAttack? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to damage a target by using a running attack.
     * ```
     * behRamAttack {
     *     priority = 5
     *     runSpeed = 0.7
     *     ramSpeed = 1.8
     *     minRamDistance = 4
     *     ramDistance = 7
     *     knockbackForce = 2.5
     *     knockbackHeight = 0.04
     *     preRamSound = pre_ram
     *     ramImpactSound = ram_impact
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behRamAttack(value: BehRamAttack.() -> Unit) {
        behRamAttackData = (behRamAttackData ?: BehRamAttack()).apply(value)
    }

    @SerializedName("minecraft:behavior.avoid_block")
    @Expose
    var behAvoidBlockData: BehAvoidBlock? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to avoid certain blocks.
     * ```
     * behAvoidBlock {
     *     priority = 1
     *     tickInterval = 5
     *     searchRange = 8
     *     searchHeight = 4
     *     walkSpeedModifier = 1
     *     sprintSpeedModifier = 1
     *     avoidBlockSound = retreat
     *     targetSelectionMethod = nearest
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behAvoidBlock(value: BehAvoidBlock.() -> Unit) {
        behAvoidBlockData = (behAvoidBlockData ?: BehAvoidBlock()).apply(value)
    }

    @SerializedName("minecraft:is_shaking")
    @Expose
    var isShakingData: IsShaking? = null
        @MonsteraBuildSetter set

    /**
     * Sets that this entity is currently shaking.
     * ```
     * isShaking {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun isShaking(value: IsShaking.() -> Unit) {
        isShakingData = (isShakingData ?: IsShaking()).apply(value)
    }

    @SerializedName("minecraft:transformation")
    @Expose
    var transformationData: Transformation? = null
        @MonsteraBuildSetter set

    /**
     * Defines an entity's transformation from the current definition into another
     * ```
     * transformation {
     *     into = minecraft:zoglin
     *     transformationSound = mob.hoglin.converted_to_zombified
     *     keepLevel = true
     *     dropEquipment = true
     *     dropInventory = true
     *     preserveEquipment = true
     *     beginTransformSound = remedy
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun transformation(value: Transformation.() -> Unit) {
        transformationData = (transformationData ?: Transformation()).apply(value)
    }

    @SerializedName("minecraft:custom_hit_test")
    @Expose
    var customHitTestData: CustomHitTest? = null
        @MonsteraBuildSetter set

    /**
     * List of hitboxes for melee and ranged hits against the entity.
     * ```
     * customHitTest {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun customHitTest(value: CustomHitTest.() -> Unit) {
        customHitTestData = (customHitTestData ?: CustomHitTest()).apply(value)
    }

    @SerializedName("minecraft:group_size")
    @Expose
    var groupSizeData: GroupSize? = null
        @MonsteraBuildSetter set

    /**
     * Keeps track of entity group size in the given radius.
     * ```
     * groupSize {
     *     radius = 32
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun groupSize(value: GroupSize.() -> Unit) {
        groupSizeData = (groupSizeData ?: GroupSize()).apply(value)
    }

    @SerializedName("minecraft:item_hopper")
    @Expose
    var itemHopperData: ItemHopper? = null
        @MonsteraBuildSetter set

    /**
     * Determines that this entity is an item hopper.
     * ```
     * itemHopper {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun itemHopper(value: ItemHopper.() -> Unit) {
        itemHopperData = (itemHopperData ?: ItemHopper()).apply(value)
    }

    @SerializedName("minecraft:behavior.find_mount")
    @Expose
    var behFindMountData: BehFindMount? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to look around for another mob to ride atop it.
     * ```
     * behFindMount {
     *     priority = 1
     *     withinRadius = 16
     *     avoidWater = true
     *     startDelay = 100
     *     targetNeeded = false
     *     mountDistance = 2.0
     *     maxFailedAttempts = 20
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behFindMount(value: BehFindMount.() -> Unit) {
        behFindMountData = (behFindMountData ?: BehFindMount()).apply(value)
    }

    @SerializedName("minecraft:preferred_path")
    @Expose
    var preferredPathData: PreferredPath? = null
        @MonsteraBuildSetter set

    /**
     * Specifies costing information for mobs that prefer to walk on preferred paths.
     * ```
     * preferredPath {
     *     maxFallBlocks = 1
     *     jumpCost = 5
     *     defaultBlockCost = 1.5
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun preferredPath(value: PreferredPath.() -> Unit) {
        preferredPathData = (preferredPathData ?: PreferredPath()).apply(value)
    }

    @SerializedName("minecraft:behavior.target_when_pushed")
    @Expose
    var behTargetWhenPushedData: BehTargetWhenPushed? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * behTargetWhenPushed {
     *     priority = 1
     *     percentChance = 5.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behTargetWhenPushed(value: BehTargetWhenPushed.() -> Unit) {
        behTargetWhenPushedData = (behTargetWhenPushedData ?: BehTargetWhenPushed()).apply(value)
    }

    @SerializedName("minecraft:behavior.move_towards_target")
    @Expose
    var behMoveTowardsTargetData: BehMoveTowardsTarget? = null
        @MonsteraBuildSetter set

    /**
     * Allows mob to move towards its current target.
     * ```
     * behMoveTowardsTarget {
     *     priority = 2
     *     speedMultiplier = 0.9
     *     withinRadius = 32
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behMoveTowardsTarget(value: BehMoveTowardsTarget.() -> Unit) {
        behMoveTowardsTargetData = (behMoveTowardsTargetData ?: BehMoveTowardsTarget()).apply(value)
    }

    @SerializedName("minecraft:behavior.move_through_village")
    @Expose
    var behMoveThroughVillageData: BehMoveThroughVillage? = null
        @MonsteraBuildSetter set

    /**
     * Can only be used by Villagers. Allows the villagers to create paths around the village.
     * ```
     * behMoveThroughVillage {
     *     priority = 3
     *     speedMultiplier = 0.6
     *     onlyAtNight = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behMoveThroughVillage(value: BehMoveThroughVillage.() -> Unit) {
        behMoveThroughVillageData = (behMoveThroughVillageData ?: BehMoveThroughVillage()).apply(value)
    }

    @SerializedName("minecraft:behavior.offer_flower")
    @Expose
    var behOfferFlowerData: BehOfferFlower? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to offer a flower to another mob with the minecraft:take_flower behavior.
     * ```
     * behOfferFlower {
     *     priority = 5
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behOfferFlower(value: BehOfferFlower.() -> Unit) {
        behOfferFlowerData = (behOfferFlowerData ?: BehOfferFlower()).apply(value)
    }

    @SerializedName("minecraft:behavior.defend_village_target")
    @Expose
    var behDefendVillageTargetData: BehDefendVillageTarget? = null
        @MonsteraBuildSetter set

    /**
     * Allows the entity to stay in a village and defend the village from aggressors. If a player is in bad standing with the village this goal will cause the entity to attack the player regardless of filter conditions.
     * ```
     * behDefendVillageTarget {
     *     priority = 1
     *     mustReach = true
     *     attackChance = 0.05
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behDefendVillageTarget(value: BehDefendVillageTarget.() -> Unit) {
        behDefendVillageTargetData = (behDefendVillageTargetData ?: BehDefendVillageTarget()).apply(value)
    }

    @SerializedName("minecraft:behavior.follow_caravan")
    @Expose
    var behFollowCaravanData: BehFollowCaravan? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to follow mobs that are in a caravan.
     * ```
     * behFollowCaravan {
     *     priority = 3
     *     speedMultiplier = 2.1
     *     entityCount = 10
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behFollowCaravan(value: BehFollowCaravan.() -> Unit) {
        behFollowCaravanData = (behFollowCaravanData ?: BehFollowCaravan()).apply(value)
    }

    @SerializedName("minecraft:strength")
    @Expose
    var strengthData: Strength? = null
        @MonsteraBuildSetter set

    /**
     * Defines the entity's strength to carry items.
     * ```
     * strength {
     *     value = 1
     *     max = 5
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun strength(value: Strength.() -> Unit) {
        strengthData = (strengthData ?: Strength()).apply(value)
    }

    @SerializedName("minecraft:area_attack")
    @Expose
    var areaAttackData: AreaAttack? = null
        @MonsteraBuildSetter set

    /**
     * A component that does damage to entities that get within range.
     * ```
     * areaAttack {
     *     damageRange = 0.15
     *     damagePerTick = 6
     *     damageCooldown = 0.5
     *     cause = entity_attack
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun areaAttack(value: AreaAttack.() -> Unit) {
        areaAttackData = (areaAttackData ?: AreaAttack()).apply(value)
    }

    @SerializedName("minecraft:movement.jump")
    @Expose
    var movementJumpData: MovementJump? = null
        @MonsteraBuildSetter set

    /**
     * Move control that causes the mob to jump as it moves with a specified delay between jumps.
     * ```
     * movementJump {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun movementJump(value: MovementJump.() -> Unit) {
        movementJumpData = (movementJumpData ?: MovementJump()).apply(value)
    }

    @SerializedName("minecraft:trusting")
    @Expose
    var trustingData: Trusting? = null
        @MonsteraBuildSetter set

    /**
     * Defines the rules for a mob to trust players.
     * ```
     * trusting {
     *     probability = 0.33
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun trusting(value: Trusting.() -> Unit) {
        trustingData = (trustingData ?: Trusting()).apply(value)
    }

    @SerializedName("minecraft:giveable")
    @Expose
    var giveableData: Giveable? = null
        @MonsteraBuildSetter set

    /**
     * Defines sets of items that can be used to trigger events when used on this entity. The item will also be taken and placed in the entity's inventory.
     * ```
     * giveable {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun giveable(value: Giveable.() -> Unit) {
        giveableData = (giveableData ?: Giveable()).apply(value)
    }

    @SerializedName("minecraft:water_movement")
    @Expose
    var waterMovementData: WaterMovement? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * waterMovement {
     *     dragFactor = 0.98
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun waterMovement(value: WaterMovement.() -> Unit) {
        waterMovementData = (waterMovementData ?: WaterMovement()).apply(value)
    }

    @SerializedName("minecraft:behavior.random_sitting")
    @Expose
    var behRandomSittingData: BehRandomSitting? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to randomly sit for a duration.
     * ```
     * behRandomSitting {
     *     priority = 5
     *     startChance = 0.01
     *     stopChance = 0.3
     *     cooldown = 30
     *     minSitTime = 10
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behRandomSitting(value: BehRandomSitting.() -> Unit) {
        behRandomSittingData = (behRandomSittingData ?: BehRandomSitting()).apply(value)
    }

    @SerializedName("minecraft:behavior.snacking")
    @Expose
    var behSnackingData: BehSnacking? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to take a load off and snack on food that it found nearby.
     * ```
     * behSnacking {
     *     priority = 2
     *     snackingCooldown = 22.5
     *     snackingCooldownMin = 20
     *     snackingStopChance = 0.001334
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behSnacking(value: BehSnacking.() -> Unit) {
        behSnackingData = (behSnackingData ?: BehSnacking()).apply(value)
    }

    @SerializedName("minecraft:behavior.roll")
    @Expose
    var behRollData: BehRoll? = null
        @MonsteraBuildSetter set

    /**
     * This allows the mob to roll forward.
     * ```
     * behRoll {
     *     priority = 12
     *     probability = 0.0016
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behRoll(value: BehRoll.() -> Unit) {
        behRollData = (behRollData ?: BehRoll()).apply(value)
    }

    @SerializedName("minecraft:behavior.sneeze")
    @Expose
    var behSneezeData: BehSneeze? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to stop and sneeze possibly startling nearby mobs and dropping an item.
     * ```
     * behSneeze {
     *     priority = 7
     *     probability = 0.0001666
     *     cooldownTime = 1.0
     *     withinRadius = 10.0
     *     dropItemChance = 0.001
     *     lootTable = loot_tables/entities/panda_sneeze.json
     *     prepareSound = presneeze
     *     prepareTime = 1.0
     *     sound = sneeze
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behSneeze(value: BehSneeze.() -> Unit) {
        behSneezeData = (behSneezeData ?: BehSneeze()).apply(value)
    }

    @SerializedName("minecraft:behavior.lay_down")
    @Expose
    var behLayDownData: BehLayDown? = null
        @MonsteraBuildSetter set

    /**
     * Allows mobs to lay down at times
     * ```
     * behLayDown {
     *     priority = 5
     *     interval = 400
     *     randomStopInterval = 2000
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behLayDown(value: BehLayDown.() -> Unit) {
        behLayDownData = (behLayDownData ?: BehLayDown()).apply(value)
    }

    @SerializedName("minecraft:behavior.scared")
    @Expose
    var behScaredData: BehScared? = null
        @MonsteraBuildSetter set

    /**
     * Allows the a mob to become scared when the weather outside is thundering
     * ```
     * behScared {
     *     priority = 1
     *     soundInterval = 20
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behScared(value: BehScared.() -> Unit) {
        behScaredData = (behScaredData ?: BehScared()).apply(value)
    }

    @SerializedName("minecraft:on_friendly_anger")
    @Expose
    var onFriendlyAngerData: OnFriendlyAnger? = null
        @MonsteraBuildSetter set

    /**
     * Adds a trigger that will run when a nearby entity of the same type as this entity becomes com.lop.devtools.monstera.files.beh.entitiy.components.scraped.Angry.
     * ```
     * onFriendlyAnger {
     *     event = minecraft:on_anger
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun onFriendlyAnger(value: OnFriendlyAnger.() -> Unit) {
        onFriendlyAngerData = (onFriendlyAngerData ?: OnFriendlyAnger()).apply(value)
    }

    @SerializedName("minecraft:navigation.fly")
    @Expose
    var navigationFlyData: NavigationFly? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to generate paths in the air like the vanilla Parrots do.
     * ```
     * navigationFly {
     *     canPathOverWater = true
     *     canPathFromAir = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun navigationFly(value: NavigationFly.() -> Unit) {
        navigationFlyData = (navigationFlyData ?: NavigationFly()).apply(value)
    }

    @SerializedName("minecraft:movement.fly")
    @Expose
    var movementFlyData: MovementFly? = null
        @MonsteraBuildSetter set

    /**
     * This move control causes the mob to fly.
     * ```
     * movementFly {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun movementFly(value: MovementFly.() -> Unit) {
        movementFlyData = (movementFlyData ?: MovementFly()).apply(value)
    }

    @SerializedName("minecraft:behavior.random_fly")
    @Expose
    var behRandomFlyData: BehRandomFly? = null
        @MonsteraBuildSetter set

    /**
     * Allows a mob to randomly fly around.
     * ```
     * behRandomFly {
     *     priority = 2
     *     xzDist = 15
     *     yDist = 1
     *     yOffset = 0
     *     speedMultiplier = 1.0
     *     canLandOnTrees = true
     *     avoidDamageBlocks = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behRandomFly(value: BehRandomFly.() -> Unit) {
        behRandomFlyData = (behRandomFlyData ?: BehRandomFly()).apply(value)
    }

    @SerializedName("minecraft:behavior.follow_mob")
    @Expose
    var behFollowMobData: BehFollowMob? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to follow other mobs.
     * ```
     * behFollowMob {
     *     priority = 3
     *     speedMultiplier = 1.0
     *     stopDistance = 3
     *     searchRange = 20
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behFollowMob(value: BehFollowMob.() -> Unit) {
        behFollowMobData = (behFollowMobData ?: BehFollowMob()).apply(value)
    }

    @SerializedName("minecraft:entity_sensor")
    @Expose
    var entitySensorData: EntitySensor? = null
        @MonsteraBuildSetter set

    /**
     * A component that fires an event when a set of conditions are met by other entities within the defined range.
     * ```
     * entitySensor {
     *     sensorRange = 2.0
     *     relativeRange = false
     *     event = minecraft:on_riding_player
     *     minimumCount = 1
     *     requireAll = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun entitySensor(value: EntitySensor.() -> Unit) {
        entitySensorData = (entitySensorData ?: EntitySensor()).apply(value)
    }

    @SerializedName("minecraft:movement.glide")
    @Expose
    var movementGlideData: MovementGlide? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * movementGlide {
     *     startSpeed = 0.1
     *     speedWhenTurning = 0.2
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun movementGlide(value: MovementGlide.() -> Unit) {
        movementGlideData = (movementGlideData ?: MovementGlide()).apply(value)
    }

    @SerializedName("minecraft:behavior.swoop_attack")
    @Expose
    var behSwoopAttackData: BehSwoopAttack? = null
        @MonsteraBuildSetter set

    /**
     * Allows an entity to attack using swoop attack behavior; Ideal for use with flying mobs. The behavior ends if the entity has a horizontal collision or gets hit.
     * ```
     * behSwoopAttack {
     *     priority = 2
     *     damageReach = 0.2
     *     speedMultiplier = 1.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behSwoopAttack(value: BehSwoopAttack.() -> Unit) {
        behSwoopAttackData = (behSwoopAttackData ?: BehSwoopAttack()).apply(value)
    }

    @SerializedName("minecraft:behavior.circle_around_anchor")
    @Expose
    var behCircleAroundAnchorData: BehCircleAroundAnchor? = null
        @MonsteraBuildSetter set

    /**
     * Causes an entity to circle around an anchor point placed near a point or target.
     * ```
     * behCircleAroundAnchor {
     *     priority = 3
     *     radiusChange = 1.0
     *     radiusAdjustmentChance = 0.004
     *     heightAdjustmentChance = 0.002857
     *     goalRadius = 1.0
     *     angleChange = 15.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behCircleAroundAnchor(value: BehCircleAroundAnchor.() -> Unit) {
        behCircleAroundAnchorData = (behCircleAroundAnchorData ?: BehCircleAroundAnchor()).apply(value)
    }

    @SerializedName("minecraft:boostable")
    @Expose
    var boostableData: Boostable? = null
        @MonsteraBuildSetter set

    /**
     * Defines the conditions and behavior of a rideable entity's boost.
     * ```
     * boostable {
     *     speedMultiplier = 1.35
     *     duration = 3.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun boostable(value: Boostable.() -> Unit) {
        boostableData = (boostableData ?: Boostable()).apply(value)
    }

    @SerializedName("minecraft:item_controllable")
    @Expose
    var itemControllableData: ItemControllable? = null
        @MonsteraBuildSetter set

    /**
     * Defines what items can be used to control this entity while ridden.
     * ```
     * itemControllable {
     *     controlItems = carrotOnAStick
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun itemControllable(value: ItemControllable.() -> Unit) {
        itemControllableData = (itemControllableData ?: ItemControllable()).apply(value)
    }

    @SerializedName("minecraft:behavior.controlled_by_player")
    @Expose
    var behControlledByPlayerData: BehControlledByPlayer? = null
        @MonsteraBuildSetter set

    /**
     * Allows the entity to be controlled by the player using an item in the item_controllable property (required). Also requires the minecraft:movement property, and the minecraft:rideable property. On every tick, the entity will attempt to rotate towards where the player is facing with the control item whilst simultaneously moving forward.
     * ```
     * behControlledByPlayer {
     *     priority = 0
     *     mountSpeedMultiplier = 1.45
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behControlledByPlayer(value: BehControlledByPlayer.() -> Unit) {
        behControlledByPlayerData = (behControlledByPlayerData ?: BehControlledByPlayer()).apply(value)
    }

    @SerializedName("minecraft:admire_item")
    @Expose
    var admireItemData: AdmireItem? = null
        @MonsteraBuildSetter set

    /**
     * Causes the mob to ignore attackable targets for a given duration.
     * ```
     * admireItem {
     *     duration = 8
     *     cooldownAfterBeingAttacked = 20
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun admireItem(value: AdmireItem.() -> Unit) {
        admireItemData = (admireItemData ?: AdmireItem()).apply(value)
    }

    @SerializedName("minecraft:annotation.open_door")
    @Expose
    var annotationOpenDoorData: AnnotationOpenDoor? = null
        @MonsteraBuildSetter set

    /**
     * Allows the actor to open doors assuming that that flags set up for the component to use in navigation
     * ```
     * annotationOpenDoor {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun annotationOpenDoor(value: AnnotationOpenDoor.() -> Unit) {
        annotationOpenDoorData = (annotationOpenDoorData ?: AnnotationOpenDoor()).apply(value)
    }

    @SerializedName("minecraft:behavior.admire_item")
    @Expose
    var behAdmireItemData: BehAdmireItem? = null
        @MonsteraBuildSetter set

    /**
     * Enables the mob to admire items that have been configured as admirable. Must be used in combination with the admire_item component
     * ```
     * behAdmireItem {
     *     priority = 2
     *     admireItemSound = admire
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behAdmireItem(value: BehAdmireItem.() -> Unit) {
        behAdmireItemData = (behAdmireItemData ?: BehAdmireItem()).apply(value)
    }

    @SerializedName("minecraft:behavior.barter")
    @Expose
    var behBarterData: BehBarter? = null
        @MonsteraBuildSetter set

    /**
     * Enables the mob to barter for items that have been configured as barter currency. Must be used in combination with the barter component
     * ```
     * behBarter {
     *     priority = 3
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behBarter(value: BehBarter.() -> Unit) {
        behBarterData = (behBarterData ?: BehBarter()).apply(value)
    }

    @SerializedName("minecraft:behavior.charge_held_item")
    @Expose
    var behChargeHeldItemData: BehChargeHeldItem? = null
        @MonsteraBuildSetter set

    /**
     * Allows an entity to charge and use their held item.
     * ```
     * behChargeHeldItem {
     *     priority = 3
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behChargeHeldItem(value: BehChargeHeldItem.() -> Unit) {
        behChargeHeldItemData = (behChargeHeldItemData ?: BehChargeHeldItem()).apply(value)
    }

    @SerializedName("minecraft:barter")
    @Expose
    var barterData: Barter? = null
        @MonsteraBuildSetter set

    /**
     * Enables the component to drop an item as a barter exchange.
     * ```
     * barter {
     *     barterTable = loot_tables/entities/piglin_barter.json
     *     cooldownAfterBeingAttacked = 20
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun barter(value: Barter.() -> Unit) {
        barterData = (barterData ?: Barter()).apply(value)
    }

    @SerializedName("minecraft:celebrate_hunt")
    @Expose
    var celebrateHuntData: CelebrateHunt? = null
        @MonsteraBuildSetter set

    /**
     * Specifies hunt celebration behaviour.
     * ```
     * celebrateHunt {
     *     broadcast = true
     *     duration = 10
     *     celebrateSound = celebrate
     *     radius = 16
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun celebrateHunt(value: CelebrateHunt.() -> Unit) {
        celebrateHuntData = (celebrateHuntData ?: CelebrateHunt()).apply(value)
    }

    @SerializedName("minecraft:is_illager_captain")
    @Expose
    var isIllagerCaptainData: IsIllagerCaptain? = null
        @MonsteraBuildSetter set

    /**
     * Sets that this entity is an illager captain.
     * ```
     * isIllagerCaptain {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun isIllagerCaptain(value: IsIllagerCaptain.() -> Unit) {
        isIllagerCaptainData = (isIllagerCaptainData ?: IsIllagerCaptain()).apply(value)
    }

    @SerializedName("minecraft:behavior.hold_ground")
    @Expose
    var behHoldGroundData: BehHoldGround? = null
        @MonsteraBuildSetter set

    /**
     * The mob freezes and looks at the mob they are targeting.
     * ```
     * behHoldGround {
     *     priority = 5
     *     minRadius = 10
     *     broadcast = true
     *     broadcastRange = 8
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behHoldGround(value: BehHoldGround.() -> Unit) {
        behHoldGroundData = (behHoldGroundData ?: BehHoldGround()).apply(value)
    }

    @SerializedName("minecraft:behavior.move_to_random_block")
    @Expose
    var behMoveToRandomBlockData: BehMoveToRandomBlock? = null
        @MonsteraBuildSetter set

    /**
     * Allows mob to move towards a random block.
     * ```
     * behMoveToRandomBlock {
     *     priority = 6
     *     speedMultiplier = 0.55
     *     withinRadius = 8
     *     blockDistance = 512
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behMoveToRandomBlock(value: BehMoveToRandomBlock.() -> Unit) {
        behMoveToRandomBlockData = (behMoveToRandomBlockData ?: BehMoveToRandomBlock()).apply(value)
    }

    @SerializedName("minecraft:behavior.follow_target_captain")
    @Expose
    var behFollowTargetCaptainData: BehFollowTargetCaptain? = null
        @MonsteraBuildSetter set

    /**
     * Allows mob to move towards its current target captain.
     * ```
     * behFollowTargetCaptain {
     *     priority = 5
     *     speedMultiplier = 0.8
     *     withinRadius = 64
     *     followDistance = 5
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behFollowTargetCaptain(value: BehFollowTargetCaptain.() -> Unit) {
        behFollowTargetCaptainData = (behFollowTargetCaptainData ?: BehFollowTargetCaptain()).apply(value)
    }

    @SerializedName("minecraft:exhaustion_values")
    @Expose
    var exhaustionValuesData: ExhaustionValues? = null
        @MonsteraBuildSetter set

    /**
     * Defines how much exhaustion each player action should take.
     * ```
     * exhaustionValues {
     *     heal = 6
     *     jump = 0.05
     *     sprintJump = 0.2
     *     mine = 0.005
     *     attack = 0.1
     *     damage = 0.1
     *     walk = 0.0
     *     sprint = 0.1
     *     swim = 0.01
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun exhaustionValues(value: ExhaustionValues.() -> Unit) {
        exhaustionValuesData = (exhaustionValuesData ?: ExhaustionValues()).apply(value)
    }

    @SerializedName("minecraft:player.saturation")
    @Expose
    var playerSaturationData: PlayerSaturation? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * playerSaturation {
     *     value = 5
     *     max = 20
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun playerSaturation(value: PlayerSaturation.() -> Unit) {
        playerSaturationData = (playerSaturationData ?: PlayerSaturation()).apply(value)
    }

    @SerializedName("minecraft:player.exhaustion")
    @Expose
    var playerExhaustionData: PlayerExhaustion? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * playerExhaustion {
     *     value = 0
     *     max = 20
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun playerExhaustion(value: PlayerExhaustion.() -> Unit) {
        playerExhaustionData = (playerExhaustionData ?: PlayerExhaustion()).apply(value)
    }

    @SerializedName("minecraft:player.level")
    @Expose
    var playerLevelData: PlayerLevel? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * playerLevel {
     *     value = 0
     *     max = 24791
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun playerLevel(value: PlayerLevel.() -> Unit) {
        playerLevelData = (playerLevelData ?: PlayerLevel()).apply(value)
    }

    @SerializedName("minecraft:player.experience")
    @Expose
    var playerExperienceData: PlayerExperience? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * playerExperience {
     *     value = 0
     *     max = 1
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun playerExperience(value: PlayerExperience.() -> Unit) {
        playerExperienceData = (playerExperienceData ?: PlayerExperience()).apply(value)
    }

    @SerializedName("minecraft:insomnia")
    @Expose
    var insomniaData: Insomnia? = null
        @MonsteraBuildSetter set

    /**
     * Adds a timer since last rested to see if phantoms should spawn.
     * ```
     * insomnia {
     *     daysUntilInsomnia = 3
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun insomnia(value: Insomnia.() -> Unit) {
        insomniaData = (insomniaData ?: Insomnia()).apply(value)
    }

    @SerializedName("minecraft:spell_effects")
    @Expose
    var spellEffectsData: SpellEffects? = null
        @MonsteraBuildSetter set

    /**
     * Defines what mob effects to add and remove to the entity when adding this component.
     * ```
     * spellEffects {
     *     removeEffects = bad_omen
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun spellEffects(value: SpellEffects.() -> Unit) {
        spellEffectsData = (spellEffectsData ?: SpellEffects()).apply(value)
    }

    @SerializedName("minecraft:raid_trigger")
    @Expose
    var raidTriggerData: RaidTrigger? = null
        @MonsteraBuildSetter set

    /**
     * Attempts to trigger a raid at the entity's location.
     * ```
     * raidTrigger {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun raidTrigger(value: RaidTrigger.() -> Unit) {
        raidTriggerData = (raidTriggerData ?: RaidTrigger()).apply(value)
    }

    @SerializedName("minecraft:behavior.stomp_attack")
    @Expose
    var behStompAttackData: BehStompAttack? = null
        @MonsteraBuildSetter set

    /**
     * Allows an entity to attack using stomp AoE damage behavior.
     * ```
     * behStompAttack {
     *     priority = 1
     *     trackTarget = true
     *     requireCompletePath = true
     *     stompRangeMultiplier = 2.0
     *     noDamageRangeMultiplier = 2.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behStompAttack(value: BehStompAttack.() -> Unit) {
        behStompAttackData = (behStompAttackData ?: BehStompAttack()).apply(value)
    }

    @SerializedName("minecraft:mob_effect")
    @Expose
    var mobEffectData: MobEffect? = null
        @MonsteraBuildSetter set

    /**
     * A component that applies a mob effect to entities that get within range.
     * ```
     * mobEffect {
     *     effectRange = 0.2
     *     mobEffect = poison
     *     effectTime = 10
     *     cooldownTime = 6
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun mobEffect(value: MobEffect.() -> Unit) {
        mobEffectData = (mobEffectData ?: MobEffect()).apply(value)
    }

    @SerializedName("minecraft:movement.skip")
    @Expose
    var movementSkipData: MovementSkip? = null
        @MonsteraBuildSetter set

    /**
     * This move control causes the mob to hop as it moves.
     * ```
     * movementSkip {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun movementSkip(value: MovementSkip.() -> Unit) {
        movementSkipData = (movementSkipData ?: MovementSkip()).apply(value)
    }

    @SerializedName("minecraft:jump.dynamic")
    @Expose
    var jumpDynamicData: JumpDynamic? = null
        @MonsteraBuildSetter set

    /**
     * Defines a dynamic type jump control that will change jump properties based on the speed modifier of the mob.
     * ```
     * jumpDynamic {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun jumpDynamic(value: JumpDynamic.() -> Unit) {
        jumpDynamicData = (jumpDynamicData ?: JumpDynamic()).apply(value)
    }

    @SerializedName("minecraft:ravager_blocked")
    @Expose
    var ravagerBlockedData: RavagerBlocked? = null
        @MonsteraBuildSetter set

    /**
     * Defines the ravager's response to their melee attack being blocked.
     * ```
     * ravagerBlocked {
     *     knockbackStrength = 3.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun ravagerBlocked(value: RavagerBlocked.() -> Unit) {
        ravagerBlockedData = (ravagerBlockedData ?: RavagerBlocked()).apply(value)
    }

    @SerializedName("minecraft:break_blocks")
    @Expose
    var breakBlocksData: BreakBlocks? = null
        @MonsteraBuildSetter set

    /**
     * Specifies the blocks that this entity can break as it moves around.
     * ```
     * breakBlocks {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun breakBlocks(value: BreakBlocks.() -> Unit) {
        breakBlocksData = (breakBlocksData ?: BreakBlocks()).apply(value)
    }

    @SerializedName("minecraft:behavior.delayed_attack")
    @Expose
    var behDelayedAttackData: BehDelayedAttack? = null
        @MonsteraBuildSetter set

    /**
     * Allows an entity to attack, while also delaying the damage-dealt until a specific time in the attack animation.
     * ```
     * behDelayedAttack {
     *     priority = 4
     *     attackOnce = false
     *     trackTarget = true
     *     requireCompletePath = false
     *     randomStopInterval = 0
     *     reachMultiplier = 1.5
     *     speedMultiplier = 1.0
     *     attackDuration = 0.75
     *     hitDelayPct = 0.5
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behDelayedAttack(value: BehDelayedAttack.() -> Unit) {
        behDelayedAttackData = (behDelayedAttackData ?: BehDelayedAttack()).apply(value)
    }

    @SerializedName("minecraft:is_stunned")
    @Expose
    var isStunnedData: IsStunned? = null
        @MonsteraBuildSetter set

    /**
     * Sets that this entity is currently stunned.
     * ```
     * isStunned {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun isStunned(value: IsStunned.() -> Unit) {
        isStunnedData = (isStunnedData ?: IsStunned()).apply(value)
    }

    @SerializedName("minecraft:behavior.knockback_roar")
    @Expose
    var behKnockbackRoarData: BehKnockbackRoar? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to perform a damaging knockback that affects all nearby entities.
     * ```
     * behKnockbackRoar {
     *     priority = 1
     *     duration = 1
     *     attackTime = 0.5
     *     knockbackDamage = 6
     *     knockbackHorizontalStrength = 3
     *     knockbackVerticalStrength = 3
     *     knockbackRange = 4
     *     cooldownTime = 0.1
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behKnockbackRoar(value: BehKnockbackRoar.() -> Unit) {
        behKnockbackRoarData = (behKnockbackRoarData ?: BehKnockbackRoar()).apply(value)
    }

    @SerializedName("minecraft:behavior.eat_block")
    @Expose
    var behEatBlockData: BehEatBlock? = null
        @MonsteraBuildSetter set

    /**
     * Allows the entity to consume a block, replace the eaten block with another block, and trigger an event as a result.
     * ```
     * behEatBlock {
     *     priority = 6
     *     successChance = query.is_baby ? 0.02 : 0.001
     *     timeUntilEat = 1.8
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behEatBlock(value: BehEatBlock.() -> Unit) {
        behEatBlockData = (behEatBlockData ?: BehEatBlock()).apply(value)
    }

    @SerializedName("minecraft:is_sheared")
    @Expose
    var isShearedData: IsSheared? = null
        @MonsteraBuildSetter set

    /**
     * Sets that this entity is currently sheared.
     * ```
     * isSheared {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun isSheared(value: IsSheared.() -> Unit) {
        isShearedData = (isShearedData ?: IsSheared()).apply(value)
    }

    @SerializedName("minecraft:behavior.silverfish_merge_with_stone")
    @Expose
    var behSilverfishMergeWithStoneData: BehSilverfishMergeWithStone? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to go into stone blocks like Silverfish do. Currently it can only be used by Silverfish.
     * ```
     * behSilverfishMergeWithStone {
     *     priority = 5
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behSilverfishMergeWithStone(value: BehSilverfishMergeWithStone.() -> Unit) {
        behSilverfishMergeWithStoneData =
            (behSilverfishMergeWithStoneData ?: BehSilverfishMergeWithStone()).apply(value)
    }

    @SerializedName("minecraft:behavior.silverfish_wake_up_friends")
    @Expose
    var behSilverfishWakeUpFriendsData: BehSilverfishWakeUpFriends? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to alert mobs in nearby blocks to come out. Currently it can only be used by Silverfish.
     * ```
     * behSilverfishWakeUpFriends {
     *     priority = 1
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behSilverfishWakeUpFriends(value: BehSilverfishWakeUpFriends.() -> Unit) {
        behSilverfishWakeUpFriendsData = (behSilverfishWakeUpFriendsData ?: BehSilverfishWakeUpFriends()).apply(value)
    }

    @SerializedName("minecraft:behavior.skeleton_horse_trap")
    @Expose
    var behSkeletonHorseTrapData: BehSkeletonHorseTrap? = null
        @MonsteraBuildSetter set

    /**
     * Allows Equine mobs to be Horse Traps and be triggered like them, spawning a lightning bolt and a bunch of horses when a player is nearby. Can only be used by Horses, Mules, Donkeys and Skeleton Horses.
     * ```
     * behSkeletonHorseTrap {
     *     withinRadius = 10.0
     *     duration = 900.0
     *     priority = 2
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behSkeletonHorseTrap(value: BehSkeletonHorseTrap.() -> Unit) {
        behSkeletonHorseTrapData = (behSkeletonHorseTrapData ?: BehSkeletonHorseTrap()).apply(value)
    }

    @SerializedName("minecraft:behavior.slime_float")
    @Expose
    var behSlimeFloatData: BehSlimeFloat? = null
        @MonsteraBuildSetter set

    /**
     * Allow slimes to float in water / lava. Can only be used by Slime and Magma Cubes.
     * ```
     * behSlimeFloat {
     *     priority = 1
     *     jumpChancePercentage = 0.8
     *     speedMultiplier = 1.2
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behSlimeFloat(value: BehSlimeFloat.() -> Unit) {
        behSlimeFloatData = (behSlimeFloatData ?: BehSlimeFloat()).apply(value)
    }

    @SerializedName("minecraft:behavior.slime_attack")
    @Expose
    var behSlimeAttackData: BehSlimeAttack? = null
        @MonsteraBuildSetter set

    /**
     * Causes the entity to grow tired every once in a while, while attacking.
     * ```
     * behSlimeAttack {
     *     priority = 3
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behSlimeAttack(value: BehSlimeAttack.() -> Unit) {
        behSlimeAttackData = (behSlimeAttackData ?: BehSlimeAttack()).apply(value)
    }

    @SerializedName("minecraft:behavior.slime_random_direction")
    @Expose
    var behSlimeRandomDirectionData: BehSlimeRandomDirection? = null
        @MonsteraBuildSetter set

    /**
     * Allows the entity to move in random directions like a slime.
     * ```
     * behSlimeRandomDirection {
     *     priority = 4
     *     addRandomTimeRange = 3
     *     turnRange = 360
     *     minChangeDirectionTime = 2.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behSlimeRandomDirection(value: BehSlimeRandomDirection.() -> Unit) {
        behSlimeRandomDirectionData = (behSlimeRandomDirectionData ?: BehSlimeRandomDirection()).apply(value)
    }

    @SerializedName("minecraft:behavior.slime_keep_on_jumping")
    @Expose
    var behSlimeKeepOnJumpingData: BehSlimeKeepOnJumping? = null
        @MonsteraBuildSetter set

    /**
     * Allows the entity to continuously jump around like a slime.
     * ```
     * behSlimeKeepOnJumping {
     *     priority = 5
     *     speedMultiplier = 1.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behSlimeKeepOnJumping(value: BehSlimeKeepOnJumping.() -> Unit) {
        behSlimeKeepOnJumpingData = (behSlimeKeepOnJumpingData ?: BehSlimeKeepOnJumping()).apply(value)
    }

    @SerializedName("minecraft:behavior.timer_flag_1")
    @Expose
    var behTimerFlag_1Data: BehTimerFlag_1? = null
        @MonsteraBuildSetter set

    /**
     * Fires an event when this behavior starts, then waits for a duration before stopping. When stopping due to that timeout or due to being interrupted by another behavior, fires another event. query.timer_flag_1 will return 1.0 on both the client and server when this behavior is running, and 0.0 otherwise.
     * ```
     * behTimerFlag_1 {
     *     priority = 6
     *     durationRange = 2.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behTimerFlag_1(value: BehTimerFlag_1.() -> Unit) {
        behTimerFlag_1Data = (behTimerFlag_1Data ?: BehTimerFlag_1()).apply(value)
    }

    @SerializedName("minecraft:behavior.timer_flag_3")
    @Expose
    var behTimerFlag_3Data: BehTimerFlag_3? = null
        @MonsteraBuildSetter set

    /**
     * Fires an event when this behavior starts, then waits for a duration before stopping. When stopping due to that timeout or due to being interrupted by another behavior, fires another event. query.timer_flag_3 will return 1.0 on both the client and server when this behavior is running, and 0.0 otherwise.
     * ```
     * behTimerFlag_3 {
     *     priority = 5
     *     cooldownRange = 0.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behTimerFlag_3(value: BehTimerFlag_3.() -> Unit) {
        behTimerFlag_3Data = (behTimerFlag_3Data ?: BehTimerFlag_3()).apply(value)
    }

    @SerializedName("minecraft:behavior.timer_flag_2")
    @Expose
    var behTimerFlag_2Data: BehTimerFlag_2? = null
        @MonsteraBuildSetter set

    /**
     * Fires an event when this behavior starts, then waits for a duration before stopping. When stopping due to that timeout or due to being interrupted by another behavior, fires another event. query.timer_flag_2 will return 1.0 on both the client and server when this behavior is running, and 0.0 otherwise.
     * ```
     * behTimerFlag_2 {
     *     priority = 2
     *     cooldownRange = 0.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behTimerFlag_2(value: BehTimerFlag_2.() -> Unit) {
        behTimerFlag_2Data = (behTimerFlag_2Data ?: BehTimerFlag_2()).apply(value)
    }

    @SerializedName("minecraft:is_pregnant")
    @Expose
    var isPregnantData: IsPregnant? = null
        @MonsteraBuildSetter set

    /**
     * Sets that this entity is currently pregnant.
     * ```
     * isPregnant {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun isPregnant(value: IsPregnant.() -> Unit) {
        isPregnantData = (isPregnantData ?: IsPregnant()).apply(value)
    }

    @SerializedName("minecraft:behavior.random_search_and_dig")
    @Expose
    var behRandomSearchAndDigData: BehRandomSearchAndDig? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to locate a random target block that it can path find to. Once found, the entity will move towards it and dig up an item. [Default target block types: Dirt, Grass, Podzol, DirtWithRoots, MossBlock, Mud, MuddyMangroveRoots].
     * ```
     * behRandomSearchAndDig {
     *     priority = 5
     *     speedMultiplier = 1.25
     *     findValidPositionRetries = 5
     *     goalRadius = 2.0
     *     searchRangeXz = 20.0
     *     searchRangeY = 3
     *     cooldownRange = 0.0
     *     itemTable = loot_tables/gameplay/entities/sniffer_seeds.json
     *     spawnItemAfterSeconds = 6.0
     *     spawnItemPosOffset = 2.25
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behRandomSearchAndDig(value: BehRandomSearchAndDig.() -> Unit) {
        behRandomSearchAndDigData = (behRandomSearchAndDigData ?: BehRandomSearchAndDig()).apply(value)
    }

    @SerializedName("minecraft:trail")
    @Expose
    var trailData: Trail? = null
        @MonsteraBuildSetter set

    /**
     * Causes an entity to leave a trail of blocks as it moves about the world.
     * ```
     * trail {
     *     blockType = minecraft:snow_layer
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun trail(value: Trail.() -> Unit) {
        trailData = (trailData ?: Trail()).apply(value)
    }

    @SerializedName("minecraft:lava_movement")
    @Expose
    var lavaMovementData: ComponentValue? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    var lavaMovement: Number? = null
        set(value) {
            lavaMovementData = ComponentValue().also { it.value = value }
            field = value
        }

    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun lavaMovement(value: ComponentValue.() -> Unit) {
        lavaMovementData = (lavaMovementData ?: ComponentValue()).apply(value)
    }

    @SerializedName("minecraft:behavior.rise_to_liquid_level")
    @Expose
    var behRiseToLiquidLevelData: BehRiseToLiquidLevel? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to stay at a certain level when in liquid.
     * ```
     * behRiseToLiquidLevel {
     *     priority = 0
     *     liquidYOffset = 0.25
     *     riseDelta = 0.01
     *     sinkDelta = 0.01
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behRiseToLiquidLevel(value: BehRiseToLiquidLevel.() -> Unit) {
        behRiseToLiquidLevelData = (behRiseToLiquidLevelData ?: BehRiseToLiquidLevel()).apply(value)
    }

    @SerializedName("minecraft:behavior.move_to_liquid")
    @Expose
    var behMoveToLiquidData: BehMoveToLiquid? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to move into a liquid when on land.
     * ```
     * behMoveToLiquid {
     *     priority = 7
     *     searchRange = 16
     *     searchHeight = 10
     *     goalRadius = 0.9
     *     materialType = Lava
     *     searchCount = 30
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behMoveToLiquid(value: BehMoveToLiquid.() -> Unit) {
        behMoveToLiquidData = (behMoveToLiquidData ?: BehMoveToLiquid()).apply(value)
    }

    @SerializedName("minecraft:is_ignited")
    @Expose
    var isIgnitedData: IsIgnited? = null
        @MonsteraBuildSetter set

    /**
     * Sets that this entity is currently on fire.
     * ```
     * isIgnited {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun isIgnited(value: IsIgnited.() -> Unit) {
        isIgnitedData = (isIgnitedData ?: IsIgnited()).apply(value)
    }

    @SerializedName("minecraft:color2")
    @Expose
    var color2Data: ComponentValue? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    var color2: Number? = null
        set(value) {
            color2Data = ComponentValue().also { it.value = value }
            field = value
        }

    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun color2(value: ComponentValue.() -> Unit) {
        color2Data = (color2Data ?: ComponentValue()).apply(value)
    }

    @SerializedName("minecraft:behavior.charge_attack")
    @Expose
    var behChargeAttackData: BehChargeAttack? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to damage a target by using a running attack.
     * ```
     * behChargeAttack {
     *     priority = 4
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behChargeAttack(value: BehChargeAttack.() -> Unit) {
        behChargeAttackData = (behChargeAttackData ?: BehChargeAttack()).apply(value)
    }

    @SerializedName("minecraft:behavior.trade_with_player")
    @Expose
    var behTradeWithPlayerData: BehTradeWithPlayer? = null
        @MonsteraBuildSetter set

    /**
     * Allows the player to trade with this mob. When the goal starts, it will stop the mob's navigation.
     * ```
     * behTradeWithPlayer {
     *     priority = 1
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behTradeWithPlayer(value: BehTradeWithPlayer.() -> Unit) {
        behTradeWithPlayerData = (behTradeWithPlayerData ?: BehTradeWithPlayer()).apply(value)
    }

    @SerializedName("minecraft:behavior.look_at_trading_player")
    @Expose
    var behLookAtTradingPlayerData: BehLookAtTradingPlayer? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to look at the player they are trading with.
     * ```
     * behLookAtTradingPlayer {
     *     priority = 2
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behLookAtTradingPlayer(value: BehLookAtTradingPlayer.() -> Unit) {
        behLookAtTradingPlayerData = (behLookAtTradingPlayerData ?: BehLookAtTradingPlayer()).apply(value)
    }

    @SerializedName("minecraft:behavior.move_indoors")
    @Expose
    var behMoveIndoorsData: BehMoveIndoors? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to move indoors.
     * ```
     * behMoveIndoors {
     *     priority = 4
     *     speedMultiplier = 0.8
     *     timeoutCooldown = 8.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behMoveIndoors(value: BehMoveIndoors.() -> Unit) {
        behMoveIndoorsData = (behMoveIndoorsData ?: BehMoveIndoors()).apply(value)
    }

    @SerializedName("minecraft:behavior.restrict_open_door")
    @Expose
    var behRestrictOpenDoorData: BehRestrictOpenDoor? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to stay indoors during night time.
     * ```
     * behRestrictOpenDoor {
     *     priority = 5
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behRestrictOpenDoor(value: BehRestrictOpenDoor.() -> Unit) {
        behRestrictOpenDoorData = (behRestrictOpenDoorData ?: BehRestrictOpenDoor()).apply(value)
    }

    @SerializedName("minecraft:behavior.open_door")
    @Expose
    var behOpenDoorData: BehOpenDoor? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to open doors. Requires the mob to be able to path through doors, otherwise the mob won't even want to try opening them.
     * ```
     * behOpenDoor {
     *     priority = 6
     *     closeDoorAfter = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behOpenDoor(value: BehOpenDoor.() -> Unit) {
        behOpenDoorData = (behOpenDoorData ?: BehOpenDoor()).apply(value)
    }

    @SerializedName("minecraft:behavior.share_items")
    @Expose
    var behShareItemsData: BehShareItems? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to give items it has to others.
     * ```
     * behShareItems {
     *     priority = 8
     *     maxDist = 3
     *     goalRadius = 2.0
     *     speedMultiplier = 0.5
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behShareItems(value: BehShareItems.() -> Unit) {
        behShareItemsData = (behShareItemsData ?: BehShareItems()).apply(value)
    }

    @SerializedName("minecraft:behavior.celebrate_survive")
    @Expose
    var behCelebrateSurviveData: BehCelebrateSurvive? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to celebrate surviving a raid by shooting fireworks.
     * ```
     * behCelebrateSurvive {
     *     priority = 5
     *     duration = 30.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behCelebrateSurvive(value: BehCelebrateSurvive.() -> Unit) {
        behCelebrateSurviveData = (behCelebrateSurviveData ?: BehCelebrateSurvive()).apply(value)
    }

    @SerializedName("minecraft:behavior.move_outdoors")
    @Expose
    var behMoveOutdoorsData: BehMoveOutdoors? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to move outdoors.
     * ```
     * behMoveOutdoors {
     *     priority = 2
     *     speedMultiplier = 0.8
     *     timeoutCooldown = 8.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behMoveOutdoors(value: BehMoveOutdoors.() -> Unit) {
        behMoveOutdoorsData = (behMoveOutdoorsData ?: BehMoveOutdoors()).apply(value)
    }

    @SerializedName("minecraft:behavior.harvest_farm_block")
    @Expose
    var behHarvestFarmBlockData: BehHarvestFarmBlock? = null
        @MonsteraBuildSetter set

    /**
     * Allows the entity to search within an area for farmland with air above it. If found, the entity will replace the air block by planting a seed item from its inventory on the farmland block. This goal requires "minecraft:inventory" and "minecraft:navigation" to execute. This goal will not execute if the entity does not have an item in its inventory.
     * ```
     * behHarvestFarmBlock {
     *     priority = 9
     *     speedMultiplier = 0.5
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behHarvestFarmBlock(value: BehHarvestFarmBlock.() -> Unit) {
        behHarvestFarmBlockData = (behHarvestFarmBlockData ?: BehHarvestFarmBlock()).apply(value)
    }

    @SerializedName("minecraft:trade_table")
    @Expose
    var tradeTableData: TradeTable? = null
        @MonsteraBuildSetter set

    /**
     * Defines this entity's ability to trade with players.
     * ```
     * tradeTable {
     *     displayName = entity.villager.farmer
     *     table = trading/farmer_trades.json
     *     convertTradesEconomy = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun tradeTable(value: TradeTable.() -> Unit) {
        tradeTableData = (tradeTableData ?: TradeTable()).apply(value)
    }

    @SerializedName("minecraft:behavior.take_flower")
    @Expose
    var behTakeFlowerData: BehTakeFlower? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to accept flowers from another mob with the minecraft:offer_flower behavior.
     * ```
     * behTakeFlower {
     *     priority = 7
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behTakeFlower(value: BehTakeFlower.() -> Unit) {
        behTakeFlowerData = (behTakeFlowerData ?: BehTakeFlower()).apply(value)
    }

    @SerializedName("minecraft:behavior.play")
    @Expose
    var behPlayData: BehPlay? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to play with other mobs by chasing each other and moving around randomly.
     * ```
     * behPlay {
     *     priority = 8
     *     speedMultiplier = 0.32
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behPlay(value: BehPlay.() -> Unit) {
        behPlayData = (behPlayData ?: BehPlay()).apply(value)
    }

    @SerializedName("minecraft:behavior.make_love")
    @Expose
    var behMakeLoveData: BehMakeLove? = null
        @MonsteraBuildSetter set

    /**
     * Allows the villager to look for a mate to spawn other villagers with. Can only be used by Villagers.
     * ```
     * behMakeLove {
     *     priority = 6
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behMakeLove(value: BehMakeLove.() -> Unit) {
        behMakeLoveData = (behMakeLoveData ?: BehMakeLove()).apply(value)
    }

    @SerializedName("minecraft:behavior.receive_love")
    @Expose
    var behReceiveLoveData: BehReceiveLove? = null
        @MonsteraBuildSetter set

    /**
     * Allows the villager to stop so another villager can breed with it. Can only be used by a Villager.
     * ```
     * behReceiveLove {
     *     priority = 7
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behReceiveLove(value: BehReceiveLove.() -> Unit) {
        behReceiveLoveData = (behReceiveLoveData ?: BehReceiveLove()).apply(value)
    }

    @SerializedName("minecraft:hide")
    @Expose
    var hideData: Hide? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * hide {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun hide(value: Hide.() -> Unit) {
        hideData = (hideData ?: Hide()).apply(value)
    }

    @SerializedName("minecraft:behavior.hide")
    @Expose
    var behHideData: BehHide? = null
        @MonsteraBuildSetter set

    /**
     * Allows a mob with the hide component to attempt to move to - and hide at - an owned or nearby POI.
     * ```
     * behHide {
     *     priority = 0
     *     speedMultiplier = 0.8
     *     poiType = bed
     *     duration = 30.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behHide(value: BehHide.() -> Unit) {
        behHideData = (behHideData ?: BehHide()).apply(value)
    }

    @SerializedName("minecraft:trade_resupply")
    @Expose
    var tradeResupplyData: TradeResupply? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * tradeResupply {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun tradeResupply(value: TradeResupply.() -> Unit) {
        tradeResupplyData = (tradeResupplyData ?: TradeResupply()).apply(value)
    }

    @SerializedName("minecraft:behavior.inspect_bookshelf")
    @Expose
    var behInspectBookshelfData: BehInspectBookshelf? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to inspect bookshelves.
     * ```
     * behInspectBookshelf {
     *     priority = 8
     *     speedMultiplier = 0.6
     *     searchRange = 4
     *     searchHeight = 3
     *     goalRadius = 0.8
     *     searchCount = 0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behInspectBookshelf(value: BehInspectBookshelf.() -> Unit) {
        behInspectBookshelfData = (behInspectBookshelfData ?: BehInspectBookshelf()).apply(value)
    }

    @SerializedName("minecraft:behavior.explore_outskirts")
    @Expose
    var behExploreOutskirtsData: BehExploreOutskirts? = null
        @MonsteraBuildSetter set

    /**
     * Allows the entity to first travel to a random point on the outskirts of the village, and then explore random points within a small distance. This goal requires "minecraft:dweller" and "minecraft:navigation" to execute.
     * ```
     * behExploreOutskirts {
     *     priority = 9
     *     nextXz = 5
     *     nextY = 3
     *     minWaitTime = 3.0
     *     maxWaitTime = 10.0
     *     maxTravelTime = 60.0
     *     speedMultiplier = 0.6
     *     exploreDist = 6.0
     *     minPerimeter = 1.0
     *     minDistFromTarget = 2.5
     *     timerRatio = 2.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behExploreOutskirts(value: BehExploreOutskirts.() -> Unit) {
        behExploreOutskirtsData = (behExploreOutskirtsData ?: BehExploreOutskirts()).apply(value)
    }

    @SerializedName("minecraft:behavior.work")
    @Expose
    var behWorkData: BehWork? = null
        @MonsteraBuildSetter set

    /**
     * Allows the NPC to use the POI
     * ```
     * behWork {
     *     priority = 7
     *     activeTime = 250
     *     speedMultiplier = 0.5
     *     goalCooldown = 200
     *     soundDelayMin = 100
     *     soundDelayMax = 200
     *     canWorkInRain = false
     *     workInRainTolerance = 100
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behWork(value: BehWork.() -> Unit) {
        behWorkData = (behWorkData ?: BehWork()).apply(value)
    }

    @SerializedName("minecraft:behavior.work_composter")
    @Expose
    var behWorkComposterData: BehWorkComposter? = null
        @MonsteraBuildSetter set

    /**
     * Allows the NPC to use the composter POI to convert excess seeds into bone meal.
     * ```
     * behWorkComposter {
     *     priority = 9
     *     activeTime = 250
     *     speedMultiplier = 0.5
     *     goalCooldown = 200
     *     canWorkInRain = false
     *     workInRainTolerance = 100
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behWorkComposter(value: BehWorkComposter.() -> Unit) {
        behWorkComposterData = (behWorkComposterData ?: BehWorkComposter()).apply(value)
    }

    @SerializedName("minecraft:behavior.mingle")
    @Expose
    var behMingleData: BehMingle? = null
        @MonsteraBuildSetter set

    /**
     * Allows an entity to go to the village bell and mingle with other entities
     * ```
     * behMingle {
     *     priority = 7
     *     speedMultiplier = 0.5
     *     duration = 30
     *     cooldownTime = 10
     *     minglePartnerType = minecraft:villager_v2
     *     mingleDistance = 2.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behMingle(value: BehMingle.() -> Unit) {
        behMingleData = (behMingleData ?: BehMingle()).apply(value)
    }

    @SerializedName("minecraft:behavior.sleep")
    @Expose
    var behSleepData: BehSleep? = null
        @MonsteraBuildSetter set

    /**
     * Allows mobs that own a bed to in a village to move to and sleep in it.
     * ```
     * behSleep {
     *     priority = 3
     *     goalRadius = 1.5
     *     speedMultiplier = 0.6
     *     sleepColliderHeight = 0.3
     *     sleepColliderWidth = 1.0
     *     sleepYOffset = 0.6
     *     timeoutCooldown = 10.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behSleep(value: BehSleep.() -> Unit) {
        behSleepData = (behSleepData ?: BehSleep()).apply(value)
    }

    @SerializedName("minecraft:behavior.fertilize_farm_block")
    @Expose
    var behFertilizeFarmBlockData: BehFertilizeFarmBlock? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to search within an area for a growable crop block. If found, the mob will use any available fertilizer in their inventory on the crop. This goal will not execute if the mob does not have a fertilizer item in its inventory.
     * ```
     * behFertilizeFarmBlock {
     *     priority = 8
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behFertilizeFarmBlock(value: BehFertilizeFarmBlock.() -> Unit) {
        behFertilizeFarmBlockData = (behFertilizeFarmBlockData ?: BehFertilizeFarmBlock()).apply(value)
    }

    @SerializedName("minecraft:behavior.trade_interest")
    @Expose
    var behTradeInterestData: BehTradeInterest? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to look at a player that is holding a tradable item.
     * ```
     * behTradeInterest {
     *     priority = 5
     *     withinRadius = 6.0
     *     interestTime = 45.0
     *     removeItemTime = 1.0
     *     carriedItemSwitchTime = 2.0
     *     cooldown = 2.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behTradeInterest(value: BehTradeInterest.() -> Unit) {
        behTradeInterestData = (behTradeInterestData ?: BehTradeInterest()).apply(value)
    }

    @SerializedName("minecraft:economy_trade_table")
    @Expose
    var economyTradeTableData: EconomyTradeTable? = null
        @MonsteraBuildSetter set

    /**
     * Defines this entity's ability to trade with players.
     * ```
     * economyTradeTable {
     *     displayName = entity.villager.farmer
     *     table = trading/economy_trades/farmer_trades.json
     *     newScreen = true
     *     persistTrades = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun economyTradeTable(value: EconomyTradeTable.() -> Unit) {
        economyTradeTableData = (economyTradeTableData ?: EconomyTradeTable()).apply(value)
    }

    @SerializedName("minecraft:skin_id")
    @Expose
    var skinIdData: ComponentValue? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    var skinId: Number? = null
        set(value) {
            skinIdData = ComponentValue().also { it.value = value }
            field = value
        }

    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun skinId(value: ComponentValue.() -> Unit) {
        skinIdData = (skinIdData ?: ComponentValue()).apply(value)
    }

    @SerializedName("minecraft:behavior.drink_potion")
    @Expose
    var behDrinkPotionData: BehDrinkPotion? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to drink potions based on specified environment conditions.
     * ```
     * behDrinkPotion {
     *     priority = 1
     *     speedModifier = -0.2
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behDrinkPotion(value: BehDrinkPotion.() -> Unit) {
        behDrinkPotionData = (behDrinkPotionData ?: BehDrinkPotion()).apply(value)
    }

    @SerializedName("minecraft:behavior.drink_milk")
    @Expose
    var behDrinkMilkData: BehDrinkMilk? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to drink milk based on specified environment conditions.
     * ```
     * behDrinkMilk {
     *     priority = 5
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behDrinkMilk(value: BehDrinkMilk.() -> Unit) {
        behDrinkMilkData = (behDrinkMilkData ?: BehDrinkMilk()).apply(value)
    }

    @SerializedName("minecraft:managed_wandering_trader")
    @Expose
    var managedWanderingTraderData: ManagedWanderingTrader? = null
        @MonsteraBuildSetter set

    /**
     * This component is used to implement part of the Wandering Trader behavior
     * ```
     * managedWanderingTrader {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun managedWanderingTrader(value: ManagedWanderingTrader.() -> Unit) {
        managedWanderingTraderData = (managedWanderingTraderData ?: ManagedWanderingTrader()).apply(value)
    }

    @SerializedName("minecraft:movement_sound_distance_offset")
    @Expose
    var movementSoundDistanceOffsetData: ComponentValue? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    var movementSoundDistanceOffset: Number? = null
        set(value) {
            movementSoundDistanceOffsetData = ComponentValue().also { it.value = value }
            field = value
        }

    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun movementSoundDistanceOffset(value: ComponentValue.() -> Unit) {
        movementSoundDistanceOffsetData = (movementSoundDistanceOffsetData ?: ComponentValue()).apply(value)
    }

    @SerializedName("minecraft:vibration_damper")
    @Expose
    var vibrationDamperData: VibrationDamper? = null
        @MonsteraBuildSetter set

    /**
     * Vibrations emitted by this entity will be ignored.
     * ```
     * vibrationDamper {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun vibrationDamper(value: VibrationDamper.() -> Unit) {
        vibrationDamperData = (vibrationDamperData ?: VibrationDamper()).apply(value)
    }

    @SerializedName("minecraft:suspect_tracking")
    @Expose
    var suspectTrackingData: SuspectTracking? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to remember suspicious locations
     * ```
     * suspectTracking {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun suspectTracking(value: SuspectTracking.() -> Unit) {
        suspectTrackingData = (suspectTrackingData ?: SuspectTracking()).apply(value)
    }

    @SerializedName("minecraft:anger_level")
    @Expose
    var angerLevelData: AngerLevel? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to track anger towards a set of nuisances
     * ```
     * angerLevel {
     *     maxAnger = 150
     *     angryThreshold = 80
     *     removeTargetsBelowAngryThreshold = true
     *     angryBoost = 20
     *     angerDecrementInterval = 1.0
     *     defaultAnnoyingness = 35
     *     defaultProjectileAnnoyingness = 10
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun angerLevel(value: AngerLevel.() -> Unit) {
        angerLevelData = (angerLevelData ?: AngerLevel()).apply(value)
    }

    @SerializedName("minecraft:heartbeat")
    @Expose
    var heartbeatData: Heartbeat? = null
        @MonsteraBuildSetter set

    /**
     * Defines the entity's heartbeat.
     * ```
     * heartbeat {
     *     interval = 2.0 - math.clamp(query.anger_level / 80 * 1.5, 0, 1.5)
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun heartbeat(value: Heartbeat.() -> Unit) {
        heartbeatData = (heartbeatData ?: Heartbeat()).apply(value)
    }

    @SerializedName("minecraft:behavior.dig")
    @Expose
    var behDigData: BehDig? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to dig into the ground before despawning.
     * ```
     * behDig {
     *     priority = 1
     *     duration = 5.5
     *     idleTime = 60.0
     *     vibrationIsDisturbance = true
     *     suspicionIsDisturbance = true
     *     digsInDaylight = false
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behDig(value: BehDig.() -> Unit) {
        behDigData = (behDigData ?: BehDig()).apply(value)
    }

    @SerializedName("minecraft:behavior.roar")
    @Expose
    var behRoarData: BehRoar? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to roar at another entity based on data in minecraft:anger_level. Once the anger threshold specified in minecraft:anger_level has been reached, this entity will roar for the specified amount of time, look at the other entity, apply anger boost towards it, and finally target it.
     * ```
     * behRoar {
     *     priority = 2
     *     duration = 4.2
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behRoar(value: BehRoar.() -> Unit) {
        behRoarData = (behRoarData ?: BehRoar()).apply(value)
    }

    @SerializedName("minecraft:behavior.sonic_boom")
    @Expose
    var behSonicBoomData: BehSonicBoom? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to perform a 'sonic boom' ranged attack
     * ```
     * behSonicBoom {
     *     priority = 3
     *     duration = 3.0
     *     speedMultiplier = 1.2
     *     attackDamage = 10
     *     attackRangeHorizontal = 15
     *     attackRangeVertical = 20
     *     attackCooldown = 2
     *     knockbackVerticalStrength = 0.5
     *     knockbackHorizontalStrength = 2.5
     *     knockbackHeightCap = 0.5
     *     durationUntilAttackSound = 1.7
     *     chargeSound = sonic_charge
     *     attackSound = sonic_boom
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behSonicBoom(value: BehSonicBoom.() -> Unit) {
        behSonicBoomData = (behSonicBoomData ?: BehSonicBoom()).apply(value)
    }

    @SerializedName("minecraft:behavior.investigate_suspicious_location")
    @Expose
    var behInvestigateSuspiciousLocationData: BehInvestigateSuspiciousLocation? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to move towards a "suspicious" position based on data gathered in minecraft:suspect_tracking
     * ```
     * behInvestigateSuspiciousLocation {
     *     priority = 5
     *     speedMultiplier = 0.7
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behInvestigateSuspiciousLocation(value: BehInvestigateSuspiciousLocation.() -> Unit) {
        behInvestigateSuspiciousLocationData =
            (behInvestigateSuspiciousLocationData ?: BehInvestigateSuspiciousLocation()).apply(value)
    }

    @SerializedName("minecraft:behavior.sniff")
    @Expose
    var behSniffData: BehSniff? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to detect the nearest player within "sniffing_radius" and update its "minecraft:suspect_tracking" component state
     * ```
     * behSniff {
     *     priority = 6
     *     duration = 4.16
     *     sniffingRadius = 24.0
     *     suspicionRadiusHorizontal = 6.0
     *     suspicionRadiusVertical = 20.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behSniff(value: BehSniff.() -> Unit) {
        behSniffData = (behSniffData ?: BehSniff()).apply(value)
    }

    @SerializedName("minecraft:behavior.emerge")
    @Expose
    var behEmergeData: BehEmerge? = null
        @MonsteraBuildSetter set

    /**
     * Allows this entity to emerge from the ground
     * ```
     * behEmerge {
     *     duration = 7.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behEmerge(value: BehEmerge.() -> Unit) {
        behEmergeData = (behEmergeData ?: BehEmerge()).apply(value)
    }

    @SerializedName("minecraft:behavior.wither_random_attack_pos_goal")
    @Expose
    var behWitherRandomAttackPosGoalData: BehWitherRandomAttackPosGoal? = null
        @MonsteraBuildSetter set

    /**
     * Allows the wither to launch random attacks. Can only be used by the Wither Boss.
     * ```
     * behWitherRandomAttackPosGoal {
     *     priority = 3
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behWitherRandomAttackPosGoal(value: BehWitherRandomAttackPosGoal.() -> Unit) {
        behWitherRandomAttackPosGoalData =
            (behWitherRandomAttackPosGoalData ?: BehWitherRandomAttackPosGoal()).apply(value)
    }

    @SerializedName("minecraft:behavior.wither_target_highest_damage")
    @Expose
    var behWitherTargetHighestDamageData: BehWitherTargetHighestDamage? = null
        @MonsteraBuildSetter set

    /**
     * Allows the wither to focus its attacks on whichever mob has dealt the most damage to it.
     * ```
     * behWitherTargetHighestDamage {
     *     priority = 1
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behWitherTargetHighestDamage(value: BehWitherTargetHighestDamage.() -> Unit) {
        behWitherTargetHighestDamageData =
            (behWitherTargetHighestDamageData ?: BehWitherTargetHighestDamage()).apply(value)
    }

    @SerializedName("minecraft:behavior.look_at_target")
    @Expose
    var behLookAtTargetData: BehLookAtTarget? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to look at the entity they are targetting.
     * ```
     * behLookAtTarget {
     *     priority = 5
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behLookAtTarget(value: BehLookAtTarget.() -> Unit) {
        behLookAtTargetData = (behLookAtTargetData ?: BehLookAtTarget()).apply(value)
    }

    @SerializedName("minecraft:behavior.beg")
    @Expose
    var behBegData: BehBeg? = null
        @MonsteraBuildSetter set

    /**
     * Allows this mob to look at and follow the player that holds food they like.
     * ```
     * behBeg {
     *     priority = 9
     *     lookDistance = 8
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behBeg(value: BehBeg.() -> Unit) {
        behBegData = (behBegData ?: BehBeg()).apply(value)
    }

    @SerializedName("minecraft:behavior.owner_hurt_by_target")
    @Expose
    var behOwnerHurtByTargetData: BehOwnerHurtByTarget? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to target another mob that hurts their owner.
     * ```
     * behOwnerHurtByTarget {
     *     priority = 1
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behOwnerHurtByTarget(value: BehOwnerHurtByTarget.() -> Unit) {
        behOwnerHurtByTargetData = (behOwnerHurtByTargetData ?: BehOwnerHurtByTarget()).apply(value)
    }

    @SerializedName("minecraft:behavior.owner_hurt_target")
    @Expose
    var behOwnerHurtTargetData: BehOwnerHurtTarget? = null
        @MonsteraBuildSetter set

    /**
     * Allows the mob to target a mob that is hurt by their owner.
     * ```
     * behOwnerHurtTarget {
     *     priority = 2
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @VanillaComponentMarker
    fun behOwnerHurtTarget(value: BehOwnerHurtTarget.() -> Unit) {
        behOwnerHurtTargetData = (behOwnerHurtTargetData ?: BehOwnerHurtTarget()).apply(value)
    }

    @DslMarker
    annotation class MonsteraComponentMarker

    @DslMarker
    annotation class VanillaComponentMarker

    /**
     * contains all components a mob needs for walking
     *
     * - physics
     * - navigation
     * - movement
     * - jump
     */
    @MonsteraComponentMarker
    fun walkMovement(speed: Number, navData: NavigationWalk.() -> Unit) {
        navigationWalk(navData)
        movement = speed
        movementBasic { }
        jumpStatic { }
        physics { }
    }

    /**
     * contains the movement type for the ghast
     *
     * - physics
     * - navigation
     * - movement
     * - jump
     * - float
     * - fly
     */
    @MonsteraComponentMarker
    fun ghastMovement(speed: Number, navData: NavigationFloat.() -> Unit) {
        behFloatWander { }
        navigationFloat(navData)
        movement = speed
        canFly {  }
        physics { }
        jumpStatic { }
    }

    /**
     * contains components for a simple ranged attack
     *
     * @param ammo the item or projectile that should be shot
     */
    @MonsteraComponentMarker
    fun rangedAttack(
        ammo: String,
        priority: Int = 2,
        radius: Number = 32,
        attackInterval: Number = 5,
        entityTypes: BehEntityTypes.() -> Unit
    ) {
        behNearestAttackableTarget {
            this.priority = priority
            mustSee = true
            mustReach = false

            entityTypes(entityTypes)
        }

        shooter {
            def = ammo
        }
        behRangedAttack {
            this.priority = priority + 1
            attackRadius = radius
            attackIntervalMin = attackInterval
        }
    }
}