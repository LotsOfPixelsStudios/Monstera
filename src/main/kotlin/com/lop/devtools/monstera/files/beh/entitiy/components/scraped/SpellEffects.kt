package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class SpellEffects {
    @SerializedName("add_effects")
    @Expose
    var addEffectsData: MutableList<AddEffects>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * addEffects {
     *     effect = bad_omen
     *     duration = 6000
     *     visible = false
     *     displayOnScreenAnimation = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun addEffects(value: AddEffects.() -> Unit) {
        addEffectsData = (addEffectsData ?: mutableListOf()).also { it.add(AddEffects().apply(value)) }
    }

    @SerializedName("remove_effects")
    @Expose
    var removeEffects: MutableList<String>? = null
        

    class AddEffects {
        @SerializedName("effect")
        @Expose
        var effect: String? = null
            

        @SerializedName("duration")
        @Expose
        var duration: Number? = null
            

        @SerializedName("visible")
        @Expose
        var visible: Boolean? = null
            

        @SerializedName("display_on_screen_animation")
        @Expose
        var displayOnScreenAnimation: Boolean? = null
            
    }
}
