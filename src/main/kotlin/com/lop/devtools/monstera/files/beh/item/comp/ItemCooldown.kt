package com.lop.devtools.monstera.files.beh.item.comp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class ItemCooldown : MonsteraRawFile() {
    @SerializedName("category")
    @Expose
    var category: String? = null

    @SerializedName("duration")
    @Expose
    var duration: Number? = null
}