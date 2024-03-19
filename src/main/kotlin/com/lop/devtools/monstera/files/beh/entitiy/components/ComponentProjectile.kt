package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject
import java.awt.Color

class ComponentProjectile {
    val general = mutableMapOf<String, Any>()

    var anchor: Int? = null
    var angleOffset: Float? = null
    var catchFire: Boolean? = null
    var critParticleOnHurt: Boolean? = null
    var destroyOnHurt: Boolean? = null
    var fireAffectedByGriefing: Boolean? = null
    var gravity: Float? = null
    var hitGroundSound: String? = null
    var hitSound: String? = null
    var homing: Boolean? = null
    var inertia: Float? = null
    var isDangerous: Boolean? = null
    var knockback: Boolean? = null
    var lightning: Boolean? = null
    var liquidInertia: Float? = null
    var multipleTargets: Boolean? = null
    var onFireTime: Float? = null
    var particle: String? = null
    var potionEffect: Int? = null
    var power: Float? = null
    var reflectOnHurt: Boolean? = null
    var semiRandomDiffDamage: Boolean? = null
    var shootSound: String? = null
    var shootTarget: Boolean? = null
    var shouldBounce: Boolean? = null
    var splashPotion: Boolean? = null
    var splashRange: Float? = null
    var uncertaintyBase: Float? = null
    var uncertaintyMultiplier: Float? = null

    /**
     * 0..1
     *
     * Entity Definitions defined here can't be hurt by the projectile
     */
    fun filter(settings: BehEntityFilter.() -> Unit) {
        general["filter"] = BehEntityFilter().apply(settings).getData()
    }

    /**
     * 0..1
     *
     * The offset from the entity's anchor where the projectile will spawn
     */
    fun offset(x: Float, y: Float, z: Float) {
        general["offset"] = arrayListOf(x, y, z)
    }

    /**
     * 0..1
     *
     * Projectile’s behavior on hit.
     */
    fun onHit(value: CompOnHit.() -> Unit) {
        general["on_hit"] = CompOnHit().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        anchor?.let { general["anchor"] = it }
        angleOffset?.let { general["angle_offset"] = it }
        catchFire?.let { general["catch_fire"] = it }
        critParticleOnHurt?.let { general["crit_particle_on_hurt"] = it }
        destroyOnHurt?.let { general["destroy_on_hurt"] = it }
        fireAffectedByGriefing?.let { general["fire_affected_by_griefing"] = it }
        gravity?.let { general["gravity"] = it }
        hitGroundSound?.let { general["hit_ground_sound"] = it }
        hitSound?.let { general["hit_sound"] = it }
        homing?.let { general["homing"] = it }
        inertia?.let { general["inertia"] = it }
        isDangerous?.let { general["is_dangerous"] = it }
        knockback?.let { general["knockback"] = it }
        lightning?.let { general["lightning"] = it }
        liquidInertia?.let { general["liquid_inertia"] = it }
        multipleTargets?.let { general["multiple_targets"] = it }
        onFireTime?.let { general["on_fire_time"] = it }
        particle?.let { general["particle"] = it }
        potionEffect?.let { general["potion_effect"] = it }
        power?.let { general["power"] = it }
        reflectOnHurt?.let { general["reflect_on_hurt"] = it }
        semiRandomDiffDamage?.let { general["semi_random_diff_damage"] = it }
        shootSound?.let { general["shoot_sound"] = it }
        shootTarget?.let { general["shoot_target"] = it }
        shouldBounce?.let { general["should_bounce"] = it }
        splashPotion?.let { general["splash_potion"] = it }
        splashRange?.let { general["splash_range"] = it }
        uncertaintyBase?.let { general["uncertainty_base"] = it }
        uncertaintyMultiplier?.let { general["uncertainty_multiplier"] = it }
        return general
    }
}

class CompOnHit {
    val general = mutableMapOf<String, Any>()

    fun impactDamage(value: ImpactDamage.() -> Unit) {
        general["impact_damage"] = ImpactDamage().apply(value).getData()
    }

    fun stickInGround(value: StickInGround.() -> Unit) {
        general["stick_in_ground"] = StickInGround().apply(value).getData()
    }

    fun spawnAoeCloud(value: SpawnAoeCloud.() -> Unit) {
        general["spawn_aoe_cloud"] = SpawnAoeCloud().apply(value).getData()
    }

    fun spawnChance(value: SpawnChance.() -> Unit) {
        general["spawn_chance"] = SpawnChance().apply(value).getData()
    }

    fun arrowEffect() {
        general["arrow_effect"] = mutableMapOf<Any, Any>()
    }

    fun teleportOwner() {
        general["teleport_owner"] = mutableMapOf<Any, Any>()
    }

    fun ignite() {
        general["ignite"] = mutableMapOf<Any, Any>()
    }

    fun removeOnHit() {
        general["remove_on_hit"] = mutableMapOf<Any, Any>()
    }

    fun douseFire() {
        general["douse_fire"] = mutableMapOf<Any, Any>()
    }

    fun definitionEvent(
        affect_projectile: Boolean = true,
        eventTriggerEvent: String? = null,
        eventTriggerTarget: Subject? = null
    ) {
        general.apply {
            val thisData = mutableMapOf<String, Any>(
                "affect_projectile" to affect_projectile
            )
            if (eventTriggerEvent != null || eventTriggerTarget != null) {
                val innerData = mutableMapOf<String, String>()
                if (eventTriggerEvent != null) {
                    innerData["event"] = eventTriggerEvent
                }
                if (eventTriggerTarget != null) {
                    innerData["target"] = eventTriggerTarget.toString().lowercase()
                }
                thisData["event_trigger"] = innerData
            }
            put("definition_event", thisData)
        }
    }

    /**
     * 0..1
     *
     * A component that applies a mob effect to entities that get within range.
     */
    fun mobEffect(data: MobEffectProjectile.() -> Unit) {
        general["minecraft:mob_effect"] = MobEffectProjectile().apply(data).getData()
    }

    fun particleOnHit(settings: BehEntityCompProjectileParticleOnHit.() -> Unit) {
        general["particle_on_hit"] = BehEntityCompProjectileParticleOnHit().apply(settings).getData()
    }

    fun filter(settings: BehEntityFilter.() -> Unit) {
        val behEntityFilter = BehEntityFilter().apply { settings(this) }
        general.apply { put("filter", behEntityFilter.getData()) }
    }

    fun thrownPotionEffect() {
        general["thrown_potion_effect"] = mutableMapOf<Any, Any>()
    }

    fun getData(): MutableMap<String, Any> {
        return general
    }
}

class MobEffectProjectile {
    val general = mutableMapOf<String, Any>()

    var effectRange: Float? = null
    var effectTime: Int? = null
    var mobEffect: String? = null
    var durationEasy: Int? = null
    var durationNormal: Int? = null
    var durationHard: Int? = null
    var amplifier: Int? = null

    fun entityFilter(entityFilter: BehEntityFilter.() -> Unit) {
        general["entity_filter"] = BehEntityFilter().apply(entityFilter).getData()
    }


    fun getData(): MutableMap<String, Any> {
        effectRange?.let { general[""] = it }
        effectTime?.let { general[""] = it }
        mobEffect?.let { general[""] = it }
        durationEasy?.let { general[""] = it }
        durationNormal?.let { general[""] = it }
        durationHard?.let { general[""] = it }
        amplifier?.let { general[""] = it }
        return general
    }
}

class BehEntityCompProjectileParticleOnHit {
    val general = mutableMapOf<String, Any>()

    var particleType: String? = null
    var onOtherHit: Boolean? = null
    var onEntityHit: Boolean? = null
    var numParticles: Int? = null

    fun getData(): MutableMap<String, Any> {
        particleType?.let { general["particle_type"] = it }
        onOtherHit?.let { general["on_other_hit"] = it }
        onEntityHit?.let { general["on_entity_hit"] = it }
        numParticles?.let { general["num_particles"] = it }
        return general
    }
}

class ImpactDamage {
    val general = mutableMapOf<String, Any>()

    var knockback: Boolean? = null
    var semiRandomDiffDamage: Boolean? = null
    var destroyOnHit: Boolean? = null
    var maxCriticalDamage: Number? = null
    var minCriticalDamage: Number? = null
    var powerMultiplier: Float? = null
    var shouldBounce: Boolean? = null

    fun damage(min: Int, max: Int = min) {
        general["damage"] = arrayListOf(min, max)
    }

    fun getData(): MutableMap<String, Any> {
        knockback?.let { general["knockback"] = it }
        semiRandomDiffDamage?.let { general["semi_random_diff_damage"] = it }
        destroyOnHit?.let { general["destroy_on_hit"] = it }
        maxCriticalDamage?.let { general["max_critical_damage"] = it }
        minCriticalDamage?.let { general["min_critical_damage"] = it }
        powerMultiplier?.let { general["power_multiplier"] = it }
        shouldBounce?.let { general["should_bounce"] = it }
        return general
    }
}

class StickInGround {
    val general = mutableMapOf<String, Any>()

    var shakeTime: Float? = null

    fun getData(): MutableMap<String, Any> {
        return general
    }
}

class SpawnAoeCloud {
    val general = mutableMapOf<String, Any>()

    var radius: Float? = null
    var radiusOnUse: Number? = null
    var potion: Int? = null
    var particle: String? = null
    var duration: Number? = null
    var color: Color? = null
    var affectOwner: Boolean? = null
    var reapplicationDelay: Number? = null

    fun getData(): MutableMap<String, Any> {
        radius?.let { general["radius"] = it }
        radiusOnUse?.let { general["radius_on_use"] = it }
        potion?.let { general["potion"] = it }
        particle?.let { general["particle"] = it }
        duration?.let { general["duration"] = it }
        color?.let { general["color"] = arrayListOf(it.red, it.green, it.blue) }
        affectOwner?.let { general["affect_owner"] = it }
        reapplicationDelay?.let { general["reapplication_delay"] = it }
        return general
    }
}

class SpawnChance {
    val general = mutableMapOf<String, Any>()

    var firstSpawnChance: Number? = null
    var secondSpawnChance: Number? = null
    var firstSpawnCount: Int? = null
    var secondSpawnCount: Int? = null
    var spawnDefinition: String? = null
    var spawnBaby: Boolean? = null

    fun firstSpawnChance(value: Number) {
        general["first_spawn_chance"] = value
    }

    fun secondSpawnChance(value: Number) {
        general["second_spawn_chance"] = value
    }

    fun firstSpawnCount(value: Int) {
        general["first_spawn_count"] = value
    }

    fun secondSpawnCount(value: Int) {
        general["second_spawn_count"] = value
    }

    fun spawnDefinition(value: String) {
        general["spawn_definition"] = value
    }

    fun spawnBaby(value: Boolean) {
        general["spawn_baby"] = value
    }

    fun getData(): MutableMap<String, Any> {
        firstSpawnChance?.let { general["first_spawn_chance"] = it }
        secondSpawnChance?.let { general["second_spawn_chance"] = it }
        firstSpawnCount?.let { general["first_spawn_count"] = it }
        secondSpawnCount?.let { general["second_spawn_count"] = it }
        spawnDefinition?.let { general["spawn_definition"] = it }
        spawnBaby?.let { general["spawn_baby"] = it }
        return general
    }
}