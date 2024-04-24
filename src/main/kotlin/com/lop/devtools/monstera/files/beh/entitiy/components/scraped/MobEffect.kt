package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class MobEffect : MonsteraRawFile() {
    @SerializedName("effect_range")
    @Expose
    var effectRange: Number? = null
        
    @SerializedName("mob_effect")
    @Expose
    var mobEffect: String? = null
        
    @SerializedName("effect_time")
    @Expose
    var effectTime: Number? = null
        
    @SerializedName("entity_filter")
    @Expose
    var entityFilterData: BehEntityFilter? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * entityFilter {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun entityFilter(value: BehEntityFilter.() -> Unit) {
        entityFilterData = (entityFilterData ?: BehEntityFilter()).apply(value)
    }

    @SerializedName("cooldown_time")
    @Expose
    var cooldownTime: Number? = null
        
}