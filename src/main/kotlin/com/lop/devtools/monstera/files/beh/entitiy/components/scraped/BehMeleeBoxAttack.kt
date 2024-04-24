package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class BehMeleeBoxAttack : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null

    @SerializedName("on_kill")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var onKillData: OnKill? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onKill {
     *     event = killed_enemy_event
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onKill(value: OnKill.() -> Unit) {
        onKillData = (onKillData ?: OnKill()).apply(value)
    }

    @SerializedName("attack_once")
    @Expose
    var attackOnce: Boolean? = null

    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null

    @SerializedName("on_attack")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var onAttackData: OnAttack? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onAttack {
     *     event = countdown_to_perish_event
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onAttack(value: OnAttack.() -> Unit) {
        onAttackData = (onAttackData ?: OnAttack()).apply(value)
    }

    @SerializedName("track_target")
    @Expose
    var trackTarget: Boolean? = null

    @SerializedName("random_stop_interval")
    @Expose
    var randomStopInterval: Number? = null

    @SerializedName("can_spread_on_fire")
    @Expose
    var canSpreadOnFire: Boolean? = null

    @SerializedName("require_complete_path")
    @Expose
    var requireCompletePath: Boolean? = null

    @SerializedName("cooldown_time")
    @Expose
    var cooldownTime: Number? = null

    @SerializedName("melee_fov")
    @Expose
    var meleeFov: Number? = null

    class OnKill : MonsteraRawFile() {

        @SerializedName("event")
        @Expose
        var event: String? = null

        @SerializedName("target")
        @Expose
        var target: Subject? = null
    }

    class OnAttack : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null

        @SerializedName("target")
        @Expose
        var target: Subject? = null
    }
}
