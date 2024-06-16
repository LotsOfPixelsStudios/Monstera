package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.entitiy.components.BehEntityTypes
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class BehTargetWhenPushed : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("percent_chance")
    @Expose
    var percentChance: Number? = null
        
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