package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentBeg: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        override fun getData(): MutableMap<String, Any> {
            priority?.let { general["priority"] = it }
            lookDistance?.let { general["look_distance"] = it }
            items?.let { general["items"] = it }
            return general
        }
    }

    var items: ArrayList<String>? = null
    var priority: Int? = null

    var lookDistance: Float? = null

    /**
     * The range of time in seconds this mob will stare at the player holding a food they like, begging for it
     */
    fun lookTime(min: Float, max: Float) {
        unsafe.general["look_time"] = arrayListOf(min, max)
    }
}
