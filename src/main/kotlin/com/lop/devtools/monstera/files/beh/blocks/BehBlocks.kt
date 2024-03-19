package com.lop.devtools.monstera.files.beh.blocks

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.MonsteraBuilder
import com.lop.devtools.monstera.files.getVersionAsString
import java.nio.file.Path

class BehBlocks : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        val description = Description()
        val components = Components()

        override fun getData(): MutableMap<String, Any> {
            general["description"] = description.unsafe.getData()
            general["components"] = components.unsafe.getData()
            return general
        }

        fun Addon.build(filename: String) =
            build(filename, getVersionAsString(config.targetMcVersion), config.paths.behBlocks)

        fun build(
            filename: String,
            version: String,
            path: Path
        ) {
            val sanFile = filename
                .removeSuffix(".json")
                .replace("-", "_")
                .replace(" ", "_")
            MonsteraBuilder.buildTo(
                path, "$sanFile.json", mutableMapOf(
                    "format_version" to version,
                    "minecraft:block" to getData()
                )
            )
        }
    }

    fun description(data: Description.() -> Unit) {
        unsafe.description.apply(data)
    }

    /**
     * ```
     * geometry()
     * materialInstance()
     * ```
     */
    fun components(data: Components.() -> Unit) {
        unsafe.components.apply(data)
    }

    class Description : MonsteraFile {
        override val unsafe = Unsafe()

        inner class Unsafe : MonsteraUnsafeMap {
            val general = mutableMapOf<String, Any>()
            override fun getData(): MutableMap<String, Any> {
                return general
            }
        }

        fun identifier(value: String) {
            unsafe.general["identifier"] = value
        }
    }

    class Components : MonsteraFile {
        override val unsafe = Unsafe()

        inner class Unsafe : MonsteraUnsafeMap {
            val general = mutableMapOf<String, Any>()
            val materialInstance = MaterialInstance()

            override fun getData(): MutableMap<String, Any> {
                general["minecraft:material_instances"] = materialInstance.unsafe.getData()
                return general
            }
        }

        fun geometry(id: String) {
            unsafe.general["minecraft:geometry"] = id
        }

        /**
         * ```
         * all("path")
         * site("*", "path")
         * ```
         */
        fun materialInstance(data: MaterialInstance.() -> Unit) {
            unsafe.materialInstance.apply(data)
        }
    }

    class MaterialInstance : MonsteraFile {
        override val unsafe = Unsafe()

        inner class Unsafe : MonsteraUnsafeMap {
            val general = mutableMapOf<String, Any>()
            val materialSettingsSite = mutableMapOf<String, MaterialSettings>()
            override fun getData(): MutableMap<String, Any> {
                materialSettingsSite.forEach { (k, v) ->
                    general[k] = v.unsafe.getData()
                }
                return general
            }
        }

        fun all(settings: MaterialSettings.() -> Unit) {
            if (unsafe.materialSettingsSite.containsKey("*")) {
                unsafe.materialSettingsSite["*"]!!.apply(settings)
            } else {
                unsafe.materialSettingsSite["*"] = MaterialSettings().apply(settings)
            }
        }
    }

    class MaterialSettings : MonsteraFile {
        override val unsafe = Unsafe()

        inner class Unsafe : MonsteraUnsafeMap {
            val general = mutableMapOf<String, Any>()
            override fun getData(): MutableMap<String, Any> {
                return general
            }
        }

        fun texture(name: String) {
            unsafe.general["texture"] = name
        }

        fun renderMethod(value: RenderMethod = RenderMethod.OPAQUE) {
            unsafe.general["render_method"] = value.toString()
        }

        enum class RenderMethod(val s: String) {
            OPAQUE("opaque"),
            DOUBLE_SIDED("double_sided"),
            ALPHA_TEST("alpha_test"),
            BLEND("blend");

            override fun toString(): String {
                return s
            }
        }

        fun ambientOcclusion(value: Boolean = true) {
            unsafe.general["ambient_occlusion"] = value
        }

        fun faceDimming(value: Boolean = true) {
            unsafe.general["face_dimming"] = value
        }
    }
}