package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class NavigationHover {
    @SerializedName("can_path_over_water")
    @Expose
    var canPathOverWater: Boolean? = null
        

    @SerializedName("can_sink")
    @Expose
    var canSink: Boolean? = null
        

    @SerializedName("can_pass_doors")
    @Expose
    var canPassDoors: Boolean? = null
        

    @SerializedName("can_path_from_air")
    @Expose
    var canPathFromAir: Boolean? = null
        

    @SerializedName("avoid_water")
    @Expose
    var avoidWater: Boolean? = null
        

    @SerializedName("avoid_damage_blocks")
    @Expose
    var avoidDamageBlocks: Boolean? = null
        

    @SerializedName("avoid_sun")
    @Expose
    var avoidSun: Boolean? = null
        
}