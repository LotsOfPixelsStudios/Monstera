package com.lop.devtools.monstera.addon.molang

import com.lop.devtools.monstera.addon.Addon

@Suppress("unused")
class Query(override var data: String) : Molang {
    companion object {

        /**
         * a query that is always true
         */
        val True = Query("1")

        /**
         * a query that is always false
         */
        val False = Query("0")

        /**
         * Returns the height of the block immediately above the highest solid block at the input (x,z position
         */
        val aboveTopSolid = Query("query.above_top_solid")

        /**
         * Returns the number of actors rendered in the last frame
         */
        val actorCount = Query("query.actor_count")

        /**
         * Only id in an animation controller.
         * Returns 1.0 if all animations in the current animation controller state have played through at least once,
         * else it returns 0.0
         */
        val allAnimationsFinished = Query("query.all_animations_finished")

        /**
         * Returns if the item or block has all the tags specified
         */
        val allTags = Query("query.all_tags")

        /**
         * Returns the time in seconds since the current animation started, else 0.0 if not called within an animation
         */
        val aimTime = Query("query.anim_time")

        /**
         * Only id in an animation controller. Returns 1.0 if any animation in the current animation controller state has played through at least once, else it returns 0.0
         */
        val anyAnimationFinished = Query("query.any_animation_finished")

        /**
         * Returns if the item or block has any of the tags specified
         */
        val anyTag = Query("query.any_tag")

        /**
         * Returns 1.0 if all of the arguments are within 0.000000 of each other, else 0.0
         */
        val approxEq = Query("query.any_tag")

        /**
         * Takes the armor slot index as a parameter, and returns the color of the armor in the requested slot
         */
        val armorColorSlot = Query("query.armor_color_slot")

        /**
         * Takes the armor slot index as a parameter, and returns the armor material type in the requested armor slot
         */
        val armorMaterialSlot = Query("query.armor_material_slot")

        /**
         * Takes the armor slot index as a parameter, and returns the texture type of the requested slot
         */
        val armorTextureSlot = Query("query.armor_texture_slot")

        /**
         * Returns the time in *seconds* of the average frame time over the last 'n' frames.
         * If an argument is passed, it is assumed to be the number of frames in the past that you wish to query.
         * `query.average_frame_time` (or the equient `query.average_frame_time(0` will
         * return the frame time of the frame before the current one.
         * `query.average_frame_time(1` will return the average frame time of the previous two frames.
         * Currently, we store the history of the last 30 frames, although note that this may change in the future.
         * Asking for more frames will result in only sampling the number of frames stored.
         */
        val averageFrameTime = Query("query.average_frame_time")

        /**
         * Returns the block face for this (only id for certain triggers such as placing blocks, or interacting with block (Down=0.0, Up=1.0, North=2.0, South=3.0, West=4.0, East=5.0, Undefined=6.0.
         */
        val blockFace = Query("query.block_face")

        /**
         * Returns the ue of the associated Blocks Block State
         */
        val blockProperty = Query("query.block_property")

        /**
         * Returns 1.0 if the entity is blocking, else it returns 0.0
         */
        val blocking = Query("query.blocking")

        /**
         * Returns the body pitch rotation if called on an actor, else it returns 0.0
         */
        val bodyXRotation = Query("query.body_x_rotation")

        /**
         * Returns the body yaw rotation if called on an actor, else it returns 0.0
         */
        val bodyYRotation = Query("query.body_y_rotation")

        /**
         * Returns the axis aligned bounding box of a bone as a struct with members '.min', '.max', along with '.x', '.y', and '.z' ues for each.
         */
        val boneAABB = Query("query.bone_aabb")

        /**
         * Returns the initial (from the .geo pivot of a bone as a struct with members '.x', '.y', and '.z'.
         */
        val boneOrigin = Query("query.bone_origin")

        /**
         * Returns the initial (from the .geo rotation of a bone as a struct with members '.x', '.y', and '.z' in degrees.
         */
        val boneRotation = Query("query.bone_rotation")

        /**
         * Takes two distances
         * (any order and return a number from 0 to 1 based on the camera distance
         * between the two ranges clamped to that range.
         * For example, `query.camera_distance_range_lerp(10, 20` will return 0 for
         * any distance less than or equal to 10, 0.2 for a distance of 12, 0.5 for 15, and 1 for 20 or greater.
         * If you pass in (20, 10, a distance of 20 will return 0.0
         */
        val cameraDistanceRangeLerp = Query("query.camera_distance_range_lerp")

        /**
         * Returns the rotation of the camera. Requires one argument representing the rotation axis you would like (0==x, 1==y
         */
        val cameraRotation = Query("query.camera_rotation")

        /**
         * Returns 1.0 if the entity can climb, else it returns 0.0
         */
        val can_Climb = Query("query.can_climb")

        /**
         * Returns 1.0 if the entity can damage nearby mobs, else it returns 0.0
         */
        val canDamageNearbyMobs = Query("query.can_damage_nearby_mobs")

        /**
         * Returns 1.0 if the entity can fly, else it returns 0.0
         */
        val canFly = Query("query.can_fly")

        /**
         * Returns 1.0 if the entity can power jump, else it returns 0.0
         */
        val canPowerJump = Query("query.can_power_jump")

        /**
         * Returns 1.0 if the entity can swim, else it returns 0.0
         */
        val canSwim = Query("query.can_swim")

        /**
         * Returns 1.0 if the entity can walk, else it returns 0.0
         */
        val canWalk = Query("query.can_walk")

        /**
         * returns ue between 0.0 and 1.0 with 0.0 meaning cape is fully down and 1.0 is cape is fully up
         */
        val capeFlapAmount = Query("query.cape_flap_amount")

        /**
         * DEPRECATED (please use query.block_face instead Returns the block face for this (only id for on_placed_by_player trigger (Down=0.0, Up=1.0, North=2.0, South=3.0, West=4.0, East=5.0, Undefined=6.0.
         */
        @Deprecated("", ReplaceWith("Query.blockFace"))
        val cardinalBlockFacePlacedOn = Query("query.cardinal_block_face_placed_on")

        /**
         * DEPRECATED (please use query.block_face instead Returns the block face for this (only id for on_placed_by_player trigger (Down=0.0, Up=1.0, North=2.0, South=3.0, West=4.0, East=5.0, Undefined=6.0.
         */
        val cardinalFacing = Query("query.cardinal_facing")

        /**
         * Returns the current facing of the player ignoring up/down part of the direction
         * (North=2.0, South=3.0, West=4.0, East=5.0, Undefined=6.0.
         */
        val cardinalFacing2D = Query("query.cardinal_facing_2d")

        /**
         * Returns the current facing of the player
         * (Down=0.0, Up=1.0, North=2.0, South=3.0, West=4.0, East=5.0, Undefined=6.0.
         */
        val cardinalPlayerFacing = Query("query.cardinal_player_facing")

        /**
         * Combines any id entity references from all arguments into a single array.
         * Note that order is not preserved, and duplicates and inid ues are removed.
         */
        val combineEntities = Query("query.combine_entities")

        /**
         * Counts the number of things passed to it
         * (arrays are counted as the number of elements they contain; non-arrays count as 1.
         */
        val count = Query("query.count")

        /**
         * Returns the squish ue for the current entity, or 0.0 if this doesn't make sense
         */
        val currentSquishue = Query("query.current_squish_ue")

        /**
         * Returns the day of the current level.
         */
        val day = Query("query.day")

        /**
         * Returns the elapsed ticks since the mob started dying.
         */
        val deathTicks = Query("query.death_ticks")

        /**
         * debug log a ue
         */
        val debugOutput = Query("query.debug_output")

        /**
         * Returns the time in seconds since the previous frame
         */
        val deltaTime = Query("query.delta_time")

        /**
         * Returns the distance of the root of this actor or particle emitter from the camera
         */
        val distanceFromCamera = Query("query.distance_from_camera")

        /**
         * Returns the total number of active emitters of the callee's particle effect type
         */
        val effectEmitterCount = Query("query.effect_emitter_count")

        /**
         * Returns the total number of active particles of the callee's particle effect type
         */
        val effectParticleCount = Query("query.effect_particle_count")

        /**
         * returns the equipment count for an actor
         */
        val equipmentCount = Query("query.equipment_count")

        /**
         * takes a slot name followed by any tag you want to check for in the form of 'tag_name' and returns 1 if all the tags are on that equipped item, 0 otherwise
         */
        val equippedItemAllTags = Query("query.equipped_item_all_tags")

        /**
         * takes a slot name followed by any tag you want to check for in the form of 'tag_name' and returns 0 if none of the tags are on that equipped item or 1 if at least 1 tag exists
         */
        val equippedItemAnyTags = Query("query.equipped_item_any_tag")

        /**
         * Takes the desired hand slot as a parameter (0 or 'main_hand' for main hand, 1 or 'off_hand' for off hand, and returns whether the item is an attachable or not.
         */
        val equippedItemIsAttachable = Query("query.equipped_item_is_attachable")

        /**
         * returns the X eye rotation of the entity if it makes sense, else it returns 0.0
         */
        val eyeTargetXRotation = Query("query.eye_target_x_rotation")

        /**
         * returns the Y eye rotation of the entity if it makes sense, else it returns 0.0
         */
        val eyeTargetYRotation = Query("query.eye_target_y_rotation")

        val facingTargetToRangeAttack = Query("query.facing_target_to_range_attack")

        /**
         * Returns the ratio (from 0 to 1 of how much between AI ticks this frame is being rendered
         */
        val frameAlpha = Query("query.frame_alpha")

        /**
         * Returns the integer id of an actor by its Query name, 1, 1
         */
        val getActorInfoId = Query("query.get_actor_info_id")

        /**
         * Returns the current texture of the item
         */
        val getAnimationFrame = Query("query.get_animation_frame")

        /**
         * Gets specified axis of the specified bone orientation pivot
         */
        val getDefaultBonePivot = Query("query.get_default_bone_pivot")

        /**
         * takes one optional hand slot as a parameter
         * (0 or 'main_hand' for main hand, 1 or 'off_hand' for offhand,
         * and a second parameter (0=default if you would like the equipped item or any non-zero number for the
         * currently rendered item, and returns the name of the item in the requested slot
         * (defaulting to the main hand if no parameter is supplied if there is one, otherwise returns ''.
         */
        val getEquippedItemName = Query("query.get_equipped_item_name")

        /**
         * Returns the root actor identifier
         */
        val getLocatorOffset = Query("query.get_locator_offset")

        /**
         * get the name of the mob if there is one, otherwise return ''
         */
        val getName = Query("query.get_name")

        /**
         * Returns the root actor identifier
         */
        val getRootLocatorOffset = Query("query.get_root_locator_offset")

        /**
         * Returns the ground speed of the entity in metres/second
         */
        val groundSpeed = Query("query.ground_speed")

        /**
         * Returns 1 if the entity has any of the specified families, else 0
         */
        val hasAnyFamily = Query("query.has_any_family")

        /**
         * Takes the armor slot index as a parameter, and returns 1.0 if the entity has armor in the requested slot, else it returns 0.0
         */
        val hasArmorSlot = Query("query.has_armor_slot")

        /**
         * Returns whether or not a Block Placement Target has a specific biome tag
         */
        val hasBiomeTag = Query("query.has_biome_tag")

        /**
         * Returns 1.0 if the associated block has the given block property or 0.0 if not
         */
        val hasBlockProperty = Query("query.has_block_property")

        /**
         * Returns 1.0 if the player has a cape, else it returns 0.0
         */
        val hasCape = Query("query.has_cape")

        /**
         * Returns 1.0 if the entity has collisions enabled, else it returns 0.0
         */
        val hasCollision = Query("query.has_collision")

        /**
         * Returns 1.0 if the entity is affected by gravity, else it returns 0.0
         */
        val hasGravity = Query("query.has_gravity")

        /**
         * Returns true if the entity has an owner ID else it returns false
         */
        val hasOwner = Query("query.has_owner")

        /**
         * Returns 1.0 if the entity has a rider, else it returns 0.0
         */
        val hasRider = Query("query.has_rider")

        /**
         * Returns 1.0 if the entity has a target, else it returns 0.0
         */
        val hasTarget = Query("query.has_target")

        /**
         * returns the roll angle of the head of the entity if it makes sense, else it returns 0.0
         */
        val headRollAngle = Query("query.head_roll_angle")

        /**
         * Takes one argument as a parameter. Returns the nth head x rotation of the entity if it makes sense, else it returns 0.0
         */
        @Deprecated("use fun with parameter", ReplaceWith("headXRotation(0)"))
        val headXRotation = Query("query.head_x_rotation")

        /**
         * Takes one argument as a parameter. Returns the nth head x rotation of the entity if it makes sense, else it returns 0.0
         */
        fun headXRotation(x: Number) = Query("query.head_x_rotation($x)")

        /**
         * Takes one argument as a parameter. Returns the nth head y rotation of the entity if it makes sense, else it returns 0.0
         */
        @Deprecated("use fun with parameter", ReplaceWith("headYRotation(0)"))
        val headYRotation = Query("query.head_y_rotation")

        /**
         * Takes one argument as a parameter. Returns the nth head y rotation of the entity if it makes sense, else it returns 0.0
         */
        fun headYRotation(x: Number) = Query("query.head_y_rotation($x)")

        /**
         * Returns the health of the entity, or 0.0 if it doesn't make sense to call on this entity.
         */
        val health = Query("query.health")

        /**
         * Queries Height Map
         */
        val heightmap = Query("heightmap")

        /**
         * returns the hurt direction for the actor, otherwise returns 0
         */
        val hurtDirection = Query("hurt_direction")

        /**
         * returns the hurt time for the actor, otherwise returns 0
         */
        val hurtTime = Query("hurt_time")

        /**
         * Returns the number of ticks of invulnerability the entity has left if it makes sense, else it returns 0.0
         */
        val invulnerableTicks = Query("query.invulnerable_ticks")

        /**
         * Returns 1.0 if the entity is admiring, else it returns 0.0
         */
        val isAdmiring = Query("query.is_admiring")

        /**
         * returns 1.0 if the entity is alive, and 0.0 if it's dead
         */
        val isAlive = Query("query.is_alive")

        /**
         * Returns 1.0 if the entity is angry, else it returns 0.0
         */
        val isAngry = Query("query.is_angry")

        /**
         * Returns 1.0 if the actor is attached to an entity, else it will return 0.0
         */
        val isAttachedToEntity = Query("query.is_attached_to_entity")

        /**
         * Returns 1.0 if the entity is fleeing from a block, else it returns 0.0
         */
        val isAvoidingBlock = Query("query.is_avoiding_block")

        /**
         * Returns 1.0 if the entity is fleeing from mobs, else it returns 0.0
         */
        val isAvoidingMobs = Query("query.is_avoiding_mobs")

        /**
         * Returns 1.0 if the entity is a baby, else it returns 0.0
         */
        val isBaby = Query("query.is_baby")

        /**
         * Returns 1.0 if the entity is breathing, else it returns 0.0
         */
        val isBreathing = Query("query.is_breathing")

        /**
         * Returns 1.0 if the entity has been bribed, else it returns 0.0
         */
        val isBribed = Query("query.is_bribed")

        /**
         * Returns 1.0 if the entity is carrying a block, else it returns 0.0
         */
        val isCarryingBlock = Query("query.is_carrying_block")

        /**
         * Returns 1.0 if the entity is casting, else it returns 0.0
         */
        val isCasting = Query("query.is_casting")

        /**
         * Returns 1.0 if the entity is celebrating, else it returns 0.0
         */
        val isCelebrating = Query("query.is_celebrating")

        /**
         * Returns 1.0 if the entity is celebrating, else it returns 0.0
         */
        val isCelebratingSpecial = Query("query.is_celebrating_special")

        /**
         * Returns 1.0 if the entity is charged, else it returns 0.0
         */
        val isCharged = Query("query.is_charged")

        /**
         * Returns 1.0 if the entity is charging, else it returns 0.0
         */
        val isCharging = Query("query.is_charging")

        /**
         * Returns 1.0 if the entity has chests attached to it, else it returns 0.0
         */
        val isChested = Query("query.is_chested")

        /**
         * Returns 1.0 if the entity is critical, else it returns 0.0
         */
        val isCritical = Query("query.is_critical")

        /**
         * Returns 1.0 if the entity is dancing, else it returns 0.0
         */
        val isDancing = Query("query.is_dancing")

        /**
         * returns 1.0 if the entity is attacking using the delayed attack, else it returns 0.0
         */
        val isDelayedAttacking = Query("query.is_delayed_attacking")

        /**
         * Returns 1.0 if the entity is eating, else it returns 0.0
         */
        val isEating = Query("query.is_eating")

        /**
         * Returns 1.0 if the entity is an elder version of it, else it returns 0.0
         */
        val isElder = Query("query.is_elder")

        /**
         * Returns 1.0 if the entity is emoting, else it returns 0.0
         */
        val isEmoting = Query("query.is_emoting")

        /**
         * Returns 1.0 if the entity is enchanted, else it returns 0.0
         */
        val isEnchanted = Query("query.is_enchanted")

        /**
         * Returns 1.0 if the entity is immune to fire, else it returns 0.0
         */
        val isFireImmune = Query("query.is_fire_immune")

        /**
         * Returns 1.0 if the entity is being rendered in first person mode, else it returns 0.0
         */
        val isFirstPerson = Query("query.is_first_person")

        /**
         * returns 1.0 if an entity is a ghost, else it returns 0.0
         */
        val isGhost = Query("query.is_ghost")

        /**
         * Returns 1.0 if the entity is gliding, else it returns 0.0
         */
        val isGliding = Query("query.is_gliding")

        /**
         * Returns 1.0 if the entity is grazing, or 0.0 if not
         */
        val isGrazing = Query("query.is_grazing")

        /**
         * Returns 1.0 if the entity is idling, else it returns 0.0
         */
        val isIdling = Query("query.is_idling")

        /**
         * Returns 1.0 if the entity is ignited, else it returns 0.0
         */
        val isIgnited = Query("query.is_ignited")

        /**
         * Returns 1.0 if the entity is an illager captain, else it returns 0.0
         */
        val isIllagerCaptain = Query("query.is_illager_captain")

        /**
         * Returns 1.0 if the entity is in contact with any water (water, rain, splash water bottle, else it returns 0.0
         */
        val isInContactWithWater = Query("query.is_in_contact_with_water")

        /**
         * Returns 1.0 if the entity is in love, else it returns 0.0
         */
        val isInLove = Query("query.is_in_love")

        /**
         * Returns 1.0 if the entity is rendered as part of the UI, else it returns 0.0
         */
        val isInUi = Query("query.is_in_ui")

        /**
         * Returns 1.0 if the entity is in water, else it returns 0.0
         */
        val isInWater = Query("query.is_in_water")

        /**
         * Returns 1.0 if the entity is in water or rain, else it returns 0.0
         */
        val isInWaterOrRain = Query("query.is_in_water_or_rain")

        /**
         * Returns 1.0 if the entity is interested, else it returns 0.0
         */
        val isInterested = Query("query.is_interested")

        /**
         * Returns 1.0 if the entity is invisible, else it returns 0.0
         */
        val is_Invisible = Query("query.is_invisible")

        /**
         * takes one optional hand slot as a parameter
         * (0 or 'main_hand' for main hand, 1 or 'off_hand' for offhand,
         * and returns 1.0 if there is an item in the requested slot
         * (defaulting to the main hand if no parameter is supplied, otherwise returns 0.0.
         */
        val isItemEquipped = Query("query.is_item_equipped")

        /**
         * Returns 1.0 if the entity is in water or rain, else it returns 0.0
         */
        val isJumping = Query("query.is_jumping")

        /**
         * Returns 1.0 if the entity is laying down, else it returns 0.0
         */
        val isLayingDown = Query("query.is_laying_down")

        /**
         * Returns 1.0 if the entity is laying an egg, else it returns 0.0
         */
        val isLayingEgg = Query("query.is_laying_egg")

        /**
         * Returns 1.0 if the entity is leashed to something, else it returns 0.0
         */
        val isLeashed = Query("query.is_leashed")

        /**
         * Returns 1.0 if the entity is levitating, else it returns 0.0
         */
        val isLevitating = Query("query.is_levitating")

        /**
         * Returns 1.0 if the entity is lingering, else it returns 0.0
         */
        val isLingering = Query("query.is_lingering")

        /**
         * Returns 1.0 if the entity is moving, else it returns 0.0
         */
        val isMoving = Query("query.is_moving")

        /**
         * returns 1.0 if the entity is on fire, else it returns 0.0
         */
        val isOnFire = Query("query.is_on_fire")

        /**
         * Returns 1.0 if the entity is on the ground, else it returns 0.0
         */
        val isOnGround = Query("query.is_on_ground")

        /**
         * returns 1.0 if this is called on an entity at a time when it is known if it is on screen, else it returns 0.0
         */
        val isOnScreen = Query("query.is_on_screen")

        /**
         * Returns 1.0 if the entity is on fire, else it returns 0.0
         */
        val isOnfire = Query("query.is_onfire")

        /**
         * Returns 1.0 if the entity is orphaned, else it returns 0.0
         */
        val isOrphaned = Query("query.is_orphaned")

        /**
         * Returns 1.0 if the player has a persona or permium skin, else it returns 0.0
         */
        val isPersonaOrPremiumSkin = Query("query.is_persona_or_premium_skin")

        /**
         * Returns 1.0 if the entity is playing dead, else it returns 0.0
         */
        val isPlayingDead = Query("query.is_playing_dead")

        /**
         * Returns 1.0 if the entity is powered, else it returns 0.0
         */
        val isPowered = Query("query.is_powered")

        /**
         * Returns 1.0 if the entity is pregnant, else it returns 0.0
         */
        val isPregnant = Query("query.is_pregnant")

        /**
         * Returns 1.0 if the entity is using a ram attack, else it returns 0.0
         */
        val isRamAttacking = Query("query.is_ram_attacking")

        /**
         * Returns 1.0 if the entity is resting, else it returns 0.0
         */
        val isResting = Query("query.is_resting")

        /**
         * Returns 1.0 if the entity is riding, else it returns 0.0
         */
        val isRiding = Query("query.is_riding")

        /**
         * returns 1.0 if the entity is currently roaring, else it returns 0.0
         */
        val isRoaring = Query("query.is_roaring")

        /**
         * Returns 1.0 if the entity is rolling, else it returns 0.0
         */
        val isRolling = Query("query.is_rolling")

        /**
         * Returns 1.0 if the entity has a saddle, else it returns 0.0
         */
        val isSaddled = Query("query.is_saddled")

        /**
         * Returns 1.0 if the entity is scared, else it returns 0.0
         */
        val isScared = Query("query.is_scared")

        /**
         * returns true if the player has selected an item in the inventory, else it returns 0.0
         */
        val isSelectedItem = Query("query.is_selected_item")

        /**
         * Returns 1.0 if the entity is casting, else it returns 0.0
         */
        val isShaking = Query("query.is_shaking")

        /**
         * returns 1.0 if the entity is shaking water off, else it returns 0.0
         */
        val isShakingWetness = Query("query.is_shaking_wetness")

        /**
         * Returns 1.0 if the entity is able to be sheared and is sheared, else it returns 0.0
         */
        val isSheared = Query("query.is_sheared")

        /**
         * Returns 1.0f if the entity has an active powered shield if it makes sense, else it returns 0.0
         */
        val isShieldPowered = Query("query.is_shield_powered")

        /**
         * Returns 1.0 if the entity is silent, else it returns 0.0
         */
        val isSilent = Query("query.is_silent")

        /**
         * Returns 1.0 if the entity is sitting, else it returns 0.0
         */
        val isSitting = Query("query.is_sitting")

        /**
         * Returns 1.0 if the entity is sleeping, else it returns 0.0
         */
        val isSleeping = Query("query.is_sleeping")

        /**
         * Returns 1.0 if the entity is sneaking, else it returns 0.0
         */
        val isSneaking = Query("query.is_sneaking")

        /**
         * Returns 1.0 if the entity is sneezing, else it returns 0.0
         */
        val isSneezing = Query("query.is_sneezing")

        /**
         * Returns 1.0 if the entity is sprinting, else it returns 0.0
         */
        val isSprinting = Query("query.is_sprinting")

        /**
         * Returns 1.0 if the entity is stackable, else it returns 0.0
         */
        val isStackable = Query("query.is_stackable")

        /**
         * Returns 1.0 if the entity is stalking, else it returns 0.0
         */
        val isStalking = Query("query.is_stalking")

        /**
         * Returns 1.0 if the entity is standing, else it returns 0.0
         */
        val isStanding = Query("query.is_standing")

        /**
         * returns 1.0 if the entity is currently stunned, else it returns 0.0
         */
        val isStunned = Query("query.is_stunned")

        /**
         * Returns 1.0 if the entity is swimming, else it returns 0.0
         */
        val isSwimming = Query("query.is_swimming")

        /**
         * Returns 1.0 if the entity is tamed, else it returns 0.0
         */
        val isTamed = Query("query.is_tamed")

        /**
         * Returns 1.0 if the entity is transforming, else it returns 0.0
         */
        val isTransforming = Query("query.is_transforming")

        /**
         * Returns 1.0 if the entity is using an item, else it returns 0.0
         */
        val isUsingItem = Query("query.is_using_item")

        /**
         * Returns 1.0 if the entity is climbing a wall, else it returns 0.0
         */
        val isWallClimbing = Query("query.is_wall_climbing")

        /**
         * Returns the amount of time an item has been in use in seconds up to the maximum duration, else 0.0 if it doesn't make sense
         */
        val itemInUseDuration = Query("query.item_in_use_duration")

        /**
         * takes one optional hand slot as a parameter
         * (0 or 'main_hand' for main hand, 1 or 'off_hand' for off hand, and returns 1.0 if the item is charged in
         * the requested slot
         * (defaulting to the main hand if no parameter is supplied, otherwise returns 0.0.
         */
        val itemIsCharged = Query("query.item_is_charged")

        /**
         * Returns the maximum amount of time the item can be used, else 0.0 if it doesn't make sense
         */
        val itemMaxUseDuration = Query("query.item_max_use_duration")

        /**
         * Returns the amount of time an item has left to use in seconds, else 0.0 if it doesn't make sense
         */
        val itemRemainingUseDuration = Query("query.item_remaining_use_duration")

        /**
         * query.item_slot_to_bone_name requires one parameter: the name of the equipment slot. This function returns the name of the bone this entity has mapped to that slot.
         */
        val itemSlotToBoneName = Query("query.item_slot_to_bone_name")

        /**
         * Returns the ratio between the previous and next key frames
         */
        val keyFrameLerpTime = Query("query.key_frame_lerp_time")

        /**
         * Returns the time in *seconds* of the last frame.
         * If an argument is passed, it is assumed to be the number of frames in the past that you wish to query.
         * `query.last_frame_time` (or the equient `query.last_frame_time(0` will return the frame time of the
         * frame before the current one. `query.last_frame_time(1` will return the frame time of two frames ago.
         * Currently we store the history of the last 30 frames, although note that this may change in the future.
         * Passing an index more than the available data will return the oldest frame stored.
         */
        val lastFrameTime = Query("query.last_frame_time")

        /**
         * Returns 1.0 if the entity was last hit by the player, else it returns 0.0.
         * If called by the client always returns 0.0
         */
        val lastHitByPlayer = Query("query.last_hit_by_player")

        /**
         * Returns the lie down amount for the entity
         */
        val lieAmount = Query("query.lie_amount")

        /**
         * returns the limited life span of an entity, or 0.0 if it lives forever
         */
        val lifeSpan = Query("query.life_span")

        /**
         * Returns the time in seconds since the current animation started, else 0.0 if not called within an animation
         */
        val lifeTime = Query("query.life_time")

        /**
         * Takes an array of distances and returns the zero - based index of which range the actor is in based on
         * distance from the camera.For example, `query.lod_index(10, 20, 30` will return 0, 1, or 2 based on whether
         * the mob is less than 10, 20, or 30 units away from the camera, or it will return 3 if it is greater than 30.
         */
        val lodIndex = Query("query.lod_index")

        /**
         * debug log a ue
         */
        val log = Query("query.log")

        /**
         * returns the use time maximum duration for the main hand item if it makes sense, else it returns 0.0
         */
        val mainHandItemMaxDuration = Query("query.main_hand_item_max_duration")

        /**
         * returns the use time for the main hand item.
         */
        val mainHandItemUseDuration = Query("query.main_hand_item_use_duration")

        /**
         * Returns the entity's mark variant
         */
        val markVariant = Query("query.mark_variant")

        /**
         * Returns the max durability an item can take
         */
        val maxDurability = Query("query.max_durability")

        /**
         * Returns the maximum health of the entity, or 0.0 if it doesn't make sense to call on this entity.
         */
        val maxHealth = Query("query.max_health")

        /**
         * Returns the maximum trade tier of the entity if it makes sense, else it returns 0.0
         */
        val maxTradeTier = Query("query.max_trade_tier")

        /**
         * Returns the time in *seconds* of the most expensive frame over the last 'n' frames.
         * If an argument is passed, it is assumed to be the number of frames in the past that you wish to query.
         * `query.maximum_frame_time` (or the equient `query.maximum_frame_time(0` will return the frame time of the
         * frame before the current one. `query.maximum_frame_time(1` will return the maximum frame time of the previous
         * two frames. Currently we store the history of the last 30 frames, although note that this may change in the
         * future. Asking for more frames will result in only sampling the number of frames stored.
         */
        val maximumFrameTime = Query("query.maximum_frame_time")

        /**
         * Returns the time in *seconds* of the least expensive frame over the last 'n' frames.
         * If an argument is passed, it is assumed to be the number of frames in the past that you wish to query.
         * `query.minimum_frame_time` (or the equient `query.minimum_frame_time(0` will return the frame time of the
         * frame before the current one. `query.minimum_frame_time(1` will return the minimum frame time of the previous
         * two frames. Currently we store the history of the last 30 frames, although note that this may change in the
         * future. Asking for more frames will result in only sampling the number of frames stored.
         */
        val minimumFrameTime = Query("query.minimum_frame_time")

        /**
         * Returns the scale of the current entity
         */
        val modelScale = Query("query.model_scale")

        /**
         * Returns the total distance the entity has moved horizontally in metres (since the entity was last loaded,
         * not necessarily since it was originally created modified along the way by status flags such as is_baby
         * or on_fire
         */
        val modifiedDistanceMoved = Query("query.modified_distance_moved")

        /**
         * Returns the current walk speed of the entity modified by status flags such as is_baby or on_fire
         */
        val modifiedMoveSpeed = Query("query.modified_move_speed")

        /**
         * Returns the brightness of the moon (FULL_MOON=1.0, WANING_GIBBOUS=0.75, FIRST_QUARTER=0.5,
         * WANING_CRESCENT=0.25, NEW_MOON=0.0, WAXING_CRESCENT=0.25, LAST_QUARTER=0.5, WAXING_GIBBOUS=0.75.
         */
        val moonBrightness = Query("query.moon_brightness")

        /**
         * Returns the phase of the moon (FULL_MOON=0, WANING_GIBBOUS=1, FIRST_QUARTER=2, WANING_CRESCENT=3,
         * NEW_MOON=4, WAXING_CRESCENT=5, LAST_QUARTER=6, WAXING_GIBBOUS=7.
         */
        val moonPhase = Query("query.moon_phase")

        /**
         * returns the specified axis of the normalized position delta of the entity
         */
        val movementDirection = Query("query.movement_direction")

        /**
         * Queries Perlin Noise Map
         */
        val noise = Query("query.noise")

        /**
         * returns the time that the entity is on fire, else it returns 0.0
         */
        val onFireTime = Query("query.on_fire_time")

        /**
         * Formatted like: query.scoreboard('objective_name')
         */
        fun scoreboard(objective: String) = Query("(query.scoreboard('$objective'))")

        /**
         * Returns 1.0 if the entity is out of control, else it returns 0.0
         */
        val outOfControl = Query("query.out_of_control")

        /**
         * Do not use - this function is deprecated and will be removed
         */
        @Deprecated("wiki: don't use")
        val overlayAlpha = Query("query.overlay_alpha")

        /**
         * Returns the root actor identifier
         */
        val ownerIdentifier = Query("query.owner_identifier")

        /**
         * returns the players level if the actor is a player, otherwise returns 0
         */
        val playerLevel = Query("query.player_level")

        /**
         * Returns the absolute position of an actor. Takes one argument that represents the desired axis
         * (0 == x-axis, 1 == y-axis, 2 == z-axis.
         */
        val position = Query("query.position")

        /**
         * returns the position delta for an actor
         */
        val positionDelta = Query("query.position_delta")

        /**
         * Returns the previous squish ue for the current entity, or 0.0 if this doesn't make sense
         */
        val previousSquishue = Query("query.previous_squish_ue")

        /**
         * Returns how much durability an item has remaining
         */
        val remainingDurability = Query("query.remaining_durability")

        /**
         * Returns the roll counter of the entity
         */
        val rollCounter = Query("query.roll_counter")

        /**
         * Returns the rotation required to aim at the camera. Requires one argument representing the rotation axis you
         * would like (0==x, 1==y
         */
        val rotationToCamera = Query("query.rotation_to_camera")

        /**
         * returns the shaking angle of the entity if it makes sense, else it returns 0.0
         */
        val shakeAngle = Query("query.shake_angle")

        /**
         * Returns the shake time of the entity.
         */
        val shakeTime = Query("query.shake_time")

        /**
         * Returns the how much the offhand shield should translate down when blocking and being hit.
         */
        val shieldBlockingBob = Query("query.shield_blocking_bob")

        /**
         * Returns 1.0 if we render the entity's bottom, else it returns 0.0
         */
        val showBottom = Query("query.show_bottom")

        /**
         * Returns the current sit amount of the entity
         */
        val sitAmount = Query("query.sit_amount")

        /**
         * Returns the entity's skin ID
         */
        val skinId = Query("query.skin_id")

        /**
         * returns the rotation of the bed the player is sleeping on.
         */
        val sleepRotation = Query("query.sleep_rotation")

        /**
         * Returns the sneeze counter of the entity
         */
        val sneezeCounter = Query("query.sneeze_counter")

        /**
         * Returns the blue colour channel of the current entity spell colour if it makes sense, else it returns 0.0
         */
        val spellcolorB = Query("query.spellcolor.b")

        /**
         * Returns the green colour channel of the current entity spell colour if it makes sense, else it returns 0.0
         */
        val spellcolorG = Query("query.spellcolor.g")

        /**
         * Returns the red colour channel of the current entity spell colour if it makes sense, else it returns 0.0
         */
        val spellcolorR = Query("query.spellcolor.r")
        val spellcolorA = Query("query.spellcolor.a")

        /**
         * Returns the scale of how standing up the entity is
         */
        val standingScale = Query("query.standing_scale")

        /**
         * returns the structural integrity for the actor, otherwise returns 0
         */
        val structuralIntegrity = Query("query.structural_integrity")

        /**
         * Returns how swollen the entity is
         */
        val swellAmount = Query("query.swell_amount")

        /**
         * Returns the swelling direction of the entity if it makes sense, else it returns 0.0
         */
        val swellingDir = Query("query.swelling_dir")

        /**
         * Returns the amount the current entity is swimming
         */
        val swimAmount = Query("query.swim_amount")

        /**
         * returns the angle of the tail of the entity if it makes sense, else it returns 0.0
         */
        val tailAngle = Query("query.tail_angle")

        /**
         * Returns the x rotation required to aim at the entity's current target if it has one, else it returns 0.0
         */
        val targetXRotation = Query("query.target_x_rotation")

        /**
         * Returns the y rotation required to aim at the entity's current target if it has one, else it returns 0.0
         */
        val targetYRotation = Query("query.target_y_rotation")

        /**
         * Returns the icon index of the experience orb
         */
        val textureFrameIndex = Query("query.texture_frame_index")

        /**
         * Returns the time of day (midnight=0.0, sunrise=0.25, noon=0.5, sunset=0.75 of the dimension the entity is in.
         */
        val timeOfDay = Query("query.time_of_day")

        /**
         * Returns the current time stamp of the level
         */
        val timeStamp = Query("query.time_stamp")

        /**
         * Returns the total number of active emitters in the world
         */
        val totalEmitterCount = Query("query.total_emitter_count")

        /**
         * Returns the total number of active particles in the world
         */
        val totalParticleCount = Query("query.total_particle_count")

        /**
         * Returns the trade tier of the entity if it makes sense, else it returns 0.0
         */
        val tradeTier = Query("query.trade_tier")

        /**
         * Returns how unhappy the entity is
         */
        val unhappyCounter = Query("query.unhappy_counter")

        /**
         * Returns the entity's variant index
         */
        val variant = Query("query.variant")

        /**
         * Returns the speed of the entity up or down in metres/second, where positive is up
         */
        val verticalSpeed = Query("query.vertical_speed")

        /**
         * Returns the walk distance of the entity.
         */
        val walkDistance = Query("query.walk_distance")

        /**
         * Returns the wing flap position of the entity, or 0.0 if this doesn't make sense
         */
        val wingFlapPosition = Query("query.wing_flap_position")

        /**
         * Returns the wing flap speed of the entity, or 0.0 if this doesn't make sense
         */
        val wingFlapSpeed = Query("query.wing_flap_speed")

        /**
         * Returns the entity's yaw speed
         */
        val yawSpeed = Query("query.yaw_speed")

        /**
         * takes the property name and returns 1 if the property was found and 0 if it was not initialized (yet)
         */
        fun hasProperty(name: String) = Query("(query.has_property('${idWithNameSpace(name)}'))")

        /**
         * this query returns the value of a given property
         */
        fun property(name: String) = Query("(query.property('${idWithNameSpace(name)}'))")

        fun angerLevel(subject: String) = Query("(query.anger_level($subject))")

        fun anyTag(vararg tags: String) = Query(
            "query.any_tag('${tags.joinToString("', '")}')"
        )

        fun allTags(vararg tags: String) = Query(
            "query.all_tags('${tags.joinToString("', '")}')"
        )

        fun equippedItemAnyTag(vararg tags: String) = Query(
            "query.equipped_item_all_tags('${tags.joinToString("', '")}')"
        )

        fun equippedItemAllTags(vararg tags: String) = Query(
            "query.equipped_item_any_tag('${tags.joinToString("', '")}')"
        )

        private fun idWithNameSpace(name: String): String {
            val props = Addon.active!!.config
            return if (name.startsWith(props.namespace)) name else "${props.namespace}:$name"
        }
    }

    override operator fun not(): Query {
        return Query("!" + this.data)
    }

    override operator fun times(data: Any): Math {
        return Math("(${this.data} * $data)")
    }

    override operator fun plus(data: Any): Math {
        return Math("(${this.data} + $data)")
    }

    override operator fun minus(data: Any): Math {
        return Math("(${this.data} - $data)")
    }

    override operator fun div(data: Any): Math {
        return Math("(${this.data} / $data)")
    }

    override fun toString(): String {
        return data
    }
}