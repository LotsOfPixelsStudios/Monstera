package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class Dash : MonsteraRawFile() {
    @SerializedName("cooldown_time")
    @Expose
    var cooldownTime: Number? = null
        
    @SerializedName("horizontal_momentum")
    @Expose
    var horizontalMomentum: Number? = null
        
    @SerializedName("vertical_momentum")
    @Expose
    var verticalMomentum: Number? = null
}