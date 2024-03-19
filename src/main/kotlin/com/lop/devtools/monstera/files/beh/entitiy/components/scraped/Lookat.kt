package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class Lookat {
    @SerializedName("search_radius")
    @Expose
    var searchRadius: Number? = null
        

    @SerializedName("set_target")
    @Expose
    var setTarget: Boolean? = null
        

    @SerializedName("look_cooldown")
    @Expose
    var lookCooldown: Number? = null
        

    @SerializedName("filters")
    @Expose
    var filtersData: BehEntityFilter? = null
        @MonsteraBuildSetter set

    /**
     * Filters allow data objects to specify test criteria which allows their use.
     * ```
     * filters {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun filters(value: BehEntityFilter.() -> Unit) {
        filtersData = (filtersData ?: BehEntityFilter()).apply(value)
    }
}