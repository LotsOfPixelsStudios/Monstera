package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehEatMob : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("run_speed")
    @Expose
    var runSpeed: Number? = null
        
    @SerializedName("eat_animation_time")
    @Expose
    var eatAnimationTime: Number? = null
        
    @SerializedName("pull_in_force")
    @Expose
    var pullInForce: Number? = null
        
    @SerializedName("reach_mob_distance")
    @Expose
    var reachMobDistance: Number? = null
        
    @SerializedName("eat_mob_sound")
    @Expose
    var eatMobSound: String? = null
        
    @SerializedName("loot_table")
    @Expose
    var lootTable: String? = null
}