package com.lop.devtools.monstera.files.beh.blocks.components

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Flammable {
    @SerializedName("catch_chance_modifier")
    @Expose
    var catchChanceModifier: Number? = null
    
    @SerializedName("destroy_chance_modifier")
    @Expose
    var destroyChanceModifier: Number? = null
}