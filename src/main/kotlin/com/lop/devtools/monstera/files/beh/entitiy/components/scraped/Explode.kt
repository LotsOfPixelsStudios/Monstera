package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class Explode {
    @SerializedName("fuse_length")
    @Expose
    var fuseLength: Number? = null
        

    @SerializedName("fuse_lit")
    @Expose
    var fuseLit: Boolean? = null
        

    @SerializedName("power")
    @Expose
    var power: Number? = null
        

    @SerializedName("causes_fire")
    @Expose
    var causesFire: Boolean? = null
        

    @SerializedName("destroy_affected_by_griefing")
    @Expose
    var destroyAffectedByGriefing: Boolean? = null
        

    @SerializedName("fire_affected_by_griefing")
    @Expose
    var fireAffectedByGriefing: Boolean? = null
        

    @SerializedName("max_resistance")
    @Expose
    var maxResistance: Number? = null
        
}