package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class BehEatCarriedItem {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("delay_before_eating")
    @Expose
    var delayBeforeEating: Number? = null
        
}