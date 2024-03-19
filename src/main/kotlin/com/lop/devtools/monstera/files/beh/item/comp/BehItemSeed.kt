package com.lop.devtools.monstera.files.beh.item.comp

class BehItemSeed {
    val general = mutableMapOf<String, Any>()

    fun cropResult(result: String) {
        general.apply {
            put("crop_result", result)
        }
    }

    fun plantAt(block: ArrayList<String>) {
        general.apply {
            put("plant_at", block)
        }
    }

    fun getData(): MutableMap<String,Any> {
        return general
    }
}