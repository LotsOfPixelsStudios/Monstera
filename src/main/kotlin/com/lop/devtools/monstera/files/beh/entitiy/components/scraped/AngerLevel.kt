package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class AngerLevel {

    @SerializedName("max_anger")
    @Expose
    var maxAnger: Number? = null

    @SerializedName("angry_threshold")
    @Expose
    var angryThreshold: Number? = null
        

    @SerializedName("remove_targets_below_angry_threshold")
    @Expose
    var removeTargetsBelowAngryThreshold: Boolean? = null

    @SerializedName("angry_boost")
    @Expose
    var angryBoost: Number? = null

    @SerializedName("anger_decrement_interval")
    @Expose
    var angerDecrementInterval: Number? = null

    @SerializedName("default_annoyingness")
    @Expose
    var defaultAnnoyingness: Number? = null

    @SerializedName("default_projectile_annoyingness")
    @Expose
    var defaultProjectileAnnoyingness: Number? = null

    @SerializedName("on_increase_sounds")
    @Expose
    var onIncreaseSoundsData: MutableList<OnIncreaseSounds>? = null
        @MonsteraBuildSetter set
        

    /**
     *
     * ```
     * onIncreaseSounds {
     *     sound = listening_angry
     *     condition = query.anger_level(this) >= 40
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onIncreaseSounds(value: OnIncreaseSounds.() -> Unit) {
        onIncreaseSoundsData =
            (onIncreaseSoundsData ?: mutableListOf()).also { it.add(OnIncreaseSounds().apply(value)) }
    }

    @SerializedName("nuisance_filter")
    @Expose
    var nuisanceFilterData: BehEntityFilter? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * nuisanceFilter {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun nuisanceFilter(value: BehEntityFilter.() -> Unit) {
        nuisanceFilterData = (nuisanceFilterData ?: BehEntityFilter()).apply(value)
    }

    class OnIncreaseSounds {
        @SerializedName("sound")
        @Expose
        var sound: String? = null

        @SerializedName("condition")
        @Expose
        var condition: String? = null
    }
}
