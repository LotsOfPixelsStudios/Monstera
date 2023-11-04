package com.lop.devtools.monstera.files.res.entities

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.MonsteraBuilder
import java.nio.file.Path

class ResEntity: MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        /**
         * access to all defined animations
         */
        val general = mutableMapOf<String, Any>()
        val description = ResEntityDescription()

        /**
         * return all Entity data for the FileBuilder, so he can parse the Map to Json
         */
        override fun getData(): MutableMap<String, Any> {
            if (unsafe.description.unsafe.getData().isNotEmpty())
                unsafe.general["description"] = unsafe.description.unsafe.getData()
            return unsafe.general
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
                    "minecraft:client_entity" to getData()
                )
            )
        }
    }

    /**
     * 1
     *
     * @sample sample
     */
    fun description(settings: ResEntityDescription.() -> Unit) {
        unsafe.description.apply(settings)
    }

    private fun sample() {
        description {
            identifier("...")
            mineEngineVersion("...")
            materials {
                //...
            }
            geometries {
                //...
            }
            textures {
                //...
            }
            animations {
                //...
            }
            animationControllers {
                //...
            }
            renderControllers {
                //...
            }
            locators {
                //...
            }
            spawnEgg {
                //...
            }
            enableAttachment()
            scripts {
                //...
            }
        }
    }
}