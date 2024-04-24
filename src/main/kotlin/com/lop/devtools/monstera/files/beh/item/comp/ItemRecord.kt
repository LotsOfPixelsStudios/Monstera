package com.lop.devtools.monstera.files.beh.item.comp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class ItemRecord : MonsteraRawFile() {
    @SerializedName("comparator_signal")
    @Expose
    var comparatorSignal: Int? = null

    @SerializedName("duration")
    @Expose
    var duration: Number? = null

    @SerializedName("sound_event")
    @Expose
    var soundEvent: String? = null
}