package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class BehEmerge {
    @SerializedName("duration")
    @Expose
    var duration: Number? = null
        

    @SerializedName("on_done")
    @Expose
    var onDoneData: OnDone? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onDone {
     *     event = minecraft:emerged
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onDone(value: OnDone.() -> Unit) {
        onDoneData = (onDoneData ?: OnDone()).apply(value)
    }

    class OnDone {
        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }
}
