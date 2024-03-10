package com.lop.devtools.monstera.files.beh.item.comp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemFuel {
    @SerializedName("duration")
    @Expose
    var duration: Number? = null
}