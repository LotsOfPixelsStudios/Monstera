package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class BehCroak {

    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("interval")
    @Expose
    var intervalData: MutableList<Number>? = null

    fun interval(vararg value: Number) {
        intervalData = (intervalData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("duration")
    @Expose
    var duration: Number? = null
        

    @SerializedName("filters")
    @Expose
    var filtersData: BehEntityFilter? = null
        @MonsteraBuildSetter set

    /**
     * com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.Filters allow data objects to specify test criteria which allows their use.
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