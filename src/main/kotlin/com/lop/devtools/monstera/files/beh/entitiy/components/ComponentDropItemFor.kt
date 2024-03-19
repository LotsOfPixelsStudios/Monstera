package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.beh.entitiy.data.Subject


class ComponentDropItemFor {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var secondsBeforePickup: Float? = null
    var cooldown: Float? = null
    var dropItemChance: Float? = null
    var offeringDistance: Float? = null
    var minimumTeleportDistance: Float? = null
    var maxHeadLookAtHeight: Float? = null
    var speedMultiplier: Float? = null
    var searchRange: Float? = null
    var searchHeight: Float? = null
    var searchCount: Int? = null
    var goalRadius: Float? = null
    var lootTable: String? = null   //todo accept behLootTable() as arg

    fun targetRange(x: Float, y: Float, z: Float) {
        general["target_range"] = arrayListOf(x, y, z)
    }

    fun teleportOffset(x: Float, y: Float, z: Float) {
        general["teleport_offset"] = arrayListOf(x, y, z)
    }

    fun timeOfDayRange(lower: Float, higher: Float) {
        general["time_of_day_range"] = arrayListOf(lower, higher)
    }

    fun entityTypes(value: BehEntityTypes.() -> Unit) {
        general["entity_types"] = BehEntityTypes().apply(value).getData()
    }

    /**
     * @param event the event to trigger
     * @param target the target where the event should be executed (self/other)
     */
    fun onDropAttempt(event: String, target: String? = null) {
        val data = mutableMapOf(
            "event" to event
        )
        if(target != null) {
            data["target"] = target
        }
        general["on_drop_attempt"] = data
    }

    /**
     * @param event the event to trigger
     * @param target the target where the event should be executed (self/other)
     */
    fun onDropAttempt(event: String, target: Subject? = null) {
        val data = mutableMapOf(
            "event" to event
        )
        if(target != null) {
            data["target"] = target.toString().lowercase()
        }
        general["on_drop_attempt"] = data
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        secondsBeforePickup?.let { general["seconds_before_pickup"] = it }
        cooldown?.let { general["cooldown"] = it }
        dropItemChance?.let { general["drop_item_chance"] = it }
        offeringDistance?.let { general["offering_distance"] = it }
        minimumTeleportDistance?.let { general["minimum_teleport_distance"] = it }
        maxHeadLookAtHeight?.let { general["max_head_look_at_height"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        searchRange?.let { general["search_range"] = it }
        searchHeight?.let { general["search_height"] = it }
        searchCount?.let { general["search_count"] = it }
        goalRadius?.let { general["goal_radius"] = it }
        lootTable?.let { general["loot_table"] = it }
        return general
    }
}