package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class AttackCooldown : MonsteraRawFile() {
    @SerializedName("attack_cooldown_time")
    @Expose
    var attackCooldownTime: Number? = null
        

    @SerializedName("attack_cooldown_complete_event")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var attackCooldownCompleteEventData: AttackCooldownCompleteEvent? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * attackCooldownCompleteEvent {
     *     event = attack_cooldown_complete_event
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun attackCooldownCompleteEvent(value: AttackCooldownCompleteEvent.() -> Unit) {
        attackCooldownCompleteEventData =
            (attackCooldownCompleteEventData ?: AttackCooldownCompleteEvent()).apply(value)
    }

    class AttackCooldownCompleteEvent : MonsteraRawFile() {

        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }
}
