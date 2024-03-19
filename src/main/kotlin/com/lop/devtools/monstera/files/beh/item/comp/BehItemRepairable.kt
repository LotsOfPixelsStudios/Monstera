package com.lop.devtools.monstera.files.beh.item.comp

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class BehItemRepairable: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        val repItems = mutableListOf<RepItem>()

        override fun getData(): MutableMap<String, Any> {
            if(repItems.isNotEmpty()) {
                general["repair_items"] = repItems.map {
                    mapOf(
                        "items" to it.items,
                        "repair_amount" to it.repairAmount
                    )
                }
            }
            return general
        }
    }

    fun items(repairAmount: String, items: List<String>) {
        unsafe.repItems.add(RepItem(repairAmount, items = items))
    }

    fun items(repairAmount: String, vararg items: String) {
        items(repairAmount, items.asList())
    }

    class RepItem(
        val repairAmount: String,
        val items: List<String>
    )
}