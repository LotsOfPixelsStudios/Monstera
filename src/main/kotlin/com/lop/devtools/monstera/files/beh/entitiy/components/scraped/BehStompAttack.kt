package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehStompAttack : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("track_target")
    @Expose
    var trackTarget: Boolean? = null
        
    @SerializedName("require_complete_path")
    @Expose
    var requireCompletePath: Boolean? = null
        
    @SerializedName("stomp_range_multiplier")
    @Expose
    var stompRangeMultiplier: Number? = null
        
    @SerializedName("no_damage_range_multiplier")
    @Expose
    var noDamageRangeMultiplier: Number? = null
}