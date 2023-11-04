package com.lop.devtools.monstera.files.res.items

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.MonsteraBuilder
import com.lop.devtools.monstera.files.lang.langKey
import java.nio.file.Path

class ResItem: MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        /**
         * access to all defined animations
         */
        val general = mutableMapOf<String, Any>()

        val component = ResItemComp()

        override fun getData(): MutableMap<String, Any> {
            unsafe.general["components"] = unsafe.component.unsafe.getData()
            return unsafe.general
        }

        fun build(
            filename: String,
            path: Path,
            version: String = "1.10.0"
        ) {
            val sanFile = filename
                .removeSuffix(".json")
                .removeSuffix(".item")
                .replace("-", "_")
                .replace(" ", "_")
            MonsteraBuilder.buildTo(
                path, "$sanFile.item.json", mutableMapOf(
                    "format_version" to version,
                    "minecraft:item" to getData()
                )
            )
        }
    }

    /**
     * 1
     */
    fun description(
        identifier: String,
        category: String = "Equipment",
        name: String
    ) {
        unsafe.general.apply {
            put(
                "description", mutableMapOf(
                    "identifier" to identifier,
                    "category" to category
                )
            )
        }
        langKey("item.$identifier.name", name)
    }

    /**
     * 0..1
     *
     * @sample sampleComp
     */
    fun components(settings: ResItemComp.() -> Unit) {
        unsafe.component.apply(settings)
    }

    private fun sampleComp() {
        components {
            icon("boatWheel")
            renderOffset("tools")
        }
    }
}