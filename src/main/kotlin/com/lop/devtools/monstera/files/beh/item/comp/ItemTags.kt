package com.lop.devtools.monstera.files.beh.item.comp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemTags {
    @SerializedName("value")
    @Expose
    var tags: MutableList<String>? = null

    fun tags(vararg value: String) {
        tags(value.toList())
    }

    fun tags(value: List<String>) {
        tags = (tags ?: mutableListOf()).apply { addAll(value) }
    }
}