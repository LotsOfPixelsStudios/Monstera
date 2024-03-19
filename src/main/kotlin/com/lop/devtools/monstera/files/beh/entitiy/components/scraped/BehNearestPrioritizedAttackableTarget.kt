package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.BehEntityTypes
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class BehNearestPrioritizedAttackableTarget {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("attack_interval")
    @Expose
    var attackInterval: Number? = null
        

    @SerializedName("reselect_targets")
    @Expose
    var reselectTargets: Boolean? = null
        

    @SerializedName("target_search_height")
    @Expose
    var targetSearchHeight: Number? = null
        

    @SerializedName("entity_types")
    @Expose
    var entityTypesData: MutableList<BehEntityTypes>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * entityTypes {
     *     maxDist = 12
     *     priority = 0
     *     filters { }
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun entityType(value: BehEntityTypes.() -> Unit) {
        entityTypesData = (entityTypesData ?: mutableListOf()).also { it.add(BehEntityTypes().apply(value)) }
    }

    @SerializedName("within_radius")
    @Expose
    var withinRadius: Number? = null
        

    @SerializedName("persist_time")
    @Expose
    var persistTime: Number? = null
        

    @SerializedName("must_see")
    @Expose
    var mustSee: Boolean? = null
        

    @SerializedName("must_reach")
    @Expose
    var mustReach: Boolean? = null
        
}