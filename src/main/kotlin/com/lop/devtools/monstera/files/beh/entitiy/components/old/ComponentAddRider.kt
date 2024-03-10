package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentAddRider: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            entityType?.let { general["entity_type"] = it }
            spawnEvent?.let { general["spawn_event"] = it }
            return general
        }
    }

    var entityType: String? = null
    var spawnEvent: String? = null
}