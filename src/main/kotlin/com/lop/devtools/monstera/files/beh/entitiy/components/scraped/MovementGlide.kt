package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class MovementGlide : MonsteraRawFile() {
    @SerializedName("start_speed")
    @Expose
    var startSpeed: Number? = null
        
    @SerializedName("speed_when_turning")
    @Expose
    var speedWhenTurning: Number? = null
}