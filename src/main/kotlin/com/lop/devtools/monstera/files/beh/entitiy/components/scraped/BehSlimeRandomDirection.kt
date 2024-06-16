package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehSlimeRandomDirection : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("add_random_time_range")
    @Expose
    var addRandomTimeRange: Number? = null
        
    @SerializedName("turn_range")
    @Expose
    var turnRange: Number? = null
        
    @SerializedName("min_change_direction_time")
    @Expose
    var minChangeDirectionTime: Number? = null
}