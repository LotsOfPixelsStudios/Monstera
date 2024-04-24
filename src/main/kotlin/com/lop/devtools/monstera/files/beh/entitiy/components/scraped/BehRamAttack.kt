package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class BehRamAttack : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("run_speed")
    @Expose
    var runSpeed: Number? = null
        
    @SerializedName("ram_speed")
    @Expose
    var ramSpeed: Number? = null
        
    @SerializedName("min_ram_distance")
    @Expose
    var minRamDistance: Number? = null
        
    @SerializedName("ram_distance")
    @Expose
    var ramDistance: Number? = null
        
    @SerializedName("knockback_force")
    @Expose
    var knockbackForce: Number? = null

    @SerializedName("knockback_height")
    @Expose
    var knockbackHeight: Number? = null
        
    @SerializedName("pre_ram_sound")
    @Expose
    var preRamSound: String? = null
        
    @SerializedName("ram_impact_sound")
    @Expose
    var ramImpactSound: String? = null
        
    @SerializedName("cooldown_range")
    @Expose
    var cooldownRangeData: MutableList<Number>? = null
        
    @Components.VanillaComponentMarker
    fun cooldownRange(vararg value: Number) {
        cooldownRangeData = (cooldownRangeData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("on_start")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var onStartData: MutableList<OnStart>? = null
        @MonsteraBuildSetter set
        
    /**
     *
     * ```
     * onStart {
     *     event = start_event
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onStart(value: OnStart.() -> Unit) {
        onStartData = (onStartData ?: mutableListOf()).also { it.add(OnStart().apply(value)) }
    }

    class OnStart : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }
}
