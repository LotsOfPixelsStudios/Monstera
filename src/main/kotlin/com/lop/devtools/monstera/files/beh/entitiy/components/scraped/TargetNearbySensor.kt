package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class TargetNearbySensor {
    @SerializedName("inside_range")
    @Expose
    var insideRange: Number? = null
        

    @SerializedName("outside_range")
    @Expose
    var outsideRange: Number? = null
        

    @SerializedName("must_see")
    @Expose
    var mustSee: Boolean? = null
        

    @SerializedName("on_inside_range")
    @Expose
    var onInsideRangeData: OnInsideRange? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onInsideRange {
     *     event = switch_to_melee
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onInsideRange(value: OnInsideRange.() -> Unit) {
        onInsideRangeData = (onInsideRangeData ?: OnInsideRange()).apply(value)
    }

    @SerializedName("on_outside_range")
    @Expose
    var onOutsideRangeData: OnOutsideRange? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onOutsideRange {
     *     event = switch_to_ranged
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onOutsideRange(value: OnOutsideRange.() -> Unit) {
        onOutsideRangeData = (onOutsideRangeData ?: OnOutsideRange()).apply(value)
    }

    @SerializedName("on_vision_lost_inside_range")
    @Expose
    var onVisionLostInsideRangeData: OnVisionLostInsideRange? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onVisionLostInsideRange {
     *     event = minecraft:stop_exploding
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onVisionLostInsideRange(value: OnVisionLostInsideRange.() -> Unit) {
        onVisionLostInsideRangeData = (onVisionLostInsideRangeData ?: OnVisionLostInsideRange()).apply(value)
    }

    class OnInsideRange {
        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }

    class OnOutsideRange {
        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }

    class OnVisionLostInsideRange {
        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }
}
