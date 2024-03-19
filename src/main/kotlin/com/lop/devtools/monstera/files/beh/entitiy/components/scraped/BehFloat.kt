package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class BehFloat {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("sink_with_passengers")
    @Expose
    var sinkWithPassengers: Boolean? = null
        
}