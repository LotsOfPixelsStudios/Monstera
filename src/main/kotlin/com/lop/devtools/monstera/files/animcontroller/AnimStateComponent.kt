package com.lop.devtools.monstera.files.animcontroller

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.getMonsteraLogger

@Suppress("MemberVisibilityCanBePrivate", "unused")
class AnimStateComponent: MonsteraFile {
    private fun logger() = getMonsteraLogger("AnimController State")
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        /**
         * access to all defined state components
         */
        val general = mutableMapOf<String, Any>()
        val animationsWithQuery = mutableListOf<Map<String, String>>()
        val transitions: MutableList<MutableMap<String, Any>> = mutableListOf()

        override fun getData(): MutableMap<String, Any> {
            val tempAnimations: MutableList<Any> = mutableListOf()
            tempAnimations.addAll(unsafe.animationsWithQuery)
            if (!animation.isNullOrEmpty()) {
                tempAnimations.addAll(animation!!)
            }
            if (tempAnimations.isNotEmpty())
                unsafe.general["animations"] = tempAnimations
            onEntry?.let { unsafe.general["on_entry"] = it }
            onExit?.let { unsafe.general["on_exit"] = it }
            blendTransition?.let { unsafe.general["blend_transition"] = it }

            onEntry?.forEach {
                if (!it.startsWith("@") && !it.startsWith("/") && !it.endsWith(";")) {
                    logger().warn("Found onEntry Command/Event without correct prefix: '$it'," +
                            " expected: '@$it' or '/$it'")
                }
            }
            onExit?.forEach {
                if (!it.startsWith("@") && !it.startsWith("/") && !it.endsWith(";")) {
                    logger().warn("Found onExit Command/Event without correct prefix: '$it'," +
                            " expected: '@$it' or '/$it'")
                }
            }

            if(transitions.isNotEmpty()) {
                general["transitions"] = transitions
            }

            return unsafe.general
        }
    }

    var animation: MutableList<String>? = null
        set(value) {
            if(value == null || field == null) {
                field = value
            } else {
                field?.addAll(value) ?: error("field changed to null while trying to write to it!")
            }
        }
    var onEntry: ArrayList<String>? = null
        set(value) {
            if(value == null || field == null) {
                field = value
            } else {
                field?.addAll(value) ?: error("field changed to null while trying to write to it!")
            }
        }
    var onExit: ArrayList<String>? = null
        set(value) {
            if(value == null || field == null) {
                field = value
            } else {
                field?.addAll(value) ?: error("field changed to null while trying to write to it!")
            }
        }
    var blendTransition: Float? = null

    /**
     * 0..1
     *
     * transition tag
     *
     * ```
     * transitions {
     *     transition("state") { Query.True }
     * }
     * ```
     */
    fun transitions(settings: AnimTransition.() -> Unit) {
        unsafe.transitions.addAll(AnimTransition().apply(settings).unsafe.getAsList())
    }

    fun animation(name: String, query: Query) {
        animation(name, query.toString())
    }

    fun animation(name: String, query: String) {
        unsafe.animationsWithQuery.add(mapOf(name to query))
    }

    /**
     * Reference an Animation Controller and decide when it should activate with a query
     *
     * @param identifier the name of the anim controller, if the name misses the `controller.animation` in the name it is assumed
     * that the entity has the controller defined, if this is not the case you have to provide the entity
     * @param query the query when the controller should be active
     */
    fun controller(identifier: String, query: Query) {
        val sanitisedName = identifier.removePrefix("controller.animation.")
        animation("controller.animation.$sanitisedName", query)
    }

    /**
     * Reference an Animation Controller and decide when it should activate with a query
     *
     * @param identifier the name of the anim controller, if the name misses the `controller.animation` in the name it is assumed
     * that the entity has the controller defined, if this is not the case you have to provide the entity
     * @param query the query when the controller should be active
     */
    fun controller(identifier: String, query: () -> Query) {
        controller(identifier, query())
    }
}