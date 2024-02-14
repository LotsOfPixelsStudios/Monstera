package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class BehSniff {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("duration")
    @Expose
    var duration: Number? = null
        

    @SerializedName("sniffing_radius")
    @Expose
    var sniffingRadius: Number? = null
        

    @SerializedName("suspicion_radius_horizontal")
    @Expose
    var suspicionRadiusHorizontal: Number? = null
        

    @SerializedName("suspicion_radius_vertical")
    @Expose
    var suspicionRadiusVertical: Number? = null
        

    @SerializedName("cooldown_range")
    @Expose
    var cooldownRangeData: MutableList<Number>? = null
        

    fun cooldownRange(vararg value: Number) {
        cooldownRangeData = (cooldownRangeData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }
}