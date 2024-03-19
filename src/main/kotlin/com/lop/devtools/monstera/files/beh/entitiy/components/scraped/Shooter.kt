package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class Shooter {
    @SerializedName("def")
    @Expose
    var def: String? = null

    @SerializedName("sound")
    @Expose
    var sound: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("aux_val")
    @Expose
    var auxVal: Number? = null

    @SerializedName("power")
    @Expose
    var power: Number? = null

    @SerializedName("projectiles")
    @Expose
    var projectilesData: MutableList<Projectiles>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * projectiles {
     *     def = minecraft:splash_potion
     *     auxVal = 21
     *     loseTarget = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun projectiles(value: Projectiles.() -> Unit) {
        projectilesData = (projectilesData ?: mutableListOf()).also { it.add(Projectiles().apply(value)) }
    }

    @SerializedName("magic")
    @Expose
    var magic: Boolean? = null

    class Projectiles {
        @SerializedName("def")
        @Expose
        var def: String? = null

        @SerializedName("aux_val")
        @Expose
        var auxVal: Number? = null

        @SerializedName("filters")
        @Expose
        var filtersData: BehEntityFilter? = null
            @MonsteraBuildSetter set

        /**
         * Filters allow data objects to specify test criteria which allows their use.
         * ```
         * filters {
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun filters(value: BehEntityFilter.() -> Unit) {
            filtersData = (filtersData ?: BehEntityFilter()).apply(value)
        }

        @SerializedName("lose_target")
        @Expose
        var loseTarget: Boolean? = null
    }
}