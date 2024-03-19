package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class GameEventMovementTracking {
    @SerializedName("emit_flap")
    @Expose
    var emitFlap: Boolean? = null
        

    @SerializedName("emit_move")
    @Expose
    var emitMove: Boolean? = null
        

    @SerializedName("emit_swim")
    @Expose
    var emitSwim: Boolean? = null
        
}