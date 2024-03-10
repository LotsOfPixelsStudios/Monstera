package com.lop.devtools.monstera.files.beh.spawnrules.conditions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SpawnHerd {
    @SerializedName("min_size")
    @Expose
    var minSize: Int? = null

    @SerializedName("max_size")
    @Expose
    var maxSize: Int? = null

    @SerializedName("event")
    @Expose
    var event: String? = null

    /**
     * on how many entities the vent shouldn't be executed, for example that in a heard not only babies are
     */
    @SerializedName("event_skip_count")
    @Expose
    var eventSkipCount: Int? = null
}