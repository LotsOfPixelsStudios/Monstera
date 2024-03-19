package com.lop.devtools.monstera.files.beh.animations

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class BehAnimTimeline: MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        /**
         * access to all defined key frames
         */
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            return unsafe.general
        }
    }

    fun keyFrame(time: Float, action: String) {
        unsafe.general[time.toString()] = action
    }

    fun keyFrame(time: Float, action: ArrayList<String>) {
        unsafe.general[time.toString()] = action
    }
}