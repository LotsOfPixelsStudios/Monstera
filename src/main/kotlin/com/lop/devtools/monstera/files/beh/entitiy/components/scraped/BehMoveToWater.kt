package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class BehMoveToWater {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("search_range")
    @Expose
    var searchRange: Number? = null
        

    @SerializedName("search_height")
    @Expose
    var searchHeight: Number? = null
        

    @SerializedName("search_count")
    @Expose
    var searchCount: Number? = null
        

    @SerializedName("goal_radius")
    @Expose
    var goalRadius: Number? = null
        
}