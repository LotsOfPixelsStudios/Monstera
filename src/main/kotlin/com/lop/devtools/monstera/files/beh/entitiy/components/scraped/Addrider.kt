package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class Addrider {

    @SerializedName("entity_type")
    @Expose
    var entityType: String? = null

    @SerializedName("spawn_event")
    @Expose
    var spawnEvent: String? = null
}