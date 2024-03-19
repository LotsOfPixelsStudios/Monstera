package com.lop.devtools.monstera.addon.entity.behaviour

import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.addon.recipes.CraftingRecipe
import com.lop.devtools.monstera.files.animcontroller.AnimationControllers
import com.lop.devtools.monstera.files.beh.animations.BehAnimations
import com.lop.devtools.monstera.files.beh.entitiy.BehEntity

class BasicBehaviourEntity(unsafeParent: Entity) : BehaviourEntityImpl(unsafeParent) {
    override val unsafeRawEntity: BehEntity = BehEntity()
    override val unsafeRawAnimations: BehAnimations = BehAnimations()
    override val unsafeRawControllers: AnimationControllers = AnimationControllers()
    override val unsafeRawCraftingRecipe: CraftingRecipe = CraftingRecipe()
}