package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class BehKnockbackRoar : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("duration")
    @Expose
    var duration: Number? = null
        
    @SerializedName("attack_time")
    @Expose
    var attackTime: Number? = null
        
    @SerializedName("knockback_damage")
    @Expose
    var knockbackDamage: Number? = null
        
    @SerializedName("knockback_horizontal_strength")
    @Expose
    var knockbackHorizontalStrength: Number? = null
        
    @SerializedName("knockback_vertical_strength")
    @Expose
    var knockbackVerticalStrength: Number? = null
        
    @SerializedName("knockback_range")
    @Expose
    var knockbackRange: Number? = null
        
    @SerializedName("knockback_filters")
    @Expose
    var knockbackFiltersData: BehEntityFilter? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * knockbackFilters {
     *     test = is_family
     *     subject = other
     *     operator = !=
     *     value = ravager
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun knockbackFilters(value: BehEntityFilter.() -> Unit) {
        knockbackFiltersData = (knockbackFiltersData ?: BehEntityFilter()).apply(value)
    }

    @SerializedName("damage_filters")
    @Expose
    var damageFiltersData: BehEntityFilter? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * damageFilters {
     *     test = is_family
     *     subject = other
     *     operator = !=
     *     value = illager
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun damageFilters(value: BehEntityFilter.() -> Unit) {
        damageFiltersData = (damageFiltersData ?: BehEntityFilter()).apply(value)
    }

    @SerializedName("on_roar_end")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var onRoarEndData: OnRoarEnd? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onRoarEnd {
     *     event = minecraft:end_roar
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onRoarEnd(value: OnRoarEnd.() -> Unit) {
        onRoarEndData = (onRoarEndData ?: OnRoarEnd()).apply(value)
    }

    @SerializedName("cooldown_time")
    @Expose
    var cooldownTime: Number? = null
        

    class OnRoarEnd : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null
    }
}
