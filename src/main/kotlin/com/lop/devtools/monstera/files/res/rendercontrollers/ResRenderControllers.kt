package com.lop.devtools.monstera.files.res.rendercontrollers

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.api.MonsteraBuildableFile
import com.lop.devtools.monstera.files.MonsteraBuilder
import com.lop.devtools.monstera.files.MonsteraMapFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.sanitiseFilename
import com.lop.devtools.monstera.getMonsteraLogger
import java.nio.file.Path

class ResRenderControllers : MonsteraBuildableFile, MonsteraRawFile() {
    override fun build(filename: String, path: Path?, version: String?): Result<Path> {
        val selPath = path ?: Addon.active?.config?.paths?.resRenderControllers ?: run {
            getMonsteraLogger(this.javaClass.name).error("Could not Resolve a path for res render controller file '$filename' as no addon was initialized!")
            return Result.failure(Error("Could not Resolve a path for res render controller file '$filename' as no addon was initialized!"))
        }
        Addon.active?.config?.formatVersions?.resRendercontroller?.let { formatVersion = it }
        version?.let { formatVersion = it }

        val target = MonsteraBuilder.buildTo(
            selPath,
            sanitiseFilename(filename, "json"),
            this
        )
        return Result.success(target)
    }

    fun isEmpty() : Boolean = renderControllers?.isEmpty() ?: true

    @SerializedName("format_version")
    @Expose
    var formatVersion: String = "1.10.0"

    @SerializedName("render_controllers")
    @Expose
    @JsonAdapter(MonsteraMapFileTypeAdapter::class)
    var renderControllers: MutableMap<String, ResRenderController>? = null
        @MonsteraBuildSetter set

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
    @OptIn(MonsteraBuildSetter::class)
    fun controllers(
        entityName: String,
        settings: ResRenderController.() -> Unit
    ) {
        val controllerName = "controller.render.${entityName.removePrefix("controller.render.")}"
        renderControllers = (renderControllers ?: mutableMapOf()).apply {
            get(controllerName)?.apply(settings) ?: run {
                put(controllerName, ResRenderController().apply(settings))
            }
        }
    }
}