package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentRandomSearchAndDig : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        val items = mutableListOf<Pair<String, Int>>()


        override fun getData(): MutableMap<String, Any> {
            if (items.isNotEmpty()) {
                general["items"] = items.map { mapOf("item" to it.first, "weight" to it.second) }
            }
            return general
        }
    }

    var priority: Int = 0
        set(value) {
            unsafe.general["priority"] = value
            field = value
        }

    var speedMultiplier: Number = 0
        set(value) {
            unsafe.general["speed_multiplier"] = value
            field = value
        }

    var findValidPositionRetries: Number = 0
        set(value) {
            unsafe.general["find_valid_position_retries"] = value
            field = value
        }

    var goalRadius: Number = 0
        set(value) {
            unsafe.general["goal_radius"] = value
            field = value
        }

    var searchRangeXz: Number = 0
        set(value) {
            unsafe.general["search_range_xz"] = value
            field = value
        }

    var searchRangeY: Number = 0
        set(value) {
            unsafe.general["search_range_y"] = value
            field = value
        }

    var cooldownRange: Number = 0
        set(value) {
            unsafe.general["cooldown_range"] = value
            field = value
        }

    fun diggingDurationRange(start: Number, end: Number) {
        unsafe.general["digging_duration_range"] = listOf(start, end)
    }

    /**
     * @param item first is the item identifier, second is the weight (default 1)
     */
    fun items(vararg item: Pair<String, Int>) {
        unsafe.items.addAll(item)
    }

    fun addItem(item: String, weight: Int = 1) {
        unsafe.items.add(item to weight)
    }

    var spawnItemAfterSeconds: Number = 0
        set(value) {
            unsafe.general["spawn_item_after_seconds"] = value
            field = value
        }

    var spawnItemPosOffset: Number = 0
        set(value) {
            unsafe.general["spawn_item_pos_offset"] = value
            field = value
        }

    fun onSearchingStart(event: String, target: Subject = Subject.SELF) {
        unsafe.general["on_searching_start"] = mapOf(
            "event" to event,
            "target" to target.toString().lowercase()
        )
    }

    fun onFailDuringSearching(event: String, target: Subject = Subject.SELF) {
        unsafe.general["on_fail_during_searching"] = mapOf(
            "event" to event,
            "target" to target.toString().lowercase()
        )
    }

    fun onDiggingStart(event: String, target: Subject = Subject.SELF) {
        unsafe.general["on_digging_start"] = mapOf(
            "event" to event,
            "target" to target.toString().lowercase()
        )
    }

    fun onItemFound(event: String, target: Subject = Subject.SELF) {
        unsafe.general["on_item_found"] = mapOf(
            "event" to event,
            "target" to target.toString().lowercase()
        )
    }

    fun onFailDuringDigging(event: String, target: Subject = Subject.SELF) {
        unsafe.general["on_fail_during_digging"] = mapOf(
            "event" to event,
            "target" to target.toString().lowercase()
        )
    }

    fun onSuccess(event: String, target: Subject = Subject.SELF) {
        unsafe.general["on_success"] = mapOf(
            "event" to event,
            "target" to target.toString().lowercase()
        )
    }
}