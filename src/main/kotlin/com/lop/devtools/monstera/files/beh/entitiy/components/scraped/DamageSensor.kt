@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter
import com.lop.devtools.monstera.files.beh.entitiy.data.DamageType
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class DamageSensor : MonsteraRawFile() {
    @SerializedName("triggers")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var triggersData: MutableList<Trigger>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * trigger {
     *     dealsDamage = false
     *     cause = DamageType
     *     damageMultiplier = 1
     *     onDamage { }
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun trigger(value: Trigger.() -> Unit) {
        triggersData = (triggersData ?: mutableListOf()).also { it.add(Trigger().apply(value)) }
    }

    class Trigger : MonsteraRawFile() {
        @SerializedName("on_damage")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var onDamageData: OnDamage? = null
            @MonsteraBuildSetter set

        /**
         *
         * ```
         * onDamage {
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun onDamage(value: OnDamage.() -> Unit) {
            onDamageData = (onDamageData ?: OnDamage()).apply(value)
        }

        @SerializedName("deals_damage")
        @Expose
        var dealsDamage: Boolean? = null

        @SerializedName("cause")
        @Expose
        var cause: DamageType? = null

        @SerializedName("damage_multiplier")
        @Expose
        var damageMultiplier: Number? = null

        @SerializedName("on_damage_sound_event")
        @Expose
        var onDamageSoundEvent: String? = null
    }

    class OnDamage : MonsteraRawFile() {
        @SerializedName("filters")
        @Expose
        var filtersData: BehEntityFilter? = null
            @MonsteraBuildSetter set

        /**
         * com.lop.devtools.monstera.files.beh.entitiy.components.scraped.Filters allow data objects to specify test criteria which allows their use.
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

        @SerializedName("target")
        @Expose
        var target: Subject? = null

        @SerializedName("event")
        @Expose
        var event: String? = null
    }
}