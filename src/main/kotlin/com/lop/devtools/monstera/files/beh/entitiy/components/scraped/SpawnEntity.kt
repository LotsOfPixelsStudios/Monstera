package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class SpawnEntity : MonsteraRawFile() {
    @SerializedName("entities")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var entitiesData: Entities? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * entities {
     *     filters {  }
     *     minWaitTime = 300
     *     maxWaitTime = 600
     *     numToSpawn = 1
     *     shouldLeash = false
     *     singleUse = false
     *     spawnEntity = "minecraft:cow"
     *     spawnEvent = "minecraft:entity_born"
     *     spawnItem = "egg"
     *     spawnMethod = "born"
     *     spawnSound = plop
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun entities(value: Entities.() -> Unit) {
        entitiesData = (entitiesData ?: Entities()).apply(value)
    }

    class Entities : MonsteraRawFile() {
        @SerializedName("min_wait_time")
        @Expose
        var minWaitTime: Number? = null
            

        @SerializedName("max_wait_time")
        @Expose
        var maxWaitTime: Number? = null

        @SerializedName("num_to_spawn")
        @Expose
        var numToSpawn: Number? = null

        @SerializedName("should_leash")
        @Expose
        var shouldLeash: Boolean? = null

        @SerializedName("single_use")
        @Expose
        var singleUse: Boolean? = null

        @SerializedName("spawn_entity")
        @Expose
        var spawnEntity: String? = null

        @SerializedName("spawn_event")
        @Expose
        var spawnEvent: String? = null

        @SerializedName("spawn_item")
        @Expose
        var spawnItem: String? = null

        @SerializedName("spawn_method")
        @Expose
        var spawnMethod: String? = null

        @SerializedName("spawn_sound")
        @Expose
        var spawnSound: String? = null
            

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