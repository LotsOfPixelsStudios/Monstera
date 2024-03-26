package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class AreaAttack {
    @SerializedName("damage_range")
    @Expose
    var damageRange: Number? = null
        

    @SerializedName("damage_per_tick")
    @Expose
    var damagePerTick: Number? = null
        

    @SerializedName("damage_cooldown")
    @Expose
    var damageCooldown: Number? = null
        

    @SerializedName("cause")
    @Expose
    var cause: String? = null
        

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
}