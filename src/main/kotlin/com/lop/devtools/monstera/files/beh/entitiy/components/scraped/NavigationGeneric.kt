package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class NavigationGeneric {
    @SerializedName("is_amphibious")
    @Expose
    var isAmphibious: Boolean? = null
        

    @SerializedName("can_path_over_water")
    @Expose
    var canPathOverWater: Boolean? = null
        

    @SerializedName("can_swim")
    @Expose
    var canSwim: Boolean? = null
        

    @SerializedName("can_walk")
    @Expose
    var canWalk: Boolean? = null
        

    @SerializedName("can_sink")
    @Expose
    var canSink: Boolean? = null
        

    @SerializedName("avoid_damage_blocks")
    @Expose
    var avoidDamageBlocks: Boolean? = null
        

    @SerializedName("can_breach")
    @Expose
    var canBreach: Boolean? = null
        

    @SerializedName("can_jump")
    @Expose
    var canJump: Boolean? = null
        

    @SerializedName("can_break_doors")
    @Expose
    var canBreakDoors: Boolean? = null
        

    @SerializedName("avoid_sun")
    @Expose
    var avoidSun: Boolean? = null
        
}