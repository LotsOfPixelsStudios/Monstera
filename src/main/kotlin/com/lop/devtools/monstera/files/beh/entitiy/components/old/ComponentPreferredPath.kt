package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentPreferredPath {
    val general = mutableMapOf<String, Any>()

    var maxFallBlocks: Int? = null
    var jumpCost: Number? = null
    var defaultBlockCost: Float? = null

    /**
     * 0..1
     *
     *  A list of blocks with their associated cost
     */
    fun preferredPathBlocks(value: PrefPathBlocks.() -> Unit) {
        general["preferred_path_blocks"] = PrefPathBlocks().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        maxFallBlocks?.let { general["max_fall_blocks"] = it }
        jumpCost?.let { general["jump_cost"] = it }
        defaultBlockCost?.let { general["default_block_cost"] = it }
        return general
    }
}

class PrefPathBlocks {
    val general = arrayListOf<Any>()

    fun blocks(cost: Int, blocks: ArrayList<String>) {
        general.add(mutableMapOf("cost" to cost, "blocks" to blocks))
    }

    fun block(cost: Int, block: String) {
        general.add(mutableMapOf("cost" to cost, "blocks" to arrayListOf(block)))
    }

    fun getData(): ArrayList<Any> {
        return general
    }
}