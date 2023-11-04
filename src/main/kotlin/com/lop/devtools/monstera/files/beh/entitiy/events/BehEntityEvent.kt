package com.lop.devtools.monstera.files.beh.entitiy.events

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.addon.molang.Molang

class BehEntityEvent(private val parent: BehEntityEvents): MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        /**
         * access to all defined data
         */
        val general = mutableMapOf<String, Any>()

        /**
         * obj for removed groups in event
         */
        val remove = BehEntityAddRemove()

        /**
         * obj for added groups in event
         */
        val add = BehEntityAddRemove()

        /**
         * obj for sequence groups in event
         */
        val sequence = BehEntitySequence(parent)

        /**
         * obj for randomized groups in event
         */
        val randomize = BehEntityRandomize(parent)

        /**
         * set for modified properties
         */
        val setProperties = mutableMapOf<String, Any>()

        fun setProperty(property: String, value: Any) {
            val propertyValue = if (value is Molang) value.toString() else value
            unsafe.setProperties[property] = propertyValue
        }

        /**
         * build object for gson parser
         */
        override fun getData(): MutableMap<String, Any> {
            if (unsafe.remove.unsafe.getData().isNotEmpty()) unsafe.general["remove"] = unsafe.remove.unsafe.getData()
            if (unsafe.add.unsafe.getData().isNotEmpty()) unsafe.general["add"] = unsafe.add.unsafe.getData()
            if (unsafe.sequence.unsafe.getData().isNotEmpty()) unsafe.general["sequence"] = unsafe.sequence.unsafe.getData()
            if (unsafe.randomize.unsafe.getData().isNotEmpty()) unsafe.general["randomize"] = unsafe.randomize.unsafe.getData()
            if (unsafe.setProperties.isNotEmpty()) unsafe.general["set_property"] = unsafe.setProperties
            //### debugger ###
            parent.unsafe.debugAddedGroups.addAll(unsafe.add.unsafe.general)
            //################

            return unsafe.general
        }
    }

    /**
     * access the unsafe context, alternatively use "unsafe."
     */
    fun unsafe(data: Unsafe.() -> Unit) {
        unsafe.apply(data)
    }

    /**
     * 0..1
     *
     * remove component groups - normal remove in the event
     * @param settings: the component{} fun for defining all the component groups
     * @ sample remove{component("...")}
     */
    fun remove(settings: BehEntityAddRemove.() -> Unit) {
        unsafe.remove.apply(settings)
    }

    /**
     * 0..1
     *
     * add component groups - normal add in a event
     * @param settings: the component{} fun for defining all the component groups
     * @ sample add{component("...")}
     */
    fun add(settings: BehEntityAddRemove.() -> Unit) {
        unsafe.add.apply(settings)
    }

    /**
     * 0..1
     *
     * add a sequence, component groups will be modified in the given order
     *
     * @param settings: add as much add{} or remove{} 's as you want, you can also add a filter see [BehEntitySequence]
     * @ sample sequence{add{...}; remove{...}; add{...}}
     */
    fun sequence(settings: BehEntitySequence.() -> Unit) {
        unsafe.sequence.apply(settings)
    }

    /**
     * 0..1
     * add component groups with different weights
     *
     * @param settings: randomComp()
     */
    fun randomize(settings: BehEntityRandomize.() -> Unit) {
        unsafe.randomize.apply(settings)
    }

    /**
     * 0..*
     *
     * modify properties defined in the description of the entity
     *
     * @param property the property to modify
     * @param value the value of the property
     */
    @Deprecated("Move to addon")    //TODO
    fun setProperty(property: String, value: Any) {
        //val key = if (property.startsWith(parent.props.namespace)) property else "${parent.props.namespace}:$property"
        val propertyValue = if (value is Molang) value.toString() else value
        unsafe.setProperties[property] = propertyValue
    }
}