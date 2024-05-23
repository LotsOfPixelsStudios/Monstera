package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class GrowsCrop : MonsteraRawFile() {
    @SerializedName("charges")
    @Expose
    var charges: Number? = null
        
    @SerializedName("chance")
    @Expose
    var chance: Number? = null
}