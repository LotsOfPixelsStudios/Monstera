package com.lop.devtools.monstera.files.res.attachables

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.MonsteraBuilder
import java.nio.file.Path

class ResAttachable: MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        /**
         * access to all defined animations
         */
        val general = mutableMapOf<String, Any>()

        val description = ResAttachableDescription()
        override fun getData(): MutableMap<String, Any> {
            unsafe.general["description"] = unsafe.description.getData()
            return unsafe.general
        }

        fun Addon.build(filename: String) =
            build(filename, config.paths.resAttachable)

        fun build(
            filename: String,
            path: Path
        ) {
            val sanFile = filename
                .removeSuffix(".json")
                .replace("-", "_")
                .replace(" ", "_")
            MonsteraBuilder.buildTo(
                path, "$sanFile.json", mutableMapOf(
                    "format_version" to "1.10.0",
                    "minecraft:attachable" to getData()
                )
            )
        }
    }

    /**
     * 0..1
     *
     * @param description the description object
     * @sample sampleDescription
     */
    fun description(description: ResAttachableDescription.() -> Unit) {
        unsafe.description.apply(description)
    }

    private fun sampleDescription() {
        description {
            identifier("identifier")
            materials(value = "parrot")
            unsafe.textures(value = "textures/attachable/test")
            geometry(value = "geometry.test")
            animations(key = "walk", value = "controller.animation.test.walk")
            renderController(arrayListOf("controller.render.test"))
            scripts(arrayListOf("walk"))
        }
    }
}