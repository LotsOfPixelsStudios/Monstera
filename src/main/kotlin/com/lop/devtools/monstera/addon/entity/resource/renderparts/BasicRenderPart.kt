package com.lop.devtools.monstera.addon.entity.resource.renderparts

import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.files.res.entities.ResEntity
import com.lop.devtools.monstera.files.res.rendercontrollers.ResRenderControllers


class BasicRenderPart(override val partName: String, query: Molang, override val unsafeParent: Entity) :
    RenderPartImpl(unsafeParent) {
    override val query: String = query.data
    override val unsafeRenderController: ResRenderControllers = unsafeParent.unsafeResourceEntity.unsafeRenderController
    override val unsafeRawEntity: ResEntity = unsafeParent.unsafeResourceEntity.unsafeRawEntity
}