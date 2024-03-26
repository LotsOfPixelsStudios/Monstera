package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class PreferredPath {
    @SerializedName("max_fall_blocks")
    @Expose
    var maxFallBlocks: Number? = null
        

    @SerializedName("jump_cost")
    @Expose
    var jumpCost: Number? = null
        

    @SerializedName("default_block_cost")
    @Expose
    var defaultBlockCost: Number? = null
        

    @SerializedName("preferred_path_blocks")
    @Expose
    var preferredPathBlocksData: MutableList<PreferredPathBlocks>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * preferredPathBlocks {
     *     cost = 0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun preferredPathBlocks(value: PreferredPathBlocks.() -> Unit) {
        preferredPathBlocksData =
            (preferredPathBlocksData ?: mutableListOf()).also { it.add(PreferredPathBlocks().apply(value)) }
    }

    class PreferredPathBlocks {
        @SerializedName("cost")
        @Expose
        var cost: Number? = null
            

        @SerializedName("blocks")
        @Expose
        var blocksData: MutableList<String>? = null
            

        fun blocks(vararg value: String) {
            blocksData = (blocksData ?: mutableListOf()).also { it.addAll(value.toList()) }
        }
    }
}
