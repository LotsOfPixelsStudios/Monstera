package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class GrowsCrop {
    @SerializedName("charges")
    @Expose
    var charges: Number? = null
        

    @SerializedName("chance")
    @Expose
    var chance: Number? = null
        
}