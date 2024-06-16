package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class Attack : MonsteraRawFile() {
    @SerializedName("damage")
    @Expose
    var damage: Number? = null

    @SerializedName("effect_name")
    @Expose
    var effectName: String? = null

    @SerializedName("effect_duration")
    @Expose
    var effectDuration: Number? = null
}