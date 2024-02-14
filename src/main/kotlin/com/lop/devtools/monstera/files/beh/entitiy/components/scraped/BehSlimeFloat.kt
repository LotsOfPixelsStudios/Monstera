package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class BehSlimeFloat {

    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("jump_chance_percentage")
    @Expose
    var jumpChancePercentage: Number? = null
        

    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null
        
}