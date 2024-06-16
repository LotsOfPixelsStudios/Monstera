package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehSkeletonHorseTrap : MonsteraRawFile() {

    @SerializedName("within_radius")
    @Expose
    var withinRadius: Number? = null
        
    @SerializedName("duration")
    @Expose
    var duration: Number? = null
        
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
}