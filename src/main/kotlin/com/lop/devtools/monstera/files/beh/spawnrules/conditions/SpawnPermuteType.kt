package com.lop.devtools.monstera.files.beh.spawnrules.conditions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SpawnPermuteType {
    @SerializedName("weight")
    @Expose
    var weight: Number? = null

    @SerializedName("entity_type")
    @Expose
    var entityType: String? = null
}