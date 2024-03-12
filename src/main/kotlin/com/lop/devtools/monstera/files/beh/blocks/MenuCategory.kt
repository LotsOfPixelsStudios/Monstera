package com.lop.devtools.monstera.files.beh.blocks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MenuCategory {
    @SerializedName("category")
    @Expose
    var category: String? = null
    
    @SerializedName("group")
    @Expose
    var group: String? = null
    
    @SerializedName("is_hidden_in_commands")
    @Expose
    var isHiddenInCommands: Boolean? = null
}