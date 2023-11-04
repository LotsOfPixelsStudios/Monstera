package com.lop.devtools.monstera.addon.sound

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.files.res.sounds.CategorySound

/**
 * applies the sound data to the addon
 */
fun Addon.unsafeApplySoundData(soundData: List<SoundData>, groupName: String) {
    sounds {
        soundsDefinitions {
            soundData.forEach {
                if (!it.skipSoundDefComponent) {
                    newSoundDef(it.identifier) {
                        category(it.category)
                        if (it.maxDistance.toFloat() > 0)
                            maxDistance(it.maxDistance.toFloat())
                        if (it.minDistance.toFloat() > 0)
                            minDistance(it.minDistance.toFloat())
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
                        unsafe.eventSettings.putAll(
                            it.soundEvents.map { event -> event.key.toString() to event.value }.toMap()
                        )
                    }
                }
            }

            soundData.filter { it.categorySound == CategorySound.ENTITY }.forEach {
                entitySounds {
                    soundEventGroup(groupName) {
                        unsafe.eventSettings.putAll(
                            it.soundEvents.map { event -> event.key.toString() to event.value }.toMap()
                        )
                    }
                }
            }

            soundData.filter { it.categorySound == CategorySound.DEFAULT_ENTITY }.forEach {
                defaultEntitySounds {
                    unsafe.eventSettings.putAll(
                        it.soundEvents.map { event -> event.key.toString() to event.value }.toMap()
                    )
                }
            }

            soundData.filter { it.categorySound == CategorySound.INDIVIDUAL }.forEach {
                individualEventSounds {
                    soundEventGroup(groupName) {
                        unsafe.eventSettings.putAll(
                            it.soundEvents.map { event -> event.key.toString() to event.value }.toMap()
                        )
                    }
                }
            }

            soundData.filter { it.categorySound == CategorySound.INTERACTIVE }.forEach {
                interactiveSounds {
                    soundEventGroup(groupName) {
                        unsafe.eventSettings.putAll(
                            it.soundEvents.map { event -> event.key.toString() to event.value }.toMap()
                        )
                    }
                }
            }
        }
    }
}
