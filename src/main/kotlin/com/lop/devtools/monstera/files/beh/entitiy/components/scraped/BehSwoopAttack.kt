package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class BehSwoopAttack {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("damage_reach")
    @Expose
    var damageReach: Number? = null
        

    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null
        

    @SerializedName("delay_range")
    @Expose
    var delayRangeData: MutableList<Number>? = null
        

    fun delayRange(vararg value: Number) {
        delayRangeData = (delayRangeData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }
}