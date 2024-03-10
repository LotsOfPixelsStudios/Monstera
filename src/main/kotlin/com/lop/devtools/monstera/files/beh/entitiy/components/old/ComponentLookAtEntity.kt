package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class ComponentLookAtEntity {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var lookDistance: Float? = null
    var angleOfViewHorizontal: Number? = null
    var angleOfViewVertical: Number? = null
    var probability: Float? = null

    /**
     * 0..1
     *
     * Time range to look at the entity
     */
    fun lookTime(lower: Float, upper: Float) {
        general["look_time"] = arrayListOf(lower, upper)
    }

    fun filters(value: BehEntityFilter.() -> Unit) {
        general["filters"] = BehEntityFilter().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        lookDistance?.let { general["look_distance"] = it }
        angleOfViewHorizontal?.let { general["angle_of_view_horizontal"] = it }
        angleOfViewVertical?.let { general["angle_of_view_vertical"] = it }
        probability?.let { general["probability"] = it }
        return general
    }
}