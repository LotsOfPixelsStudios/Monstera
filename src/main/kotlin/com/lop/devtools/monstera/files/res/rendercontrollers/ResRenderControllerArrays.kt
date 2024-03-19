@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.lop.devtools.monstera.files.res.rendercontrollers

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.files.MonsteraRawFile

class ResRenderControllerArrays(private val parent: ResRenderController): MonsteraRawFile() {
    @SerializedName("textures")
    @Expose
    var texturesData: MutableMap<String, MutableList<String>>? = null
        @MonsteraBuildSetter set

    @SerializedName("geometries")
    @Expose
    var geometriesData: MutableMap<String, MutableList<String>>? = null
        @MonsteraBuildSetter set

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
    @OptIn(MonsteraBuildSetter::class)
    fun textures(namespace: String, query: String = "", textures: ResObj.() -> Unit) {
        val resObj = ResObj().apply(textures)
        texturesData = (texturesData ?: mutableMapOf()).apply {
            get("Array.$namespace")?.addAll(resObj.list) ?: run {
                put("Array.$namespace", resObj.list)
            }
        }
        parent.texture("Array.$namespace[$query]")
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
    @OptIn(MonsteraBuildSetter::class)
    fun geometries(namespace: String, query: String = "", geometries: ResObj.() -> Unit) {
        val resObj = ResObj().apply(geometries)
        geometriesData = (geometriesData ?: mutableMapOf()).apply {
            get("Array.$namespace")?.addAll(resObj.list) ?: run {
                put("Array.$namespace", resObj.list)
            }
        }
        parent.geometry = "Array.$namespace[$query]"
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