package com.lop.devtools.monstera.files.beh.item.comp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemUseModifiers {
    @SerializedName("use_duration")
    @Expose
    var useDuration: Number? = null

    @SerializedName("movement_modifier")
    @Expose
    var movementModifier: Number? = null
}