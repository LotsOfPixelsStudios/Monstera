package com.lop.devtools.monstera.files.beh.tables.trading

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.MonsteraBuilder
import java.nio.file.Path

class BehEconomyTrades: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        val general = ArrayList<Any>()

        override fun getData(): MutableMap<String, Any> {
            return mutableMapOf("tiers" to general)
        }

        fun build(
            filename: String,
            path: Path
        ) {
            val sanFile = filename
                .removeSuffix(".json")
                .replace("-", "_")
                .replace(" ", "_")
            MonsteraBuilder.buildTo(
                path, "$sanFile.json", getData()
            )
        }
    }

    /**
     * 1..*
     *
     * @sample sampleTier
     */
    fun tier(value: BehEcoTier.() -> Unit) {
        unsafe.general.add(BehEcoTier().apply(value).getData())
    }

    private fun sampleTier() {
        tier {
            totalExpRequired = 2
            group {
                //...
            }
        }
    }
}