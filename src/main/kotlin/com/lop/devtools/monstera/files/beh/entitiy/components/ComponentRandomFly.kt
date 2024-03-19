package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentRandomFly {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var xzDist: Int? = null
    var yDist: Int? = null
    var yOffset: Float? = null
    var speedMultiplier: Float? = null
    var canLandOnTrees: Boolean? = null
    var avoidDamageBlocks: Boolean? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        xzDist?.let { general["xz_dist"] = it }
        yDist?.let { general["y_dist"] = it }
        yOffset?.let { general["y_offset"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        canLandOnTrees?.let { general["can_land_on_trees"] = it }
        avoidDamageBlocks?.let { general["avoid_damage_blocks"] = it }
        return general
    }
}