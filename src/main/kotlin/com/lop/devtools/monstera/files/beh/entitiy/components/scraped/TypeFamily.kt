package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class TypeFamily {
    @SerializedName("family")
    @Expose
    var familyData: MutableList<String>? = null
        

    @Components.VanillaComponentMarker
    fun family(vararg value: String) {
        familyData = (familyData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }
}