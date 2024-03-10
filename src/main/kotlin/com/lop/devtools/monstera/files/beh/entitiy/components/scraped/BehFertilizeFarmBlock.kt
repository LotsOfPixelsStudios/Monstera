package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class BehFertilizeFarmBlock {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
}