@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.lop.devtools.monstera.addon.entity

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.entity.behaviour.BehaviourEntity
import com.lop.devtools.monstera.addon.entity.resource.ResourceEntity
import com.lop.devtools.monstera.addon.sound.Sound
import com.lop.devtools.monstera.addon.sound.SoundData
import com.lop.devtools.monstera.addon.sound.unsafeApplySoundData

open class Entity(
    val addon: Addon,
    var name: String = "undefined",
    var displayName: String = name
) {
    data class Data(
        var addon: Addon,
        var name: String,
        var displayName: String,
        var identifier: String,
        var spawnAble: Boolean,
        var sounds: MutableList<SoundData> = mutableListOf()
    )

    val data = Data(
        addon = addon,
        name = name,
        displayName = displayName,
        identifier = getIdentifier(),
        spawnAble = false,
        sounds = mutableListOf()
    )

    val unsafeBehaviourEntity: BehaviourEntity = BehaviourEntity(data)
    val unsafeResourceEntity: ResourceEntity = ResourceEntity(data)

    /**
     * @return the identifier of the entity as it is defined in the final beh/res pack
     */
    fun getIdentifier(): String {
        return addon.config.namespace + ":" + name
    }

    /**
     * modify the resource components of the entity
     *
     * ```
     * resource {
     *     textureLayer(getResource("texture/pig.png"))
     *     geometryLayer(getResource("geo/pig.json"))
     *     animation(getResource("anim/pig.json"))
     *     animationController("walk") { }
     *     renderPart("partName", Query.isBaby) {
     *          textureLayer(getResource("texture/pig.png"))
     *          geometryLayer(getResource("geo/pig.json"))
     *     }
     * }
     * ```
     */
    fun resource(entity: ResourceEntity.() -> Unit) {
        unsafeResourceEntity.apply(entity)
    }

    /**
     * modify the behaviour of the entity
     *
     * ````
     *  behaviour {
     *      animController("name") {
     *          //normal beh anim-controller
     *      }
     *
     *      animation("") {
     *          //normal beh animations
     *      }
     *
     *      //beh Entity Components
     *      componentGroup("") {
     *
     *      }
     *      components {
     *
     *      }
     *      events {
     *
     *      }
     *  }
     * ````
     */
    fun behaviour(entity: BehaviourEntity.() -> Unit) {
        unsafeBehaviourEntity.apply(entity)
    }

    fun build() {
        unsafeBehaviourEntity.build()
        unsafeResourceEntity.build()

        if (data.sounds.isNotEmpty()) {
            addon.unsafeApplySoundData(data.sounds, getIdentifier())
        }
    }
}