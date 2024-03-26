package com.lop.devtools.monstera.addon.entitiy

import com.lop.devtools.monstera.addon.buildTestAddon
import com.lop.devtools.monstera.addon.sound.Sound
import com.lop.devtools.monstera.addon.testAddon
import com.lop.devtools.monstera.files.getResource
import com.lop.devtools.monstera.files.res.sounds.SoundEvent
import java.awt.Color
import kotlin.test.AfterTest
import kotlin.test.Test

class SoundEntityTest {
    @AfterTest
    fun buildTask() {
        buildTestAddon()
    }

    @Test
    fun soundTest() = testAddon {
        entity("sound_entity", "My Sound Entity") {
            behaviour {
                components {
                    physics { }
                    pushable { }
                    walkMovement(0.2) { }
                    behRandomStroll { }

                    interact {
                        interaction {
                            swing = true
                            playSound = sound("mob.soldier.interact") {
                                sound(
                                    arrayListOf(
                                        getResource("sounds/fill1.fsb") to Sound.SoundDefData(),
                                        getResource("sounds/fill2.fsb") to Sound.SoundDefData(),
                                        getResource("sounds/fill3.fsb") to Sound.SoundDefData(),
                                        getResource("sounds/fill4.fsb") to Sound.SoundDefData()
                                    )
                                )
                                maxDistance = 5
                                minDistance = 0
                            }
                        }
                    }

                    sound("mob.soldier.hit") {
                        onEvent(SoundEvent.HURT)
                        sound(
                            arrayListOf(
                                getResource("sounds/hit1.fsb") to Sound.SoundDefData(),
                                getResource("sounds/hit2.fsb") to Sound.SoundDefData(),
                                getResource("sounds/hit3.fsb") to Sound.SoundDefData()
                            )
                        )
                    }
                    sound("mob.$name.ambient") {
                        onEvent(SoundEvent.AMBIENT)
                        sound(
                            arrayListOf(
                                getResource("sounds/fill1.fsb") to Sound.SoundDefData(),
                                getResource("sounds/fill2.fsb") to Sound.SoundDefData()
                            )
                        )
                    }
                }
            }
            resource {
                geometryLayer(getResource("entity/geometries/soldier_npc.geo.json"))
                textureLayer(getResource("entity/textures/soldier_npc_0.png"))
                animation(getResource("entity/animations/soldier_npc.animation.json"))
                animationController("general") {
                    initialState = "default"
                    state("default") {
                        animations("base_pose", "default", "move")
                    }
                }
                components {
                    scripts {
                        preAnimationEntry("variable.tcos0 = (Math.cos(query.modified_distance_moved * 38.17) * query.modified_move_speed / variable.gliding_speed_value) * 57.3;")
                    }
                    spawnEgg {
                        eggByColor(Color.BLACK, Color.GRAY)
                    }
                }
            }
        }
    }
}