package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehRandomLookAround : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("look_distance")
    @Expose
    var lookDistance: Number? = null
}