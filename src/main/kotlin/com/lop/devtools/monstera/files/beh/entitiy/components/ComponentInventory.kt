package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.Config

class ComponentInventory {
    val general = mutableMapOf<String, Any>()

    var containerType: String? = null
    var inventorySize: Int? = null
    var additionalSlotPerStrength: Int? = null
    var canBeSiphonedFrom: Boolean? = null
    var private: Boolean? = null
    var restrictToOwner: Boolean? = null
    var identifier: String? = null

    /**
     * 0..1
     *
     * Overwrites the name of the entity (not the item name)
     *
     * @param displayName the name that should be displayed
     * @param entityName simply give the entity identifier, you can also give a key in form container.<entity>
     *     sample lang key looks like this: entity.chest_minecart.name=Minecart with Chest
     */
    fun containerName(displayName: String, entityName: String, config: Config? = null) {
        val langKey = if(entityName.contains("entity.")) entityName else "entity.$entityName.name"
        config?.langFileBuilder?.addonRes?.addOrReplace(langKey, displayName)
    }

    fun getData(): MutableMap<String, Any> {
        containerType?.let { general["container_type"] = it }
        inventorySize?.let { general["inventory_size"] = it }
        additionalSlotPerStrength?.let { general["additional_slots_per_strength"] = it }
        canBeSiphonedFrom?.let { general["can_be_siphoned_from"] = it }
        private?.let { general["private"] = it }
        restrictToOwner?.let { general["restrict_to_owner"] = it }
        identifier?.let { general["identifier"] = it }
        return general
    }
}