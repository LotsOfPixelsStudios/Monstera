package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class ConditionalBandwidthOptimization {
    @SerializedName("default_values")
    @Expose
    var defaultValuesData: DefaultValues? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * defaultValues {
     *     maxOptimizedDistance = 80.0
     *     maxDroppedTicks = 7
     *     useMotionPredictionHints = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun defaultValues(value: DefaultValues.() -> Unit) {
        defaultValuesData = (defaultValuesData ?: DefaultValues()).apply(value)
    }

    @SerializedName("conditional_values")
    @Expose
    var conditionalValuesData: MutableList<ConditionalValues>? = null
        @MonsteraBuildSetter set

    /**
     * Conditions that must be met for these optimization values to be used.
     * ```
     * conditionalValues {
     *     maxOptimizedDistance = 0.0
     *     maxDroppedTicks = 0
     *     useMotionPredictionHints = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun conditionalValues(value: ConditionalValues.() -> Unit) {
        conditionalValuesData =
            (conditionalValuesData ?: mutableListOf()).also { it.add(ConditionalValues().apply(value)) }
    }

    class DefaultValues {
        @SerializedName("max_optimized_distance")
        @Expose
        var maxOptimizedDistance: Number? = null
            

        @SerializedName("max_dropped_ticks")
        @Expose
        var maxDroppedTicks: Number? = null
            

        @SerializedName("use_motion_prediction_hints")
        @Expose
        var useMotionPredictionHints: Boolean? = null
            
    }

    class ConditionalValues {
        @SerializedName("max_optimized_distance")
        @Expose
        var maxOptimizedDistance: Number? = null
            

        @SerializedName("max_dropped_ticks")
        @Expose
        var maxDroppedTicks: Number? = null
            

        @SerializedName("use_motion_prediction_hints")
        @Expose
        var useMotionPredictionHints: Boolean? = null
            

        @SerializedName("conditional_values")
        @Expose
        var conditionalValuesData: MutableList<BehEntityFilter>? = null
            @MonsteraBuildSetter set

        /**
         * Conditions that must be met for these optimization values to be used.
         * ```
         * conditionalValues {
         *     test = is_moving
         *     subject = self
         *     operator = ==
         *     value = true
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun conditionalValues(value: BehEntityFilter.() -> Unit) {
            conditionalValuesData =
                (conditionalValuesData ?: mutableListOf()).also { it.add(BehEntityFilter().apply(value)) }
        }
    }
}
