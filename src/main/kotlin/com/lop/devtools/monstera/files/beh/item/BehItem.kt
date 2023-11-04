package com.lop.devtools.monstera.files.beh.item

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.MonsteraBuilder
import com.lop.devtools.monstera.files.beh.item.comp.Slot
import java.nio.file.Path

class BehItem : MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        /**
         * access to all defined data
         */
        val general = mutableMapOf<String, Any>()
        val components = BehItemComponents()

        override fun getData(): MutableMap<String, Any> {
            if (components.unsafe.getData().isNotEmpty())
                general["components"] = components.unsafe.getData()
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
                    "minecraft:item" to getData()
                )
            )
        }
    }

    fun description(identifier: String, category: String = "Equipment") {
        unsafe.general.apply {
            put(
                "description", mutableMapOf(
                    "identifier" to identifier,
                    "category" to category
                )
            )
        }
    }

    /**
     * 0..1
     *
     * @sample sampleComp
     */
    fun components(settings: BehItemComponents.() -> Unit) {
        unsafe.components.apply(settings)
    }

    @ExperimentalUnsignedTypes
    private fun sampleComp() {
        components {
            maxDamage(2)
            handEquipped = true
            stackedByData(true)
            foil(false)
            block("grass")
            maxStackSize(1)
            useDuration(32)
            food {
                //...
            }
            //experimental
            armor(1, "iron")
            blockPlacer("grass", arrayListOf("stone", "dirt"))
            coolDown("?", 0.2f)
            digger("?", "?", true)
            durability(0.2f, 200)
            dyePowder("red")
            entityPlacer(arrayListOf("grass"), "quazal")
            fuel(125.0f)
            knockBackResistance(0.2f)
            onUse("?")
            onUseOn("?")
            projectile(30.0f, "arrow")
            repairable {

            }
            shooter {
                //...
            }
            weapon {
                //...
            }
            wearable(true, Slot.ARMOR)
        }
    }
}