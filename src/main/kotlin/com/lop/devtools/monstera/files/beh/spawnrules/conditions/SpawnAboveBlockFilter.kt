package com.lop.devtools.monstera.files.beh.spawnrules.conditions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class SpawnAboveBlockFilter : MonsteraRawFile() {
    @SerializedName("blocks")
    @Expose
    var blocks: MutableList<String>? = null

    @SerializedName("distance")
    @Expose
    var distance: Number? = null
}