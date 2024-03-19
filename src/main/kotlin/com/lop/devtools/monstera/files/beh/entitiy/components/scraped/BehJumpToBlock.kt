package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class BehJumpToBlock {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("search_width")
    @Expose
    var searchWidth: Number? = null
        

    @SerializedName("search_height")
    @Expose
    var searchHeight: Number? = null
        

    @SerializedName("minimum_path_length")
    @Expose
    var minimumPathLength: Number? = null
        

    @SerializedName("minimum_distance")
    @Expose
    var minimumDistance: Number? = null
        

    @SerializedName("scale_factor")
    @Expose
    var scaleFactor: Number? = null
        

    @SerializedName("max_velocity")
    @Expose
    var maxVelocity: Number? = null
        

    @SerializedName("cooldown_range")
    @Expose
    var cooldownRangeData: MutableList<Number>? = null

    fun cooldownRange(vararg value: Number) {
        cooldownRangeData = (cooldownRangeData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("preferred_blocks")
    @Expose
    var preferredBlocksData: MutableList<String>? = null

    fun preferredBlocks(vararg value: String) {
        preferredBlocksData = (preferredBlocksData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("preferred_blocks_chance")
    @Expose
    var preferredBlocksChance: Number? = null
        

    @SerializedName("forbidden_blocks")
    @Expose
    var forbiddenBlocksData: MutableList<String>? = null

    fun forbiddenBlocks(vararg value: String) {
        forbiddenBlocksData = (forbiddenBlocksData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }
}