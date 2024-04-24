package com.lop.devtools.monstera.files.beh.item.comp

import com.google.gson.annotations.Expose
import com.lop.devtools.monstera.files.MonsteraRawFile

open class ItemArmor : MonsteraRawFile() {
    @Expose
    var protection: Number? = null
}