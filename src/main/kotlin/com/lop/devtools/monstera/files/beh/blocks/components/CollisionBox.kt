package com.lop.devtools.monstera.files.beh.blocks.components

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class CollisionBox: MonsteraRawFile() {
    @SerializedName("origin")
    @Expose
    var origin: MutableList<Number>? = null

    /**
     * ```
     * origin(-8, 0, -8)
     * ```
     */
    fun origin(x: Number, y: Number, z: Number) {
        origin = mutableListOf(x, y, z)
    }

    @SerializedName("size")
    @Expose
    var size: MutableList<Number>? = null

    /**
     * ```
     * size(16, 16, 16)
     * ```
     */
    fun size(x: Number, y: Number, z: Number) {
        size = mutableListOf(x, y, z)
    }
}