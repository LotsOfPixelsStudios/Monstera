package com.lop.devtools.monstera.files.beh.spawnrules.conditions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class SpawnPermuteType : MonsteraRawFile() {
    @SerializedName("weight")
    @Expose
    var weight: Number? = null

    @SerializedName("entity_type")
    @Expose
    var entityType: String? = null
}