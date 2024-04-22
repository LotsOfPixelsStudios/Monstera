package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class Boss : MonsteraRawFile() {
    @SerializedName("should_darken_sky")
    @Expose
    var shouldDarkenSky: Boolean? = null
        
    @SerializedName("hud_range")
    @Expose
    var hudRange: Number? = null
}