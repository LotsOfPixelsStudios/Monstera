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
    val unsafeParent: Entity,
    val unsafeRawEntity: ResEntity,
    val unsafeRenderController: ResRenderControllers
) {
    var enableAttachment: Boolean? = null
    var material: String = "parrot"
        set(value) {
            disableMaterial = false
            field = value
        }
    var disableMaterial: Boolean = false

    fun spawnEgg(displayText: String, data: ResEntitySpawnEgg.() -> Unit) {
        unsafeRawEntity.description {
            this.spawnEgg(data)
            langKey(
                "item.spawn_egg.entity.${unsafeParent.getIdentifier()}.name",
                displayText
            )
        }
        //tells the behaviour to set the entity as spawnAble so the spawnegg is displayed in the creative inventory
        unsafeParent.unsafeSpawnAble = true
    }

    /**
     * can be called multiple times
     * ```
     * particleEffect("smoke", "namespace:smoke_particle")
     * ```
     */
    fun particleEffect(name: String, particle: String) = unsafeRawEntity.description { particleEffect(name, particle) }

    /**
     * can be called multiple times
     * ```
     * soundEffect("attack_1", "mob.entity.attack_1")
     * ```
     */
    fun soundEffect(name: String, sound: String) = unsafeRawEntity.description { soundEffect(name, sound) }

    fun locators(data: ResEntityLocators.() -> Unit) {
        unsafeRawEntity.description {
            this.locators(data)
        }
    }

    fun scripts(settings: ResEntityScripts.() -> Unit) {
        unsafeRawEntity.description {
            this.scripts(settings)
        }
    }

    fun build() {
        enableAttachment?.let { unsafeRawEntity.description { enableAttachment = it } }
        if (!disableMaterial) {
            unsafeParent.resource {
                renderPart("default") {
                    material = this@ResourceEntityComponents.material
                }
            }
        }
    }
}