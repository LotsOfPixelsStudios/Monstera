package com.lop.devtools.monstera.files.beh.entitiy.description

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class BehEntityDescAnimations: MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        /**
         * access to all defined data
         */
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            return unsafe.general
        }
    }

    /**
     * define an animation that is part of the entity
     * @param animName: the name that is referred to within this file like [BehEntityDescScripts]
     * @param animIdentifier: the full name of the animation defined in the animation file
     */
    fun addAnim(animName: String, animIdentifier: String) {
        unsafe.general.apply {
            put(animName,animIdentifier)
        }
    }
}