package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehRaidGarden : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("blocks")
    @Expose
    var blocksData: MutableList<String>? = null
        
    fun blocks(vararg value: String) {
        blocksData = (blocksData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null
        
    @SerializedName("search_range")
    @Expose
    var searchRange: Number? = null
        
    @SerializedName("search_height")
    @Expose
    var searchHeight: Number? = null
        
    @SerializedName("goal_radius")
    @Expose
    var goalRadius: Number? = null
        
    @SerializedName("max_to_eat")
    @Expose
    var maxToEat: Number? = null
        
    @SerializedName("initial_eat_delay")
    @Expose
    var initialEatDelay: Number? = null
}