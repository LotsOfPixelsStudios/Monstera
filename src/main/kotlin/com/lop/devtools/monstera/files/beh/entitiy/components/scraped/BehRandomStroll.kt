package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehRandomStroll : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("interval")
    @Expose
    var interval: Number? = null
        
    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null
        
    @SerializedName("xz_dist")
    @Expose
    var xzDist: Number? = null

    @SerializedName("y_dist")
    @Expose
    var yDist: Number? = null
}