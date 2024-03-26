package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class BehLookAtPlayer {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("target_distance")
    @Expose
    var targetDistance: Number? = null
        

    @SerializedName("probability")
    @Expose
    var probability: Number? = null
        

    @SerializedName("look_distance")
    @Expose
    var lookDistance: Number? = null
        

    @SerializedName("angle_of_view_horizontal")
    @Expose
    var angleOfViewHorizontal: Number? = null
        
}