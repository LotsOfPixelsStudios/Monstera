package com.lop.devtools.monstera.files.beh.tables.loot

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.MonsteraBuilder
import java.nio.file.Path

class BehLootTables: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        val general = ArrayList<Any>()

        override fun getData(): MutableMap<String, Any> {
            return mutableMapOf("pools" to general)
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
     * @param rolls: how often the entries are looked at
     * @sample samplePool
     */
    fun pool(rolls: Int = 1, settings: BehLootEntries.() -> Unit) {
        val behLootEntries = BehLootEntries().apply { settings(this) }

        unsafe.general.add(
            mutableMapOf(
                "rolls" to rolls,
                "entries" to behLootEntries.getData()
            )
        )
    }

    private fun samplePool() {
        pool(rolls = 1) {
            entry(type = "block", name = "grass", 2) {
                functionEnchantBook(1,1,1,1)
            }
        }
    }
}