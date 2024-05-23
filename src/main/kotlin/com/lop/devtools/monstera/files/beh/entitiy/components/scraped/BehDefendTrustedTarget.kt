package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class BehDefendTrustedTarget : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("within_radius")
    @Expose
    var withinRadius: Number? = null
        
    @SerializedName("must_see")
    @Expose
    var mustSee: Boolean? = null
        
    @SerializedName("aggro_sound")
    @Expose
    var aggroSound: String? = null
        
    @SerializedName("sound_chance")
    @Expose
    var soundChance: Number? = null
        
    @SerializedName("on_defend_start")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var onDefendStartData: OnDefendStart? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onDefendStart {
     *     event = minecraft:fox_configure_defending
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onDefendStart(value: OnDefendStart.() -> Unit) {
        onDefendStartData = (onDefendStartData ?: OnDefendStart()).apply(value)
    }

    class OnDefendStart : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null

        @SerializedName("target")
        @Expose
        var target: Subject? = null
    }
}
