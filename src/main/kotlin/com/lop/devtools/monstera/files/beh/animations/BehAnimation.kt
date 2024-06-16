package com.lop.devtools.monstera.files.beh.animations

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile

open class BehAnimation : MonsteraRawFile() {
    @SerializedName("timeline")
    @Expose
    var timelineData: MutableMap<String, MutableList<String>>? = null
        @MonsteraBuildSetter set

    @SerializedName("animation_length")
    @Expose
    var animLength: Number? = null

    /**
     * 1
     *
     * @sample sampleTimeLine
     */
    @OptIn(MonsteraBuildSetter::class)
    fun timeline(settings: BehAnimTimeline.() -> Unit) {
        timelineData = (timelineData ?: mutableMapOf()).also {
            it.putAll(BehAnimTimeline(it).apply(settings).keyFrameData)
        }
    }

    /**
     * Sample
     */
    private fun sampleTimeLine() {
        timeline {
            keyFrame(0.2f, "/say hello")
            keyFrame(0.1f, "/say test")
        }
    }
}