package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class BlockSensor : MonsteraRawFile() {
    @SerializedName("sensor_radius")
    @Expose
    var sensorRadius: Number? = null
        
    @SerializedName("sources")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var sourcesData: MutableList<Sources>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * sources {
     *     test = has_silk_touch
     *     subject = other
     *     value = false
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun sources(value: Sources.() -> Unit) {
        sourcesData = (sourcesData ?: mutableListOf()).also { it.add(Sources().apply(value)) }
    }

    @SerializedName("on_break")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var onBreakData: MutableList<OnBreak>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onBreak {
     *     onBlockBroken = hive_destroyed
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onBreak(value: OnBreak.() -> Unit) {
        onBreakData = (onBreakData ?: mutableListOf()).also { it.add(OnBreak().apply(value)) }
    }

    class Sources : MonsteraRawFile() {
        @SerializedName("test")
        @Expose
        var test: String? = null
            
        @SerializedName("subject")
        @Expose
        var subject: Subject? = null
            
        @SerializedName("value")
        @Expose
        var value: Boolean? = null
    }

    class OnBreak : MonsteraRawFile() {
        @SerializedName("block_list")
        @Expose
        var blockListData: MutableList<String>? = null

        fun blockList(vararg value: String) {
            blockListData = (blockListData ?: mutableListOf()).also { it.addAll(value.toList()) }
        }

        @SerializedName("on_block_broken")
        @Expose
        var onBlockBroken: String? = null
    }
}
