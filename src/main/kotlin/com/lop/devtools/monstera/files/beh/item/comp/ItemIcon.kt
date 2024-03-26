package com.lop.devtools.monstera.files.beh.item.comp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemIcon {
    @SerializedName("texture")
    @Expose
    var texture: String? = null
}