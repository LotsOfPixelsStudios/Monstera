package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class BreakBlocks {
    @SerializedName("breakable_blocks")
    @Expose
    var breakableBlocksData: MutableList<String>? = null
        

    fun breakableBlocks(vararg value: String) {
        breakableBlocksData = (breakableBlocksData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }
}