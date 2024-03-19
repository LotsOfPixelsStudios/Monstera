package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class NavigationClimb {
    @SerializedName("can_path_over_water")
    @Expose
    var canPathOverWater: Boolean? = null
        
}