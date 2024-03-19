package com.lop.devtools.monstera.files.res.entities.comp

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ResEntityAnimations : MonsteraFile {
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
     * @param identifier: callable from animation_controllers
     * @param anim: identifier coming from the animation file ("animation.pig.setup")
     */
    fun animation(identifier: String, anim: String) {
        var animMod = anim
        if (!anim.contains("animation.")) {
            animMod = "animation.$anim"
        }

        unsafe.general.apply {
            put(identifier, animMod)
        }
    }
}