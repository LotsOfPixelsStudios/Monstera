package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter
import com.lop.devtools.monstera.files.beh.entitiy.data.DamageType

class HurtOnCondition {
    @SerializedName("damage_conditions")
    @Expose
    var damageConditionsData: MutableList<DamageConditions>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * damageConditions {
     *     cause = lava
     *     damagePerTick = 4
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun damageConditions(value: DamageConditions.() -> Unit) {
        damageConditionsData =
            (damageConditionsData ?: mutableListOf()).also { it.add(DamageConditions().apply(value)) }
    }
    class DamageConditions {

        @SerializedName("filters")
        @Expose
        var filtersData: BehEntityFilter? = null
            @MonsteraBuildSetter set

        /**
         * Filters allow data objects to specify test criteria which allows their use.
         * ```
         * filters {
         *     test = in_lava
         *     subject = self
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

        @SerializedName("cause")
        @Expose
        var cause: DamageType? = null
            

        @SerializedName("damage_per_tick")
        @Expose
        var damagePerTick: Number? = null
            
    }
}