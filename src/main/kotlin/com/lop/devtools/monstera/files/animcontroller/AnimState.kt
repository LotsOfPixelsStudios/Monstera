package com.lop.devtools.monstera.files.animcontroller

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

@DslMarker
annotation class AnimationState

@Suppress("unused", "MemberVisibilityCanBePrivate")
class AnimState: MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        /**
         * access to all defined anim states
         */
        val general = mutableMapOf<String, Any>()
        val states = mutableMapOf<String, AnimStateComponent>()

        override fun getData(): MutableMap<String, Any> {
            unsafe.general.putAll(unsafe.states.map { it.key to it.value.unsafe.getData() })
            return unsafe.general
        }
    }

    /**
     * 1..*
     *
     * @param name: Name of the State, no naming conventions/ use at least once "default"
     * @param settings: see [AnimStateComponent]
     *
     * ```
     * animState("name") {
     *     animation = arrayListOf("anim1", "anim2")
     *     transitions {
     *         //...
     *     }
     *     onEntry = arrayListOf("/say test")
     *     onExit = arrayListOf("/say test")
     *     blendTransition = 0.2f
     * }
     * ```
     */
    @AnimationState
    fun animState(
        name: String,
        settings: AnimStateComponent.() -> Unit
    ) {
        unsafe.states[name]?.apply(settings) ?: run {
            unsafe.states[name] = AnimStateComponent().apply(settings)
        }
    }

    /**
     * 1..*
     *
     * @param name: Name of the State, no naming conventions/ use at least once "default"
     * @param data: see [AnimStateComponent]
     *
     * ```
     * animState("name") {
     *     animation = arrayListOf("anim1", "anim2")
     *     transitions {
     *         //...
     *     }
     *     onEntry = arrayListOf("/say test")
     *     onExit = arrayListOf("/say test")
     *     blendTransition = 0.2f
     * }
     * ```
     */
    @AnimationState
    fun state(name: String, data: AnimStateComponent.() -> Unit) = animState(name, data)
}