package com.lop.devtools.monstera.files.beh.entitiy.events

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class BehEntitySequenceComp(private val parent: BehEntityEvents): MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        /**
         * access to all defined data
         */
        val general = mutableMapOf<String, Any>()

        val add = BehEntityAddRemove()
        val remove = BehEntityAddRemove()
        val filters = BehEntityFilter()
        val randomize = BehEntityRandomize(parent)

        override fun getData(): MutableMap<String, Any> {
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

    /**
     * 0..*
     *
     * @sample sampleAdd
     */
    fun add(add: BehEntityAddRemove.() -> Unit) {
        this.unsafe.add.apply(add)
    }

    /**
     * 0..1
     *
     * @sample sampleRemove
     */
    fun remove(remove: BehEntityAddRemove.() -> Unit) {
        this.unsafe.remove.apply(remove)
    }

    /**
     * 0..1
     *
     * @sample sampleRandomize
     */
    fun randomise(randomize: BehEntityRandomize.() -> Unit) {
        this.unsafe.randomize.apply(randomize)
    }

    /**
     * 0..1
     *
     * @sample sampleFilter
     */
    fun filters(filter: BehEntityFilter.() -> Unit) {
        this.unsafe.filters.apply(filter)
    }

    private fun sampleAdd() {
        add { componentGroups = arrayListOf("sample") }
    }

    private fun sampleRemove() {
        remove { componentGroups = arrayListOf("sample") }
    }

    private fun sampleRandomize() {
        randomise {
            randomComp {  }
        }
    }

    private fun sampleFilter() {
        filters { isFamily(value = "player") }
    }
}