package com.lop.devtools.monstera.files.beh.entitiy.events

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class BehEntityAddRemove {
    @SerializedName("component_groups")
    @Expose
    var componentGroups: MutableList<String>? = null
}