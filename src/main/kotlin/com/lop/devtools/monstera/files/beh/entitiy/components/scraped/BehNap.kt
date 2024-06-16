package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class BehNap : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("cooldown_min")
    @Expose
    var cooldownMin: Number? = null
        
    @SerializedName("cooldown_max")
    @Expose
    var cooldownMax: Number? = null
        
    @SerializedName("mob_detect_dist")
    @Expose
    var mobDetectDist: Number? = null
        
    @SerializedName("mob_detect_height")
    @Expose
    var mobDetectHeight: Number? = null
        
    @SerializedName("can_nap_filters")
    @Expose
    var canNapFiltersData: BehEntityFilter? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * canNapFilters {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun canNapFilters(value: BehEntityFilter.() -> Unit) {
        canNapFiltersData = (canNapFiltersData ?: BehEntityFilter()).apply(value)
    }

    @SerializedName("wake_mob_exceptions")
    @Expose
    var wakeMobExceptionsData: BehEntityFilter? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * wakeMobExceptions {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun wakeMobExceptions(value: BehEntityFilter.() -> Unit) {
        wakeMobExceptionsData = (wakeMobExceptionsData ?: BehEntityFilter()).apply(value)
    }
}