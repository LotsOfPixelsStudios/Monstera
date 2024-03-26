package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class Buoyant {
    @SerializedName("base_buoyancy")
    @Expose
    var baseBuoyancy: Number? = null
        

    @SerializedName("apply_gravity")
    @Expose
    var applyGravity: Boolean? = null
        

    @SerializedName("simulate_waves")
    @Expose
    var simulateWaves: Boolean? = null
        

    @SerializedName("big_wave_probability")
    @Expose
    var bigWaveProbability: Number? = null
        

    @SerializedName("big_wave_speed")
    @Expose
    var bigWaveSpeed: Number? = null
        

    @SerializedName("liquid_blocks")
    @Expose
    var liquidBlocksData: MutableList<String>? = null
        

    fun liquidBlocks(vararg value: String) {
        liquidBlocksData = (liquidBlocksData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("drag_down_on_buoyancy_removed")
    @Expose
    var dragDownOnBuoyancyRemoved: Number? = null
        
}