package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentMoveToBlock {
    val general = mutableMapOf<String, Any>()
    val events = arrayListOf<MutableMap<String, String>>()

    var priority: Int? = null
    var tickInterval: Int? = null
    var startChance: Float? = null
    var searchRange: Number? = null
    var searchHeight: Number? = null
    var goalRadius: Float? = null
    var stayDuration: Float? = null
    var targetSelectionMethod: String? = null
    var targetBlocks: ArrayList<String>? = null

    fun targetOffset(x: Float, y: Float, z: Float) {
        general["target_offset"] = arrayListOf(x, y, z)
    }

    /**
     * 0..n
     */
    fun onStayCompleted(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if(target != null)
            data["target"] = target.toString().lowercase()
        events.add(data)
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        tickInterval?.let { general["tick_interval"] = it }
        startChance?.let { general["start_chance"] = it }
        searchRange?.let { general["search_range"] = it }
        searchHeight?.let { general["search_height"] = it }
        goalRadius?.let { general["goal_radius"] = it }
        stayDuration?.let { general["stay_duration"] = it }
        targetSelectionMethod?.let { general["target_selection_method"] = it }
        targetBlocks?.let { general["target_blocks"] = it }

        if(events.isNotEmpty())
            general["on_stay_completed"] = events
        return general
    }
}