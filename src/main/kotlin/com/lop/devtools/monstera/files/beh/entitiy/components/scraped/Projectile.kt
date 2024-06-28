package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class Projectile : MonsteraRawFile() {
    @SerializedName("on_hit")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var onHitData: OnHit? = null
        @MonsteraBuildSetter set

    /**
     * Projectile's behavior on hit
     *
     * ```
     * onHit {
     *      stickInGround {
     *                 shakeTime = 2
     *             }
     *             arrowEffect {
     *                 applyEffectToBlockingTargets = true
     *             }
     *             definitionEvent {
     *                 affectProjectile = true
     *                 affectShooter = true
     *                 affectTarget = true
     *                 affectSplashArea = true
     *                 splashArea = 4
     *                 eventTrigger {
     *                     event = ""
     *                     target = "other"
     *                     filters {
     *                         distanceToNearestPlayer(subject = Subject.SELF, value = 2f)
     *                     }
     *                 }
     *             }
     *             teleportOwner = true
     *             removeOnHit = true
     *             catchFire = true
     *             douseFire = true
     *      }
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onHit(value: OnHit.() -> Unit) {
        onHitData = (onHitData ?: OnHit()).apply(value)
    }

    /**
     * Entity Definitions defined here can't be hurt by the projectile
     */
    @SerializedName("filter")
    @Expose
    var filter: String? = null

    /**
     * If true, whether the projectile causes fire is affected by the mob griefing game rule
     */
    @SerializedName("fire_affected_by_griefing")
    @Expose
    var fireAffectedByGriefing: Boolean? = null

    /**
     *	The sound that plays when the projectile hits an entity
     */
    @SerializedName("hit_sound")
    @Expose
    var hitSound: String? = null

    /**
     * 	Determines the velocity of the projectile
     */
    @SerializedName("power")
    @Expose
    var power: Number? = null

    /**
     * The gravity applied to this entity when thrown. The higher the value, the faster the entity falls
     */
    @SerializedName("gravity")
    @Expose
    var gravity: Number? = null

    /**
     * 	The base accuracy. Accuracy is determined by the formula uncertaintyBase - difficultyLevel * uncertaintyMultiplier
     */
    @SerializedName("uncertainty_base")
    @Expose
    var uncertaintyBase: Number? = null

    /**
     * 	Determines how much difficulty affects accuracy. Accuracy is determined by the formula uncertaintyBase - difficultyLevel * uncertaintyMultiplier
     */
    @SerializedName("uncertainty_multiplier")
    @Expose
    var uncertaintyMultiplier: Number? = null

    @SerializedName("anchor")
    @Expose
    var anchor: Number? = null

    /**
     * 	If true, the projectile will bounce upon hit
     */
    @SerializedName("should_bounce")
    @Expose
    var shouldBounce: Boolean? = null

    @SerializedName("offset")
    @Expose
    var offsetData: MutableList<Number>? = null

    /**
     * The offset from the entity's anchor where the projectile will spawn
     */
    fun offset(vararg value: Number) {
        offsetData = (offsetData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    /**
     * 	Time in seconds that the entity hit will be on fire for
     */
    @SerializedName("on_fire_time")
    @Expose
    var onFireTime: Number? = null

    /**
     * Defines the effect the arrow will apply to the entity it hits
     */
    @SerializedName("potion_effect")
    @Expose
    var potionEffect: String? = null

    /**
     * 	If true, the projectile will be treated like a splash potion
     */
    @SerializedName("splash_potion")
    @Expose
    var splashPotion: Boolean? = null

    /**
     * Radius in blocks of the 'splash' effect
     */
    @SerializedName("splash_range")
    @Expose
    var splashRange: Number? = null

    /**
     * 	If true, liquid blocks will be treated as solid. Requires "Education Edition" toggle active
     */
    @SerializedName("hit_water")
    @Expose
    var hitWater: Boolean? = null

    /**
     * 	The fraction of the projectile's speed maintained every frame while traveling in air
     */
    @SerializedName("inertia")
    @Expose
    var inertia: Number? = null

    /**
     * 	If true, damage will be randomized based on damage and speed
     */
    @SerializedName("semi_random_diff_damage")
    @Expose
    var semiRandomDiffDamage: Boolean? = null

    /**
     * 	If true, this entity will be reflected back when hit
     */
    @SerializedName("reflect_on_hurt")
    @Expose
    var reflectOnHurt: Boolean? = null

    /**
     * 	Determines the angle at which the projectile is thrown
     */
    @SerializedName("angle_offset")
    @Expose
    var angleOffset: Number? = null

    /**
     * 	The fraction of the projectile's speed maintained every frame while traveling in water
     */
    @SerializedName("liquid_inertia")
    @Expose
    var liquidInertia: Number? = null

    /**
     * 	If true, the entity hit will be set on fire
     */
    @SerializedName("catch_fire")
    @Expose
    var catchFire: Boolean? = null

    /**
     * 	If true, this entity will be destroyed when hit
     */
    @SerializedName("destroyOnHurt")
    @Expose
    var destroyOnHurt: Boolean? = null

    /**
     * If true, the projectile will produce critical hit particles when it happens
     */
    @SerializedName("crit_particle_on_hurt")
    @Expose
    var critParticleOnHurt: Boolean? = null

    /**
     * Particle to use upon collision
     */
    @SerializedName("particle")
    @Expose
    var particle: String? = null

    /**
     * 	If true, the projectile homes in to the nearest. Does not work on 1.18.2 entity
     */
    @SerializedName("homing")
    @Expose
    var homing: Boolean? = null

    /**
     * 	The sound that plays when the projectile hits ground
     */
    @SerializedName("hit_ground_sound")
    @Expose
    var hitGroundSound: String? = null

    /**
     * 	If true, the entity hit will be struck by lightning
     */
    @SerializedName("lightning")
    @Expose
    var lightning: Boolean? = null

    @SerializedName("stop_on_hurt")
    @Expose
    var stopOnHurt: Boolean? = null

    /**
     * 	If true, the projectile can hit multiple entities per flight
     */
    @SerializedName("multiple_targets")
    @Expose
    var multipleTargets: Boolean? = null

    /**
     * 	The sound that plays when the projectile is shot
     */
    @SerializedName("shoot_sound")
    @Expose
    var shootSound: String? = null

    /**
     * If true, the projectile will be shot towards the target of the entity firing it
     */
    @SerializedName("shoot_target")
    @Expose
    var shootTarget: Boolean? = null

    /**
     * 	If true, the projectile will be treated as dangerous to the players
     */
    @SerializedName("is_dangerous")
    @Expose
    var isDangerous: Boolean? = null

    class OnHit : MonsteraRawFile() {
        @SerializedName("impact_damage")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var impactDamageData: ImpactDamage? = null
            @MonsteraBuildSetter set

        /**
         *
         * ```
         * impactDamage {
         *     knockback = true
         *     semiRandomDiffDamage = false
         *     destroyOnHit = true
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun impactDamage(value: ImpactDamage.() -> Unit) {
            impactDamageData = (impactDamageData ?: ImpactDamage()).apply(value)
        }

        @SerializedName("stick_in_ground")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var stickInGroundData: StickInGround? = null
            @MonsteraBuildSetter set

        /**
         *
         * ```
         * stickInGround {
         *     shakeTime = 0.35
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun stickInGround(value: StickInGround.() -> Unit) {
            stickInGroundData = (stickInGroundData ?: StickInGround()).apply(value)
        }

        @SerializedName("arrow_effect")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var arrowEffectData: ArrowEffect? = null
            @MonsteraBuildSetter set

        /**
         *
         * ```
         * arrowEffect {
         *     applyEffectToBlockingTargets = false
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun arrowEffect(value: ArrowEffect.() -> Unit) {
            arrowEffectData = (arrowEffectData ?: ArrowEffect()).apply(value)
        }

        /**
         * Teleports shooter to the hit location.
         */
        @SerializedName("teleport_owner")
        @Expose
        var teleportOwner: Boolean? = null

        /**
         * Removes the projectile when it hits something.
         */
        @SerializedName("remove_on_hit")
        @Expose
        var removeOnHit: Boolean? = null

        /**
         * Sets target on fire
         */
        @SerializedName("catch_fire")
        @Expose
        var catchFire: Boolean? = null

        @SerializedName("douse_fire")
        @Expose
        var douseFire: Boolean? = null

        @SerializedName("definition_event")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var definitionEventData: DefinitionEvent? = null
            @MonsteraBuildSetter set

        /**
         * ```
         * affectProjectile = true
         * affectShooter = true
         * affectTarget = true
         * affectSplashArea = true
         * splashArea = 4
         * eventTrigger { }
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun definitionEvent(data: DefinitionEvent.() -> Unit) {
            definitionEventData = (definitionEventData ?: DefinitionEvent()).apply(data)
        }
    }

    class DefinitionEvent : MonsteraRawFile() {

        /**
         * 	Event will be triggered for projectile entity
         */
        @SerializedName("affect_projectile")
        @Expose
        var affectProjectile: Boolean? = null

        /**
         * Event will be triggered for shooter entity
         */
        @SerializedName("affect_shooter")
        @Expose
        var affectShooter: Boolean? = null

        /**
         * Event will be triggered for hit entity
         */
        @SerializedName("affect_target")
        @Expose
        var affectTarget: Boolean? = null

        /**
         * 	Event will be triggered for all entities in an area
         */
        @SerializedName("affect_splash_area")
        @Expose
        var affectSplashArea: Boolean? = null

        /**
         * Area of entities
         */
        @SerializedName("splash_area")
        @Expose
        var splashArea: Number? = null

        @SerializedName("event_trigger")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var eventTriggerData: EventTrigger? = null
            @MonsteraBuildSetter set

        /**
         * ```
         * event = ""
         * target = "other"
         * filters { distanceToNearestPlayer() }
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun eventTrigger(data: EventTrigger.() -> Unit) {
            eventTriggerData = (eventTriggerData ?: EventTrigger()).apply(data)
        }
    }

    class EventTrigger: MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null

        @SerializedName("target")
        @Expose
        var target: String? = null

        @SerializedName("filters")
        @Expose
        var filters: BehEntityFilter? = null
            @MonsteraBuildSetter set

        @OptIn(MonsteraBuildSetter::class)
        fun filters(data: BehEntityFilter.() -> Unit) {
            filters = (filters ?: BehEntityFilter()).apply(data)
        }
    }

    class ImpactDamage : MonsteraRawFile() {
        @SerializedName("damage")
        @Expose
        var damageData: MutableList<Number>? = null

        /**
         * Damage dealt to entity on hit
         */
        fun damage(vararg value: Number) {
            damageData = (damageData ?: mutableListOf()).also { it.addAll(value.toList()) }
        }

        @SerializedName("knockback")
        @Expose
        var knockback: Boolean? = null

        /**
         * 	If true, damage will be randomized based on damage and speed
         */
        @SerializedName("semi_random_diff_damage")
        @Expose
        var semiRandomDiffDamage: Boolean? = null

        @SerializedName("destroy_on_hit")
        @Expose
        var destroyOnHit: Boolean? = null

        @SerializedName("max_critical_damage")
        @Expose
        var maxCriticalDamage: Number? = null

        @SerializedName("min_critical_damage")
        @Expose
        var minCriticalDamage: Number? = null

        @SerializedName("power_multiplier")
        @Expose
        var powerMultiplier: Number? = null

        @SerializedName("channeling")
        @Expose
        var channeling: Boolean? = null

        @SerializedName("set_last_hurt_requires_damage")
        @Expose
        var setLastHurtRequiresDamage: Boolean? = null

        @SerializedName("destroy_on_hit_requires_damage")
        @Expose
        var destroyOnHitRequiresDamage: Boolean? = null

        /**
         * Entity to affect. Much more primitive than filters used elsewhere, as it cannot "test" for anything other than an identifier
         */
        @SerializedName("filter")
        @Expose
        var filter: String? = null
    }

    class StickInGround : MonsteraRawFile() {
        @SerializedName("shake_time")
        @Expose
        var shakeTime: Number? = null
    }

    class ArrowEffect : MonsteraRawFile() {
        @SerializedName("apply_effect_to_blocking_targets")
        @Expose
        var applyEffectToBlockingTargets: Boolean? = null
    }
}
