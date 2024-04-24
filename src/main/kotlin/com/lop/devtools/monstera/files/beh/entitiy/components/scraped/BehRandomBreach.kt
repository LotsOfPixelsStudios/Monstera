package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehRandomBreach : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("interval")
    @Expose
    var interval: Number? = null
        
    @SerializedName("xz_dist")
    @Expose
    var xzDist: Number? = null
        
    @SerializedName("cooldown_time")
    @Expose
    var cooldownTime: Number? = null
}