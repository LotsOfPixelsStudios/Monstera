package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class BehSleep {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("goal_radius")
    @Expose
    var goalRadius: Number? = null
        

    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null
        

    @SerializedName("sleep_collider_height")
    @Expose
    var sleepColliderHeight: Number? = null
        

    @SerializedName("sleep_collider_width")
    @Expose
    var sleepColliderWidth: Number? = null
        

    @SerializedName("sleep_y_offset")
    @Expose
    var sleepYOffset: Number? = null
        

    @SerializedName("timeout_cooldown")
    @Expose
    var timeoutCooldown: Number? = null
        
}