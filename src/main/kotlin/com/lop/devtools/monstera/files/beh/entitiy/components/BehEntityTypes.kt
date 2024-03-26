package com.lop.devtools.monstera.files.beh.entitiy.components

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class BehEntityTypes {
    @SerializedName("max_dist")
    @Expose
    var maxDist: Number? = null

    @SerializedName("must_see")
    @Expose
    var mustSee: Boolean? = null

    @SerializedName("must_see_forget_duration")
    @Expose
    var mustForgetDuration: Number? = null

    @SerializedName("sprint_speed_multiplier")
    @Expose
    var sprintSpeedMultiplier: Float? = null

    @SerializedName("walk_speed_multiplier")
    @Expose
    var walkSpeedMultiplier: Float? = null

    @SerializedName("priority")
    @Expose
    var priority: Int? = null

    @SerializedName("filters")
    @Expose
    var filterData: BehEntityFilter? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    fun filters(data: BehEntityFilter.() -> Unit) {
        filterData = (filterData ?: BehEntityFilter()).apply(data)
    }
}