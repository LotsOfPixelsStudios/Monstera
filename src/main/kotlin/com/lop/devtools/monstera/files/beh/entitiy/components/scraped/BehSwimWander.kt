package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class BehSwimWander {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("interval")
    @Expose
    var interval: Number? = null
        

    @SerializedName("look_ahead")
    @Expose
    var lookAhead: Number? = null
        

    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null
        

    @SerializedName("wander_time")
    @Expose
    var wanderTime: Number? = null
        
}