package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class Pushable : MonsteraRawFile() {
    @SerializedName("is_pushable")
    @Expose
    var isPushable: Boolean? = null
        
    @SerializedName("is_pushable_by_piston")
    @Expose
    var isPushableByPiston: Boolean? = null
}