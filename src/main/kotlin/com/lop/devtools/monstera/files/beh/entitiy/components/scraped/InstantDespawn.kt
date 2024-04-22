package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class InstantDespawn : MonsteraRawFile() {
    @SerializedName("remove_child_entities")
    @Expose
    var removeChildEntities: Boolean? = null
}