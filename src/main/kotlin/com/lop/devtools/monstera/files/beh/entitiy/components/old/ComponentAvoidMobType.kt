package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentAvoidMobType: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        val entityTypes = mutableListOf<Any>()

        override fun getData(): MutableMap<String, Any> {
            priority?.let { general["priority"] = it }
            maxDist?.let { general["max_dist"] = it }
            ignoreVisibility?.let { general["ignore_visibility"] = it }
            if(entityTypes.isNotEmpty())
                general["entity_types"] = entityTypes
            return general
        }
    }
    var priority: Int? = null
    var maxDist: Float? = null

    var ignoreVisibility: Boolean? = null

    fun entityTypes(value: BehEntityTypes.() -> Unit) {
        unsafe.entityTypes.add(BehEntityTypes().apply(value).getData())
    }
}