package com.lop.devtools.monstera.files.beh.animations

class BehAnimTimeline(
    val keyFrameData: MutableMap<String, MutableList<String>>
) {
    fun keyFrame(time: Float, action: String) {
        keyFrameData[time.toString()]?.apply { add(action) } ?: run {
            keyFrameData[time.toString()] = mutableListOf(action)
        }
    }

    fun keyFrame(time: Float, action: ArrayList<String>) {
        keyFrameData[time.toString()]?.apply { addAll(action) } ?: run {
            keyFrameData[time.toString()] = action
        }
    }
}