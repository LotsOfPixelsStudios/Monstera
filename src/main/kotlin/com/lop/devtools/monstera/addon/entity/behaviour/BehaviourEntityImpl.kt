@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.lop.devtools.monstera.addon.entity.behaviour

import com.lop.devtools.monstera.addon.api.DebugMarker
import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.addon.recipes.CraftingRecipe
import com.lop.devtools.monstera.files.animcontroller.AnimationControllers
import com.lop.devtools.monstera.files.beh.animations.BehAnimation
import com.lop.devtools.monstera.files.beh.animations.BehAnimations
import com.lop.devtools.monstera.files.beh.entitiy.BehEntity
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.description.RuntimeIdentifier
import com.lop.devtools.monstera.files.beh.entitiy.events.BehEntityEvent
import com.lop.devtools.monstera.files.beh.tables.loot.BehLootTables
import com.lop.devtools.monstera.files.getVersionAsString
import com.lop.devtools.monstera.files.properties.EntityProperties
import com.lop.devtools.monstera.getMonsteraLogger

open class BehaviourEntity(val unsafeParent: Entity): OverwriteComponents(unsafeParent) {
    private fun logger() = getMonsteraLogger("Behaviour")

    val unsafeRawEntity: BehEntity = BehEntity()
    val unsafeRawAnimations: BehAnimations = BehAnimations()
    val unsafeRawControllers: AnimationControllers = AnimationControllers()
    val unsafeRawCraftingRecipe: CraftingRecipe = CraftingRecipe()
    val unsafeLootTable = BehLootTables()

    /**
     * add a runtimeIdentifier like guardian
     */
    var runtimeIdentifier: RuntimeIdentifier? = null
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
     * animation {
     *      timeline {
     *
     *      }
     *      animationLength = 2f
     * }
     * ```
     */
    fun animation(name: String, settings: BehAnimation.() -> Unit) {
        unsafeRawAnimations.animation("animation.${unsafeParent.name}.$name", settings)
        addSharedAnimation("animation.${unsafeParent.name}.$name", name)
    }

    /**
     * add an animation that is defined somewhere else, like another entity
     * if you are unsure of the correct identifier, look at /build/development_behaviorpacks/projectShort_BP/animations
     *
     * @param animIdentifier can be a single wort, monstera will attempt to add 'animation.name.' automatically
     */
    fun addSharedAnimation(animIdentifier: String, name: String = animIdentifier.split(".").last()) {
        unsafeRawEntity.apply {
            description {
                if(animationData?.containsKey(name) == true) {
                    logger().warn(
                        "(${unsafeParent.name}) Animation '${
                            name.split(".").last()
                        }' already exists (overwriting)"
                    )
                }
                addAnimation(name ,animIdentifier)
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
    fun addSharedAnimation(originalName: String, localName: String = originalName, from: Entity) {
        addSharedAnimation("animation.${from.name}.$originalName", localName)
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
    fun animController(name: String, query: Molang = Query.True, data: AnimationControllers.Controller.() -> Unit) {
        val id = "controller.animation.${unsafeParent.name}.$name"
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
    fun addSharedController(controllerIdentifier: String, query: Molang = Query.True, name: String) {
        unsafeRawEntity.apply {
            description {
                scripts {
                    animate(name, query)
                }
                //controllers are loaded the same way as animations
                addSharedAnimation(controllerIdentifier, name)
            }
        }
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
    fun addSharedController(originalName: String, localName: String = originalName, from: Entity, query: Molang) {
        addSharedController("controller.animation.${from.name}.$originalName", query, localName)
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
    fun components(data: Components.() -> Unit) {
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
    fun componentGroup(name: String, components: Components.() -> Unit) {
        unsafeRawEntity.componentGroup(name, components)
    }

    /**
     * add events
     *
     * ```
     * events {
     *     event("do_stuff") {
     *         add { }
     *     }
     * }
     * ```
     */
    fun events(name: String, data: BehEntityEvent.() -> Unit) {
        unsafeRawEntity.event(name, data)
    }

    /**
     * define spawn rules for the entity
     *
     * ```
     * populationControl()
     * condition { }
     * ```
     */
    fun spawnRule(value: SysSpawnRule.() -> Unit) {
        SysSpawnRule(unsafeParent.getIdentifier(), unsafeParent.name, addon = unsafeParent.addon).apply(value).build()
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
    fun properties(data: EntityProperties.() -> Unit) {
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
    fun craftingRecipe(data: CraftingRecipe.() -> Unit) {
        unsafeRawCraftingRecipe.apply(data)
    }

    /**
     * generate a valid loot table
     *
     * @return a valid table that can be used in the loot table component
     */
    fun generateLootTable(data: BehLootTables.() -> Unit): String {
        unsafeLootTable.apply(data)
        return unsafeParent.addon.config.paths.lootTableEntity.toString() + "/${unsafeParent.name}"
    }

    @OptIn(DebugMarker::class)
    fun build() {
        unsafeRawEntity.description {
            identifier = unsafeParent.getIdentifier()
            isSpawnable = unsafeParent.unsafeSpawnAble
            isSummonable = true
            isExperimental = false
        }

        unsafeRawEntity.debugEntity()

        unsafeRawEntity.build(
            unsafeParent.name,
            unsafeParent.addon.config.paths.behEntity,
            getVersionAsString(unsafeParent.addon.config.targetMcVersion)
        )
        if (!unsafeRawAnimations.isEmpty())
            unsafeRawAnimations.build(
                unsafeParent.name,
                unsafeParent.addon.config.paths.behAnim
            )
        if (!unsafeRawControllers.isEmpty())
            unsafeRawControllers.build(
                unsafeParent.name,
                unsafeParent.addon.config.paths.behAnimController
            )

        if(!unsafeRawCraftingRecipe.unsafe.isEmpty()) {
            unsafeRawCraftingRecipe.unsafe.build(
                unsafeParent.name,
                unsafeParent.getIdentifier() + "_spawn_egg",
                unsafeParent.addon
            )
        }

        if(!unsafeLootTable.isEmpty()) {
            BehLootTables.Entity(unsafeLootTable).build(unsafeParent.name)
        }
    }
}