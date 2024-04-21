package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.entitiy.components.BehEntityTypes
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class BehDropItemFor : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("seconds_before_pickup")
    @Expose
    var secondsBeforePickup: Number? = null
        
    @SerializedName("cooldown")
    @Expose
    var cooldown: Number? = null
        
    @SerializedName("drop_item_chance")
    @Expose
    var dropItemChance: Number? = null
        
    @SerializedName("offering_distance")
    @Expose
    var offeringDistance: Number? = null

    @SerializedName("minimum_teleport_distance")
    @Expose
    var minimumTeleportDistance: Number? = null
        
    @SerializedName("max_head_look_at_height")
    @Expose
    var maxHeadLookAtHeight: Number? = null
        
    @SerializedName("target_range")
    @Expose
    var targetRangeData: MutableList<Number>? = null

    fun targetRange(vararg value: Number) {
        targetRangeData = (targetRangeData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("teleport_offset")
    @Expose
    var teleportOffsetData: MutableList<Number>? = null

    fun teleportOffset(vararg value: Number) {
        teleportOffsetData = (teleportOffsetData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("time_of_day_range")
    @Expose
    var timeOfDayRangeData: MutableList<Number>? = null

    fun timeOfDayRange(vararg value: Number) {
        timeOfDayRangeData = (timeOfDayRangeData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null
        
    @SerializedName("search_range")
    @Expose
    var searchRange: Number? = null
        
    @SerializedName("search_height")
    @Expose
    var searchHeight: Number? = null

    @SerializedName("search_count")
    @Expose
    var searchCount: Number? = null
        
    @SerializedName("goal_radius")
    @Expose
    var goalRadius: Number? = null
        
    @SerializedName("entity_types")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var entityTypesData: MutableList<BehEntityTypes>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * entityTypes {
     *     maxDist = 6
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun entityTypes(value: BehEntityTypes.() -> Unit) {
        entityTypesData = (entityTypesData ?: mutableListOf()).also { it.add(BehEntityTypes().apply(value)) }
    }

    @SerializedName("loot_table")
    @Expose
    var lootTable: String? = null
        

    @SerializedName("on_drop_attempt")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var onDropAttemptData: OnDropAttempt? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onDropAttempt {
     *     event = minecraft:cat_gifted_owner
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onDropAttempt(value: OnDropAttempt.() -> Unit) {
        onDropAttemptData = (onDropAttemptData ?: OnDropAttempt()).apply(value)
    }

    class OnDropAttempt : MonsteraRawFile() {

        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }
}
