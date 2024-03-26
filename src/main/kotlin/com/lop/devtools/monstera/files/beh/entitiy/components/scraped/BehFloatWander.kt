package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class BehFloatWander {
    @SerializedName("xz_dist")
    @Expose
    var xzDist: Number? = null
        

    @SerializedName("y_dist")
    @Expose
    var yDist: Number? = null
        

    @SerializedName("y_offset")
    @Expose
    var yOffset: Number? = null
        

    @SerializedName("random_reselect")
    @Expose
    var randomReselect: Boolean? = null
        

    @SerializedName("float_duration")
    @Expose
    var floatDurationData: MutableList<Number>? = null

    fun floatDuration(vararg value: Number) {
        floatDurationData = (floatDurationData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("must_reach")
    @Expose
    var mustReach: Boolean? = null
        
}