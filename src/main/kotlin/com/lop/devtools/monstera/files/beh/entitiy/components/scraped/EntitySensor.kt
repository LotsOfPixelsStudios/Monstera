package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class EntitySensor {
    @SerializedName("sensor_range")
    @Expose
    var sensorRange: Number? = null
        

    @SerializedName("relative_range")
    @Expose
    var relativeRange: Boolean? = null
        

    @SerializedName("event_filters")
    @Expose
    var eventFiltersData: BehEntityFilter? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * eventFilters {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun eventFilters(value: BehEntityFilter.() -> Unit) {
        eventFiltersData = (eventFiltersData ?: BehEntityFilter()).apply(value)
    }

    @SerializedName("event")
    @Expose
    var event: String? = null
        

    @SerializedName("minimum_count")
    @Expose
    var minimumCount: Number? = null
        

    @SerializedName("require_all")
    @Expose
    var requireAll: Boolean? = null
        
}