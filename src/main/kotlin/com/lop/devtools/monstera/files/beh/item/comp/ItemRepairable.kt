package com.lop.devtools.monstera.files.beh.item.comp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemRepairable {
    @SerializedName("on_repaired")
    @Expose
    var onRepaired: String? = null

    @SerializedName("repair_items")
    @Expose
    var repairItems: MutableList<String>? = null
}