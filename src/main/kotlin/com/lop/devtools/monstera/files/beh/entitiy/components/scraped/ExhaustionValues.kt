package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class ExhaustionValues : MonsteraRawFile() {
    @SerializedName("heal")
    @Expose
    var heal: Number? = null
        
    @SerializedName("jump")
    @Expose
    var jump: Number? = null
        
    @SerializedName("sprint_jump")
    @Expose
    var sprintJump: Number? = null
        
    @SerializedName("mine")
    @Expose
    var mine: Number? = null
        
    @SerializedName("attack")
    @Expose
    var attack: Number? = null
        
    @SerializedName("damage")
    @Expose
    var damage: Number? = null
        
    @SerializedName("walk")
    @Expose
    var walk: Number? = null
        
    @SerializedName("sprint")
    @Expose
    var sprint: Number? = null
        
    @SerializedName("swim")
    @Expose
    var swim: Number? = null
}