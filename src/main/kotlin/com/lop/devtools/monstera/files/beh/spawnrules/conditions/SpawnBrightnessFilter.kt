package com.lop.devtools.monstera.files.beh.spawnrules.conditions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class SpawnBrightnessFilter : MonsteraRawFile() {
    @SerializedName("min")
    @Expose
    var min: Number? = null

    @SerializedName("max")
    @Expose
    var max: Number? = null

    @SerializedName("adjust_for_weather")
    @Expose
    var adjustForWeather: Boolean? = null
}