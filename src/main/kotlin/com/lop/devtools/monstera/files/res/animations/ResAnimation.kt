package com.lop.devtools.monstera.files.res.animations

fun resAnimation(data: ResAnimation.() -> Unit): MutableMap<String, Any> {
    return ResAnimation().apply(data).getData()
}

class ResAnimation {
    val unsafe = Unsafe()

    inner class Unsafe {
        val general = mutableMapOf<String, Any>()
        val animations = ResAnimations()
    }

    var formatVersion: String = "1.8.0"
    fun animations(data: ResAnimations.() -> Unit) {
        unsafe.animations.apply(data)
    }

    fun getData(): MutableMap<String, Any> {
        unsafe.general["format_version"] = formatVersion
        unsafe.general["animations"] = unsafe.animations.getData()
        return unsafe.general
    }
}

class ResAnimations {
    val unsafe = Unsafe()

    inner class Unsafe {
        val general = mutableMapOf<String, Any>()
    }

    fun animation(name: String, data: ResAnimData.() -> Unit) {
        unsafe.general[name] = ResAnimData().apply(data).getData()
    }

    fun getData(): MutableMap<String, Any> {
        return unsafe.general
    }
}

class ResAnimData {
    val unsafe = Unsafe()

    inner class Unsafe {
        val general = mutableMapOf<String, Any>()
        val bones = ResAnimBones()
    }

    var loop: Any = "hold_on_last_frame"
    var animationLength: Number = 0f

    fun bones(data: ResAnimBones.() -> Unit) {
        unsafe.bones.apply(data)
    }

    fun getData(): MutableMap<String, Any> {
        unsafe.general["loop"] = loop
        if(animationLength != 0f)
            unsafe.general["animation_length"] = animationLength
        unsafe.general["bones"] = unsafe.bones.getData()
        return unsafe.general
    }
}

class ResAnimBones {
    val unsafe = Unsafe()

    inner class Unsafe {
        val general = mutableMapOf<String, Any>()
    }

    fun bone(name: String, data: ResAnimBone.() -> Unit) {
        unsafe.general[name] = ResAnimBone().apply(data).getData()
    }

    fun getData(): MutableMap<String, Any> {
        return unsafe.general
    }
}

class ResAnimBone {
    val unsafe = Unsafe()

    inner class Unsafe {
        val general = mutableMapOf<String, Any>()
        val rotation = ResAnimKeyFrames()
        val position = ResAnimKeyFrames()
        val scale = ResAnimKeyFrames()
    }

    fun rotation(data: ResAnimKeyFrames.() -> Unit) {
        unsafe.rotation.apply(data)
    }

    fun position(data: ResAnimKeyFrames.() -> Unit) {
        unsafe.position.apply(data)
    }

    fun scale(data: ResAnimKeyFrames.() -> Unit) {
        unsafe.scale.apply(data)
    }

    fun getData(): MutableMap<String, Any> {
        if (unsafe.rotation.getData().isNotEmpty())
            unsafe.general["rotation"] = unsafe.rotation.getData()
        if (unsafe.position.getData().isNotEmpty())
            unsafe.general["position"] = unsafe.position.getData()
        if (unsafe.scale.getData().isNotEmpty())
            unsafe.general["scale"] = unsafe.scale.getData()

        return unsafe.general
    }
}

class ResAnimKeyFrames {
    val unsafe = Unsafe()

    inner class Unsafe {
        val general = mutableMapOf<String, Any>()
        val keyFrames = arrayListOf<Frame>()
    }

    inner class Frame(
        val time: Number,
        val data: Any
    )

    fun keyFrame(time: Number, data: ResAnimKeyFrame.() -> Unit) {
        keyFrame(time.toString(), data)
    }

    fun keyFrame(time: String, data: ResAnimKeyFrame.() -> Unit) {
        unsafe.keyFrames.add(
            Frame(time.toFloat(), ResAnimKeyFrame().apply(data).getData())
        )
    }

    fun keyFrame(time: String, data: ArrayList<Any>) {
        unsafe.keyFrames.add(
            Frame(time.toFloat(), data)
        )
    }

    fun getData(): MutableMap<String, Any> {
        unsafe.keyFrames.sortBy { it.time.toFloat() }
        unsafe.keyFrames.forEach {
            unsafe.general[it.time.toString()] = it.data
        }
        return unsafe.general
    }
}

class ResAnimKeyFrame {
    val unsafe = Unsafe()

    inner class Unsafe {
        val general = mutableMapOf<String, Any>()
    }

    var post: ArrayList<Any> = arrayListOf()
    var pre: ArrayList<Any> = arrayListOf()
    var lerpMode: String? = null

    fun getData(): MutableMap<String, Any> {
        if (pre.size > 0) {
            val tmp = mutableListOf<Any>()
            pre.forEach {
                try {
                    val data = it.toString().toFloat()
                    tmp.add(data)
                } catch (e: Exception) {
                    tmp.add(it)
                }
            }
            unsafe.general["pre"] = tmp
        }

        if (post.size > 0){
            val tmp = mutableListOf<Any>()
            post.forEach {
                try {
                    val data = it.toString().toFloat()
                    tmp.add(data)
                } catch (e: Exception) {
                    tmp.add(it)
                }
            }
            unsafe.general["post"] = tmp
        }
        lerpMode?.let { unsafe.general["lerp_mode"] = it }
        return unsafe.general
    }
}