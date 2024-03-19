package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentInsideBlockNotifier {
    val general = mutableMapOf<String, Any>()
    val blocks = arrayListOf<Any>()

    fun block(value: BlockNotifierBlock.() -> Unit) {
        blocks.add(BlockNotifierBlock().apply(value).getData())
    }

    fun getData(): MutableMap<String, Any> {
        general["block_list"] = blocks
        return general
    }
}

class BlockNotifierBlock {
    val general = mutableMapOf<String, Any>()
    val blockInfo = mutableMapOf<String, Any>()

    var blockName: String? = null

    fun blockState(dragDown: Boolean? = null) {
        val data = mutableMapOf<String, Any>()
        if(dragDown != null)
            data["drag_down"] = dragDown
        blockInfo["states"] = data
    }

    fun enteredBlockEvent(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if(target != null)
            data["target"] = target.toString().lowercase()
        general["entered_block_event"] = data
    }

    fun exitedBlockEvent(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if(target != null)
            data["target"] = target.toString().lowercase()
        general["exited_block_event"] = data
    }

    fun getData(): MutableMap<String, Any> {
        blockName?.let { blockInfo["name"] = it }
        general["block"] = blockInfo
        return general
    }
}