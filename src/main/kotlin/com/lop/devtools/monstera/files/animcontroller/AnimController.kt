package com.lop.devtools.monstera.files.animcontroller

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.addon.molang.Query

@Suppress("MemberVisibilityCanBePrivate", "unused")
class AnimController : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        /**
         * mostly just the initial state, anim stats are applied in getData()
         */
        val general = mutableMapOf<String, Any>()

        /**
         * the anim state obj where all anim states are saved
         */
        val animStates = AnimState()

        val variables = arrayListOf<String>()

        override fun getData(): MutableMap<String, Any> {
            if (unsafe.variables.isNotEmpty()) {
                val initState: String =
                    if (initialState != null)
                        initialState
                            ?: throw IllegalStateException("Initial state of anim controller has changed while building!")
                    else if (unsafe.animStates.unsafe.getData().containsKey("default"))
                        "default"
                    else
                        unsafe.animStates.unsafe.getData().keys.firstOrNull()
                            ?: throw IllegalStateException("declared variables in anim controller without any other states to transition to!")

                animStates {
                    animState("variable_init") {
                        onEntry = this@AnimController.unsafe.variables
                        transitions { transition("variable_init_2") { Query.True } }
                    }
                    animState("variable_init_2") {
                        onEntry = this@AnimController.unsafe.variables
                        transitions { transition(initState) { Query.True } }
                    }
                }
                unsafe.general["initial_state"] = "variable_init"
            } else {
                initialState?.let { unsafe.general["initial_state"] = it }
            }

            if (unsafe.animStates.unsafe.getData().isNotEmpty()) unsafe.general["states"] =
                unsafe.animStates.unsafe.getData()
            return unsafe.general
        }
    }

    var initialState: String? = null

    /**
     * initialize variables to use in transitions, onEntry & onExit
     *
     * Note: this is a "legacy" feature it might work to just initialize the variables yourself, this is just a fallback
     * option to create the states that try to initialize the variables
     *
     * ```
     * variables {
     *      set("my_var", Query.bodyYRotation(1))
     *      set("my_var", "5")
     *      set(Variable.new("my_var" to Query.headXRotation(2)))
     * }
     * ```
     */
    fun variables(data: AnimControllerVariables.() -> Unit) {
        unsafe.variables.addAll(AnimControllerVariables().apply(data).unsafe.getAsList())
    }

    /**
     * 1
     *
     * ````
     * initialState = "default"
     * animStates {
     *     animState("default") {
     *         //...
     *     }
     * }
     * ````
     */
    fun animStates(settings: AnimState.() -> Unit) {
        unsafe.animStates.apply(settings)
    }

    /**
     * ```
     * initialState = "default"
     * animStates {
     *     animState("default") {
     *          //...
     *     }
     * }
     * ```
     */
    fun states(data: AnimState.() -> Unit) = animStates(data)
}