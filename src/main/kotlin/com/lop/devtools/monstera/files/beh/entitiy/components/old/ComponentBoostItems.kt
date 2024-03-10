package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentBoostItems: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        val boostItems = mutableListOf<Any>()

        override fun getData(): MutableMap<String, Any> {
            speedMultiplier?.let { general["speed_multiplier"] = it }
            duration?.let { general["duration"] = it }
            if(boostItems.isNotEmpty())
                general["boost_items"] = boostItems
            return general
        }
    }
    var speedMultiplier: Float? = null


    var duration: Float? = null

    fun boostItems(value: BoostItem.() -> Unit) {
        unsafe.boostItems.add(BoostItem().apply(value).getData())
    }
}

class BoostItem {
    val general = arrayListOf<Any>()

    fun item(item: String? = null, damage: Int? = null, replaceItem: String? = null) {
        val data = mutableMapOf<String, Any>()
        if(item != null)
            data["item"] = item
        if(damage != null)
            data["damage"] = damage
        if(replaceItem != null)
            data["replace_item"] = replaceItem
        general.add(data)
    }

    fun item(data: BoostItemEntry.() -> Unit) {
        general.add(BoostItemEntry().apply(data).unsafe.getData())
    }

    fun getData(): ArrayList<Any> {
        return general
    }
}

class BoostItemEntry: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            item?.let { general["item"] = it }
            damage?.let { general["damage"] = it }
            replaceItem?.let { general["replace_item"] = it }
            return general
        }
    }

    var item: String? = null
    var damage: Int? = null
    var replaceItem: String? = null
}