package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class Projectile {
    @SerializedName("on_hit")
    @Expose
    var onHitData: OnHit? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onHit {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onHit(value: OnHit.() -> Unit) {
        onHitData = (onHitData ?: OnHit()).apply(value)
    }

    @SerializedName("hit_sound")
    @Expose
    var hitSound: String? = null
        

    @SerializedName("power")
    @Expose
    var power: Number? = null
        

    @SerializedName("gravity")
    @Expose
    var gravity: Number? = null
        

    @SerializedName("uncertainty_base")
    @Expose
    var uncertaintyBase: Number? = null
        

    @SerializedName("uncertainty_multiplier")
    @Expose
    var uncertaintyMultiplier: Number? = null
        

    @SerializedName("anchor")
    @Expose
    var anchor: Number? = null
        

    @SerializedName("should_bounce")
    @Expose
    var shouldBounce: Boolean? = null
        

    @SerializedName("offset")
    @Expose
    var offsetData: MutableList<Number>? = null
        

    fun offset(vararg value: Number) {
        offsetData = (offsetData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("inertia")
    @Expose
    var inertia: Number? = null
        

    @SerializedName("semi_random_diff_damage")
    @Expose
    var semiRandomDiffDamage: Boolean? = null
        

    @SerializedName("reflect_on_hurt")
    @Expose
    var reflectOnHurt: Boolean? = null
        

    @SerializedName("angle_offset")
    @Expose
    var angleOffset: Number? = null
        

    @SerializedName("liquid_inertia")
    @Expose
    var liquidInertia: Number? = null
        

    @SerializedName("catch_fire")
    @Expose
    var catchFire: Boolean? = null
        

    @SerializedName("destroyOnHurt")
    @Expose
    var destroyOnHurt: Boolean? = null
        

    @SerializedName("crit_particle_on_hurt")
    @Expose
    var critParticleOnHurt: Boolean? = null
        

    @SerializedName("homing")
    @Expose
    var homing: Boolean? = null
        

    @SerializedName("hit_ground_sound")
    @Expose
    var hitGroundSound: String? = null
        

    @SerializedName("stop_on_hurt")
    @Expose
    var stopOnHurt: Boolean? = null
        

    @SerializedName("multiple_targets")
    @Expose
    var multipleTargets: Boolean? = null
        

    @SerializedName("shoot_sound")
    @Expose
    var shootSound: String? = null
        

    @SerializedName("shoot_target")
    @Expose
    var shootTarget: Boolean? = null
        

    @SerializedName("is_dangerous")
    @Expose
    var isDangerous: Boolean? = null
        

    class OnHit {
        @SerializedName("impact_damage")
        @Expose
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
    }

    class ImpactDamage {
        @SerializedName("damage")
        @Expose
        var damageData: MutableList<Number>? = null
            

        fun damage(vararg value: Number) {
            damageData = (damageData ?: mutableListOf()).also { it.addAll(value.toList()) }
        }

        @SerializedName("knockback")
        @Expose
        var knockback: Boolean? = null
            

        @SerializedName("semi_random_diff_damage")
        @Expose
        var semiRandomDiffDamage: Boolean? = null
            

        @SerializedName("destroy_on_hit")
        @Expose
        var destroyOnHit: Boolean? = null
            
    }

    class StickInGround {

        @SerializedName("shake_time")
        @Expose
        var shakeTime: Number? = null
            
    }

    class ArrowEffect {

        @SerializedName("apply_effect_to_blocking_targets")
        @Expose
        var applyEffectToBlockingTargets: Boolean? = null
            
    }
}
