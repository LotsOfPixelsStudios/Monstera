package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentRiseToLiquidLevel {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var liquidYOffset: Float? = null
    var riseDelta: Float? = null
    var sinkDelta: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        liquidYOffset?.let { general["liquid_y_offset"] = it }
        riseDelta?.let { general["rise_delta"] = it }
        sinkDelta?.let { general["sink_delta"] = it }
        return general
    }
}