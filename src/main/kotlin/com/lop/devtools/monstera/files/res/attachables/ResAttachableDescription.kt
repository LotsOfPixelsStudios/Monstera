package com.lop.devtools.monstera.files.res.attachables

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.files.res.TextureIndex

class ResAttachableDescription {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    val unsafe = Unsafe()

    inner class Unsafe {
        /**
         * access to all defined animations
         */
        val general = mutableMapOf<String, Any>()

        val materialMap = mutableMapOf<String, String>()
        val textureMap = mutableMapOf<String, String>()
        val geometryMap = mutableMapOf<String, String>()
        val animationMap = mutableMapOf<String, String>()

        /**
         * 0..1
         *
         * @param key the identifier of the texture that can be called in the render controller, default is "default"
         * @param value the texture path like "textures/attachable/test"
         */
        fun textures(key: String = "default", value: String) {
            unsafe.textureMap[key] = value
        }
    }

    /**
     * 1
     *
     * @param value the identifier like minecraft:sheep
     */
    fun identifier(value: String) {
        unsafe.general["identifier"] = value
    }

    /**
     * 0..1
     *
     * @param key the identifier of the material that can be called in the render controller, default is "default"
     * @param value the actual material like "parrot"
     */
    fun materials(key: String = "default", value: String) {
        unsafe.materialMap[key] = value
    }

    /**
     * 0..1
     *
     * @param key the identifier of the texture that can be called in the render controller, default is "default"
     * @param value the texture path like "textures/attachable/test"
     */
    fun Addon.textures(key: String = "default", value: String) {
        unsafe.textureMap[key] = value
        TextureIndex.instance(this).textures.add(value)
    }

    /**
     * 0..1
     *
     * @param key the identifier of the geometry that can be called in the render controller, default is "default"
     * @param value the model identifier like geometry.sheep
     */
    fun geometry(key: String = "default", value: String) {
        unsafe.geometryMap[key] = value
    }

    /**
     * 0..1
     *
     * @param key the identifier of the animation that can be called in the animation controller
     * OR a animation controller that can be called in scripts
     * @param value the animation identifier (defined in ../animations/)
     * OR the animation controller identifier (defined in ../animation_controllers/)
     */
    fun animations(key: String, value: String) {
        unsafe.animationMap[key] = value
    }

    /**
     * 0..1
     *
     * @param controllers most likely only one animation controller like: arraylistOf("controller.render.sheep")
     */
    fun renderController(controllers: ArrayList<String> = arrayListOf("controller.render.default")) {
        unsafe.general["render_controllers"] = controllers
    }

    /**
     * 0..1
     *
     * @param scripts animation keys that are scripts (all that begin with "controller.animation.")
     */
    fun scripts(scripts: ArrayList<String>) {
        unsafe.general["scripts"] = mutableMapOf("animate" to scripts)
    }

    fun getData(): MutableMap<String, Any> {
        if (unsafe.materialMap.isNotEmpty())
            unsafe.general["materials"] = unsafe.materialMap
        if (unsafe.textureMap.isNotEmpty())
            unsafe.general["textures"] = unsafe.textureMap
        if (unsafe.geometryMap.isNotEmpty())
            unsafe.general["geometry"] = unsafe.geometryMap
        if (unsafe.animationMap.isNotEmpty())
            unsafe.general["animations"] = unsafe.animationMap
        return unsafe.general
    }
}