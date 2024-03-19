package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.BehEntityTypes
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class BehHurtByTarget {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("entity_types")
    @Expose
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

    @SerializedName("hurt_owner")
    @Expose
    var hurtOwner: Boolean? = null
        

    @SerializedName("alert_same_type")
    @Expose
    var alertSameType: Boolean? = null
        
}
