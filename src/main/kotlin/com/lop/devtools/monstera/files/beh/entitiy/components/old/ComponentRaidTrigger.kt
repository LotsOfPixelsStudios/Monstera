package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentRaidTrigger {
    val general = mutableMapOf<String, Any>()

    var triggeredEvent: String? = null
    var target: Subject? = null

    fun getData(): MutableMap<String, Any> {
        val data = mutableMapOf<String, Any>()
        triggeredEvent?.let { data["event"] = it }
        target?.let { data["target"] = it.toString().lowercase() }
        if(triggeredEvent != null && target != null)
            general["triggered_event"] = data
        return general
    }
}