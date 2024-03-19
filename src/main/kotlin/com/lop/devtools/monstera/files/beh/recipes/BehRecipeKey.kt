package com.lop.devtools.monstera.files.beh.recipes

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class BehRecipeKey: MonsteraFile {
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
     * 0..*
     *
     * @param keyVal: the key used in pattern() to represent a item
     * @param item: the actual item the key was representing
     */
    fun key(keyVal: String, item: String) {
        unsafe.general.apply {
            put(keyVal, mutableMapOf("item" to item))
        }
    }
}