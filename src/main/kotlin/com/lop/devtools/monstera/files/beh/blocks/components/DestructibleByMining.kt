package com.lop.devtools.monstera.files.beh.blocks.components

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DestructibleByMining {
    @SerializedName("seconds_to_destroy")
    @Expose
    var secondsToDestroy: Number? = null
}