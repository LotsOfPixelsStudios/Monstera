package com.lop.devtools.monstera.files.animcontroller

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.api.MonsteraBuildableFile
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.files.MonsteraBuilder
import com.lop.devtools.monstera.getMonsteraLogger
import java.nio.file.Path

@Suppress("MemberVisibilityCanBePrivate", "unused")
class AnimationControllers: MonsteraBuildableFile {
    override fun build(filename: String, path: Path?, version: String?) {
        if(animationControllersData.isEmpty())
            return

        val sanFile = filename
            .removeSuffix(".json")
            .replace("-", "_")
            .replace(" ", "_")
        if(path == null)
            error("path may not be null! can't build anim controller when not clear if beh or res.")

        MonsteraBuilder.buildTo(path, "$sanFile.json", this)
    }

    /**
     * returns true if no controller was defined
     */
    fun isEmpty() = animationControllersData.isEmpty()

    @SerializedName("format_version")
    @Expose
    var formatVersion: String = "1.10.0"

    @SerializedName("animation_controllers")
    @Expose
    var animationControllersData: MutableMap<String, Controller> = mutableMapOf()
        @MonsteraBuildSetter set

    /**
     * adds an anim controller
     *
     * ```
     * animController("controller.animation.pig.walk_controller") {
     *     initialState = "default"
     *     state("default") { ... }
     * }
     * ```
     *
     * @param name should contain the fully qualified name like: controller.animation.pig.walk_controller
     */
    fun animController(name: String, data: Controller.() -> Unit) {
        animationControllersData[name]?.apply(data) ?: run {
            animationControllersData.put(name, Controller().apply(data))
        }
    }

    class Controller {
        @SerializedName("initial_state")
        @Expose
        var initialState: String? = null

        @SerializedName("states")
        @Expose
        var statesData: MutableMap<String, State>? = null

        fun state(name: String, data: State.() -> Unit) {
            statesData = (statesData ?: mutableMapOf()).apply {
                //check if a state with this name already exists and apply if it does
                get(name)?.apply(data) ?: run {
                    //if missing create a mew state amd apply
                    put(name, State().apply(data))
                }
            }
        }
    }

    class State {
        @SerializedName("animations")
        @Expose
        var animations: MutableList<Any>? = null
            @MonsteraBuildSetter set

        @OptIn(MonsteraBuildSetter::class)
        fun animations(vararg values: String) {
            animations = (animations ?: mutableListOf()).also { it.addAll(values) }
        }

        @OptIn(MonsteraBuildSetter::class)
        fun animation(id: String, query: Molang) {
            animations = (animations ?: mutableListOf()).also { it.add(mutableMapOf(id to query.data)) }
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

        @SerializedName("transitions")
        @Expose
        var transitionsData: MutableList<MutableMap<String, String>>? = null
            @MonsteraBuildSetter set

        @OptIn(MonsteraBuildSetter::class)
        fun transition(state: String, condition: Molang) {
            transitionsData = (transitionsData ?: mutableListOf()).apply { add(mutableMapOf(state to condition.data)) }
        }

        /**
         * note: this is only available in the beh pack
         *
         * executes commands like "/say hello" or can trigger events like "@s namespace:my_event"
         */
        @SerializedName("on_entry")
        @Expose
        var onEntry: MutableList<String>? = null
            set(value) {
                value?.forEach {
                    warnOnInvalidCommandInput(it)
                }
                field = value
            }

        /**
         * note: this is only available in the beh pack
         *
         * executes commands like "/say hello" or can trigger events like "@s namespace:my_event"
         */
        @SerializedName("on_exit")
        @Expose
        var onExit: MutableList<String>? = null
            set(value) {
                value?.forEach {
                    warnOnInvalidCommandInput(it)
                }
                field = value
            }

        private fun warnOnInvalidCommandInput(value: String) {
            if (!value.startsWith("@") && !value.startsWith("/") && !value.endsWith(";"))
                getMonsteraLogger("Anim Controller").warn(
                    "Found onEntry/onExit Command/Event without correct prefix: '$value'," +
                            " expected: '@$value' or '/$value'"
                )
        }
    }
}