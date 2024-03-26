package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class RailSensor {
    @SerializedName("check_block_types")
    @Expose
    var checkBlockTypes: Boolean? = null
        

    @SerializedName("eject_on_activate")
    @Expose
    var ejectOnActivate: Boolean? = null
        

    @SerializedName("eject_on_deactivate")
    @Expose
    var ejectOnDeactivate: Boolean? = null
        

    @SerializedName("tick_command_block_on_activate")
    @Expose
    var tickCommandBlockOnActivate: Boolean? = null
        

    @SerializedName("tick_command_block_on_deactivate")
    @Expose
    var tickCommandBlockOnDeactivate: Boolean? = null
        

    @SerializedName("on_deactivate")
    @Expose
    var onDeactivateData: OnDeactivate? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onDeactivate {
     *     event = minecraft:command_block_deactivate
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onDeactivate(value: OnDeactivate.() -> Unit) {
        onDeactivateData = (onDeactivateData ?: OnDeactivate()).apply(value)
    }

    @SerializedName("on_activate")
    @Expose
    var onActivateData: OnActivate? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onActivate {
     *     event = minecraft:command_block_activate
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onActivate(value: OnActivate.() -> Unit) {
        onActivateData = (onActivateData ?: OnActivate()).apply(value)
    }

    class OnDeactivate {
        @SerializedName("event")
        @Expose
        var event: String? = null
            
    }

    class OnActivate {
        @SerializedName("event")
        @Expose
        var event: String? = null
            
    }
}
