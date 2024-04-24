package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehFindMount : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("within_radius")
    @Expose
    var withinRadius: Number? = null
        
    @SerializedName("avoid_water")
    @Expose
    var avoidWater: Boolean? = null
        
    @SerializedName("start_delay")
    @Expose
    var startDelay: Number? = null
        
    @SerializedName("target_needed")
    @Expose
    var targetNeeded: Boolean? = null
        
    @SerializedName("mount_distance")
    @Expose
    var mountDistance: Number? = null
        
    @SerializedName("max_failed_attempts")
    @Expose
    var maxFailedAttempts: Number? = null
}