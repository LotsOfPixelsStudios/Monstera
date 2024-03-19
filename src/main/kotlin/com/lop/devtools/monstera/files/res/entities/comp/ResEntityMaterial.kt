package com.lop.devtools.monstera.files.res.entities.comp

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ResEntityMaterial : MonsteraFile {
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
     * @param material: like "pig" or "parrot", note that not every material supports transparent textures
     */
    fun material(identifier: String = "default", material: String) {
        unsafe.general.apply {
            put(identifier, material)
        }
    }
}