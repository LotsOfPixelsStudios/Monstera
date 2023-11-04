package com.lop.devtools.monstera.files.res.entities.comp

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ResEntityGeometries : MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        /**
         * access to all defined animations
         */
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            return unsafe.general
        }
    }

    /**
     * 1..*
     *
     * @param identifier: callable from render controller
     * @param geo: geo identifier coming from models like "geometry.pig.v1.8"
     */
    fun geometry(identifier: String = "default", geo: String) {
        if (!geo.contains("geometry.")) {
            unsafe.general.apply {
                put(identifier, "geometry.$geo")
            }
        } else {
            unsafe.general.apply {
                put(identifier, geo)
            }
        }
    }
}