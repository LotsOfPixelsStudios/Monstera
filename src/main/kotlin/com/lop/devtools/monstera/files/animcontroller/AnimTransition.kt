package com.lop.devtools.monstera.files.animcontroller

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.addon.molang.Molang

@Suppress("MemberVisibilityCanBePrivate", "unused")
class AnimTransition: MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        /**
         * access to all defined transitions
         */
        val general = mutableListOf<Pair<String, Any>>()

        @Deprecated("not used", ReplaceWith("getAsList"))
        override fun getData(): MutableMap<String, Any> {
            error("Use getAsList")
        }

        fun getAsList(): List<MutableMap<String, Any>> = general.map {
            mutableMapOf(it.first to it.second)
        }
    }

    /**
     * ```
     * transition("a", "1")
     * transition("b", Query.allTags eq 2)
     * transition("state") {
     *     Query.hasRider and (!Query.variant eq 1)
     * }
     * ```
     */
    fun transition(state: String, query: () -> Molang) {
        transition(state, query())
    }

    /**
     * ```
     * transition("a", "1")
     * transition("b", Query.allTags eq 2)
     * transition("state") {
     *     Query.hasRider and (!Query.variant eq 1)
     * }
     * ```
     */
    fun transition(state: String, query: Molang) {
        transition(state, query.data)
    }

    /**
     * ```
     * transition("a", "1")
     * transition("b", Query.allTags eq 2)
     * transition("state") {
     *     Query.hasRider and (!Query.variant eq 1)
     * }
     * ```
     */
    fun transition(state: String, query: String) {
        unsafe.general.add(state to query)
    }
}