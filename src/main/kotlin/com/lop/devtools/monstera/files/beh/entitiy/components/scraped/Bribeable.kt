package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class Bribeable : MonsteraRawFile() {

    @SerializedName("bribe_items")
    @Expose
    var bribeItemsData: MutableList<String>? = null
        

    fun bribeItems(vararg value: String) {
        bribeItemsData = (bribeItemsData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }
}