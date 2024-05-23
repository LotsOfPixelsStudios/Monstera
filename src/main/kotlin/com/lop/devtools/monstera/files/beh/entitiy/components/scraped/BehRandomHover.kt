package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehRandomHover : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null

    @SerializedName("xz_dist")
    @Expose
    var xzDist: Number? = null

    @SerializedName("y_dist")
    @Expose
    var yDist: Number? = null

    @SerializedName("y_offset")
    @Expose
    var yOffset: Number? = null

    @SerializedName("interval")
    @Expose
    var interval: Number? = null

    @SerializedName("hover_height")
    @Expose
    var hoverHeightData: MutableList<Number>? = null

    fun hoverHeight(vararg value: Number) {
        hoverHeightData = (hoverHeightData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }
}