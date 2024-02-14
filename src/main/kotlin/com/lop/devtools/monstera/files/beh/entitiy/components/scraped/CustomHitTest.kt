package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class CustomHitTest {
    @SerializedName("hitboxes")
    @Expose
    var hitboxesData: MutableList<Hitboxes>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * hitboxes {
     *     width = 1.0
     *     height = 0.85
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun hitboxes(value: Hitboxes.() -> Unit) {
        hitboxesData = (hitboxesData ?: mutableListOf()).also { it.add(Hitboxes().apply(value)) }
    }

    class Hitboxes {

        @SerializedName("width")
        @Expose
        var width: Number? = null
            

        @SerializedName("height")
        @Expose
        var height: Number? = null
            

        @SerializedName("pivot")
        @Expose
        var pivotData: MutableList<Number>? = null
            

        fun pivot(vararg value: Number) {
            pivotData = (pivotData ?: mutableListOf()).also { it.addAll(value.toList()) }
        }
    }
}
