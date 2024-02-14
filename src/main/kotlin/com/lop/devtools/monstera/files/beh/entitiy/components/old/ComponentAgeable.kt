package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentAgeable: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        val feedItemsComp = CompFeedItemsGrow()

        override fun getData(): MutableMap<String, Any> {
            if(feedItemsComp.getData().isNotEmpty())
                general["feed_items"] = feedItemsComp.getData()
            duration?.let { general["duration"] = it }
            feedItems?.let { general["feed_items"] = it }
            transformToItem?.let { general["transform_from_to_item"] = it }
            dropItems?.let { general["drop_items"] = it }
            return general
        }
    }



    var duration: Number? = null
    var feedItems: ArrayList<String>? = null
    var transformToItem: String? = null
    var dropItems: ArrayList<String>? = null

    fun feedItemsGrow(data: CompFeedItemsGrow.() -> Unit) {
        unsafe.feedItemsComp.apply(data)
    }

    /**
     * 0..1
     *
     * Event to run when this entity grows up.
     */
    fun growUp(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if(target != null)
            data["target"] = target.toString().lowercase()
        unsafe.general["grow_up"] = data
    }
}

class CompFeedItemsGrow {
    val general = arrayListOf<Any>()

    fun item(itemName: String, growth: Float? = null) {
        val data = mutableMapOf<String, Any>(
            "item" to itemName
        )
        if(growth != null)
            data["growth"] = growth
        general.add(data)
    }

    fun getData(): ArrayList<Any> {
        return general
    }
}