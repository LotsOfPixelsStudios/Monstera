package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class AdmireItem {

    @SerializedName("duration")
    @Expose
    var duration: Number? = null

    @SerializedName("cooldown_after_being_attacked")
    @Expose
    var cooldownAfterBeingAttacked: Number? = null
}