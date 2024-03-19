package com.lop.devtools.monstera.files.res.entities.comp

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ResEntityLocators : MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        /**
         * access to all defined animations
         */
        val general = mutableMapOf<String, Any>()

        val lead = ResEntityLead()
        override fun getData(): MutableMap<String, Any> {
            if (unsafe.lead.getData().isNotEmpty()) unsafe.general["lead"] = unsafe.lead.getData()

            return unsafe.general
        }
    }

    /**
     * 1
     *
     * @sample sample
     */
    fun lead(settings: ResEntityLead.() -> Unit) {
        unsafe.lead.apply(settings)
    }

    private fun sample() {
        lead {
            attach("head", arrayListOf(0.0f, 0.0f, 0.0f))
            attach("head", 0.0f, 0.0f, 0.0f)
        }
    }
}

class ResEntityLead {
    val general = mutableMapOf<String, Any>()

    /**
     * 1
     *
     * @param locationName: where to attach name
     * @param location: 3 Floats in a List where the pos is
     */
    fun attach(locationName: String = "head", location: ArrayList<Float>) {
        general[locationName] = location
    }

    /**
     * 1
     *
     * @param locationName: where to attach name
     * @param x: Float with x coords
     * @param y: Float with y coords
     * @param z: Float with z coords
     */
    fun attach(locationName: String = "head", x: Float, y: Float, z: Float) {
        general[locationName] = arrayListOf(x, y, z)
    }

    fun getData(): Map<String, Any> {
        return general
    }
}