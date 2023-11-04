package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentAvoidBlock: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        val events = arrayListOf<Any>()


        override fun getData(): MutableMap<String, Any> {
            priority?.let { general["priority"] = it }
            tickInterval?.let { general["tick_interval"] = it }
            searchRange?.let { general["search_range"] = it }
            searchHeight?.let { general["search_height"] = it }
            walkSpeedModifier?.let { general["walk_speed_modifier"] = it }
            sprintSpeedModifier?.let { general["sprint_speed_modifier"] = it }
            avoidBlockSound?.let { general["avoid_block_sound"] = it }
            targetSelectionMethod?.let { general["target_selection_method"] = it }
            targetBlocks?.let { general["target_blocks"] = it }

            if (events.isNotEmpty())
                general["on_escape"] = events
            return general
        }
    }
    var priority: Int? = null
    var tickInterval: Int? = null
    var searchRange: Number? = null
    var searchHeight: Number? = null
    var walkSpeedModifier: Float? = null
    var sprintSpeedModifier: Float? = null
    var avoidBlockSound: String? = null
    var targetSelectionMethod: String? = null

    var targetBlocks: ArrayList<String>? = null

    fun soundInterval(min: Float, max: Float = min) {
        unsafe.general["sound_interval"] = mutableMapOf("range_min" to min, "range_max" to max)
    }

    /**
     * 0..n
     */
    fun onEscape(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if (target != null)
            data["target"] = target.toString().lowercase()
        unsafe.events.add(data)
    }
}