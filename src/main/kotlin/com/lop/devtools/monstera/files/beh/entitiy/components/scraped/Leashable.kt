package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class Leashable {
    @SerializedName("soft_distance")
    @Expose
    var softDistance: Number? = null
        

    @SerializedName("hard_distance")
    @Expose
    var hardDistance: Number? = null
        

    @SerializedName("max_distance")
    @Expose
    var maxDistance: Number? = null
        

    @SerializedName("can_be_stolen")
    @Expose
    var canBeStolen: Boolean? = null
        

    @SerializedName("on_leash")
    @Expose
    var onLeashData: OnLeash? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onLeash {
     *     event = minecraft:on_leash
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onLeash(value: OnLeash.() -> Unit) {
        onLeashData = (onLeashData ?: OnLeash()).apply(value)
    }

    @SerializedName("on_unleash")
    @Expose
    var onUnleashData: OnUnleash? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onUnleash {
     *     event = minecraft:on_unleash
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onUnleash(value: OnUnleash.() -> Unit) {
        onUnleashData = (onUnleashData ?: OnUnleash()).apply(value)
    }

    class OnLeash {
        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }

    class OnUnleash {
        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }
}
