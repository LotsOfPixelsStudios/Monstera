package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehChargeHeldItem : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null

    @SerializedName("items")
    @Expose
    var itemsData: MutableList<String>? = null

    fun items(vararg value: String) {
        itemsData = (itemsData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }
}