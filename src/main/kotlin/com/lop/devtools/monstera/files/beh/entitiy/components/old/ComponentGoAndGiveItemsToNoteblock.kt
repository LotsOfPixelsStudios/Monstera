package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentGoAndGiveItemsToNoteblock : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        val onItemThrownEvents = mutableListOf<Map<String, String>>()

        override fun getData(): MutableMap<String, Any> {
            general["on_item_throw"] = onItemThrownEvents
            return general
        }
    }

    var priority: Int = 0
        set(value) {
            unsafe.general["priority"] = value
            field = value
        }

    var runSpeed: Number = 0
        set(value) {
            unsafe.general["run_speed"] = value
            field = value
        }

    var throwSound: String = ""
        set(value) {
            unsafe.general["throw_sound"] = value
            field = value
        }

    fun onItemThrown(event: String, target: Subject) {
        unsafe.onItemThrownEvents.add(mapOf("event" to event, "target" to target.toString().lowercase()))
    }
}