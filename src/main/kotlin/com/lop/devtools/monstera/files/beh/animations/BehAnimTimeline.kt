package com.lop.devtools.monstera.files.beh.animations

class BehAnimTimeline(
    val keyFrameData: MutableMap<String, MutableList<String>>
) {
    fun keyFrame(time: Number, action: String) {
        keyFrameData[time.toString()]?.apply { add(action) } ?: run {
            keyFrameData[time.toString()] = mutableListOf(action)
        }
    }

    fun keyFrame(time: Number, action: ArrayList<String>) {
        keyFrameData[time.toString()]?.apply { addAll(action) } ?: run {
            keyFrameData[time.toString()] = action
        }
    }
}