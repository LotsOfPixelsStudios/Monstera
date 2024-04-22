package com.lop.devtools.monstera.files.beh.item.comp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile

class ItemDigger : MonsteraRawFile() {
    @SerializedName("use_efficiency")
    @Expose
    var useEfficiency: Boolean? = null

    @SerializedName("destroy_speeds")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var destroySpeedsData: MutableList<DestroySpeed>? = null
        @MonsteraBuildSetter set

    /**
     * adds a destroy speed for a block or block group
     *
     * can be called multiple times
     */
    @OptIn(MonsteraBuildSetter::class)
    fun destroySpeed(data: DestroySpeed.() -> Unit) {
        destroySpeedsData = (destroySpeedsData ?: mutableListOf()).apply { add(DestroySpeed().apply(data)) }
    }


    class DestroySpeed : MonsteraRawFile() {
        @SerializedName("speed")
        @Expose
        var speed: Number? = null

        @SerializedName("block")
        @Expose
        var blockData: Any? = null
            @MonsteraBuildSetter set

        @OptIn(MonsteraBuildSetter::class)
        fun block(id: String) {
            blockData = id
        }

        /**
         * select blocks with tags
         *
         * ```
         * block {
         *     tags = Query.anyTag("stone", "metal")
         * }
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun block(data: Block.() -> Unit) {
            blockData = Block().apply(data)
        }
    }

    class Block {
        @SerializedName("tags")
        @Expose
        var tags: String? = null
    }
}