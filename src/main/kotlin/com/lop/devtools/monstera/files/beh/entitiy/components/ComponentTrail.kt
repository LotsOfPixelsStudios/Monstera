package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class ComponentTrail {
    val general = mutableMapOf<String, Any>()

    var blockType: String? = null

    /**
     * 0..1
     *
     * The distance from the entities current position to spawn the block. Capped at up to 16 blocks away. The X value is left/right(-/+), the Z value is backward/forward(-/+), the Y value is below/above(-/+).
     */
    fun spawnOffset(x: Float, y: Float, z: Float) {
        general["spawn_offset"] = arrayListOf(x, y, z)
    }

    /**
     * 0..1
     */
    fun spawnFilters(value: BehEntityFilter.() -> Unit) {
        general["spawn_filter"] = BehEntityFilter().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        blockType?.let { general["block_type"] = it }
        return general
    }
}