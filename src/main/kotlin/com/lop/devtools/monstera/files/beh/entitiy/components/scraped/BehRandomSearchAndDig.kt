package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject
import com.lop.devtools.monstera.files.beh.tables.loot.BehLootTables
import com.lop.devtools.monstera.getMonsteraLogger

class BehRandomSearchAndDig : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null

    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null

    @SerializedName("find_valid_position_retries")
    @Expose
    var findValidPositionRetries: Number? = null

    @SerializedName("target_blocks")
    @Expose
    var targetBlocksData: MutableList<String>? = null

    fun targetBlocks(vararg value: String) {
        targetBlocksData = (targetBlocksData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("goal_radius")
    @Expose
    var goalRadius: Number? = null

    @SerializedName("search_range_xz")
    @Expose
    var searchRangeXz: Number? = null

    @SerializedName("search_range_y")
    @Expose
    var searchRangeY: Number? = null

    @SerializedName("cooldown_range")
    @Expose
    var cooldownRange: Number? = null

    @SerializedName("digging_duration_range")
    @Expose
    var diggingDurationRangeData: MutableList<Number>? = null

    fun diggingDurationRange(vararg value: Number) {
        diggingDurationRangeData = (diggingDurationRangeData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("item_table")
    @Expose
    var itemTable: String? = null

    /**
     * ```
     * table("name") {
     *     pool {  }
     * }
     * ```
     */
    fun itemTable(tableName: String, data: BehLootTables.() -> Unit) {
        val lootTables = BehLootTables().apply(data)
        lootTables.debug(tableName)
        val target = BehLootTables.Entity(lootTables).build(tableName)
        target.fold({
            itemTable = BehLootTables.resolveRelative(it)
        }, {
            getMonsteraLogger(this::class.simpleName ?: "Entity search and dig").warn("Item table not added!")
        })
    }

    @SerializedName("spawn_item_after_seconds")
    @Expose
    var spawnItemAfterSeconds: Number? = null

    @SerializedName("spawn_item_pos_offset")
    @Expose
    var spawnItemPosOffset: Number? = null

    @SerializedName("on_searching_start")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var onSearchingStartData: OnSearchingStart? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onSearchingStart {
     *     event = on_searching_start
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onSearchingStart(value: OnSearchingStart.() -> Unit) {
        onSearchingStartData = (onSearchingStartData ?: OnSearchingStart()).apply(value)
    }

    @SerializedName("on_fail_during_searching")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var onFailDuringSearchingData: OnFailDuringSearching? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onFailDuringSearching {
     *     event = on_fail_during_searching
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onFailDuringSearching(value: OnFailDuringSearching.() -> Unit) {
        onFailDuringSearchingData = (onFailDuringSearchingData ?: OnFailDuringSearching()).apply(value)
    }

    @SerializedName("on_digging_start")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var onDiggingStartData: OnDiggingStart? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onDiggingStart {
     *     event = on_digging_start
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onDiggingStart(value: OnDiggingStart.() -> Unit) {
        onDiggingStartData = (onDiggingStartData ?: OnDiggingStart()).apply(value)
    }

    @SerializedName("on_item_found")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var onItemFoundData: OnItemFound? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onItemFound {
     *     event = on_item_found
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onItemFound(value: OnItemFound.() -> Unit) {
        onItemFoundData = (onItemFoundData ?: OnItemFound()).apply(value)
    }

    @SerializedName("on_fail_during_digging")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var onFailDuringDiggingData: OnFailDuringDigging? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onFailDuringDigging {
     *     event = on_fail_during_digging
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onFailDuringDigging(value: OnFailDuringDigging.() -> Unit) {
        onFailDuringDiggingData = (onFailDuringDiggingData ?: OnFailDuringDigging()).apply(value)
    }

    @SerializedName("on_success")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var onSuccessData: OnSuccess? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onSuccess {
     *     event = on_search_and_digging_success
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onSuccess(value: OnSuccess.() -> Unit) {
        onSuccessData = (onSuccessData ?: OnSuccess()).apply(value)
    }

    class OnSearchingStart : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null

        @SerializedName("target")
        @Expose
        var target: Subject? = null
    }

    class OnFailDuringSearching : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null

        @SerializedName("target")
        @Expose
        var target: String? = null
    }

    class OnDiggingStart : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null

        @SerializedName("target")
        @Expose
        var target: Subject? = null
    }

    class OnItemFound : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null

        @SerializedName("target")
        @Expose
        var target: Subject? = null
    }

    class OnFailDuringDigging : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null

        @SerializedName("target")
        @Expose
        var target: Subject? = null
    }

    class OnSuccess : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null

        @SerializedName("target")
        @Expose
        var target: Subject? = null
    }
}
