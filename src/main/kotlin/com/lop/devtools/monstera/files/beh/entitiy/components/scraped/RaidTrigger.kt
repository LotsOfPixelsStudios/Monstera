package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class RaidTrigger {
    @SerializedName("triggered_event")
    @Expose
    var triggeredEventData: TriggeredEvent? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * triggeredEvent {
     *     event = minecraft:remove_raid_trigger
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun triggeredEvent(value: TriggeredEvent.() -> Unit) {
        triggeredEventData = (triggeredEventData ?: TriggeredEvent()).apply(value)
    }

    class TriggeredEvent {
        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }
}
