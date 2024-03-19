package com.lop.devtools.monstera.files.res.entities.comp

import com.lop.devtools.monstera.addon.molang.Molang

class ResEntityRenderCon {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    val unsafe = Unsafe()

    inner class Unsafe {
        /**
         * access to all defined animations
         */
        val general = arrayListOf<Any>()

        fun getData(): ArrayList<Any> {
            return unsafe.general
        }
    }

    /**
     * 1..*
     *
     * @param id: name of the controller
     */
    fun renderCon(id: String) {
        unsafe.general.add(id)
    }

    /**
     * 1..*
     *
     * @param id name of the controller
     * @param query if more than one controller, select with query
     */
    @Deprecated("User DSL", ReplaceWith("renderCon(id){}"))
    fun renderCon(id: String, query: String) {
        unsafe.general.add(mutableMapOf(id to query))
    }

    /**
     * 1..*
     *
     * @param id name of the controller
     * @param query if more than one controller, select with query
     */
    fun renderCon(id: String, query: Unit.() -> Molang) {
        unsafe.general.add(mutableMapOf(id to query(Unit).data))
    }

    /**
     * 1..*
     *
     * @param id name of the controller
     * @param query if more than one controller, select with query
     */
    fun renderCon(id: String, query: Molang) {
        unsafe.general.add(mutableMapOf(id to query.data))
    }
}