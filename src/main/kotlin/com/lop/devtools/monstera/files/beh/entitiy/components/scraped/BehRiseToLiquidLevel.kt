package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehRiseToLiquidLevel : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("liquid_y_offset")
    @Expose
    var liquidYOffset: Number? = null
        
    @SerializedName("rise_delta")
    @Expose
    var riseDelta: Number? = null
        
    @SerializedName("sink_delta")
    @Expose
    var sinkDelta: Number? = null
}