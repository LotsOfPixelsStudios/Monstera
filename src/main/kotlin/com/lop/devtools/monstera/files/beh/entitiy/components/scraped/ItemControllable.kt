package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class ItemControllable : MonsteraRawFile() {
    @SerializedName("control_items")
    @Expose
    var controlItems: String? = null
}