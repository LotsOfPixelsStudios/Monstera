package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class InstantDespawn {
    @SerializedName("remove_child_entities")
    @Expose
    var removeChildEntities: Boolean? = null
}