package com.lop.devtools.monstera.addon.entity.behaviour

import com.lop.devtools.monstera.addon.api.MonsteraApi
import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.addon.sound.Sound
import com.lop.devtools.monstera.files.beh.entitiy.components.scraped.Inventory
import com.lop.devtools.monstera.files.beh.entitiy.components.scraped.Rideable
import com.lop.devtools.monstera.files.beh.entitiy.events.BehEntityEvent
import com.lop.devtools.monstera.files.res.sounds.CategorySound
import com.lop.devtools.monstera.files.res.sounds.SoundCategory


open class OverwriteComponents(private val entityData: Entity.Data) {
    fun Rideable.exitText(displayText: String, key: String) {
        exitText(displayText, key, entityData.addon.config)
    }

    fun Rideable.interactText(
        displayName: String?,
        key: String = "action.interact." + displayName?.replace(" ", "_")?.lowercase()
    ) {
        interactText(displayName, key, entityData.addon.config)
    }

    /**
     * add a sound to a component
     *
     * ```
     * sound(identifier = "block.sand.fall") {
     *     pitch = 1f to 1.2f   //default 1 to 1
     *     volume = 0.7f to 1f  //default 1 to 1
     *     maxDistance = 16
     *     minDistance = 2
     *     category = SoundCategory.NEUTRAL //default
     *     categorySound = CategorySound.ENTITY //consider changing it to DEFAULT_ENTITY
     *     sound(getResource(/*...*/))  //load a single ogg file with optional settings, see docs of [Sounds.sound()], can be called multiple times
     *    onEvent(event = SoundEvent.DEFAULT, pitch = 1 to 2, volume = 0.7f to 1)
     *    onEvent(event = SoundEvent.PLACE, pitch = 0.9f to 1, volume = 0.7f to 1)
     *
     *    //you can also import sounds definitions with some limitations like
     *    importSound("ambient.basalt_deltas.loop") //note: this will overwrite the identifier if one is set!
     *
     *    //or define multiple Sounds that will be randomly selected
     *    sound(arrayListOf(
     *        getResource("file.ogg") to SoundDefData(is3D = false, volume = 1.2f, pitch = 1, weight = 3),
     *        getResource("file.ogg") to SoundDefData(is3D = false, volume = 1.1f, pitch = 1, weight = 2),
     *        getResource("file.ogg") to SoundDefData() //use default values
     *    ))
     * }
     * ```
     *
     * @return the sound identifier
     */
    @MonsteraApi
    fun sound(identifier: String, data: Sound.() -> Unit): String {
        val soundData = Sound(entityData.addon)
        soundData.identifier = identifier
        soundData.category = SoundCategory.NEUTRAL
        soundData.categorySound = CategorySound.ENTITY
        unsafeParent.unsafeSoundData.add(soundData.apply(data))
        return soundData.identifier
    }

    /**
     * overwrites the name of the entity (not the item)
     */
    fun Inventory.containerName(displayName: String) {
        val langKey = "entity.${entityData.name}.name"
        entityData.addon.config.langFileBuilder.addonRes.addOrReplace(langKey, displayName)
    }

    fun BehEntityEvent.setProperty(property: String, value: Any) {
        setProperty(property, value, entityData.addon)
    }
}