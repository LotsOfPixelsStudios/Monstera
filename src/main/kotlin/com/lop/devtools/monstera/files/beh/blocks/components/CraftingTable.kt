package com.lop.devtools.monstera.files.beh.blocks.components

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile

class CraftingTable: MonsteraRawFile() {
    @SerializedName("table_name")
    @Expose
    var tableName: String? = null
    
    @SerializedName("crafting_tags")
    @Expose
    var craftingTagsData: MutableList<String>? = null
        @MonsteraBuildSetter set
    
    /**
     * ```
     * craftingTags("crafting_table", "wiki_workbench")
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun craftingTags(vararg tags: String) {
        craftingTagsData = (craftingTagsData ?: mutableListOf()).apply {
            addAll(tags)
        }
    }
}