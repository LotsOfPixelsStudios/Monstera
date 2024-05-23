@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.lop.devtools.monstera.addon.entity.resource

import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.files.lang.langKey
import com.lop.devtools.monstera.files.res.entities.ResEntity
import com.lop.devtools.monstera.files.res.entities.comp.ResEntityLocators
import com.lop.devtools.monstera.files.res.entities.comp.ResEntityScripts
import com.lop.devtools.monstera.files.res.entities.comp.ResEntitySpawnEgg
import com.lop.devtools.monstera.files.res.rendercontrollers.ResRenderControllers

open class ResourceEntityComponents(
    val entityData: Entity.Data,
    val unsafeRawEntity: ResEntity,
    val unsafeRenderController: ResRenderControllers
) {
    open var enableAttachment: Boolean? = null
    open var material: String = "parrot"
        set(value) {
            disableMaterial = false
            field = value
        }
    open var disableMaterial: Boolean = false

    open var hideArmor: Boolean = false
        set(value) {
            unsafeRawEntity.description {
                hideArmor = value
            }
            field = value
        }

    open fun spawnEgg(displayText: String = "Spawn ${entityData.displayName}", data: ResEntitySpawnEgg.() -> Unit) {
        unsafeRawEntity.description {
            this.spawnEgg(data)
            langKey(
                "item.spawn_egg.entity.${entityData.identifier}.name",
                displayText
            )
        }
        //tells the behaviour to set the entity as spawnAble so the spawnegg is displayed in the creative inventory
        entityData.spawnAble = true
    }

    /**
     * can be called multiple times
     * ```
     * particleEffect("smoke", "namespace:smoke_particle")
     * ```
     */
    open fun particleEffect(name: String, particle: String) = unsafeRawEntity.description { particleEffect(name, particle) }

    /**
     * can be called multiple times
     * ```
     * soundEffect("attack_1", "mob.entity.attack_1")
     * ```
     */
    open fun soundEffect(name: String, sound: String) = unsafeRawEntity.description { soundEffect(name, sound) }

    open fun locators(data: ResEntityLocators.() -> Unit) {
        unsafeRawEntity.description {
            this.locators(data)
        }
    }

    open fun scripts(settings: ResEntityScripts.() -> Unit) {
        unsafeRawEntity.description {
            this.scripts(settings)
        }
    }

    open fun build() {
        enableAttachment?.let { unsafeRawEntity.description { enableAttachment = it } }
    }
}