package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehFollowOwner : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null

    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null

    @SerializedName("start_distance")
    @Expose
    var startDistance: Number? = null

    @SerializedName("stop_distance")
    @Expose
    var stopDistance: Number? = null

    @SerializedName("can_teleport")
    @Expose
    var canTeleport: Boolean? = null

    @SerializedName("ignore_vibration")
    @Expose
    var ignoreVibration: Boolean? = null
}