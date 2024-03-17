package com.lop.devtools.monstera.files.beh.entitiy.events

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.DebugMarker
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

open class BehEntityEvent {
    open class ContainsFilter : BehEntityEvent() {
        @SerializedName("filters")
        @Expose
        var filter: BehEntityFilter? = null
            @MonsteraBuildSetter set

        @OptIn(MonsteraBuildSetter::class)
        fun filters(data: BehEntityFilter. () -> Unit) {
            filter = (filter ?: BehEntityFilter()).apply(data)
        }
    }

    open class ContainsWeight : ContainsFilter() {
        @SerializedName("weight")
        @Expose
        var weight: Number? = null
    }

    @SerializedName("add")
    @Expose
    var addGroupsData: BehEntityAddRemove? = null
        @MonsteraBuildSetter set

    @SerializedName("remove")
    @Expose
    var removeGroupsData: BehEntityAddRemove? = null
        @MonsteraBuildSetter set

    @SerializedName("sequence")
    @Expose
    var sequenceData: MutableList<ContainsFilter>? = null
        @MonsteraBuildSetter set

    @SerializedName("randomize")
    @Expose
    var randomizeData: MutableList<ContainsWeight>? = null
        @MonsteraBuildSetter set

    @SerializedName("set_property")
    @Expose
    var setPropertyData: MutableMap<String, Any>? = null
        @MonsteraBuildSetter set

    var queueCommand = null


    /**
     * 0..1
     *
     * remove component groups - normal remove in the event
     * @param settings: the component{} fun for defining all the component groups
     * @ sample remove{component("...")}
     */
    @OptIn(MonsteraBuildSetter::class)
    fun remove(settings: BehEntityAddRemove.() -> Unit) {
        removeGroupsData = (removeGroupsData ?: BehEntityAddRemove()).apply(settings)
    }

    /**
     * 0..1
     *
     * add component groups - normal add in a event
     * @param settings: the component{} fun for defining all the component groups
     * @ sample add{component("...")}
     */
    @OptIn(MonsteraBuildSetter::class)
    fun add(settings: BehEntityAddRemove.() -> Unit) {
        addGroupsData = (addGroupsData ?: BehEntityAddRemove()).apply(settings)
    }

    /**
     * 0..1
     *
     * add a sequence, component groups will be modified in the given order
     *
     * @param settings: add as much add{} or remove{} 's as you want, you can also add a filter see [BehEntitySequence]
     * @ sample sequence{add{...}; remove{...}; add{...}}
     */
    @OptIn(MonsteraBuildSetter::class)
    fun sequence(settings: BehEntitySequence.() -> Unit) {
        sequenceData =
            (sequenceData ?: mutableListOf()).also { it.addAll(BehEntitySequence().apply(settings).sequenceEvents) }
    }

    /**
     * 0..1
     * add component groups with different weights
     *
     * @param settings: randomComp()
     */
    @OptIn(MonsteraBuildSetter::class)
    fun randomize(settings: BehEntityRandomize.() -> Unit) {
        randomizeData =
            (randomizeData ?: mutableListOf()).also { it.addAll(BehEntityRandomize().apply(settings).randomEvents) }
    }

    /**
     * 0..*
     *
     * modify properties defined in the description of the entity
     *
     * @param property the property to modify
     * @param value the value of the property
     */
    @OptIn(MonsteraBuildSetter::class)
    fun setProperty(property: String, value: Any, addon: Addon) {
        val key = if (property.startsWith(addon.config.namespace)) property else "${addon.config.namespace}:$property"
        val propertyValue = if (value is Molang) value.toString() else value
        setPropertyData = (setPropertyData ?: mutableMapOf()).apply {
            this[key] = propertyValue
        }
    }

    /**
     * 0..*
     *
     * modify properties defined in the description of the entity
     *
     * @param property the property to modify
     * @param value the value of the property
     */
    fun Addon.setProperty(property: String, value: Any) {
        setProperty(property, value, this)
    }

    /**
     * returns all component group names that where added with events
     */
    @DebugMarker
    fun getAddedGroups(): MutableList<String> {
        val groups = mutableListOf<String>()

        groups.addAll(addGroupsData?.componentGroups ?: listOf())
        groups.addAll(sequenceData?.flatMap { it.getAddedGroups() } ?: listOf())
        groups.addAll(randomizeData?.flatMap { it.getAddedGroups() } ?: listOf())
        
        return groups
    }
}