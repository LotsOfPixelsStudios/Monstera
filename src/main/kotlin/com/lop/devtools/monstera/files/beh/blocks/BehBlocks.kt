@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.lop.devtools.monstera.files.beh.blocks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.api.MonsteraBuildableFile
import com.lop.devtools.monstera.files.MonsteraBuilder
import com.lop.devtools.monstera.getMonsteraLogger
import java.lang.Error
import java.nio.file.Path

class BehBlocks : MonsteraBuildableFile {
    override fun build(filename: String, path: Path?, version: String?): Result<Path> {
        val sanFile = filename
            .removeSuffix(".json")
            .replace("-", "_")
            .replace(" ", "_")
        val selPath = path ?: Addon.active?.config?.paths?.behBlocks ?: run {
            getMonsteraLogger(this.javaClass.name).error("Could not Resolve a path for block file '$sanFile' as no addon was initialized!")
            return Result.failure(Error("Could not Resolve a path for block file '$sanFile' as no addon was initialized!"))
        }

        val target = MonsteraBuilder.buildTo(
            selPath,
            "$sanFile.json",
            FileHeader(
                version ?: Addon.active?.config?.formatVersions?.behBlock ?: "1.20.50",
                this
            )
        )
        return Result.success(target)
    }

    /**
     * load json blocks with this class
     */
    data class FileHeader(
        @SerializedName("format_version")
        @Expose
        var formatVersion: String = "1.20.50",

        @SerializedName("minecraft:block")
        @Expose
        var block: BehBlocks
    )

    @SerializedName("description")
    @Expose
    var descriptionData: Description? = null
        @MonsteraBuildSetter set

    @SerializedName("components")
    @Expose
    var componentsData: Components? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    fun description(data: Description.() -> Unit) {
        descriptionData = (descriptionData ?: Description()).apply(data)
    }

    /**
     * ```
     * geometry()
     * materialInstance()
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun components(data: Components.() -> Unit) {
        componentsData = (componentsData ?: Components()).apply(data)
    }

    class Description {
        @SerializedName("components")
        @Expose
        var identifier: String? = null
    }

    class Components {
        @SerializedName("minecraft:geometry")
        @Expose
        var geometry: String? = null

        @SerializedName("minecraft:material_instances")
        @Expose
        var materialInstance: MutableMap<String, MaterialSettings>? = null
            @MonsteraBuildSetter set

        /**
         * ```
         * all("path")
         * site("*", "path")
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun materialInstance(data: MaterialInstance.() -> Unit) {
            materialInstance = (materialInstance
                ?: mutableMapOf()).also { it.putAll(MaterialInstance().apply(data).materialSettingsSite) }
        }
    }

    class MaterialInstance {
        val materialSettingsSite = mutableMapOf<String, MaterialSettings>()

        fun all(settings: MaterialSettings.() -> Unit) {
            if (materialSettingsSite.containsKey("*")) {
                materialSettingsSite["*"]!!.apply(settings)
            } else {
                materialSettingsSite["*"] = MaterialSettings().apply(settings)
            }
        }
    }

    class MaterialSettings {
        @SerializedName("texture")
        @Expose
        var texture: String? = null

        @SerializedName("render_method")
        @Expose
        var renderMethod: RenderMethod? = null

        @SerializedName("ambient_occlusion")
        @Expose
        var ambientOcclusion: Boolean? = null

        @SerializedName("face_dimming")
        @Expose
        var faceDimming: Boolean? = null

        enum class RenderMethod(val s: String) {
            OPAQUE("opaque"),
            DOUBLE_SIDED("double_sided"),
            ALPHA_TEST("alpha_test"),
            BLEND("blend");

            override fun toString(): String {
                return s
            }
        }
    }
}