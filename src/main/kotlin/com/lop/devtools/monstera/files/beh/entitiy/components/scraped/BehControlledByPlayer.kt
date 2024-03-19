package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class BehControlledByPlayer {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("mount_speed_multiplier")
    @Expose
    var mountSpeedMultiplier: Number? = null
        
}