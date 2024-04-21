package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class CollisionBox : MonsteraRawFile() {

    @SerializedName("width")
    @Expose
    var width: Number? = null
        
    @SerializedName("height")
    @Expose
    var height: Number? = null
}