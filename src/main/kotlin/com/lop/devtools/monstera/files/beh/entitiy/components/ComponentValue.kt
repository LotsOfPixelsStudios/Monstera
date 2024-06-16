package com.lop.devtools.monstera.files.beh.entitiy.components

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class ComponentValue : MonsteraRawFile() {
    @SerializedName("value")
    @Expose
    var value: Any? = null
}