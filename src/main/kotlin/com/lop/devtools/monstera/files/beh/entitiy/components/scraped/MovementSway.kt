package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class MovementSway {
    @SerializedName("sway_amplitude")
    @Expose
    var swayAmplitude: Number? = null
        
}