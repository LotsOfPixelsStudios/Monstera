package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehRandomSitting : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("start_chance")
    @Expose
    var startChance: Number? = null
        
    @SerializedName("stop_chance")
    @Expose
    var stopChance: Number? = null
        
    @SerializedName("cooldown")
    @Expose
    var cooldown: Number? = null
        
    @SerializedName("min_sit_time")
    @Expose
    var minSitTime: Number? = null
}