package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class DamageOverTime {
    @SerializedName("damage_per_hurt")
    @Expose
    var damagePerHurt: Number? = null
        

    @SerializedName("time_between_hurt")
    @Expose
    var timeBetweenHurt: Number? = null
        
}