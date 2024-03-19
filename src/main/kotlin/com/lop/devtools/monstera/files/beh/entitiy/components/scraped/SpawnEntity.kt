package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class SpawnEntity {
    @SerializedName("entities")
    @Expose
    var entitiesData: Entities? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * entities {
     *     minWaitTime = 300
     *     maxWaitTime = 600
     *     spawnSound = plop
     *     spawnItem = egg
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun entities(value: Entities.() -> Unit) {
        entitiesData = (entitiesData ?: Entities()).apply(value)
    }

    class Entities {
        @SerializedName("min_wait_time")
        @Expose
        var minWaitTime: Number? = null
            

        @SerializedName("max_wait_time")
        @Expose
        var maxWaitTime: Number? = null
            

        @SerializedName("spawn_sound")
        @Expose
        var spawnSound: String? = null
            

        @SerializedName("spawn_item")
        @Expose
        var spawnItem: String? = null
            

        @SerializedName("filters")
        @Expose
        var filtersData: BehEntityFilter? = null
            @MonsteraBuildSetter set

        /**
         * com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.Filters allow data objects to specify test criteria which allows their use.
         * ```
         * filters {
         *     test = rider_count
         *     subject = self
         *     operator = ==
         *     value = 0
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun filters(value: BehEntityFilter.() -> Unit) {
            filtersData = (filtersData ?: BehEntityFilter()).apply(value)
        }
    }
}