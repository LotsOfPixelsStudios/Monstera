package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehMingle : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null
        
    @SerializedName("duration")
    @Expose
    var duration: Number? = null
        
    @SerializedName("cooldown_time")
    @Expose
    var cooldownTime: Number? = null
        
    @SerializedName("mingle_partner_type")
    @Expose
    var minglePartnerType: String? = null
        
    @SerializedName("mingle_distance")
    @Expose
    var mingleDistance: Number? = null
}