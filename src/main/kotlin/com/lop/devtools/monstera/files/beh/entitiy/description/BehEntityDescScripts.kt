package com.lop.devtools.monstera.files.beh.entitiy.description

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.addon.molang.Query

class BehEntityDescScripts: MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        /**
         * access to all defined data
         */
        val general = mutableMapOf<String, Any>()
        val animations = arrayListOf<Any>()

        override fun getData(): MutableMap<String, Any> {
            if(unsafe.animations.isNotEmpty()) unsafe.general["animate"] = unsafe.animations
            return unsafe.general
        }
    }

    /**
     * 0..1
     *
     * @param anim: List of Strings that activate Animations defined in animations
     * @ sample animate(listOf("...","..."))
     */
    fun animate(anim: ArrayList<String>) {
        unsafe.animations.addAll(anim)
    }

    fun animate(animation: String, query: Molang = Query.True) {
        if(query == Query.True)
            unsafe.animations.add(animation)
        else
            unsafe.animations.add(mutableMapOf(animation to query))
    }
}