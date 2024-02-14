package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentCircleAroundAnchor : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            priority?.let { general["priority"] = it }
            radiusChange?.let { general["radius_change"] = it }
            radiusAdjustmentChance?.let { general["radius_adjustment_chance"] = it }
            heightAdjustmentChance?.let { general["height_adjustment_chance"] = it }
            goalRadius?.let { general["goal_radius"] = it }
            angleChange?.let { general["angle_change"] = it }

            return general
        }
    }
    var priority: Int? = null
    var radiusChange: Float? = null
    var radiusAdjustmentChance: Float? = null
    var heightAdjustmentChance: Float? = null
    var goalRadius: Float? = null

    var angleChange: Float? = null

    fun radiusRange(min: Float, max: Float) {
        unsafe.general["radius_range"] = arrayListOf(min, max)
    }

    fun heightOffsetRange(min: Float, max: Float) {
        unsafe.general["height_offset_range"] = arrayListOf(min, max)
    }

    fun heightAboveTargetRange(min: Float, max: Float) {
        unsafe.general["height_above_target_range"] = arrayListOf(min, max)
    }
}