package com.lop.devtools.monstera.files.beh.spawnrules.conditions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class SpawnWeight : MonsteraRawFile() {
    @SerializedName("default")
    @Expose
    var default: Number? = null
}