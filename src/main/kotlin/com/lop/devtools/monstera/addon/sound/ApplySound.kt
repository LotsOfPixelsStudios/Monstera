package com.lop.devtools.monstera.addon.sound

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.res.sounds.CategorySound

/**
 * applies the sound data to the addon
 */
@OptIn(MonsteraBuildSetter::class)
fun Addon.unsafeApplySoundData(soundData: List<Sound>, groupName: String) {
    sounds {
        soundsDefinitions {
            soundData.forEach {
                if (!it.skipSoundDefComponent) {
                    newSoundDef(it.identifier) {
                        category = it.category
                        if (it.maxDistance.toFloat() > 0)
                            maxDistance = it.maxDistance
                        if (it.minDistance.toFloat() > 0)
                            minDistance = it.maxDistance
                        it.getAsSoundComponent().forEach { comp ->
                            sound(comp)
                        }
                    }
                }
            }
        }
        categorySounds {
            soundData.filter { it.categorySound == CategorySound.BLOCK }.forEach {
                blockSounds {
                    soundEventGroup(groupName) {
                        eventsData = (eventsData ?: mutableMapOf()).apply {
                            putAll(it.soundEvents.map { event -> event.key.toString() to event.value }.toMap())
                        }
                    }
                }
            }

            soundData.filter { it.categorySound == CategorySound.ENTITY }.forEach {
                entitySounds {
                    soundEventGroup(groupName) {
                        eventsData = (eventsData ?: mutableMapOf()).apply {
                            putAll(it.soundEvents.map { event -> event.key.toString() to event.value }.toMap())
                        }
                    }
                }
            }

            soundData.filter { it.categorySound == CategorySound.DEFAULT_ENTITY }.forEach {
                defaultEntitySounds {
                    eventsData = (eventsData ?: mutableMapOf()).apply {
                        putAll(it.soundEvents.map { event -> event.key.toString() to event.value }.toMap())
                    }
                }
            }

            soundData.filter { it.categorySound == CategorySound.INDIVIDUAL }.forEach {
                individualEventSounds {
                    soundEventGroup(groupName) {
                        eventsData = (eventsData ?: mutableMapOf()).apply {
                            putAll(it.soundEvents.map { event -> event.key.toString() to event.value }.toMap())
                        }
                    }
                }
            }

            soundData.filter { it.categorySound == CategorySound.INTERACTIVE }.forEach {
                interactiveSounds {
                    soundEventGroup(groupName) {
                        eventsData = (eventsData ?: mutableMapOf()).apply {
                            putAll(it.soundEvents.map { event -> event.key.toString() to event.value }.toMap())
                        }
                    }
                }
            }
        }
    }
}
