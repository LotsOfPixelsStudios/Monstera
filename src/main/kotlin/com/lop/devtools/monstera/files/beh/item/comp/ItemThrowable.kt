package com.lop.devtools.monstera.files.beh.item.comp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemThrowable {
    @SerializedName("do_swing_animation")
    @Expose
    var doSwingAnimation: Boolean? = null

    @SerializedName("launch_power_scale")
    @Expose
    var launchPowerScale: Number? = null

    @SerializedName("max_draw_duration")
    @Expose
    var maxDrawDuration: Number? = null

    @SerializedName("max_launch_power")
    @Expose
    var maxLaunchPower: Number? = null

    @SerializedName("min_draw_duration")
    @Expose
    var minDrawDuration: Number? = null

    @SerializedName("scale_power_by_draw_duration")
    @Expose
    var scalePowerByDrawDuration: Boolean? = null
}