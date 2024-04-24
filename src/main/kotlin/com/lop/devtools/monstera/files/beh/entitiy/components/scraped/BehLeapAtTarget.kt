package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehLeapAtTarget : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("target_dist")
    @Expose
    var targetDist: Number? = null
        
    @SerializedName("yd")
    @Expose
    var yd: Number? = null
        
    @SerializedName("must_be_on_ground")
    @Expose
    var mustBeOnGround: Boolean? = null
}