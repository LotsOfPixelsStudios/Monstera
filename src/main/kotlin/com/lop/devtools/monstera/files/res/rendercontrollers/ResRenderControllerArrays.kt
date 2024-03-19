package com.lop.devtools.monstera.files.res.rendercontrollers

import com.lop.devtools.monstera.addon.molang.Molang

class ResRenderControllerArrays(private val parent: ResRenderController) {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    val unsafe = Unsafe()

    inner class Unsafe {
        /**
         * access to all defined animations
         */
        val general = mutableMapOf<String, Any>()

        val textureMap = mutableMapOf<String, ResObj>()
        val geometryMap = mutableMapOf<String, ResObj>()
    }

    /**
     * access the unsafe context, alternatively use "unsafe."
     */
    fun unsafe(data: Unsafe.() -> Unit) {
        unsafe.apply(data)
    }

    /**
     * add multiple textures to the render controller
     *
     * @param namespace to add multiple texture layers with queries etc
     * @param query if not empty, monstera will attempt to call texture("Arrays.$nameSpace", query) within the renderController
     */
    fun textures(namespace: String, query: Unit.() -> Molang, textures: ResObj.() -> Unit) {
        textures(namespace, query(Unit).data, textures)
    }

    /**
     * add multiple textures to the render controller
     *
     * @param namespace to add multiple texture layers with queries etc
     * @param query if not empty, monstera will attempt to call texture("Arrays.$nameSpace", query) within the renderController
     */
    fun textures(namespace: String, query: Molang, textures: ResObj.() -> Unit) {
        textures(namespace, query.data, textures)
    }

    /**
     * add multiple textures to the render controller
     *
     * @param namespace to add multiple texture layers with queries etc
     * @param query if not empty, monstera will attempt to call texture("Arrays.$nameSpace", query) within the renderController
     */
    fun textures(namespace: String, query: String = "", textures: ResObj.() -> Unit) {
        val resObj = ResObj().apply(textures)
        if(unsafe.textureMap.contains(namespace)) {
            val tmpObj = unsafe.textureMap[namespace]!!
            tmpObj.addAll(resObj.list)
            unsafe.textureMap[namespace] = tmpObj
        } else {
            unsafe.textureMap[namespace] = resObj
        }


        unsafe.general["textures"] = mutableMapOf<String, ArrayList<String>>().apply {
            unsafe.textureMap.forEach { (namespace, textures) -> put("Array.$namespace", textures.list) }
        }

        if(query != "") {
            if(!parent.unsafe.textureList.contains("Array.$namespace[$query]"))
                parent.texture("Array.$namespace[$query]")
        }
    }

    /**
     * add multiple geometries to the render controller
     *
     * @param namespace to add multiple texture layers with an own namespace queries etc
     * @param query if not empty, monstera will attempt to call texture("Arrays.$nameSpace", query) within the renderController
     */
    fun geometries(namespace: String, query: Unit.() -> Molang, geometries: ResObj.() -> Unit) {
        geometries(namespace, query(Unit).data, geometries)
    }

    /**
     * add multiple geometries to the render controller
     *
     * @param namespace to add multiple texture layers with an own namespace queries etc
     * @param query if not empty, monstera will attempt to call texture("Arrays.$nameSpace", query) within the renderController
     */
    fun geometries(namespace: String, query: Molang, geometries: ResObj.() -> Unit) {
        geometries(namespace, query.data, geometries)
    }

    /**
     * add multiple geometries to the render controller
     *
     * @param namespace to add multiple texture layers with an own namespace queries etc
     * @param query if not empty, monstera will attempt to call texture("Arrays.$nameSpace", query) within the renderController
     */
    fun geometries(namespace: String, query: String = "", geometries: ResObj.() -> Unit) {
        val resObj = ResObj().apply(geometries)
        if(unsafe.geometryMap.contains(namespace)) {
            val tmpObj = unsafe.geometryMap[namespace]!!
            tmpObj.addAll(resObj.list)
            unsafe.geometryMap[namespace] = tmpObj
        } else {
            unsafe.geometryMap[namespace] = resObj
        }

        unsafe.general["geometries"] = mutableMapOf<String, ArrayList<String>>().apply {
            unsafe.geometryMap.forEach { (namespace, geos) -> put("Array.$namespace", geos.list) }
        }

        if(query != "") {
            parent.geometry("Array.$namespace[$query]")
        }
    }

    fun getData(): MutableMap<String, Any> {
        return unsafe.general
    }

    class ResObj {
        val list = arrayListOf<String>()

        fun add(entry: String) {
            list.add(entry)
        }

        fun addAll(entries: ArrayList<String>) {
            list.addAll(entries)
        }
    }
}