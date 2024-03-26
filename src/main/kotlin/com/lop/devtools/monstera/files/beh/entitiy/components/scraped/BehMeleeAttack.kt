package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class BehMeleeAttack {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null
        

    @SerializedName("track_target")
    @Expose
    var trackTarget: Boolean? = null
        

    @SerializedName("reach_multiplier")
    @Expose
    var reachMultiplier: Number? = null
        
}