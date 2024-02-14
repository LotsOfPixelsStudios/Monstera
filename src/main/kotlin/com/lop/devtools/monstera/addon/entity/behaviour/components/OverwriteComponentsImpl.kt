package com.lop.devtools.monstera.addon.entity.behaviour.components

import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.addon.sound.Sound
import com.lop.devtools.monstera.addon.sound.SoundData
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.components.scraped.Inventory
import com.lop.devtools.monstera.files.beh.entitiy.components.scraped.Loot
import com.lop.devtools.monstera.files.beh.entitiy.components.scraped.Rideable
import com.lop.devtools.monstera.files.beh.entitiy.events.BehEntityEvent
import com.lop.devtools.monstera.files.beh.tables.loot.BehLootTables
import com.lop.devtools.monstera.files.res.sounds.CategorySound
import com.lop.devtools.monstera.files.res.sounds.SoundCategory


abstract class OverwriteComponentsImpl(override val unsafeParent: Entity) : OverwriteComponents {
    override fun Loot.table(tableName: String, table: BehLootTables.() -> Unit) {
        this.table = ""
        genTable(tableName, unsafeParent.addon, table)
    }

    override fun Rideable.exitText(displayText: String, key: String) {
        exitText(displayText, key, unsafeParent.addon.config)
    }

    override fun Rideable.interactText(displayName: String?, key: String) {
        interactText(displayName, key, unsafeParent.addon.config)
    }

    override fun Components.sound(data: Sound.() -> Unit): String {
        val soundData = SoundData(unsafeParent.addon)
        soundData.category = SoundCategory.NEUTRAL
        soundData.categorySound = CategorySound.ENTITY
        unsafeParent.unsafeSoundData.add(soundData.apply(data))
        return soundData.identifier
    }

    override fun Inventory.containerName(displayName: String) {
        containerName(displayName, unsafeParent.name)
    }

    override fun BehEntityEvent.setProperty(property: String, value: Any) {
        setProperty(property, value, unsafeParent.addon)
    }
}