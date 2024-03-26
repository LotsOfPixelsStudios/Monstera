package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class BehStrollTowardsVillage {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null
        

    @SerializedName("goal_radius")
    @Expose
    var goalRadius: Number? = null
        

    @SerializedName("cooldown_time")
    @Expose
    var cooldownTime: Number? = null
        

    @SerializedName("search_range")
    @Expose
    var searchRange: Number? = null
        

    @SerializedName("start_chance")
    @Expose
    var startChance: Number? = null
        
}