package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentEatBlock {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var successChance: Any? = null
    var timeUntilEat: Float? = null

    /**
     * 0..1
     *
     * Add a pair of blocks that should be replaced
     */
    fun eatAndReplaceBlockPairs(value: EatReplaceBlockPairs.() -> Unit) {
        general["eat_and_replace_block_pairs"] = EatReplaceBlockPairs().apply(value).getData()
    }

    /**
     * 0..1
     *
     * @param event the event to trigger
     * @param target where to trigger the event (self, other)
     */
    fun onEat(event: String, target: String?) {
        val data = mutableMapOf(
            "event" to event
        )
        if (target != null) {
            data["target"] = target
        }
        general["on_eat"] = data
    }

    /**
     * 0..1
     *
     * @param event the event to trigger
     * @param target where to trigger the event (self, other)
     */
    fun onEat(event: String, target: Subject) {
        general["on_eat"] = mutableMapOf(
            "event" to event,
            "target" to target.toString().lowercase()
        )
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        successChance?.let { general["success_chance"] = it }
        timeUntilEat?.let { general["time_until_eat"] = it }
        return general
    }
}

class EatReplaceBlockPairs {
    val general = arrayListOf<MutableMap<String, String>>()

    /**
     * 1..n
     */
    fun entry(eatBlock: String, replaceBlock: String) {
        general.add(
            mutableMapOf(
                "eat_block" to eatBlock,
                "replace_block" to replaceBlock
            )
        )
    }

    fun getData(): ArrayList<MutableMap<String, String>> {
        return general
    }
}