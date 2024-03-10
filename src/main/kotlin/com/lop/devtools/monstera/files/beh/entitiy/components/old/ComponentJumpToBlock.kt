package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentJumpToBlock {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var searchWidth: Number? = null
    var searchHeight: Number? = null
    var minimumPathLength: Number? = null
    var minimumDistance: Number? = null
    var scaleFactor: Float? = null

    fun cooldownRange(min: Number, max: Number) {
        general["cooldown_range"] = arrayListOf(min, max)
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        searchWidth?.let { general["search_width"] = it }
        searchHeight?.let { general["search_height"] = it }
        minimumPathLength?.let { general["minimum_path_length"] = it }
        minimumDistance?.let { general["minimum_distance"] = it }
        scaleFactor?.let { general["scale_factor"] = it }
        return general
    }
}