package com.lop.devtools.monstera.files.beh.spawnrules.conditions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class SpawnDifficultyFilter : MonsteraRawFile() {
    @SerializedName("min")
    @Expose
    var min: String? = null

    @SerializedName("max")
    @Expose
    var max: String? = null
}