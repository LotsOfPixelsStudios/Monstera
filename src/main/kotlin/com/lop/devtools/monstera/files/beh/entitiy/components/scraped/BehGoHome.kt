package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class BehGoHome {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null
        

    @SerializedName("interval")
    @Expose
    var interval: Number? = null
        

    @SerializedName("goal_radius")
    @Expose
    var goalRadius: Number? = null
        

    @SerializedName("on_home")
    @Expose
    var onHomeData: MutableList<OnHome>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onHome {
     *     event = minecraft:bee_returned_to_hive
     *     target = block
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onHome(value: OnHome.() -> Unit) {
        onHomeData = (onHomeData ?: mutableListOf()).also { it.add(OnHome().apply(value)) }
    }

    @SerializedName("on_failed")
    @Expose
    var onFailedData: MutableList<OnFailed>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onFailed {
     *     event = find_hive_event
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onFailed(value: OnFailed.() -> Unit) {
        onFailedData = (onFailedData ?: mutableListOf()).also { it.add(OnFailed().apply(value)) }
    }

    class OnHome {
        @SerializedName("filters")
        @Expose
        var filtersData: BehEntityFilter? = null
            

        /**
         * com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.Filters allow data objects to specify test criteria which allows their use.
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

        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }

    class OnFailed {

        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }
}
