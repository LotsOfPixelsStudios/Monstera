package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class BehGoAndGiveItemsToOwner {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("run_speed")
    @Expose
    var runSpeed: Number? = null
        

    @SerializedName("throw_sound")
    @Expose
    var throwSound: String? = null
        

    @SerializedName("on_item_throw")
    @Expose
    var onItemThrowData: MutableList<OnItemThrow>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onItemThrow {
     *     event = pickup_item_delay
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onItemThrow(value: OnItemThrow.() -> Unit) {
        onItemThrowData = (onItemThrowData ?: mutableListOf()).also { it.add(OnItemThrow().apply(value)) }
    }

    class OnItemThrow {
        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }
}
