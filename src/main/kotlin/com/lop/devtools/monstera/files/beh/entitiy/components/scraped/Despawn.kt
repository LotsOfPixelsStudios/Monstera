package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class Despawn {
    @SerializedName("despawn_from_distance")
    @Expose
    var despawnFromDistanceData: DespawnFromDistance? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * despawnFromDistance {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun despawnFromDistance(value: DespawnFromDistance.() -> Unit) {
        despawnFromDistanceData = (despawnFromDistanceData ?: DespawnFromDistance()).apply(value)
    }

    @SerializedName("filters")
    @Expose
    var filtersData: BehEntityFilter? = null
        @MonsteraBuildSetter set

    /**
     * com.lop.devtools.monstera.files.beh.entitiy.components.scraped.Filters allow data objects to specify test criteria which allows their use.
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

    @SerializedName("remove_child_entities")
    @Expose
    var removeChildEntities: Boolean? = null
        

    class DespawnFromDistance
}