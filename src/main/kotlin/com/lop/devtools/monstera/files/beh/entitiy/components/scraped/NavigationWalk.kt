package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class NavigationWalk {
    @SerializedName("can_path_over_water")
    @Expose
    var canPathOverWater: Boolean? = null
        

    @SerializedName("avoid_water")
    @Expose
    var avoidWater: Boolean? = null
        

    @SerializedName("avoid_damage_blocks")
    @Expose
    var avoidDamageBlocks: Boolean? = null
        

    @SerializedName("can_float")
    @Expose
    var canFloat: Boolean? = null
        

    @SerializedName("can_pass_doors")
    @Expose
    var canPassDoors: Boolean? = null
        

    @SerializedName("can_open_doors")
    @Expose
    var canOpenDoors: Boolean? = null
        

    @SerializedName("can_sink")
    @Expose
    var canSink: Boolean? = null
        

    @SerializedName("blocks_to_avoid")
    @Expose
    var blocksToAvoidData: MutableList<BlocksToAvoid>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * blocksToAvoid {
     *     name = minecraft:powder_snow
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun blocksToAvoid(value: BlocksToAvoid.() -> Unit) {
        blocksToAvoidData = (blocksToAvoidData ?: mutableListOf()).also { it.add(BlocksToAvoid().apply(value)) }
    }

    @SerializedName("is_amphibious")
    @Expose
    var isAmphibious: Boolean? = null
        

    @SerializedName("can_break_doors")
    @Expose
    var canBreakDoors: Boolean? = null
        

    @SerializedName("avoid_portals")
    @Expose
    var avoidPortals: Boolean? = null
        

    @SerializedName("avoid_sun")
    @Expose
    var avoidSun: Boolean? = null
        

    @SerializedName("can_path_over_lava")
    @Expose
    var canPathOverLava: Boolean? = null
        

    @SerializedName("can_walk_in_lava")
    @Expose
    var canWalkInLava: Boolean? = null
        

    @SerializedName("can_walk")
    @Expose
    var canWalk: Boolean? = null
        

    class BlocksToAvoid {
        @SerializedName("name")
        @Expose
        var name: String? = null
            
    }
}
