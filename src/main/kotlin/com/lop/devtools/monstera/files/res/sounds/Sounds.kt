package com.lop.devtools.monstera.files.res.sounds

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.api.MonsteraBuildableFile
import com.lop.devtools.monstera.files.MonsteraBuilder
import com.lop.devtools.monstera.files.MonsteraMapFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.getMonsteraLogger
import java.lang.Error
import java.nio.file.Path

class Sounds : MonsteraBuildableFile, MonsteraRawFile() {
    /**
     * @param filename ignored, is always sounds.json
     */
    override fun build(filename: String, path: Path?, version: String?): Result<Path> {
        val selPath = path ?: Addon.active?.config?.resPath ?: run {
            getMonsteraLogger(this.javaClass.name).error("Could not Resolve a path for sounds.json as no addon was initialized!")
            return Result.failure(Error("Could not Resolve a path for sounds.json as no addon was initialized!"))
        }

        val target = MonsteraBuilder.buildTo(
            selPath,
            "sounds.json",
            this
        )
        return Result.success(target)
    }

    @SerializedName("block_sounds")
    @Expose
    @JsonAdapter(MonsteraMapFileTypeAdapter::class)
    var blockSoundsData: MutableMap<String, SoundEvents>? = null
        @MonsteraBuildSetter set

    @SerializedName("entity_sounds")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var entitySoundsData: EntitySpecSound? = null
        @MonsteraBuildSetter set

    @SerializedName("individual_event_sounds")
    @Expose
    @JsonAdapter(MonsteraMapFileTypeAdapter::class)
    var individualEventSoundData: MutableMap<String, SoundEvents>? = null
        @MonsteraBuildSetter set

    @SerializedName("interactive_sounds")
    @Expose
    @JsonAdapter(MonsteraMapFileTypeAdapter::class)
    var interactiveSoundData: MutableMap<String, SoundEvents>? = null
        @MonsteraBuildSetter set

    class EntitySpecSound : MonsteraRawFile() {
        @SerializedName("entities")
        @Expose
        @JsonAdapter(MonsteraMapFileTypeAdapter::class)
        var entitiesData: MutableMap<String, SoundEvents>? = null
            @MonsteraBuildSetter set

        @SerializedName("defaults")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var defaultsData: SoundEvents? = null
            @MonsteraBuildSetter set
    }

    /**
     * 0..1
     *
     * sound category of blocks
     * @param soundIdGroups manage sound events
     * @sample sample
     */
    @OptIn(MonsteraBuildSetter::class)
    fun blockSounds(soundIdGroups: SpecSounds.() -> Unit) {
        blockSoundsData = (blockSoundsData ?: mutableMapOf()).apply {
            SpecSounds().apply(soundIdGroups).eventData.forEach { (name, setting) ->
                get(name)?.apply(setting) ?: put(name, SoundEvents().apply(setting))
            }
        }
    }

    /**
     * 0..1
     *
     * sound category of entities
     * @param soundIdGroups manage sound events
     * @sample sample
     */
    @OptIn(MonsteraBuildSetter::class)
    fun entitySounds(soundIdGroups: SpecSounds.() -> Unit) {
        entitySoundsData = (entitySoundsData ?: EntitySpecSound()).apply {
            entitiesData = (entitiesData ?: mutableMapOf()).apply {
                SpecSounds().apply(soundIdGroups).eventData.forEach { (name, setting) ->
                    get(name)?.apply(setting) ?: put(name, SoundEvents().apply(setting))
                }
            }
        }
    }

    /**
     * 0..1
     *
     * sound category of entities
     * @param events manage sound events
     * @sample sample
     */
    @OptIn(MonsteraBuildSetter::class)
    fun defaultEntitySounds(events: SoundEvents.() -> Unit) {
        entitySoundsData = (entitySoundsData ?: EntitySpecSound()).apply {
            defaultsData = (defaultsData ?: SoundEvents()).apply(events)
        }
    }

    /**
     * 0..1
     *
     * sound category of individual events
     * @param soundIdGroups manage sound events
     * @sample sample
     */
    @OptIn(MonsteraBuildSetter::class)
    fun individualEventSounds(soundIdGroups: SpecSounds.() -> Unit) {
        individualEventSoundData = (individualEventSoundData ?: mutableMapOf()).apply {
            SpecSounds().apply(soundIdGroups).eventData.forEach { (name, setting) ->
                get(name)?.apply(setting) ?: put(name, SoundEvents().apply(setting))
            }
        }
    }

    /**
     * 0..1
     *
     * sound category of interactive sounds
     * @param soundIdGroups manage sound events
     * @sample sample
     */
    @OptIn(MonsteraBuildSetter::class)
    fun interactiveSounds(soundIdGroups: SpecSounds.() -> Unit) {
        interactiveSoundData = (interactiveSoundData ?: mutableMapOf()).apply {
            SpecSounds().apply(soundIdGroups).eventData.forEach { (name, setting) ->
                get(name)?.apply(setting) ?: put(name, SoundEvents().apply(setting))
            }
        }
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

open class SpecSounds {
    val eventData = mutableMapOf<String, SoundEvents.() -> Unit>()

    /**
     * 0..1
     *
     * set events with sounds
     * @param soundIdentifier like "ambient.candle" or "villager"
     */
    fun soundEventGroup(soundIdentifier: String, events: SoundEvents.() -> Unit) {
        eventData[soundIdentifier] = events
    }
}

open class SoundEvents : MonsteraRawFile() {
    @SerializedName("events")
    @Expose
    @JsonAdapter(MonsteraMapFileTypeAdapter::class)
    var eventsData: MutableMap<String, SoundEventSettings>? = null
        @MonsteraBuildSetter set

    fun event(event: SoundEvent, settings: SoundEventSettings.() -> Unit) {
        event(event.toString(), settings)
    }

    @OptIn(MonsteraBuildSetter::class)
    fun event(event: String, settings: SoundEventSettings.() -> Unit) {
        eventsData = (eventsData ?: mutableMapOf()).also {
            it[event]?.apply(settings) ?: run {
                it.put(event, SoundEventSettings().apply(settings))
            }
        }
    }
}

open class SoundEventSettings : MonsteraRawFile() {
    @SerializedName("volume")
    @Expose
    var volumeData: Any? = null
        @MonsteraBuildSetter set

    @SerializedName("pitch")
    @Expose
    var pitchData: Any? = null
        @MonsteraBuildSetter set

    @SerializedName("sound")
    @Expose
    var sound: String? = null

    @OptIn(MonsteraBuildSetter::class)
    fun volume(value: Number) {
        volumeData = value
    }

    @OptIn(MonsteraBuildSetter::class)
    fun volume(value: Pair<Number, Number>) {
        volumeData = if (value.first == value.second)
            value.first
        else
            listOf(value.first, value.second)
    }

    @OptIn(MonsteraBuildSetter::class)
    fun pitch(value: Number) {
        pitchData = value
    }

    @OptIn(MonsteraBuildSetter::class)
    fun pitch(value: Pair<Number, Number>) {
        pitchData = if (value.first == value.second)
            value.first
        else
            listOf(value.first, value.second)
    }

    fun sound(value: String) {
        sound = value
    }
}