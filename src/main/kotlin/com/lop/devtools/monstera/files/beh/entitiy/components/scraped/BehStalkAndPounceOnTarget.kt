package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class BehStalkAndPounceOnTarget {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("stalk_speed")
    @Expose
    var stalkSpeed: Number? = null
        

    @SerializedName("max_stalk_dist")
    @Expose
    var maxStalkDist: Number? = null
        

    @SerializedName("leap_height")
    @Expose
    var leapHeight: Number? = null
        

    @SerializedName("leap_dist")
    @Expose
    var leapDist: Number? = null
        

    @SerializedName("pounce_max_dist")
    @Expose
    var pounceMaxDist: Number? = null
        

    @SerializedName("interest_time")
    @Expose
    var interestTime: Number? = null
        

    @SerializedName("stuck_time")
    @Expose
    var stuckTime: Number? = null
        

    @SerializedName("strike_dist")
    @Expose
    var strikeDist: Number? = null
        

    @SerializedName("stuck_blocks")
    @Expose
    var stuckBlocksData: BehEntityFilter? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * stuckBlocks {
     *     test = is_block
     *     subject = block
     *     operator = ==
     *     value = snow_layer
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun stuckBlocks(value: BehEntityFilter.() -> Unit) {
        stuckBlocksData = (stuckBlocksData ?: BehEntityFilter()).apply(value)
    }
}