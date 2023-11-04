package com.lop.devtools.monstera.files.res.rendercontrollers

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.MonsteraBuilder
import java.nio.file.Path

class ResRenderControllers: MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        /**
         * access to all defined animations
         */
        val general = mutableMapOf<String, Any>()
        val controllers = mutableMapOf<String, ResRenderController>()

        override fun getData(): MutableMap<String, Any> {
            controllers.forEach { (name, controller) ->
                val newName = name.removePrefix("controller.render.")
                general["controller.render.$newName"] = controller.unsafe.getData()
            }
            return general
        }

        fun build(
            filename: String,
            path: Path,
            version: String = "1.8.0"
        ) {
            val sanFile = filename
                .removeSuffix(".json")
                .replace("-", "_")
                .replace(" ", "_")
            MonsteraBuilder.buildTo(
                path, "$sanFile.json", mutableMapOf(
                    "format_version" to version,
                    "render_controllers" to getData()
                )
            )
        }
    }

    /**
     * 1..*
     *
     * don't need to put controller.render. in front of the name
     *
     * ```
     * controllers("ocelot") {
     *     arrays {
     *         textures(namespace = "layer1", query = Query.variant.toString()) {
     *             add("layer1_var1")
     *             add("layer1_var2")
     *             add("layer1_var3")
     *             addAll(arrayListOf("layer1_var4", "layer1_var5"))
     *         }
     *         geometries("layer1") {
     *             //...
     *         }
     *     }
     *     texture("layer2")
     *     materials()
     *     geometry()
     *     onHurtColor(0.2f, 0.1f, 0.8f, 0.5f)
     *     onFireColor(0.2f, 0.1f, 0.8f, 0.5f)
     *     overlayColor(
     *         "variable.is_invulnerable ? 1.0 : this",
     *         "variable.is_invulnerable ? 1.0 : this",
     *         "variable.is_invulnerable ? 1.0 : this",
     *         "variable.is_invulnerable ? query.overlay_alpha : this"
     *     )
     *     uvAnim {
     *         offset(1, Query.lifeTime)
     *         scale(1, "1/4")
     *     }
     * }
     * ```
     */
    fun controllers(
        entityName: String,
        settings: ResRenderController.() -> Unit
    ) {
        unsafe.controllers[entityName] =
            //apply data if exist or else create a new obj
            unsafe.controllers[entityName]?.apply(settings) ?: ResRenderController().apply(settings)
    }
}