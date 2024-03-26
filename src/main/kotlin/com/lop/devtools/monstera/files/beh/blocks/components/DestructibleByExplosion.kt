package com.lop.devtools.monstera.files.beh.blocks.components

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DestructibleByExplosion {
    @SerializedName("explosion_resistance")
    @Expose
    var explosionResistance: Number? = null
}