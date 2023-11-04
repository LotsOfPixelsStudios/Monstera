package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentTransformation {
    val general = mutableMapOf<String, Any>()

    var addComponentGroups: ArrayList<String>? = null
    var beginTransformationSound: String? = null
    var dropEquipment: Boolean? = null
    var into: String? = null
    var keepLevel: Boolean? = null
    var keepOwner: Boolean? = null
    var transformationSound: String? = null

    /**
     * 0..1
     *
     * Defines the properties of the delay for the transformation
     */
    fun delay(value: CompTransformDelay.() -> Unit) {
        general["delay"] = CompTransformDelay().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        addComponentGroups?.let { general["add"] = mutableMapOf("component_groups" to it) }
        beginTransformationSound?.let { general["begin_transform_sound"] = it }
        dropEquipment?.let { general["drop_equipment"] = it }
        into?.let { general["into"] = it }
        keepLevel?.let { general["keep_level"] = it }
        keepOwner?.let { general["keep_owner"] = it }
        transformationSound?.let { general["transformation_sound"] = it }
        return general
    }
}

class CompTransformDelay {
    val general = mutableMapOf<String, Any>()

    var value: Float? = null
    var blockAssistChance: Float? = null
    var blockChance: Float? = null
    var blockMax: Int? = null
    var blockRadius: Number? = null
    var blockTypes: ArrayList<String>? = null

    fun getData(): MutableMap<String, Any> {
        value?.let { general["value"] = it }
        blockAssistChance?.let { general["block_assist_chance"] = it }
        blockChance?.let { general["block_chance"] = it }
        blockMax?.let { general["block_max"] = it }
        blockRadius?.let { general["block_radius"] = it }
        blockTypes?.let { general["block_types"] = it }
        return general
    }
}