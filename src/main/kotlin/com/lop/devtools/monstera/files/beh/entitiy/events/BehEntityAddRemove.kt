package com.lop.devtools.monstera.files.beh.entitiy.events

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehEntityAddRemove : MonsteraRawFile() {
    @SerializedName("component_groups")
    @Expose
    var componentGroups: MutableList<String>? = null

    fun componentGroups(vararg groups: String) {
        componentGroups = (componentGroups ?: mutableListOf()).apply { addAll(groups) }
    }
}