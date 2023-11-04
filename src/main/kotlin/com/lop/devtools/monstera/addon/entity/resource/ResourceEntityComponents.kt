package com.lop.devtools.monstera.addon.entity.resource

import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.files.res.entities.ResEntity
import com.lop.devtools.monstera.files.res.entities.comp.*
import com.lop.devtools.monstera.files.res.rendercontrollers.ResRenderControllers
import java.io.File

interface ResourceEntityComponents {
    val unsafeParent: Entity
    val unsafeRawEntity: ResEntity
    val unsafeRenderController: ResRenderControllers

    var enableAttachment: Boolean?

    /**
     * material like parrot, sets 'disableMaterial' to false
     */
    var material: String

    /**
     * disable the material, material will be ignored
     */
    var disableMaterial: Boolean

    /**
     * define the spawn egg of the entity,
     * required if the egg should appear in the creative inventory
     * @param displayText the lang value of the spawn egg
     * @param data color etc
     */
    fun spawnEgg(displayText: String = "Spawn ${unsafeParent.displayName}", data: ResEntitySpawnEgg.() -> Unit)

    /**
     * add a particle to the entity
     *
     * ```
     * particleEffects {
     *     particleEffect("exhaust", "gl:drone_exhaust")
     * }
     * ```
     */
    fun particleEffects(data: ResEntityParticleEffect.() -> Unit)

    /**
     * add a sound to the entity
     *
     * ```
     * soundEffects {
     *     soundEffect("door_open", "random.door_open")
     * }
     * ```
     */
    fun soundEffects(data: ResEntitySoundEffect.() -> Unit)

    /**
     * add a locator for a lead etc
     *
     * ```
     * locators {
     *     lead {
     *         attach(x = 1.0f, y = 1.0f, z = 1.0f)
     *     }
     * }
     * ```
     */
    fun locators(data: ResEntityLocators.() -> Unit)

    /**
     * add scripts, variables etc
     *
     * ```
     * scripts {
     *     preAnim(arrayListOf("var.setSth", "var.tan"))
     *     script("scale", "0.35")
     *     script("scale2", "0.31")
     * }
     * ```
     */
    fun scripts(settings: ResEntityScripts.() -> Unit)

    fun ResEntitySpawnEgg.eggByFile(file: File) {
        eggByFile(file, unsafeParent.addon)
    }

    /**
     * internal function for building the entity, can be called multiple times
     */
    fun build()
}