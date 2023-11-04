package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentBreathable : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            totalSupply?.let { general["total_supply"] = it }
            suffocateTime?.let { general["suffocate_time"] = it }
            breathsWater?.let { general["breathes_water"] = it }
            breathsLava?.let { general["breathes_lava"] = it }
            breathsSolids?.let { general["breathes_solids"] = it }
            breathsAir?.let { general["breathes_air"] = it }
            generatesBubbles?.let { general["generates_bubbles"] = it }
            inhaleTime?.let { general["inhale_time"] = it }
            breathBlocks?.let { general["breathe_blocks"] = it }
            nonBreathBlocks?.let { general["non_breathe_blocks"] = it }
            return general
        }
    }

    var totalSupply: Int? = null
    var suffocateTime: Int? = null
    var breathsWater: Boolean? = null
    var breathsLava: Boolean? = null
    var breathsSolids: Boolean? = null
    var breathsAir: Boolean? = null
    var generatesBubbles: Boolean? = null
    var inhaleTime: Float? = null
    var breathBlocks: ArrayList<String>? = null
        set(value) {
            if (value == null || field == null) {
                field = value
            } else {
                field?.addAll(value) ?: error("field changed to null while trying to write to it!")
            }
        }

    var nonBreathBlocks: ArrayList<String>? = null
        set(value) {
            if (value == null || field == null) {
                field = value
            } else {
                field?.addAll(value) ?: error("field changed to null while trying to write to it!")
            }
        }
}