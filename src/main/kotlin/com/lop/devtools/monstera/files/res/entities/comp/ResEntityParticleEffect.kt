package com.lop.devtools.monstera.files.res.entities.comp

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ResEntityParticleEffect : MonsteraFile {
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
     * 0..*
     */
    fun particleEffect(name: String, identifier: String) {
        unsafe.general.apply {
            put(name, identifier)
        }
    }
}