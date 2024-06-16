package com.lop.devtools.monstera.files.beh.item.comp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class ItemIcon : MonsteraRawFile() {
    @SerializedName("texture")
    @Expose
    var texture: String? = null
}