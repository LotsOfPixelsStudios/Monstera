package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class BehLayEgg : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
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
        
    @SerializedName("target_blocks")
    @Expose
    var targetBlocksData: MutableList<String>? = null

    fun targetBlocks(vararg value: String) {
        targetBlocksData = (targetBlocksData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("target_materials_above_block")
    @Expose
    var targetMaterialsAboveBlockData: MutableList<String>? = null

    fun targetMaterialsAboveBlock(vararg value: String) {
        targetMaterialsAboveBlockData =
            (targetMaterialsAboveBlockData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("allow_laying_from_below")
    @Expose
    var allowLayingFromBelow: Boolean? = null
        
    @SerializedName("use_default_animation")
    @Expose
    var useDefaultAnimation: Boolean? = null
        
    @SerializedName("lay_seconds")
    @Expose
    var laySeconds: Number? = null
        
    @SerializedName("egg_type")
    @Expose
    var eggType: String? = null
        
    @SerializedName("lay_egg_sound")
    @Expose
    var layEggSound: String? = null
        
    @SerializedName("on_lay")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var onLayData: OnLay? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onLay {
     *     event = laid_egg
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onLay(value: OnLay.() -> Unit) {
        onLayData = (onLayData ?: OnLay()).apply(value)
    }

    class OnLay : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null
            
        @SerializedName("target")
        @Expose
        var target: Subject? = null
    }
}
