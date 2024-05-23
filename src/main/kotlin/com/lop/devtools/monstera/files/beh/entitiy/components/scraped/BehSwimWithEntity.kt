package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.entitiy.components.BehEntityTypes
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class BehSwimWithEntity : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("success_rate")
    @Expose
    var successRate: Number? = null
        
    @SerializedName("chance_to_stop")
    @Expose
    var chanceToStop: Number? = null
        
    @SerializedName("state_check_interval")
    @Expose
    var stateCheckInterval: Number? = null
        
    @SerializedName("catch_up_threshold")
    @Expose
    var catchUpThreshold: Number? = null
        
    @SerializedName("match_direction_threshold")
    @Expose
    var matchDirectionThreshold: Number? = null
        
    @SerializedName("catch_up_multiplier")
    @Expose
    var catchUpMultiplier: Number? = null
        
    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null
        
    @SerializedName("search_range")
    @Expose
    var searchRange: Number? = null
        
    @SerializedName("stop_distance")
    @Expose
    var stopDistance: Number? = null
        
    @SerializedName("entity_types")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var entityTypesData: MutableList<BehEntityTypes>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * entityTypes {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun entityTypes(value: BehEntityTypes.() -> Unit) {
        entityTypesData = (entityTypesData ?: mutableListOf()).also { it.add(BehEntityTypes().apply(value)) }
    }
}