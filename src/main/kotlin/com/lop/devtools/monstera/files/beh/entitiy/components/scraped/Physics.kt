package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class Physics {
    @SerializedName("has_gravity")
    @Expose
    var hasGravity: Boolean? = null
        

    @SerializedName("has_collision")
    @Expose
    var hasCollision: Boolean? = null
        

    @SerializedName("push_towards_closest_space")
    @Expose
    var pushTowardsClosestSpace: Boolean? = null
        
}