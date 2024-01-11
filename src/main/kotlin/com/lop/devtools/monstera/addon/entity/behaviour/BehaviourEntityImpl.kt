package com.lop.devtools.monstera.addon.entity.behaviour

import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.addon.entity.behaviour.components.OverwriteComponentsImpl
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.addon.recipes.CraftingRecipe
import com.lop.devtools.monstera.files.animcontroller.AnimController
import com.lop.devtools.monstera.files.animcontroller.AnimStateComponent
import com.lop.devtools.monstera.files.beh.animations.BehAnimation
import com.lop.devtools.monstera.files.beh.entitiy.BehEntityComponentGroups
import com.lop.devtools.monstera.files.beh.entitiy.BehEntityComponents
import com.lop.devtools.monstera.files.beh.entitiy.description.RuntimeIdentifier
import com.lop.devtools.monstera.files.beh.entitiy.events.BehEntityEvents
import com.lop.devtools.monstera.files.getVersionAsString
import com.lop.devtools.monstera.files.properties.EntityProperties
import com.lop.devtools.monstera.getMonsteraLogger

abstract class BehaviourEntityImpl(
    override val unsafeParent: Entity
) : BehaviourEntity, OverwriteComponentsImpl(unsafeParent) {
    private fun logger() = getMonsteraLogger("Behaviour")

    override var runtimeIdentifier: RuntimeIdentifier? = null
        set(value) {
            unsafeRawEntity.description {
                runtimeIdentifier = value
            }
            field = value
        }

    override fun animation(name: String, settings: BehAnimation.() -> Unit) {
        unsafeRawAnimations.animation("animation.${unsafeParent.name}.$name", settings)
        addSharedAnimation("animation.${unsafeParent.name}.$name", name)
    }

    override fun addSharedAnimation(animIdentifier: String, name: String) {
        unsafeRawEntity.apply {
            description {
                animations {
                    if (unsafe.general.containsKey(name)) {
                        logger().warn(
                            "(${unsafeParent.name}) Animation '${
                                name.split(".").last()
                            }' already exists (overwriting)"
                        )
                    }
                    addAnim(name, animIdentifier)
                }
            }
        }
    }

    override fun addSharedAnimation(originalName: String, localName: String, from: Entity) {
        addSharedAnimation("animation.${from.name}.$originalName", localName)
    }

    override fun animController(name: String, query: Molang, data: AnimController.() -> Unit) {
        val id = "controller.animation.${unsafeParent.name}.$name"
        unsafeRawControllers.animController(id, data)
        addSharedController(id, query, name)
    }

    override fun addSharedController(controllerIdentifier: String, query: Molang, name: String) {
        unsafeRawEntity.apply {
            description {
                scripts {
                    animate(name, query)
                }
                animations {
                    if (unsafe.general.containsKey(name)) {
                        logger().warn(
                            "(${unsafeParent.name})" + "Animation '${
                                name.split(".").last()
                            }' already exists (overwriting)"
                        )
                    }
                    addAnim(name, controllerIdentifier)
                }
            }
        }
    }

    override fun addSharedController(originalName: String, localName: String, from: Entity, query: Molang) {
        addSharedController("controller.animation.${from.name}.$originalName", query, localName)
    }

    override fun components(data: BehEntityComponents.() -> Unit) {
        unsafeRawEntity.components(data)
    }

    override fun componentGroups(data: BehEntityComponentGroups.() -> Unit) {
        unsafeRawEntity.componentGroups(data)
    }

    override fun events(data: BehEntityEvents.() -> Unit) {
        unsafeRawEntity.events(data)
    }

    override fun spawnRule(value: SysSpawnRule.() -> Unit) {
        SysSpawnRule(unsafeParent.getIdentifier(), unsafeParent.name, addon = unsafeParent.addon).apply(value).build()
    }

    override fun properties(data: EntityProperties.() -> Unit) {
        unsafeRawEntity.apply {
            description {
                properties(data)
            }
        }
    }

    override fun craftingRecipe(data: CraftingRecipe.() -> Unit) {
        unsafeRawCraftingRecipe.apply(data)
    }

    override fun AnimStateComponent.controller(name: String, query: Query, data: AnimController.() -> Unit) {
        controller("${unsafeParent.name}.$name", query)
        animController(name, Query.False, data)
    }

    override fun AnimStateComponent.controller(name: String, query: () -> Query, data: AnimController.() -> Unit) {
        controller("${unsafeParent.name}.$name", query)
        animController(name, Query.False, data)
    }

    override fun build() {
        unsafeRawEntity.description {
            identifier = unsafeParent.getIdentifier()
            isSpawnable = unsafeParent.unsafeSpawnAble
            isSummonable = true
            isExperimental = false
        }

        unsafeRawEntity.unsafe.build(
            unsafeParent.name,
            unsafeParent.addon.config.paths.behEntity,
            getVersionAsString(unsafeParent.addon.config.targetMcVersion)
        )
        if (unsafeRawAnimations.unsafe.getData().isNotEmpty())
            unsafeRawAnimations.unsafe.build(
                unsafeParent.name,
                unsafeParent.addon.config.paths.behAnim
            )
        if (unsafeRawControllers.unsafe.getData().isNotEmpty())
            unsafeRawControllers.unsafe.build(
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
    }
}