package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class MovementJump {
    @SerializedName("jump_delay")
    @Expose
    var jumpDelayData: MutableList<Number>? = null
        

    @Components.VanillaComponentMarker
    fun jumpDelay(vararg value: Number) {
        jumpDelayData = (jumpDelayData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }
}