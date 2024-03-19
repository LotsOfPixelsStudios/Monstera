package com.lop.devtools.monstera.files.beh.entitiy.events

import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class BehEntityRandomComp(private val parent: BehEntityEvents) {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    val unsafe = Unsafe()

    inner class Unsafe {
        /**
         * access to all defined data
         */
        val general = mutableMapOf<String, Any>()

        val add = BehEntityAddRemove()
        val remove = BehEntityAddRemove()
        val filters = BehEntityFilter()
        val randomize = BehEntityRandomize(parent)
    }

    /**
     * access the unsafe context, alternatively use "unsafe."
     */
    fun unsafe(data: Unsafe.() -> Unit) {
        unsafe.apply(data)
    }

    @Deprecated("use unsafe context", ReplaceWith("unsafe.general"))
    val general = mutableMapOf<String, Any>()
    var weight: Int? = null

    fun add(settings: BehEntityAddRemove.() -> Unit) {
        unsafe.add.apply(settings)
    }

    fun remove(settings: BehEntityAddRemove.() -> Unit) {
        unsafe.remove.apply(settings)
    }

    /**
     * 0..1
     *
     * put a filter on top, so this part of the sequence isn't always executed
     * @param filter default filter
     */
    fun filters(filter: BehEntityFilter.() -> Unit) {
        unsafe.filters.apply(filter)
    }

    /**
     * 0..1
     * add component groups with different weights
     *
     * @param settings: add
     */
    fun randomize(settings: BehEntityRandomize.() -> Unit) {
        unsafe.randomize.apply(settings)
    }

    fun getData(): Map<String, Any> {
        weight?.let { unsafe.general["weight"] = it }

        if (unsafe.add.unsafe.getData().isNotEmpty()) unsafe.general["add"] = unsafe.add.unsafe.getData()
        if (unsafe.remove.unsafe.getData().isNotEmpty()) unsafe.general["remove"] = unsafe.remove.unsafe.getData()
        if (unsafe.filters.getData().isNotEmpty()) unsafe.general["filters"] = unsafe.filters.getData()
        if (unsafe.randomize.unsafe.getData().isNotEmpty()) unsafe.general["randomize"] = unsafe.randomize.unsafe.getData()

        //### debugger ###
        parent.unsafe.debugAddedGroups.addAll(unsafe.add.unsafe.general)
        //################

        return unsafe.general
    }
}