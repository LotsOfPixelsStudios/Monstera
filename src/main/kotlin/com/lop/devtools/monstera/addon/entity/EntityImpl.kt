package com.lop.devtools.monstera.addon.entity

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.entity.behaviour.BehaviourEntity
import com.lop.devtools.monstera.addon.entity.resource.ResourceEntity
import com.lop.devtools.monstera.addon.sound.SoundData
import com.lop.devtools.monstera.addon.sound.unsafeApplySoundData

abstract class EntityImpl(
    override val addon: Addon,
    override var name: String = "",
    override var displayName: String = ""
) : Entity {
    override var unsafeSpawnAble: Boolean = false
    override val unsafeSoundData: MutableList<SoundData> = mutableListOf()

    override fun getIdentifier(): String {
        return addon.config.namespace + ":" + name
    }

    override fun resource(entity: ResourceEntity.() -> Unit) {
        unsafeResourceEntity.apply(entity)
    }

    override fun behaviour(entity: BehaviourEntity.() -> Unit) {
        unsafeBehaviourEntity.apply(entity)
    }

    override fun build() {
        unsafeBehaviourEntity.build()
        unsafeResourceEntity.build()

        if (unsafeSoundData.isNotEmpty()) {
            addon.unsafeApplySoundData(unsafeSoundData, name)
        }
    }
}