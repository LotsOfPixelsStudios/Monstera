package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class ComponentEnvSensorTriggers {
    val general = arrayListOf<Any>()

    /**
     * 1..*
     *
     * @param filters: Filter
     */
    fun trigger(event: String? = null, filters: BehEntityFilter.() -> Unit) {
        val behEntityFilter = BehEntityFilter().apply { filters(this) }
        val thisData = mutableMapOf<String,Any>(
            "filters" to behEntityFilter.getData()
        )
        if(event != null) {
            thisData.apply {
                put("event", event)
            }
        }
        general.add(thisData)
    }

    fun getData(): ArrayList<Any> {
        return general
    }
}