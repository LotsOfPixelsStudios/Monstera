package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehPickupItems : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null

    @SerializedName("max_dist")
    @Expose
    var maxDist: Number? = null

    @SerializedName("search_height")
    @Expose
    var searchHeight: Number? = null

    @SerializedName("goal_radius")
    @Expose
    var goalRadius: Number? = null

    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null

    @SerializedName("pickup_based_on_chance")
    @Expose
    var pickupBasedOnChance: Boolean? = null

    @SerializedName("can_pickup_any_item")
    @Expose
    var canPickupAnyItem: Boolean? = null

    @SerializedName("can_pickup_to_hand_or_equipment")
    @Expose
    var canPickupToHandOrEquipment: Boolean? = null

    @SerializedName("pickup_same_items_as_in_hand")
    @Expose
    var pickupSameItemsAsInHand: Boolean? = null

    @SerializedName("excluded_items")
    @Expose
    var excludedItemsData: MutableList<String>? = null

    fun excludedItems(vararg value: String) {
        excludedItemsData = (excludedItemsData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("cooldown_after_being_attacked")
    @Expose
    var cooldownAfterBeingAttacked: Number? = null
}