package com.lop.devtools.monstera.files.beh.entitiy.components

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ComponentValue {
    @SerializedName("value")
    @Expose
    var value: Any? = null
}