package com.lop.devtools.monstera.addon.entitiy.wiki

import com.lop.devtools.monstera.addon.buildTestAddon
import com.lop.devtools.monstera.addon.testAddon
import kotlin.test.AfterTest
import kotlin.test.Test

class Boat {
    @AfterTest
    fun buildTask() {
        buildTestAddon()
    }

    @Test
    fun testBoat() = testAddon {
        entity("my_boat", "My Boat") {
            behaviour {
                components {
                    behRiseToLiquidLevel {
                        priority = 0
                        liquidYOffset = 0.5
                        riseDelta = 0.05
                        sinkDelta = 0.05
                    }
                    underwaterMovement {
                        value = 5
                    }
                    navigationWalk {
                        canSink = false
                    }
                    rideable {
                        seatCount = 1
                        familyTypes("player")
                        interactText("ride", "action.interact.enter_boat")
                        seat {
                            position(0, 0, 0)
                        }
                    }
                    inputGroundControlled { }
                    collisionBox {
                        width = 1
                        height = 1
                    }
                    physics { }
                }
            }
        }
        entity("my_second_boat") {
            behaviour {
                components {
                    buoyant {
                        applyGravity = true
                        baseBuoyancy = 1
                        simulateWaves = true
                        bigWaveProbability = 0.03
                        bigWaveSpeed = 10
                        dragDownOnBuoyancyRemoved = 0
                        liquidBlocks("water")
                    }
                    underwaterMovement {
                        value = 5
                    }
                    navigationWalk {
                        canSink = false
                    }
                    rideable {
                        seatCount = 1
                        familyTypes("player")
                        interactText("ride", "action.interact.enter_boat")
                        seat {
                            position(0, 0, 0)
                        }
                    }
                    inputGroundControlled { }
                    collisionBox {
                        width = 1
                        height = 1
                    }
                    physics { }
                    movementBasic {  }
                }
            }
        }
    }
}