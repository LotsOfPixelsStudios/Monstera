package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehPetSleepWithOwner : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null
        
    @SerializedName("search_radius")
    @Expose
    var searchRadius: Number? = null
        
    @SerializedName("search_height")
    @Expose
    var searchHeight: Number? = null
        
    @SerializedName("goal_radius")
    @Expose
    var goalRadius: Number? = null
}