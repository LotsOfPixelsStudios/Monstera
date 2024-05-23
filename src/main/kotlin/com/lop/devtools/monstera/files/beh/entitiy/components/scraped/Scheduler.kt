package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class Scheduler : MonsteraRawFile() {
    @SerializedName("min_delay_secs")
    @Expose
    var minDelaySecs: Number? = null
        
    @SerializedName("max_delay_secs")
    @Expose
    var maxDelaySecs: Number? = null
        

    @SerializedName("scheduled_events")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var scheduledEventsData: MutableList<ScheduledEvents>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * scheduledEvents {
     *     event = minecraft:ambient_sleep
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun scheduledEvents(value: ScheduledEvents.() -> Unit) {
        scheduledEventsData = (scheduledEventsData ?: mutableListOf()).also { it.add(ScheduledEvents().apply(value)) }
    }

    class ScheduledEvents : MonsteraRawFile() {
        @SerializedName("filters")
        @Expose
        var filtersData: MutableList<BehEntityFilter>? = null
            @MonsteraBuildSetter set

        /**
         * com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.Filters allow data objects to specify test criteria which allows their use.
         * ```
         * filters {
         *     test = is_sleeping
         *     value = true
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun filters(value: BehEntityFilter.() -> Unit) {
            filtersData = (filtersData ?: mutableListOf()).also { it.add(BehEntityFilter().apply(value)) }
        }

        @SerializedName("event")
        @Expose
        var event: String? = null
    }
}