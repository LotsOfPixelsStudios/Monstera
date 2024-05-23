package com.lop.devtools.monstera.files.beh.item.comp

import com.google.gson.annotations.Expose
import com.lop.devtools.monstera.files.MonsteraRawFile

open class ItemCreativeCategory : MonsteraRawFile() {
    @Expose
    var parent: String? = null  
}