package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class Trail {
    @SerializedName("block_type")
    @Expose
    var blockType: String? = null
        

    @SerializedName("spawn_filter")
    @Expose
    var spawnFilterData: BehEntityFilter? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * spawnFilter {
     *     test = is_temperature_value
     *     operator = <
     *     value = 0.81
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun spawnFilter(value: BehEntityFilter.() -> Unit) {
        spawnFilterData = (spawnFilterData ?: BehEntityFilter()).apply(value)
    }
}
