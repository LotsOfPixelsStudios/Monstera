package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class EnvironmentSensor {
    @SerializedName("triggers")
    @Expose
    var triggersData: MutableList<Triggers>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * triggers {
     *     event = start_drying_out
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun triggers(value: Triggers.() -> Unit) {
        triggersData = (triggersData ?: mutableListOf()).also { it.add(Triggers().apply(value)) }
    }

    class Triggers {
        @SerializedName("filters")
        @Expose
        var filtersData: BehEntityFilter? = null
            @MonsteraBuildSetter set

        /**
         * com.lop.devtools.monstera.files.beh.entitiy.components.scraped.Filters allow data objects to specify test criteria which allows their use.
         * ```
         * filters {
         *     test = in_water
         *     operator = !=
         *     value = true
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun filters(value: BehEntityFilter.() -> Unit) {
            filtersData = (filtersData ?: BehEntityFilter()).apply(value)
        }

        @SerializedName("event")
        @Expose
        var event: String? = null
            
    }
}
