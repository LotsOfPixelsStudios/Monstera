package com.lop.devtools.monstera.files.res.sounds

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.MonsteraBuilder
import java.nio.file.Path

class Sounds : MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        /**
         * access to all defined animations
         */
        val general = mutableMapOf<String, Any>()
        val blockSounds = SpecSounds()
        val entitySound = SpecSounds()
        val defaultEntitySounds = SoundEvents()
        val individualEventSounds = SpecSounds()
        val interactiveSounds = SpecSounds()


        override fun getData(): MutableMap<String, Any> {
            general["block_sounds"] = blockSounds.unsafe.getData()
            general["entity_sounds"] = mapOf(
                "entities" to entitySound.unsafe.getData(),
                "defaults" to defaultEntitySounds.unsafe.getData()
            )
            general["individual_event_sounds"] to individualEventSounds.unsafe.getData()
            general["interactive_sounds"] to interactiveSounds.unsafe.getData()
            return general
        }

        fun build(
            filename: String = "sounds.json",
            path: Path
        ) {
            MonsteraBuilder.buildTo(
                path, filename, getData()
            )
        }
    }

    /**
     * 0..1
     *
     * sound category of blocks
     * @param soundIdGroups manage sound events
     * @sample sample
     */
    fun blockSounds(soundIdGroups: SpecSounds.() -> Unit) {
        unsafe.blockSounds.apply(soundIdGroups)
    }

    /**
     * 0..1
     *
     * sound category of entities
     * @param soundIdGroups manage sound events
     * @sample sample
     */
    fun entitySounds(soundIdGroups: SpecSounds.() -> Unit) {
        unsafe.entitySound.apply(soundIdGroups)
    }

    /**
     * 0..1
     *
     * sound category of entities
     * @param events manage sound events
     * @sample sample
     */
    fun defaultEntitySounds(events: SoundEvents.() -> Unit) {
        unsafe.defaultEntitySounds.apply(events)
    }

    /**
     * 0..1
     *
     * sound category of individual events
     * @param soundIdGroups manage sound events
     * @sample sample
     */
    fun individualEventSounds(soundIdGroups: SpecSounds.() -> Unit) {
        unsafe.individualEventSounds.apply(soundIdGroups)
    }

    /**
     * 0..1
     *
     * sound category of interactive sounds
     * @param soundIdGroups manage sound events
     * @sample sample
     */
    fun interactiveSounds(soundIdGroups: SpecSounds.() -> Unit) {
        unsafe.interactiveSounds.apply(soundIdGroups)
    }

    private fun sample() {
        blockSounds {
            soundEventGroup("test") {
                event(SoundEvent.ATTACK) {
                    sound("test.sound")
                    volume(0.2f)
                }
                event(SoundEvent.AMBIENT) {
                    sound("test2.sound")
                    pitch(0.3f)
                }
            }
            soundEventGroup("test2") {
                event(SoundEvent.SCREAM) { }
            }
        }
    }
}

class SpecSounds : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        val eventGroups = mutableMapOf<String, SoundEvents>()
        override fun getData(): MutableMap<String, Any> {
            eventGroups.forEach { (key, value) ->
                general[key] = mapOf("events" to value.unsafe.getData())
            }
            return general
        }
    }

    /**
     * 0..1
     *
     * set events with sounds
     * @param soundIdentifier like "ambient.candle" or "villager"
     */
    fun soundEventGroup(soundIdentifier: String, events: SoundEvents.() -> Unit) {
        if (unsafe.eventGroups.containsKey(soundIdentifier)) {
            unsafe.eventGroups[soundIdentifier]!!.apply(events)
        } else {
            unsafe.eventGroups[soundIdentifier] = SoundEvents().apply(events)
        }
    }
}

class SoundEvents : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        val eventSettings = mutableMapOf<String, SoundEventSettings>()

        override fun getData(): MutableMap<String, Any> {
            general.putAll(
                eventSettings.map { it.key to it.value.getData() }.toMap()
            )
            return general
        }
    }

    fun event(event: SoundEvent, settings: SoundEventSettings.() -> Unit) {
        if (unsafe.eventSettings.containsKey(event.toString())) {
            unsafe.eventSettings[event.toString()]!!.apply(settings)
        } else {
            unsafe.eventSettings[event.toString()] = SoundEventSettings().apply(settings)
        }
    }

    fun event(event: String, settings: SoundEventSettings.() -> Unit) {
        if (unsafe.eventSettings.containsKey(event)) {
            unsafe.eventSettings[event]!!.apply(settings)
        } else {
            unsafe.eventSettings[event] = SoundEventSettings().apply(settings)
        }
    }
}

class SoundEventSettings {
    val general = mutableMapOf<String, Any>()

    fun volume(value: Float) {
        general["volume"] = value
    }

    fun volume(value: Pair<Number, Number>) {
        if (value.first == value.second)
            general["volume"] = value.first
        else
            general["volume"] = listOf(value.first, value.second)
    }

    fun pitch(value: Float) {
        general["pitch"] = value
    }

    fun pitch(value: Pair<Number, Number>) {
        if (value.first == value.second)
            general["pitch"] = value.first
        else
            general["pitch"] = listOf(value.first, value.second)
    }

    fun sound(value: String) {
        general["sound"] = value
    }

    fun getData(): MutableMap<String, Any> {
        return general
    }
}