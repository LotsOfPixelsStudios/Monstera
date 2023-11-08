package com.lop.devtools.monstera.addon.entity.behaviour.components

import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.addon.sound.Sound
import com.lop.devtools.monstera.addon.sound.SoundData
import com.lop.devtools.monstera.files.beh.entitiy.BehEntityComponents
import com.lop.devtools.monstera.files.beh.entitiy.components.ComponentInventory
import com.lop.devtools.monstera.files.beh.entitiy.components.ComponentLoot
import com.lop.devtools.monstera.files.beh.entitiy.events.BehEntityEvent
import com.lop.devtools.monstera.files.beh.tables.loot.BehLootTables
import com.lop.devtools.monstera.files.res.sounds.CategorySound
import com.lop.devtools.monstera.files.res.sounds.SoundCategory


abstract class OverwriteComponentsImpl(override val unsafeParent: Entity) : OverwriteComponents {
    override fun ComponentLoot.table(tableName: String, table: BehLootTables.() -> Unit) {
        genTable(tableName, unsafeParent.addon, table)
    }

    override fun BehEntityComponents.sound(data: Sound.() -> Unit): String {
        val soundData = SoundData(unsafeParent.addon)
        soundData.category = SoundCategory.NEUTRAL
        soundData.categorySound = CategorySound.ENTITY
        unsafeParent.unsafeSoundData.add(soundData.apply(data))
        return soundData.identifier
    }

    override fun ComponentInventory.containerName(displayName: String) {
        containerName(displayName, unsafeParent.name)
    }

    override fun BehEntityEvent.setProperty(property: String, value: Any) {
        setProperty(property, value, unsafeParent.addon)
    }
}