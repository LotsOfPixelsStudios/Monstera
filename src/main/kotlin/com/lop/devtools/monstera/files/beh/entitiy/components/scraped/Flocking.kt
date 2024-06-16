package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class Flocking : MonsteraRawFile() {
    @SerializedName("in_water")
    @Expose
    var inWater: Boolean? = null
        
    @SerializedName("match_variants")
    @Expose
    var matchVariants: Boolean? = null
        
    @SerializedName("use_center_of_mass")
    @Expose
    var useCenterOfMass: Boolean? = null
        
    @SerializedName("low_flock_limit")
    @Expose
    var lowFlockLimit: Number? = null
        
    @SerializedName("high_flock_limit")
    @Expose
    var highFlockLimit: Number? = null
        
    @SerializedName("goal_weight")
    @Expose
    var goalWeight: Number? = null
        
    @SerializedName("loner_chance")
    @Expose
    var lonerChance: Number? = null
        
    @SerializedName("influence_radius")
    @Expose
    var influenceRadius: Number? = null
        
    @SerializedName("breach_influence")
    @Expose
    var breachInfluence: Number? = null
        
    @SerializedName("separation_weight")
    @Expose
    var separationWeight: Number? = null
        
    @SerializedName("separation_threshold")
    @Expose
    var separationThreshold: Number? = null
        
    @SerializedName("cohesion_weight")
    @Expose
    var cohesionWeight: Number? = null
        
    @SerializedName("cohesion_threshold")
    @Expose
    var cohesionThreshold: Number? = null
        
    @SerializedName("innner_cohesion_threshold")
    @Expose
    var innnerCohesionThreshold: Number? = null
        
    @SerializedName("min_height")
    @Expose
    var minHeight: Number? = null
        
    @SerializedName("max_height")
    @Expose
    var maxHeight: Number? = null
        
    @SerializedName("block_distance")
    @Expose
    var blockDistance: Number? = null
        
    @SerializedName("block_weight")
    @Expose
    var blockWeight: Number? = null
}