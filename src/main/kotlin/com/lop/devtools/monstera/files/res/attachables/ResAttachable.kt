@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.lop.devtools.monstera.files.res.attachables

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.api.MonsteraBuildableFile
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.files.MonsteraBuilder
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.sanetiseFilename
import com.lop.devtools.monstera.getMonsteraLogger
import java.lang.Error
import java.nio.file.Path

class ResAttachable : MonsteraBuildableFile, MonsteraRawFile() {
    override fun build(filename: String, path: Path?, version: String?): Result<Path> {
        if(isEmpty())
            return Result.failure(Error("File is empty!"))
        val selPath = path ?: Addon.active?.config?.paths?.resAttachable ?: run {
            getMonsteraLogger(this.javaClass.name).error("Could not Resolve a path for attachable file '$filename' as no addon was initialized!")
            return Result.failure(Error("Could not Resolve a path for attachable file '$filename' as no addon was initialized!"))
        }

        val target = MonsteraBuilder.buildTo(
            selPath,
            sanetiseFilename(filename, "json"),
            FileHeader(
                version ?: Addon.active?.config?.formatVersions?.resAttachable ?: "1.10.0",
                this
            )
        )
        return Result.success(target)
    }

    fun isEmpty() = descriptionData == null

    /**
     * load json blocks with this class
     */
    data class FileHeader(
        @SerializedName("format_version")
        @Expose
        var formatVersion: String = "1.10.0",

        @SerializedName("minecraft:attachable")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var attachable: ResAttachable
    ): MonsteraRawFile()

    @SerializedName("description")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var descriptionData: Description? = null
        @MonsteraBuildSetter set

    /**
     * ```
     * description {
     *      identifier = "namespace:my_attachable"
     *      material {
     *          default = "entity"
     *          enchanted = "entity_alphatest_glint"
     *      }
     *      textures {
     *          default = "textures/entity/steve"
     *          enchanted = "textures/misc/enchanted_item_glint"
     *      }
     *      geometry {
     *           default = "geometry.wiki.steve_head"
     *      }
     *      animation("hold_first_person", "animation.steve_head.hold_first_person")
     *      animation("hold_third_person", "animation.steve_head.hold_third_person")
     *      scripts {
     *          animate("hold_first_person", Query.isFirstPerson)
     *          animate("hold_third_person", !Query.isFirstPerson)
     *          parentSetup = "v.chest_layer_visible = 0.0;"
     *      }
     *      renderController("controller.render.armor")
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun description(data: Description.() -> Unit) {
        descriptionData = (descriptionData ?: Description()).apply(data)
    }

    class Description: MonsteraRawFile() {
        @SerializedName("identifier")
        @Expose
        var identifier: String? = null

        @SerializedName("materials")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var materialsData: Materials? = null
            @MonsteraBuildSetter set

        /**
         * ```
         * material {
         *     default = "entity"
         *     enchanted = "entity_alphatest_glint"
         * }
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun material(data: Materials.() -> Unit) {
            materialsData = (materialsData ?: Materials()).apply(data)
        }

        @SerializedName("textures")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var texturesData: Textures? = null
            @MonsteraBuildSetter set

        /**
         * ```
         * textures {
         *     default = "textures/entity/steve"
         *     enchanted = "textures/misc/enchanted_item_glint"
         * }
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun textures(data: Textures.() -> Unit) {
            texturesData = (texturesData ?: Textures()).apply(data)
        }

        @SerializedName("geometry")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var geometryData: Geometry? = null
            @MonsteraBuildSetter set

        /**
         * ```
         * geometry {
         *     default = "geometry.wiki.steve_hea"
         * }
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun geometry(data: Geometry.() -> Unit) {
            geometryData = (geometryData ?: Geometry()).apply(data)
        }

        @SerializedName("animations")
        @Expose
        var animationsData: MutableMap<String, String>? = null
            @MonsteraBuildSetter set

        /**
         * adds an animation, call be called multiple times
         */
        @OptIn(MonsteraBuildSetter::class)
        fun animation(name: String, id: String) {
            animationsData = (animationsData ?: mutableMapOf()).apply { put(name, id) }
        }

        @SerializedName("scripts")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var scriptsData: Scripts? = null
            @MonsteraBuildSetter set

        @OptIn(MonsteraBuildSetter::class)
        fun scripts(data: Scripts.() -> Unit) {
            scriptsData = (scriptsData ?: Scripts()).apply(data)
        }

        @SerializedName("render_controllers")
        @Expose
        var renderControllerData: MutableList<Any>? = null
            @MonsteraBuildSetter set

        @OptIn(MonsteraBuildSetter::class)
        fun renderController(vararg controller: String) {
            renderControllerData = (renderControllerData ?: mutableListOf()).apply {
                addAll(controller)
            }
        }

        @OptIn(MonsteraBuildSetter::class)
        fun renderController(controller: List<String>) {
            renderControllerData = (renderControllerData ?: mutableListOf()).apply {
                addAll(controller)
            }
        }
    }

    open class Materials: MonsteraRawFile() {
        @SerializedName("default")
        @Expose
        var default: String? = null

        @SerializedName("enchanted")
        @Expose
        var enchanted: String? = null
    }

    open class Textures: MonsteraRawFile() {
        @SerializedName("default")
        @Expose
        var default: String? = null

        @SerializedName("enchanted")
        @Expose
        var enchanted: String? = null
    }

    open class Geometry: MonsteraRawFile() {
        @SerializedName("default")
        @Expose
        var default: String? = null
    }

    open class Scripts: MonsteraRawFile() {
        @SerializedName("animate")
        @Expose
        var animateData: MutableList<MutableMap<String, String>>? = null
            @MonsteraBuildSetter set

        /**
         * call multiple times
         */
        @OptIn(MonsteraBuildSetter::class)
        fun animate(name: String, query: Molang) {
            animateData = (animateData ?: mutableListOf()).apply { add(mutableMapOf(name to query.data)) }
        }

        @SerializedName("parent_setup")
        @Expose
        var parentSetup: String? = null
    }
}