package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.Config
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentInteractions {
    val general = arrayListOf<Any>()

    /**
     * 0..*
     */
    fun interactGroup(settings: ComponentInteract.() -> Unit) {
        val componentInteract = ComponentInteract().apply { settings(this) }
        general.add(componentInteract.getData())
    }

    fun getData(): MutableMap<String, Any> {
        return mutableMapOf("interactions" to general)
    }
}

class ComponentInteract {
    val general = mutableMapOf<String,Any>()

    var addItems: String? = null
    var cooldown: Float? = null
    var cooldownAfterBeingAttacked: Float? = null
    var hurtItem: Int? = null
    var playSound: String? = null
    var spawnEntities: String? = null
    var spawnItems: String? = null
    var swing: Boolean? = null
    var transformToItem: String? = null
    var useItem: Boolean? = null

    /**
     * 0..1
     *
     * Text to show when the player is able to interact in this way with this entity when playing with Touch-screen controls.
     */
    fun text(key: String, displayName: String, config: Config? = null) {
        general["interact_text"] = key
        config?.langFileBuilder?.addonRes?.add(key, displayName)
    }

    /**
     * 0..1
     *
     * @param event Event to fire when the interaction occurs.
     * @param filter optional entity filter
     */
    fun onInteract(event: String, filter: BehEntityFilter.() -> Unit) {
        val behEntityFilter = BehEntityFilter().apply { filter(this) }
        general.apply {
            put("on_interact", mutableMapOf(
                "event" to event,
                "filters" to behEntityFilter.getData()
            ))
        }
    }

    fun onInteract(data: CompOnInteract.() -> Unit) {
        general["on_interact"] = CompOnInteract().apply(data).getData()
    }

    /**
     * 0..1
     *
     * Particle effect that will be triggered at the start of the interaction.
     * sry docu is really bad, like no types etc
     * @param particleOffsetTowardsInteractor Whether or not the particle will appear closer to who performed the interaction.
     * @param particleType The type of particle that will be spawned.
     * @param particleYOffset Will offset the particle this amount in the y direction.
     */
    fun particleOnStart(particleOffsetTowardsInteractor: Boolean = false, particleType: String? = null, particleYOffset: Float = 0.0f) {
        val thisData = mutableMapOf<String,Any>(
            "particle_offset_towards_interactor" to particleOffsetTowardsInteractor,
            "particle_y_offset" to particleYOffset
        )
        if(particleType != null) {
            thisData.apply {
                put("particle_type", particleType)
            }
        }

        general.apply {
            put("particle_on_start", thisData)
        }
    }

    fun getData(): MutableMap<String, Any> {
        addItems?.let { general["add_items"] = it }
        cooldown?.let { general["cooldown"] = it }
        cooldownAfterBeingAttacked?.let { general["cooldown_after_being_attacked"] = it }
        hurtItem?.let { general["hurt_item"] = it }
        playSound?.let { general["play_sounds"] = it }
        spawnEntities?.let { general["spawn_entities"] = it }
        spawnItems?.let { general["spawn_items"] = mutableMapOf("table" to it) }
        swing?.let { general["swing"] = it }
        transformToItem?.let { general["transform_to_item"] = it }
        useItem?.let { general["use_item"] = it }
        return general
    }
}

class CompOnInteract {
    val general = mutableMapOf<String, Any>()

    fun event(event: String, target: Subject = Subject.SELF) {
        general["event"] = event
        general["target"] = target.toString().lowercase()
    }

    fun filters(filter: BehEntityFilter.() -> Unit) {
        general["filters"] = BehEntityFilter().apply(filter).getData()
    }

    fun getData(): MutableMap<String, Any> {
        return general
    }
}