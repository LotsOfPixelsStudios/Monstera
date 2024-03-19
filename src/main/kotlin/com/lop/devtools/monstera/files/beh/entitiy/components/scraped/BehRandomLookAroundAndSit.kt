package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class BehRandomLookAroundAndSit {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("continue_if_leashed")
    @Expose
    var continueIfLeashed: Boolean? = null
        

    @SerializedName("continue_sitting_on_reload")
    @Expose
    var continueSittingOnReload: Boolean? = null
        

    @SerializedName("min_look_count")
    @Expose
    var minLookCount: Number? = null
        

    @SerializedName("max_look_count")
    @Expose
    var maxLookCount: Number? = null
        

    @SerializedName("min_look_time")
    @Expose
    var minLookTime: Number? = null
        

    @SerializedName("max_look_time")
    @Expose
    var maxLookTime: Number? = null
        

    @SerializedName("min_angle_of_view_horizontal")
    @Expose
    var minAngleOfViewHorizontal: Number? = null
        

    @SerializedName("max_angle_of_view_horizontal")
    @Expose
    var maxAngleOfViewHorizontal: Number? = null
        

    @SerializedName("random_look_around_cooldown")
    @Expose
    var randomLookAroundCooldown: Number? = null
        

    @SerializedName("probability")
    @Expose
    var probability: Number? = null
        
}