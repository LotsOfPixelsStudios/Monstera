package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentNpc {
    val general = mutableMapOf<String, Any>()

    fun portraitOffsets(translate: Triple<Int, Int, Int>? = null, scale: Triple<Float, Float, Float>? = null) {
        if (translate != null || scale != null) {
            val thisData = mutableMapOf<String, Any>()

            if (translate != null) {
                thisData["translate"] = translate
            }
            if (scale != null) {
                thisData["scale"] = scale
            }

            general["portrait_offsets"] = thisData
        }
    }

    fun pickerOffsets(translate: Triple<Int, Int, Int>? = null, scale: Triple<Float, Float, Float>? = null) {
        if (translate != null || scale != null) {
            val thisData = mutableMapOf<String, Any>()

            if (translate != null) {
                thisData["translate"] = translate
            }
            if (scale != null) {
                thisData["scale"] = scale
            }

            general["picker_offsets"] = thisData
        }
    }

    fun skinList(variants: SkinList.() -> Unit) {
        general["skin_list"] = SkinList().apply(variants).getData()
    }

    fun skinList(variants: ArrayList<MutableMap<String, Int>>) {
        general["skin_list"] = variants
    }

    fun getData(): MutableMap<String, Any> {
        return general
    }
}

class SkinList {
    val general = ArrayList<MutableMap<String, Any>>()

    fun addVariant(id: Int) {
        general.add(mutableMapOf("variant" to id))
    }

    fun getData(): ArrayList<MutableMap<String, Any>> {
        return general
    }
}