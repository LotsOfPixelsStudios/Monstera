package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentAmbientSoundInterval : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            eventName?.let { general["event_name"] = it }
            range?.let { general["range"] = it }
            value?.let { general["value"] = it }
            return general
        }
    }

    var eventName: String? = null
    var range: Float? = null
    var value: Float? = null
}