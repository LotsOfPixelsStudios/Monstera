package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class BehSwell {
    @SerializedName("start_distance")
    @Expose
    var startDistance: Number? = null
        

    @SerializedName("stop_distance")
    @Expose
    var stopDistance: Number? = null
        

    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
}