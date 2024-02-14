package com.lop.devtools.monstera.files.beh.item.comp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemBlockPlacer {
    @SerializedName("block")
    @Expose
    var block: String? = null

    @SerializedName("user_on")
    @Expose
    var useOn: MutableList<String>? = null
}