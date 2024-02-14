package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class PlayerExhaustion {
    @SerializedName("value")
    @Expose
    var value: Number? = null
        

    @SerializedName("max")
    @Expose
    var max: Number? = null
        
}