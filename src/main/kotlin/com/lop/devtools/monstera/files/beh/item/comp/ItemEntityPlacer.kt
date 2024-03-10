package com.lop.devtools.monstera.files.beh.item.comp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemEntityPlacer {
    @SerializedName("entity")
    @Expose
    var entity: String? = null

    @SerializedName("dispense_on")
    @Expose
    var dispenseOn: MutableList<String>? = null

    @SerializedName("use_on")
    @Expose
    var useOn: MutableList<String>? = null
}