package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class BehBeg : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("look_distance")
    @Expose
    var lookDistance: Number? = null
        
    @SerializedName("look_time")
    @Expose
    var lookTimeData: MutableList<Number>? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun lookTime(vararg value: Number) {
        lookTimeData = (lookTimeData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("items")
    @Expose
    var itemsData: MutableList<String>? = null
        @MonsteraBuildSetter set
        

    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun items(vararg value: String) {
        itemsData = (itemsData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }
}