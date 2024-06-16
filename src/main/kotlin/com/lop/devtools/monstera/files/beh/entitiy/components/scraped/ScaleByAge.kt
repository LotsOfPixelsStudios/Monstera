package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class ScaleByAge : MonsteraRawFile() {
    @SerializedName("start_scale")
    @Expose
    var startScale: Number? = null
        
    @SerializedName("end_scale")
    @Expose
    var endScale: Number? = null
}