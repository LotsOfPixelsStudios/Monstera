package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentCustomHitTest : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        val hitBoxes = arrayListOf<Any>()

        override fun getData(): MutableMap<String, Any> {
            if (hitBoxes.isNotEmpty())
                general["hitboxes"] = hitBoxes
            return general
        }
    }

    /**
     * 0..n
     */
    fun addHitBox(value: HitBoxDef.() -> Unit) {
        unsafe.hitBoxes.add(HitBoxDef().apply(value).getData())
    }
}

class HitBoxDef {
    val general = mutableMapOf<String, Any>()

    var width: Float? = null
    var height: Float? = null

    fun pivot(x: Float, y: Float, z: Float) {
        general["pivot"] = arrayListOf(x, y, z)
    }

    fun getData(): MutableMap<String, Any> {
        width?.let { general["width"] = it }
        height?.let { general["height"] = it }
        return general
    }
}