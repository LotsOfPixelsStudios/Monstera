package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class OnHurt {
    @SerializedName("event")
    @Expose
    var event: String? = null
        

    @SerializedName("target")
    @Expose
    var target: Subject? = null
        
}