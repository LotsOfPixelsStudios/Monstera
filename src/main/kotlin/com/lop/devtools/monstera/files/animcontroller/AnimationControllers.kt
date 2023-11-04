package com.lop.devtools.monstera.files.animcontroller

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.MonsteraBuilder
import java.nio.file.Path

@Suppress("MemberVisibilityCanBePrivate", "unused")
class AnimationControllers: MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        /**
         * access to all defined anim controllers
         */
        val general = mutableMapOf<String, Any>()
        val controllers = mutableMapOf<String, AnimController>()

        override fun getData(): MutableMap<String, Any> {
            controllers.forEach { (name, controller) ->
                val newName = name.removePrefix("controller.animation.")
                general["controller.animation.$newName"] = controller.unsafe.getData()
            }
            return general
        }

        fun build(
            filename: String,
            path: Path,
            version: String = "1.10.0"
        ) {
            val sanFile = filename
                .removeSuffix(".json")
                .replace("-", "_")
                .replace(" ", "_")
            MonsteraBuilder.buildTo(
                path, "$sanFile.json", mutableMapOf(
                    "format_version" to version,
                    "animation_controllers" to getData()
                )
            )
        }
    }

    /**
     * 1..*
     *
     * note: if the state is named default and no initial state is set, default is always executed first
     * @param name: name of the Animation Controller.
     * Naming convention: controller.animation.<EntityName>.<ControllerName>, notice: you can leave out controller.animation.
     * you only have to put <EntityName>.<ControllerName>
     * @sample sampleCon
     */
    fun animController(
        name: String,
        settings: AnimController.() -> Unit
    ) {
        unsafe.controllers[name] =
                //apply data if exist or else create a new obj
            unsafe.controllers[name]?.apply(settings) ?: AnimController().apply(settings)
    }

    private fun sampleCon() {
        animController("filename") {
            initialState = "default"
            animStates {
                //...
            }
        }
    }
}