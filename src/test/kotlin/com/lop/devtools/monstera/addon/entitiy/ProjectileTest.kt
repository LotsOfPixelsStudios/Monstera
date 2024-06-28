package com.lop.devtools.monstera.addon.entitiy

import com.lop.devtools.monstera.addon.buildTestAddon
import com.lop.devtools.monstera.addon.testAddon
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject
import kotlin.test.Test
import kotlin.test.AfterTest

class ProjectileTest {
    @AfterTest
    fun buildTask() {
        buildTestAddon()
    }

    @Test
    fun basicProjectileTest() = testAddon {
        entity("projectile_test", "Projectile Test") {
            behaviour {
                components {
                    physics { }
                    projectile {
                        hitSound = "bow.hit"
                        power = 1.6
                        gravity = 0.05
                        uncertaintyBase = 16
                        uncertaintyMultiplier = 4
                        anchor = 1
                        shouldBounce = true
                        inertia = 1
                        semiRandomDiffDamage = true
                        reflectOnHurt = true
                        angleOffset = 0.0
                        liquidInertia = 1
                        catchFire = true
                        destroyOnHurt = true
                        critParticleOnHurt = true
                        homing = true
                        hitGroundSound = "item.trident.hit_ground"
                        stopOnHurt = true
                        multipleTargets = false
                        shootSound = "bow"
                        shootTarget = false
                        isDangerous = true

                        onHit {
                            stickInGround {
                                shakeTime = 2
                            }
                            arrowEffect {
                                applyEffectToBlockingTargets = true
                            }
                            definitionEvent {
                                affectProjectile = true
                                affectShooter = true
                                affectTarget = true
                                affectSplashArea = true
                                splashArea = 4
                                eventTrigger {
                                    event = ""
                                    target = "other"
                                    filters {
                                        distanceToNearestPlayer(subject = Subject.SELF, value = 2f)
                                    }
                                }
                            }
                            teleportOwner = true
                            removeOnHit = true
                            catchFire = true
                            douseFire = true
                        }
                    }
                }
            }
        }
    }
}