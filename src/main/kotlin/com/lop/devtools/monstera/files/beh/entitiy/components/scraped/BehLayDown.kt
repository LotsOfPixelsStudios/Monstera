package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehLayDown : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("interval")
    @Expose
    var interval: Number? = null
        
    @SerializedName("random_stop_interval")
    @Expose
    var randomStopInterval: Number? = null
}