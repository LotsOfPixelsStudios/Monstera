package com.lop.devtools.monstera.files.beh.entitiy.events

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class BehEntityAddRemove: MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        /**
         * access to all defined data
         */
        val general = arrayListOf<String>()

        /**
         * build object for gson parser
         */
        override fun getData(): MutableMap<String, Any> {
            if (unsafe.general.isEmpty())
                return mutableMapOf()
            return mutableMapOf("component_groups" to unsafe.general)
        }
    }

    var componentGroups: ArrayList<String> = arrayListOf()
        set(value) {
            unsafe.general.addAll(value)
            field = value
        }
    var componentGroup: String = ""
        set(value) {
            unsafe.general.add(value)
            field = value
        }
}