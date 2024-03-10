package com.lop.devtools.monstera.addon.entity

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.entity.behaviour.BehaviourEntity
import com.lop.devtools.monstera.addon.entity.resource.ResourceEntity
import com.lop.devtools.monstera.addon.sound.SoundData
import com.lop.devtools.monstera.addon.sound.unsafeApplySoundData

open class Entity(
    val addon: Addon,
    var name: String = "",
    var displayName: String = ""
) {
    val unsafeBehaviourEntity: BehaviourEntity = BehaviourEntity(this)
    val unsafeResourceEntity: ResourceEntity = ResourceEntity(this)

    var unsafeSpawnAble: Boolean = false
    val unsafeSoundData: MutableList<SoundData> = mutableListOf()

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

        if (unsafeSoundData.isNotEmpty()) {
            addon.unsafeApplySoundData(unsafeSoundData, name)
        }
    }
}