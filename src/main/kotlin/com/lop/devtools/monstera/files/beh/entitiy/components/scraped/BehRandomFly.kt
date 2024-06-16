package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehRandomFly : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("xz_dist")
    @Expose
    var xzDist: Number? = null
        
    @SerializedName("y_dist")
    @Expose
    var yDist: Number? = null
        
    @SerializedName("y_offset")
    @Expose
    var yOffset: Number? = null
        
    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null
        
    @SerializedName("can_land_on_trees")
    @Expose
    var canLandOnTrees: Boolean? = null
        
    @SerializedName("avoid_damage_blocks")
    @Expose
    var avoidDamageBlocks: Boolean? = null
}