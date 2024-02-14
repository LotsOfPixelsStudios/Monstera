package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class VariableMaxAutoStep {
    @SerializedName("base_value")
    @Expose
    var baseValue: Number? = null
        

    @SerializedName("controlled_value")
    @Expose
    var controlledValue: Number? = null
        

    @SerializedName("jump_prevented_value")
    @Expose
    var jumpPreventedValue: Number? = null
        
}