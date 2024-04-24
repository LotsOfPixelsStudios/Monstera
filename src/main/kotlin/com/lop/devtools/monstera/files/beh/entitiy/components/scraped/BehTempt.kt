package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehTempt : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null

    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null

    @SerializedName("can_tempt_vertically")
    @Expose
    var canTemptVertically: Boolean? = null

    @SerializedName("items")
    @Expose
    var itemsData: MutableList<String>? = null

    fun items(vararg value: String) {
        itemsData = (itemsData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("within_radius")
    @Expose
    var withinRadius: Number? = null

    @SerializedName("can_get_scared")
    @Expose
    var canGetScared: Boolean? = null

    @SerializedName("tempt_sound")
    @Expose
    var temptSound: String? = null

    @SerializedName("sound_interval")
    @Expose
    var soundIntervalData: MutableList<Number>? = null

    fun soundInterval(vararg value: Number) {
        soundIntervalData = (soundIntervalData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("can_tempt_while_ridden")
    @Expose
    var canTemptWhileRidden: Boolean? = null
}