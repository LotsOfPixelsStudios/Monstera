package com.lop.devtools.monstera.files.beh.animations

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class BehAnimation: MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        /**
         * access to all defined animations
         */
        val general = mutableMapOf<String, Any>()
        val timeline = BehAnimTimeline()

        override fun getData(): MutableMap<String, Any> {
            general["timeline"] = timeline.unsafe.getData()
            return unsafe.general
        }
    }

    var animLength: Number = 0
        set(value) {
            unsafe.general["animation_length"] = value
            field = value
        }

    /**
     * 1
     *
     * @sample sampleTimeLine
     */
    fun timeline(settings: BehAnimTimeline.() -> Unit) {
        unsafe.timeline.apply(settings)
    }

    /**
     * Sample
     */
    private fun sampleTimeLine() {
        timeline {
            keyFrame(0.2f, "/say hello")
            keyFrame(0.1f, "/say test")
        }
    }

    /**
     * Sample
     */
    private fun sampleAnimLength() {
        animLength = 0.2f
    }
}