package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class ComponentConditionalBandwidthOptimization : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            return general
        }
    }

    /**
     * 0..1
     */
    fun defaultValues(
        maxOptimizedDistance: Float? = null,
        maxDroppedTicks: Int? = 20,
        useMotionPrediction: Boolean? = null
    ) {
        val data = mutableMapOf<String, Any>()
        if(maxOptimizedDistance != null)
            data["max_optimized_distance"] = maxOptimizedDistance
        if(maxDroppedTicks != null)
            data["max_dropped_ticks"] = maxDroppedTicks
        if(useMotionPrediction != null)
            data["use_motion_prediction_hints"] = useMotionPrediction
        unsafe.general["default_values"] = data
    }

    /**
     * 0..1
     */
    fun conditionalValues(value: BandwidthOptCondValues.() -> Unit) {
        unsafe.general["conditional_values"] = BandwidthOptCondValues().apply(value).unsafe.getData()
    }
}

class BandwidthOptCondValues {
    val unsafe = Unsafe()

    inner class Unsafe {
        val general = arrayListOf<Any>()

        fun getData(): ArrayList<Any> {
            return general
        }
    }

    /**
     * 1..n
     */
    fun entry(value: BandwidthOptCondEntry.() -> Unit) {
        unsafe.general.add(BandwidthOptCondEntry().apply(value).unsafe.getData())
    }
}

class BandwidthOptCondEntry : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        val filterList = arrayListOf<Any>()
        override fun getData(): MutableMap<String, Any> {
            maxOptimizedDistance?.let { general["max_optimized_distance"] = it }
            maxDroppedTicks?.let { general["max_dropped_ticks"] = it }
            useMotionPredictionHints?.let { general["use_motion_prediction_hints"] = it }
            general["conditional_values"] = filterList
            return general
        }
    }
    var maxOptimizedDistance: Float? = null
    var maxDroppedTicks: Int? = null

    var useMotionPredictionHints: Boolean? = null

    /**
     * 1..n
     */
    fun conditionalValues(value: BehEntityFilter.() -> Unit) {
        unsafe.filterList.add(BehEntityFilter().apply(value).getData())
    }
}