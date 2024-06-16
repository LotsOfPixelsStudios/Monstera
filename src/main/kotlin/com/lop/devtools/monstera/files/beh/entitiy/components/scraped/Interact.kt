package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject
import com.lop.devtools.monstera.files.beh.tables.loot.BehLootTables
import com.lop.devtools.monstera.getMonsteraLogger

class Interact : MonsteraRawFile() {

    @SerializedName("interactions")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var interactionsData: MutableList<Interaction>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * interaction {
     *     giveItem = true
     *     takeItem = true
     *     interactText = action.interact.allay
     *     useItem = true
     *     swing = true
     *     playSound = "ignite"
     *     equipItemSlot = 1
     *     spawnEntities = ""
     *     spawnItems("table_name") { pool { } }
     *     onInteract { }
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun interaction(value: Interaction.() -> Unit) {
        interactionsData = (interactionsData ?: mutableListOf()).also { it.add(Interaction().apply(value)) }
    }

    class Interaction : MonsteraRawFile() {
        @SerializedName("on_interact")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var onInteractData: OnInteract? = null
            @MonsteraBuildSetter set

        /**
         *
         * ```
         * onInteract {
         *     event = ""
         *     target = Subject.SELF
         *     filters {  }
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun onInteract(value: OnInteract.() -> Unit) {
            onInteractData = (onInteractData ?: OnInteract()).apply(value)
        }

        @SerializedName("give_item")
        @Expose
        var giveItem: Boolean? = null


        @SerializedName("take_item")
        @Expose
        var takeItem: Boolean? = null


        /**
         * eg action.interact.saddle
         */
        @SerializedName("interact_text")
        @Expose
        var interactText: String? = null

        @SerializedName("use_item")
        @Expose
        var useItem: Boolean? = null

        @SerializedName("transform_to_item")
        @Expose
        var transformToItem: String? = null

        @SerializedName("swing")
        @Expose
        var swing: Boolean? = null

        /**
         * eg "ignite"
         */
        @SerializedName("play_sounds")
        @Expose
        var playSound: String? = null

        @SerializedName("hurt_item")
        @Expose
        var hurtItem: Int? = null

        @SerializedName("equip_item_slot")
        @Expose
        var equipItemSlot: Int? = null

        @SerializedName("add_items")
        @Expose
        var addItems: String? = null

        @SerializedName("cooldown")
        @Expose
        var cooldown: Number? = null

        @SerializedName("cooldown_after_being_attacked")
        @Expose
        var cooldownAfterBeingAttacked: Number? = null

        @SerializedName("spawn_entities")
        @Expose
        var spawnEntities: String? = null

        @SerializedName("spawn_items")
        @Expose
        var spawnItems: MutableMap<String, String>? = null
            @MonsteraBuildSetter set

        @OptIn(MonsteraBuildSetter::class)
        fun spawnItems(tableName: String, data: BehLootTables.() -> Unit) {
            val lootTables = BehLootTables().apply(data)
            val target = BehLootTables.Entity(lootTables).build(tableName)
            target.fold({
                spawnItems = mutableMapOf("table" to BehLootTables.resolveRelative(it))
            }, {
                getMonsteraLogger(this.javaClass.name).warn("spawn item table not added!")
            })
        }
    }

    class OnInteract : MonsteraRawFile() {

        @SerializedName("filters")
        @Expose
        var filtersData: BehEntityFilter? = null
            @MonsteraBuildSetter set

        /**
         * com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.Filters allow data objects to specify test criteria which allows their use.
         * ```
         * filters {
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun filters(value: BehEntityFilter.() -> Unit) {
            filtersData = (filtersData ?: BehEntityFilter()).apply(value)
        }

        @SerializedName("event")
        @Expose
        var event: String? = null

        @SerializedName("target")
        @Expose
        var target: Subject? = null
    }
}