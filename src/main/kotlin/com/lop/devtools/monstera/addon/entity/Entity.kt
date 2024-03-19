package com.lop.devtools.monstera.addon.entity

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.entity.behaviour.BehaviourEntity
import com.lop.devtools.monstera.addon.entity.resource.ResourceEntity
import com.lop.devtools.monstera.addon.sound.SoundData

interface Entity {
    val name: String
    val displayName: String
    val addon: Addon

    val unsafeBehaviourEntity: BehaviourEntity
    val unsafeResourceEntity: ResourceEntity
    var unsafeSpawnAble: Boolean
    val unsafeSoundData: MutableList<SoundData>

    /**
     * @return the identifier of the entity as it is defined in the final beh/res pack
     */
    fun getIdentifier(): String

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
    fun resource(entity: ResourceEntity.() -> Unit)

    /**
     * modify the behaviour of the entity
     *
     * ````
     *  behaviour {
     *      animController("name") {
     *          //normal beh anim-controller
     *      }
     *
     *      animations {
     *          //normal beh animations
     *      }
     *
     *      //beh Entity Components
     *      componentGroups {
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
    fun behaviour(entity: BehaviourEntity.() -> Unit)

    fun build()
}