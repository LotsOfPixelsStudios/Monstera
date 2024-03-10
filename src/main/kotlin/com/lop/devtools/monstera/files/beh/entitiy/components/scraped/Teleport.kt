package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class Teleport {
    @SerializedName("random_teleports")
    @Expose
    var randomTeleports: Boolean? = null
        

    @SerializedName("max_random_teleport_time")
    @Expose
    var maxRandomTeleportTime: Number? = null
        

    @SerializedName("random_teleport_cube")
    @Expose
    var randomTeleportCubeData: MutableList<Number>? = null
        

    fun randomTeleportCube(vararg value: Number) {
        randomTeleportCubeData = (randomTeleportCubeData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("target_distance")
    @Expose
    var targetDistance: Number? = null
        

    @SerializedName("target_teleport_chance")
    @Expose
    var targetTeleportChance: Number? = null
        

    @SerializedName("light_teleport_chance")
    @Expose
    var lightTeleportChance: Number? = null
        
}