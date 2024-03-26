package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class Breathable {
    @SerializedName("totalSupply")
    @Expose
    var totalSupply: Number? = null
        

    @SerializedName("suffocateTime")
    @Expose
    var suffocateTime: Number? = null
        

    @SerializedName("breathes_water")
    @Expose
    var breathesWater: Boolean? = null
        

    @SerializedName("breathes_air")
    @Expose
    var breathesAir: Boolean? = null
        

    @SerializedName("generates_bubbles")
    @Expose
    var generatesBubbles: Boolean? = null
        

    @SerializedName("breathes_lava")
    @Expose
    var breathesLava: Boolean? = null
        

    @SerializedName("inhale_time")
    @Expose
    var inhaleTime: Number? = null
        
}