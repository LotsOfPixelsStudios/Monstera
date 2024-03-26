package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class BehPlayDead {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("duration")
    @Expose
    var duration: Number? = null
        

    @SerializedName("force_below_health")
    @Expose
    var forceBelowHealth: Number? = null
        

    @SerializedName("random_start_chance")
    @Expose
    var randomStartChance: Number? = null
        

    @SerializedName("random_damage_range")
    @Expose
    var randomDamageRangeData: MutableList<Number>? = null
        

    fun randomDamageRange(vararg value: Number) {
        randomDamageRangeData = (randomDamageRangeData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("damage_sources")
    @Expose
    var damageSourcesData: MutableList<String>? = null
        

    fun damageSources(vararg value: String) {
        damageSourcesData = (damageSourcesData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("apply_regeneration")
    @Expose
    var applyRegeneration: Boolean? = null
        

    @SerializedName("filters")
    @Expose
    var filtersData: BehEntityFilter? = null
        @MonsteraBuildSetter set

    /**
     * com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.Filters allow data objects to specify test criteria which allows their use.
     * ```
     * filters {
     *     test = in_water
     *     operator = ==
     *     value = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun filters(value: BehEntityFilter.() -> Unit) {
        filtersData = (filtersData ?: BehEntityFilter()).apply(value)
    }
}