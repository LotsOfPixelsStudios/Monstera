package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.entitiy.components.BehEntityTypes
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class BehFollowCaravan : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null
        
    @SerializedName("entity_count")
    @Expose
    var entityCount: Number? = null
        
    @SerializedName("entity_types")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var entityTypesData: BehEntityTypes? = null
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
        entityTypesData = (entityTypesData ?: BehEntityTypes()).apply(value)
    }
}