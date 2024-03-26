package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class BehMoveTowardsTarget {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null
        

    @SerializedName("within_radius")
    @Expose
    var withinRadius: Number? = null
        
}