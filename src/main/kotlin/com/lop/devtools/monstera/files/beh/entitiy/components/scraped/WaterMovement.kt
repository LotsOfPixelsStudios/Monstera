package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class WaterMovement : MonsteraRawFile() {
    @SerializedName("drag_factor")
    @Expose
    var dragFactor: Number? = null
}