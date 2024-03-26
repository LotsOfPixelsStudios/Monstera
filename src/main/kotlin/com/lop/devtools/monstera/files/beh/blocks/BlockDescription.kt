package com.lop.devtools.monstera.files.beh.blocks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter

class BlockDescription: MonsteraRawFile() {
    @SerializedName("identifier")
    @Expose
    var identifier: String? = null

    @SerializedName("menu_category")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var menuCategory: MenuCategory? = null
        @MonsteraBuildSetter set

    /**
     * ```
     * menuCategory {
     *     category = "construction"
     *     group = "itemGroup.name.concrete"
     *     isHiddenInCommands = false
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun menuCategory(data: MenuCategory.() -> Unit) {
        menuCategory = (menuCategory ?: MenuCategory()).apply(data)
    }
}