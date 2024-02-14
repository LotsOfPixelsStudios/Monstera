package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class BehTradeInterest {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("within_radius")
    @Expose
    var withinRadius: Number? = null
        

    @SerializedName("interest_time")
    @Expose
    var interestTime: Number? = null
        

    @SerializedName("remove_item_time")
    @Expose
    var removeItemTime: Number? = null
        

    @SerializedName("carried_item_switch_time")
    @Expose
    var carriedItemSwitchTime: Number? = null
        

    @SerializedName("cooldown")
    @Expose
    var cooldown: Number? = null
        
}