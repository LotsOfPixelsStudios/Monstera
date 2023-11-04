package com.lop.devtools.monstera.files.res.entities.comp

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ResEntityScripts : MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        /**
         * access to all defined animations
         */
        val general = mutableMapOf<String, Any>()
        val animControllers = mutableListOf<Any>()

        override fun getData(): MutableMap<String, Any> {
            if(unsafe.animControllers.isNotEmpty())
                unsafe.general["animate"] = unsafe.animControllers
            return unsafe.general
        }
    }

    /**
     * 0..1
     */
    fun preAnim(args: ArrayList<String>) {
        unsafe.general.apply {
            put("pre_animation", args)
        }
    }

    /**
     * 0..*
     */
    fun script(scriptName: String, args: String) {
        unsafe.general.apply {
            put(scriptName, args)
        }
    }

    /**
     * 0..n
     *
     * animate(arrayListOf<Any>("humanoid_base_pose", mutableMapOf("humanoid_big_head" to "query.is_baby"),"move")
     */
    fun animate(args: ArrayList<Any>) {
        unsafe.animControllers.addAll(args)
    }
}