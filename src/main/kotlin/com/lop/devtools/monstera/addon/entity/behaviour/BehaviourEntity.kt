package com.lop.devtools.monstera.addon.entity.behaviour

import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.addon.entity.behaviour.components.OverwriteComponents
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.addon.recipes.CraftingRecipe
import com.lop.devtools.monstera.files.animcontroller.AnimController
import com.lop.devtools.monstera.files.animcontroller.AnimationControllers
import com.lop.devtools.monstera.files.beh.animations.BehAnimation
import com.lop.devtools.monstera.files.beh.animations.BehAnimations
import com.lop.devtools.monstera.files.beh.entitiy.BehEntity
import com.lop.devtools.monstera.files.beh.entitiy.BehEntityComponentGroups
import com.lop.devtools.monstera.files.beh.entitiy.BehEntityComponents
import com.lop.devtools.monstera.files.beh.entitiy.description.RuntimeIdentifier
import com.lop.devtools.monstera.files.beh.entitiy.events.BehEntityEvents
import com.lop.devtools.monstera.files.properties.EntityProperties

interface BehaviourEntity: OverwriteComponents, AnimationControllerExtensions {
    val unsafeRawEntity: BehEntity
    val unsafeRawAnimations: BehAnimations
    val unsafeRawControllers: AnimationControllers
    val unsafeRawCraftingRecipe: CraftingRecipe

    /**
     * add a runtimeIdentifier like guardian
     */
    var runtimeIdentifier: RuntimeIdentifier?

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
    fun animation(name: String, settings: BehAnimation.() -> Unit)

    /**
     * add an animation that is defined somewhere else, like another entity
     * if you are unsure of the correct identifier, look at /build/development_behaviorpacks/projectShort_BP/animations
     *
     * @param animIdentifier can be a single wort, monstera will attempt to add 'animation.name.' automatically
     */
    fun addSharedAnimation(animIdentifier: String, name: String = animIdentifier.split(".").last())

    /**
     * add an animation from another entity
     *
     * @param originalName the name of the animation at the original entity, that first implemented the animation
     * @param localName the name of the animation in the current entity,
     * this can differ from the originalName if it helps the naming convention - optional, defaults to originalName
     * @param from the original entity where the animation was defined
     */
    fun addSharedAnimation(originalName: String, localName: String = originalName, from: Entity)

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
    fun animController(name: String, query: Molang = Query.True, data: AnimController.() -> Unit)

    /**
     * add a controller that is defined somewhere else, like another entity
     *
     * if you are unsure of the correct identifier, look at /build/development_behaviorpacks/projectShort_BP/animation_controllers
     *
     * @param controllerIdentifier can be a single wort, monstera will attempt to add 'controller.animation.name.' automatically
     */
    fun addSharedController(
        controllerIdentifier: String,
        query: Molang = Query.True,
        name: String = controllerIdentifier.split(".").last()
    )

    /**
     * add an animation from another entity
     *
     * @param originalName the name of the controller at the original entity, that first implemented the controller
     * @param localName the name of the controller in the current entity,
     * this can differ from the originalName if it helps the naming convention - optional, defaults to originalName
     * @param from the original entity where the controller was defined
     * @param query when the controller should be active - optional, default always
     */
    fun addSharedController(
        originalName: String,
        localName: String = originalName,
        from: Entity,
        query: Molang = Query.True
    )


    /**
     * add default behaviour components to the entity
     *
     * ```
     * components {
     *     physics { }
     * }
     * ```
     */
    fun components(data: BehEntityComponents.() -> Unit)

    /**
     * add component groups
     *
     * ```
     * componentGroups {
     *     componentGroup("my_component_group") {
     *          //...
     *     }
     * }
     * ```
     */
    fun componentGroups(data: BehEntityComponentGroups.() -> Unit)

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
    fun events(data: BehEntityEvents.() -> Unit)

    /**
     * define spawn rules for the entity
     *
     * ```
     * populationControl()
     * condition { }
     * ```
     */
    fun spawnRule(value: SysSpawnRule.() -> Unit)

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
    fun properties(data: EntityProperties.() -> Unit)

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
    fun craftingRecipe(data: CraftingRecipe.() -> Unit)

    /**
     * internal function for building the beh files
     */
    fun build()
}