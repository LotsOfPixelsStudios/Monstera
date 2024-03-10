package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentNameable {
    val general = mutableMapOf<String, Any>()

    var allowNameTagRenaming: Boolean? = null
    var alwaysShow: Boolean? = null

    fun defaultTrigger(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if(target != null)
            data["target"] = target.toString().lowercase()
        general["default_trigger"] = data
    }

    fun nameActions(value: ComponentNameActions.() -> Unit) {
        general["name_actions"] = ComponentNameActions().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        allowNameTagRenaming?.let { general["allow_name_tag_renaming"] = it }
        alwaysShow?.let { general["always_show"] = it }
        return general
    }
}

class ComponentNameActions {
    val general = arrayListOf<Any>()

    /**
     * 0..1
     *
     * @param nameFilter List of special names that will cause the events defined in 'on_named' to fire (make to arraylist if not working)
     * @param onNamed Event to be called when this entity acquires the name specified in 'name_filter'
     */
    fun nameAction(nameFilter: String? = null, onNamed: String? = null, onNamedTarget: Subject? = null) {
        val data = mutableMapOf<String, Any>()
        val eventData = mutableMapOf<String, Any>()
        if(nameFilter != null)
            data["name_filter"] = nameFilter
        if(onNamed != null)
            eventData["event"] = onNamed
        if(onNamedTarget != null)
            eventData["target"] = onNamedTarget.toString().lowercase()
        if(eventData.isNotEmpty())
            data["on_named"] = eventData
        general.add(data)
    }

    fun getData(): ArrayList<Any> {
        return general
    }
}