package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.DebugMarker
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.getMonsteraLogger

class BehRangedAttack : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null

    @SerializedName("burst_shots")
    @Expose
    var burstShots: Number? = null

    @SerializedName("burst_interval")
    @Expose
    var burstInterval: Number? = null

    @SerializedName("charge_charged_trigger")
    @Expose
    var chargeChargedTrigger: Number? = null

    @SerializedName("charge_shoot_trigger")
    @Expose
    var chargeShootTrigger: Number? = null

    @SerializedName("attack_interval_min")
    @Expose
    var attackIntervalMin: Number? = null

    @SerializedName("attack_interval_max")
    @Expose
    var attackIntervalMax: Number? = null

    @SerializedName("attack_radius")
    @Expose
    var attackRadius: Number? = null

    @SerializedName("swing")
    @Expose
    var swing: Boolean? = null

    @SerializedName("attack_radius_min")
    @Expose
    var attackRadiusMin: Number? = null

    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null

    @SerializedName("target_in_sight_time")
    @Expose
    var targetInSightTime: Number? = null

    @SerializedName("attack_interval")
    @Expose
    var attackInterval: Number? = null

    @DebugMarker
    fun verify() {
        if(attackRadius == null) {
            getMonsteraLogger(this.javaClass.name).warn("Ranged attack has no attackRadius!")
        }
    }
}