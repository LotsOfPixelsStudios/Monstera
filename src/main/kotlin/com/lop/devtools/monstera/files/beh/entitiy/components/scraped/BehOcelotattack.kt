package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class BehOcelotattack {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("cooldown_time")
    @Expose
    var cooldownTime: Number? = null
        

    @SerializedName("x_max_rotation")
    @Expose
    var xMaxRotation: Number? = null
        

    @SerializedName("y_max_head_rotation")
    @Expose
    var yMaxHeadRotation: Number? = null
        

    @SerializedName("max_distance")
    @Expose
    var maxDistance: Number? = null
        

    @SerializedName("max_sneak_range")
    @Expose
    var maxSneakRange: Number? = null
        

    @SerializedName("max_sprint_range")
    @Expose
    var maxSprintRange: Number? = null
        

    @SerializedName("reach_multiplier")
    @Expose
    var reachMultiplier: Number? = null
        

    @SerializedName("sneak_speed_multiplier")
    @Expose
    var sneakSpeedMultiplier: Number? = null
        

    @SerializedName("sprint_speed_multiplier")
    @Expose
    var sprintSpeedMultiplier: Number? = null
        

    @SerializedName("walk_speed_multiplier")
    @Expose
    var walkSpeedMultiplier: Number? = null
        
}