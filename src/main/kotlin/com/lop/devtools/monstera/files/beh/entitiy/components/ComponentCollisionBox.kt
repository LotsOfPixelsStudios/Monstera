package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentCollisionBox : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            height?.let { general["height"] = it }
            width?.let { general["width"] = it }
            return general
        }
    }

    var height: Number? = null
    var width: Number? = null
}