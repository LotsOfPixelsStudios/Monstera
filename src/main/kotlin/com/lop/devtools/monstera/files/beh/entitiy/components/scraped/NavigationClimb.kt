package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile

class NavigationComp: MonsteraRawFile() {
    @SerializedName("can_path_over_water")
    @Expose
    var canPathOverWater: Boolean? = null

    @SerializedName("avoid_portals")
    @Expose
    var avoidPortals: Boolean? = null

    @SerializedName("avoid_sun")
    @Expose
    var avoidSun: Boolean? = null

    @SerializedName("avoid_water")
    @Expose
    var avoidWater: Boolean? = null

    @SerializedName("can_break_doors")
    @Expose
    var canBreakDoors: Boolean? = null

    @SerializedName("can_open_doors")
    @Expose
    var canOpenDoors: Boolean? = null

    @SerializedName("can_open_iron_doors")
    @Expose
    var canOpenIronDoors: Boolean? = null

    @SerializedName("can_pass_doors")
    @Expose
    var canPassDoors: Boolean? = null

    @SerializedName("can_path_from_air")
    @Expose
    var canPathFromAir: Boolean? = null

    @SerializedName("can_path_over_lava")
    @Expose
    var canPathOverLava: Boolean? = null

    @SerializedName("can_sink")
    @Expose
    var canSink: Boolean? = null

    @SerializedName("can_walk_in_lava")
    @Expose
    var canWalkInLava: Boolean? = null

    @SerializedName("is_amphibious")
    @Expose
    var isAmphibious: Boolean? = null

    @SerializedName("can_swim")
    @Expose
    var canSwim: Boolean? = null

    @SerializedName("can_walk")
    @Expose
    var canWalk: Boolean? = null

    @SerializedName("avoid_damage_blocks")
    @Expose
    var avoidDamageBlocks: Boolean? = null

    @SerializedName("can_breach")
    @Expose
    var canBreach: Boolean? = null

    @SerializedName("can_float")
    @Expose
    var canFloat: Boolean? = null

    @SerializedName("blocks_to_avoid")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var blocksToAvoid: MutableList<Block>? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    fun avoidBlocks(vararg blocks: String) {
        blocksToAvoid = (blocksToAvoid ?: mutableListOf()).apply {
            addAll(blocks.map { Block().apply { this.name = it } })
        }
    }

    open class Block : MonsteraRawFile() {
        @SerializedName("name")
        @Expose
        var name: String? = null
    }
}