package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class InsideBlockNotifier {
    @SerializedName("block_list")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var blockListData: MutableList<BlockList>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * blockList {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun blockList(value: BlockList.() -> Unit) {
        blockListData = (blockListData ?: mutableListOf()).also { it.add(BlockList().apply(value)) }
    }

    class BlockList : MonsteraRawFile() {
        @SerializedName("block")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var blockData: Block? = null
            @MonsteraBuildSetter set

        /**
         *
         * ```
         * block {
         *     name = minecraft:bubble_column
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun block(value: Block.() -> Unit) {
            blockData = (blockData ?: Block()).apply(value)
        }

        @SerializedName("entered_block_event")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var enteredBlockEventData: EnteredBlockEvent? = null
            @MonsteraBuildSetter set

        /**
         *
         * ```
         * enteredBlockEvent {
         *     event = minecraft:entered_bubble_column_down
         *     target = self
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun enteredBlockEvent(value: EnteredBlockEvent.() -> Unit) {
            enteredBlockEventData = (enteredBlockEventData ?: EnteredBlockEvent()).apply(value)
        }

        @SerializedName("exited_block_event")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var exitedBlockEventData: ExitedBlockEvent? = null
            @MonsteraBuildSetter set

        /**
         *
         * ```
         * exitedBlockEvent {
         *     event = minecraft:exited_bubble_column
         *     target = self
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun exitedBlockEvent(value: ExitedBlockEvent.() -> Unit) {
            exitedBlockEventData = (exitedBlockEventData ?: ExitedBlockEvent()).apply(value)
        }
    }

    class Block : MonsteraRawFile() {
        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("states")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var statesData: States? = null
            @MonsteraBuildSetter set

        /**
         *
         * ```
         * states {
         *     dragDown = true
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun states(value: States.() -> Unit) {
            statesData = (statesData ?: States()).apply(value)
        }
    }

    class States : MonsteraRawFile() {
        @SerializedName("drag_down")
        @Expose
        var dragDown: Boolean? = null
            
    }

    class EnteredBlockEvent : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null
            
        @SerializedName("target")
        @Expose
        var target: Subject? = null
    }

    class ExitedBlockEvent : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null
            
        @SerializedName("target")
        @Expose
        var target: Subject? = null
    }
}
