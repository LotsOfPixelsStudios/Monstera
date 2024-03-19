package com.lop.devtools.monstera.addon.entity

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.entity.behaviour.BasicBehaviourEntity
import com.lop.devtools.monstera.addon.entity.behaviour.BehaviourEntity
import com.lop.devtools.monstera.addon.entity.resource.BasicResourceEntity
import com.lop.devtools.monstera.addon.entity.resource.ResourceEntity

class BasicEntity(addon: Addon, override var name: String, override var displayName: String) : EntityImpl(addon) {
    override val unsafeBehaviourEntity: BehaviourEntity = BasicBehaviourEntity(this)
    override val unsafeResourceEntity: ResourceEntity = BasicResourceEntity(this)
}