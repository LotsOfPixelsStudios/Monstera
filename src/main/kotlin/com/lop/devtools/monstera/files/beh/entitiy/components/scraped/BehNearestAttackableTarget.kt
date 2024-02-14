package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.BehEntityTypes
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class BehNearestAttackableTarget {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null

    @SerializedName("must_see")
    @Expose
    var mustSee: Boolean? = null

    @SerializedName("reselect_targets")
    @Expose
    var reselectTargets: Boolean? = null

    @SerializedName("within_radius")
    @Expose
    var withinRadius: Number? = null

    @SerializedName("must_see_forget_duration")
    @Expose
    var mustSeeForgetDuration: Number? = null

    @SerializedName("entity_types")
    @Expose
    var entityTypesData: MutableList<BehEntityTypes>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * entityTypes {
     *     maxDist = 8
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun entityTypes(value: BehEntityTypes.() -> Unit) {
        entityTypesData = (entityTypesData ?: mutableListOf()).also { it.add(BehEntityTypes().apply(value)) }
    }

    @SerializedName("attack_interval")
    @Expose
    var attackInterval: Number? = null

    @SerializedName("persist_time")
    @Expose
    var persistTime: Number? = null

    @SerializedName("attack_interval_min")
    @Expose
    var attackIntervalMin: Number? = null

    @SerializedName("must_reach")
    @Expose
    var mustReach: Boolean? = null

    @SerializedName("scan_interval")
    @Expose
    var scanInterval: Number? = null

    @SerializedName("target_search_height")
    @Expose
    var targetSearchHeight: Number? = null
}