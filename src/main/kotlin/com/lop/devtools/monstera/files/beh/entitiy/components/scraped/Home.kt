package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class Home {
    @SerializedName("restriction_radius")
    @Expose
    var restrictionRadius: Number? = null
        
    @SerializedName("home_block_list")
    @Expose
    var homeBlockListData: MutableList<String>? = null
        
    fun homeBlockList(vararg value: String) {
        homeBlockListData = (homeBlockListData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }
}