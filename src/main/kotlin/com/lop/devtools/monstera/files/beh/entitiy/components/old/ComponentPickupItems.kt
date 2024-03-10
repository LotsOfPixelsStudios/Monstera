package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentPickupItems {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var maxDist: Float? = null
    var goalRadius: Float? = null
    var speedMultiplier: Float? = null
    var pickupBasedOnChance: Boolean? = null
    var canPickupAnyItem: Boolean? = null
    var cooldownAfterBeingAttacked: Float? = null
    var trackTarget: Boolean? = null
    var canPickUpHandOrEquipment: Boolean? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        maxDist?.let { general["max_dist"] = it }
        goalRadius?.let { general["goal_radius"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        pickupBasedOnChance?.let { general["pickup_based_on_chance"] = it }
        canPickupAnyItem?.let { general["can_pickup_any_item"] = it }
        cooldownAfterBeingAttacked?.let { general["cooldown_after_being_attacked"] = it }
        trackTarget?.let { general["track_target"] = it }
        canPickUpHandOrEquipment?.let { general["can_pickup_to_hand_or_equipment"] = it }
        return general
    }
}