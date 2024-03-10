package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentTeleport {
    val general = mutableMapOf<String, Any>()

    var randomTeleports: Boolean? = null
    var targetDistance: Float? = null
    var targetTeleportChance: Float? = null
    var lightTeleportChance: Float? = null
    var darkTeleportChance: Float? = null
    var maxRandomTeleportTime: Float? = null
    var minRandomTeleportTime: Float? = null


    /**
     * 0..1
     *
     * Entity will teleport to a random position within the area defined by this cube
     */
    fun randomTeleportCube(x: Float, y: Float, z: Float) {
        general["random_teleport_cube"] = arrayListOf(x, y, z)
    }

    fun getData(): MutableMap<String, Any> {
        randomTeleports?.let { general["random_teleports"] = it }
        targetDistance?.let { general["target_distance"] = it }
        targetTeleportChance?.let { general["target_teleport_chance"] = it }
        lightTeleportChance?.let { general["light_teleport_chance"] = it }
        darkTeleportChance?.let { general["dark_teleport_chance"] = it }
        maxRandomTeleportTime?.let { general["max_random_teleport_time"] = it }
        minRandomTeleportTime?.let { general["min_random_teleport_time"] = it }
        return general
    }
}