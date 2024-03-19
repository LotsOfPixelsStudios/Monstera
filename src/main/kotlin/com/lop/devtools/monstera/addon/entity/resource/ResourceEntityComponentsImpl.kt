package com.lop.devtools.monstera.addon.entity.resource

import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.files.res.entities.ResEntity
import com.lop.devtools.monstera.files.res.entities.comp.*
import com.lop.devtools.monstera.files.res.rendercontrollers.ResRenderControllers

open class ResourceEntityComponentsImpl(
    override val unsafeParent: Entity,
    override val unsafeRawEntity: ResEntity,
    override val unsafeRenderController: ResRenderControllers
) : ResourceEntityComponents {
    override var enableAttachment: Boolean? = null
    override var material: String = "parrot"
        set(value) {
            disableMaterial = false
            field = value
        }
    override var disableMaterial: Boolean = false

    override fun spawnEgg(displayText: String, data: ResEntitySpawnEgg.() -> Unit) {
        unsafeRawEntity.description {
            this.spawnEgg(
                langValue = displayText,
                langKey = "item.spawn_egg.entity.${unsafeParent.getIdentifier()}.name",
                data
            )
        }
        //tells the behaviour to set the entity as spawnAble so the spawnegg is displayed in the creative inventory
        unsafeParent.unsafeSpawnAble = true
    }

    override fun particleEffects(data: ResEntityParticleEffect.() -> Unit) {
        unsafeRawEntity.description {
            this.particleEffects(data)
        }
    }

    override fun soundEffects(data: ResEntitySoundEffect.() -> Unit) {
        unsafeRawEntity.description {
            this.soundEffects(data)
        }
    }

    override fun locators(data: ResEntityLocators.() -> Unit) {
        unsafeRawEntity.description {
            this.locators(data)
        }
    }

    override fun scripts(settings: ResEntityScripts.() -> Unit) {
        unsafeRawEntity.description {
            this.scripts(settings)
        }
    }

    override fun build() {
        enableAttachment?.let { unsafeRawEntity.description { enableAttachment = it } }
        if (!disableMaterial) {
            unsafeParent.resource {
                renderPart("default") {
                    material = this@ResourceEntityComponentsImpl.material
                }
            }
        }
    }
}