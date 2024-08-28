@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.lop.devtools.monstera.addon.entity.behaviour

import com.lop.devtools.monstera.addon.api.DebugMarker
import com.lop.devtools.monstera.addon.concept.recipes.CraftingRecipe
import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.files.animcontroller.AnimationControllers
import com.lop.devtools.monstera.files.beh.animations.BehAnimation
import com.lop.devtools.monstera.files.beh.animations.BehAnimations
import com.lop.devtools.monstera.files.beh.entitiy.BehEntity
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.description.RuntimeIdentifier
import com.lop.devtools.monstera.files.beh.entitiy.events.BehEntityEvent
import com.lop.devtools.monstera.files.beh.tables.loot.BehLootTables
import com.lop.devtools.monstera.files.properties.EntityProperties
import com.lop.devtools.monstera.getMonsteraLogger


open class BehaviourEntity(val entityData: Entity.Data) : OverwriteComponents(entityData) {
    private fun logger() = getMonsteraLogger("Behaviour")

    open val unsafeRawEntity: BehEntity = BehEntity()
    open val unsafeRawAnimations: BehAnimations = BehAnimations()
    open val unsafeRawControllers: AnimationControllers = AnimationControllers()
    open val unsafeRawCraftingRecipe: CraftingRecipe = CraftingRecipe()
    open val unsafeLootTable = BehLootTables()
    open var unsafeBehEntityVersion: String? = null
    open var unsafeAnimVersion: String? = null
    open var unsafeAnimControllerVersion: String? = null

    /**
     * add a runtimeIdentifier like guardian
     */
    open var runtimeIdentifier: RuntimeIdentifier? = null
        set(value) {
            unsafeRawEntity.description {
                runtimeIdentifier = value
            }
            field = value
        }

    /**
     * add a behaviour animation
     *
     * ```
     * animation("walk") {
     *      timeline {
     *
     *      }
     *      animationLength = 2f
     * }
     * ```
     */
    open fun animation(name: String, settings: BehAnimation.() -> Unit) {
        unsafeRawAnimations.animation("animation.${entityData.addon.config.namespace}.${entityData.name}.$name", settings)
        addSharedAnimation("animation.${entityData.addon.config.namespace}.${entityData.name}.$name", name)
    }

    /**
     * add an animation that is defined somewhere else, like another entity
     * if you are unsure of the correct identifier, look at /build/development_behaviorpacks/projectShort_BP/animations
     *
     * @param animIdentifier can be a single wort, monstera will attempt to add 'animation.name.' automatically
     */
    open fun addSharedAnimation(animIdentifier: String, name: String = animIdentifier.split(".").last()) {
        unsafeRawEntity.apply {
            description {
                if (animationData?.containsKey(name) == true) {
                    logger().warn(
                        "(${entityData.name}) Animation '${
                            name.split(".").last()
                        }' already exists (overwriting)"
                    )
                }
                addAnimation(name, animIdentifier)
            }
        }
    }

    /**
     * add an animation from another entity
     *
     * @param originalName the name of the animation at the original entity, that first implemented the animation
     * @param localName the name of the animation in the current entity,
     * this can differ from the originalName if it helps the naming convention - optional, defaults to originalName
     * @param from the original entity where the animation was defined
     */
    open fun addSharedAnimation(originalName: String, localName: String = originalName, from: Entity) {
        addSharedAnimation("animation.${entityData.addon.config.namespace}.${from.name}.$originalName", localName)
    }

    /**
     * add a animation controller
     *
     * ```
     * animController("controller") {
     *     animStates {
     *      //...
     *     }
     * }
     * ```
     *
     * @param name the name of the anim controller, should be unique within the entity
     * @param query an additional query when the anim controller should be active
     * @param data the controller
     */
    open fun animController(name: String, query: Molang = Query.True, data: AnimationControllers.Controller.() -> Unit) {
        val id = "controller.animation.${entityData.addon.config.namespace}.${entityData.name}.$name"
        unsafeRawControllers.animController(id, data)
        addSharedController(id, query, name)
    }

    /**
     * add a controller that is defined somewhere else, like another entity
     *
     * if you are unsure of the correct identifier, look at /build/development_behaviorpacks/projectShort_BP/animation_controllers
     *
     * @param controllerIdentifier can be a single wort, monstera will attempt to add 'controller.animation.name.' automatically
     */
    open fun addSharedController(controllerIdentifier: String, query: Molang = Query.True, name: String) {
        unsafeRawEntity.apply {
            description {
                scripts {
                    animate(name, query)
                }
            }
        }
        //controllers are loaded the same way as animations
        addSharedAnimation(controllerIdentifier, name)
    }

    /**
     * add an animation from another entity
     *
     * @param originalName the name of the controller at the original entity, that first implemented the controller
     * @param localName the name of the controller in the current entity,
     * this can differ from the originalName if it helps the naming convention - optional, defaults to originalName
     * @param from the original entity where the controller was defined
     * @param query when the controller should be active - optional, default always
     */
    open fun addSharedController(originalName: String, localName: String = originalName, from: Entity, query: Molang) {
        addSharedController("controller.animation.${entityData.addon.config.namespace}.${from.name}.$originalName", query, localName)
    }

    /**
     * add default behaviour components to the entity
     *
     * ```
     * components {
     *     physics { }
     * }
     * ```
     */
    open fun components(data: Components.() -> Unit) {
        unsafeRawEntity.components(data)
    }

    /**
     * add component groups
     *
     * ```
     * componentGroup("my_component_group") {
     *      //...
     * }
     * ```
     */
    open fun componentGroup(name: String, components: Components.() -> Unit) {
        unsafeRawEntity.componentGroup(name, components)
    }

    /**
     * add events
     *
     * ```
     * event("do_stuff") {
     *     add { }
     * }
     * ```
     */
    @Deprecated("spelling", ReplaceWith("event(name, data)"))
    open fun events(name: String, data: BehEntityEvent.() -> Unit) = event(name, data)

    /**
     * add events
     *
     * ```
     * event("do_stuff") {
     *     add { }
     * }
     * ```
     */
    open fun event(name: String, data: BehEntityEvent.() -> Unit) {
        unsafeRawEntity.event(name, data)
    }

    open fun eventBorn(data: BehEntityEvent.() -> Unit) = unsafeRawEntity.eventBorn(data)
    open fun eventSpawned(data: BehEntityEvent.() -> Unit) = unsafeRawEntity.eventSpawned(data)
    open fun eventOnPrime(data: BehEntityEvent.() -> Unit) = unsafeRawEntity.eventOnPrime(data)
    open fun eventTransformed(data: BehEntityEvent.() -> Unit) = unsafeRawEntity.eventTransformed(data)

    /**
     * define spawn rules for the entity
     *
     * ```
     * populationControl()
     * condition { }
     * ```
     */
    open fun spawnRule(value: SysSpawnRule.() -> Unit) {
        SysSpawnRule(entityData.identifier, entityData.name, addon = entityData.addon).apply(value).build()
    }

    /**
     * set new properties to an entity
     *
     * ```
     * enum("name") { }
     * bool("name") { }
     * int("name") { }
     * float("name") { }
     * ```
     */
    open fun properties(data: EntityProperties.() -> Unit) {
        unsafeRawEntity.apply {
            description {
                properties(data)
            }
        }
    }

    /**
     * creates a recipe for the crafting table
     *
     * ```
     * craftingRecipe {
     *     craftingPattern(
     *         t("","minecraft:diamond","minecraft:diamond"),
     *         t("","minecraft:diamond",""),
     *         t("","minecraft:stick","")
     *     )
     *     unlock {
     *         item("minecraft:wood", count = 3, data = 2)
     *         context()
     *     }
     * }
     * ```
     */
    open fun craftingRecipe(data: CraftingRecipe.() -> Unit) {
        unsafeRawCraftingRecipe.apply(data)
    }

    /**
     * generate a valid loot table
     *
     * @return a valid table that can be used in the loot table component
     */
    @Deprecated("Use in component table() call", ReplaceWith("table()"))
    fun generateLootTable(data: BehLootTables.() -> Unit): String {
        unsafeLootTable.apply(data)
        return entityData.addon.config.paths.lootTableEntity.toString() + "/${entityData.name}"
    }

    @OptIn(DebugMarker::class)
    open fun build() {
        unsafeRawEntity.description {
            identifier = entityData.identifier
            isSpawnable = entityData.spawnAble
            isSummonable = true
            isExperimental = false
        }

        unsafeRawEntity.debugEntity()

        unsafeRawEntity.build(
            entityData.name,
            entityData.addon.config.paths.behEntity,
            unsafeBehEntityVersion
        )
        if (!unsafeRawAnimations.isEmpty())
            unsafeRawAnimations.build(
                entityData.name,
                entityData.addon.config.paths.behAnim,
                unsafeAnimVersion
            )
        if (!unsafeRawControllers.isEmpty())
            unsafeRawControllers.build(
                entityData.name,
                entityData.addon.config.paths.behAnimController,
                unsafeAnimControllerVersion
            )

        if (!unsafeRawCraftingRecipe.unsafe.isEmpty()) {
            unsafeRawCraftingRecipe.unsafe.build(
                entityData.name,
                entityData.identifier + "_spawn_egg",
                entityData.addon
            )
        }

        if (!unsafeLootTable.isEmpty()) {
            unsafeLootTable.debug(entityData.name)
            BehLootTables.Entity(unsafeLootTable).build(entityData.name)
        }
    }
}