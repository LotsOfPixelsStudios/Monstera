package com.lop.devtools.monstera.addon.entity.resource

import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.files.animcontroller.AnimationControllers
import com.lop.devtools.monstera.files.res.entities.ResEntity
import com.lop.devtools.monstera.files.res.materials.Materials
import com.lop.devtools.monstera.files.res.rendercontrollers.ResRenderControllers

class BasicResourceEntity(override val unsafeParent: Entity) : ResourceEntityImpl(unsafeParent) {
    override val unsafeRawEntity: ResEntity = ResEntity()
    override val unsafeAnimations: ArrayList<Pair<String, String>> = arrayListOf()
    override val unsafeRenderController: ResRenderControllers = ResRenderControllers()
    override val unsafeControllers: AnimationControllers = AnimationControllers()
    override val unsafeComponents: ResourceEntityComponents =
        ResourceEntityComponentsImpl(unsafeParent, unsafeRawEntity, unsafeRenderController)
    override val unsafeMaterials: Materials = Materials.instance(unsafeParent.addon)
    override var unsafeApplyDefaultTexture: Boolean = true
    override var unsafeApplyDefaultGeometry: Boolean = true
}