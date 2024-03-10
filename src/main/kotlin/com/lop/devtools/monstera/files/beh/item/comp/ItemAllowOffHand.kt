package com.lop.devtools.monstera.files.beh.item.comp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemAllowOffHand {
    @SerializedName("value")
    @Expose
    var value: Boolean? = null
}