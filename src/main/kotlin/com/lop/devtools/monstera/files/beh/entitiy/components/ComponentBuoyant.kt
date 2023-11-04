package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentBuoyant : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        override fun getData(): MutableMap<String, Any> {
            applyGravity?.let { general["apply_gravity"] = it }
            baseBuoyancy?.let { general["base_buoyancy"] = it }
            bigWaveProbability?.let { general["big_wave_probability"] = it }
            bigWaveSpeed?.let { general["big_wave_speed"] = it }
            dragDownOnBuoyancyRemoved?.let { general["drag_down_on_buoyancy_removed"] = it }
            liquidBlocks?.let { general["liquid_blocks"] = it }
            simulateWave?.let { general["simulate_waves"] = it }
            return general
        }
    }

    var applyGravity: Boolean? = null
    var baseBuoyancy: Float? = null
    var bigWaveProbability: Float? = null
    var bigWaveSpeed: Float? = null
    var dragDownOnBuoyancyRemoved: Float? = null
    var liquidBlocks: ArrayList<String>? = null
        set(value) {
            if (value == null || field == null) {
                field = value
            } else {
                field?.addAll(value) ?: error("field changed to null while trying to write to it!")
            }
        }

    var simulateWave: Boolean? = null
}