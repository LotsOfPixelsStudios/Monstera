package com.lop.devtools.monstera.files.beh.blocks.components

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.molang.Molang

class PlacementFilter {
    @SerializedName("conditions")
    @Expose
    var conditionsData: MutableList<Condition>? = null
        @MonsteraBuildSetter set

    /**
     * add a condition for the placement of the block
     *
     * ```
     * condition {
     *     allowedFaces(Face.UP, Face.DOWN)
     *     blockFilter { }
     *     blockFilter("minecraft:dirt")
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun condition(data: Condition.() -> Unit) {
        conditionsData = (conditionsData ?: mutableListOf()).apply {
            add(Condition().apply(data))
        }
    }

    class Condition {
        @SerializedName("allowed_faces")
        @Expose
        var allowedFacesData: MutableList<Face>? = null
        
        fun allowedFaces(vararg face: Face) {
            allowedFacesData = (allowedFacesData ?: mutableListOf()).apply {
                addAll(face)
            }
        }

        @SerializedName("block_filter")
        @Expose
        var blockFilterData: MutableList<Any>? = null
            @MonsteraBuildSetter set
        
        /**
         * ```
         * blockFilter {
         *     tags(Query.anyTag("stone", "monstera"))
         * }
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun blockFilter(tag: TagFilter.() -> Unit) {
            blockFilterData = (blockFilterData ?: mutableListOf()).apply {
                add(TagFilter().apply(tag))
            }
        }
        
        @OptIn(MonsteraBuildSetter::class)
        fun blockFilter(blockIdentifier: String) {
            blockFilterData = (blockFilterData ?: mutableListOf()).apply {
                add(blockIdentifier)
            }
        }
    }
    
    class TagFilter {
        @SerializedName("tags")
        @Expose
        var tagsData: String? = null
            @MonsteraBuildSetter set
        
        fun tags(query: Molang) {
            tags(query.data)   
        }
        
        @OptIn(MonsteraBuildSetter::class)
        fun tags(query: String) {
            tagsData = query
        }
    }
}

enum class Face {
    @SerializedName("up")
    UP,
    @SerializedName("down")
    DOWN,
    @SerializedName("north")
    NORTH,
    @SerializedName("east")
    EAST,
    @SerializedName("south")
    SOUTH,
    @SerializedName("west")
    WEST,
    @SerializedName("side")
    SIDE
}