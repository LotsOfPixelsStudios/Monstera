package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentMoveToPOI {
    val general = mutableMapOf<String, Any>()

    var poiType: String? = null
    var speedMultiplier: Float? = null

    fun getData(): MutableMap<String, Any> {
        poiType?.let { general["poi_type"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }

        return general
    }
}