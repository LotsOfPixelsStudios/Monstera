package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehDelayedAttack : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("attack_once")
    @Expose
    var attackOnce: Boolean? = null
        
    @SerializedName("track_target")
    @Expose
    var trackTarget: Boolean? = null
        
    @SerializedName("require_complete_path")
    @Expose
    var requireCompletePath: Boolean? = null
        
    @SerializedName("random_stop_interval")
    @Expose
    var randomStopInterval: Number? = null
        
    @SerializedName("reach_multiplier")
    @Expose
    var reachMultiplier: Number? = null
        
    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null
        
    @SerializedName("attack_duration")
    @Expose
    var attackDuration: Number? = null
        
    @SerializedName("hit_delay_pct")
    @Expose
    var hitDelayPct: Number? = null
}