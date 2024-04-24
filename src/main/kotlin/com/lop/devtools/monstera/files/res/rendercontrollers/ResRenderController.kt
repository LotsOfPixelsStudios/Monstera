@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.lop.devtools.monstera.files.res.rendercontrollers

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import java.awt.Color

@OptIn(MonsteraBuildSetter::class)
open class ResRenderController : MonsteraRawFile() {
    @SerializedName("textures")
    @Expose
    var texturesData: MutableList<String>? = null
        @MonsteraBuildSetter set

    /**
     * @param texture like "Texture.default" or Array.<namespace>[<query>]
     */
    @OptIn(MonsteraBuildSetter::class)
    fun texture(texture: String = "Texture.default", uniqe: Boolean = true) {
        texturesData = (texturesData ?: mutableListOf()).apply {
            if(!uniqe || !contains(texture))
                add(texture)
        }
    }

    @SerializedName("geometry")
    @Expose
    var geometry: String? = null

    /**
     * @param geometry like "Geometry.default" or Array.<namespace>[<query>]
     */
    @Deprecated("Use field", ReplaceWith("this.geometry = geometry"))
    fun geometry(geometry: String = "Geometry.default") {
        this.geometry = geometry
    }

    @SerializedName("materials")
    @Expose
    var materialsData: MutableList<MutableMap<String, String>>? = null
        @MonsteraBuildSetter set

    /**
     * 1
     */
    fun materials(material: String = "Material.default") {
        materialsData = (materialsData ?: mutableListOf()).apply {
            add(mutableMapOf("*" to material))
        }
    }

    fun material(applyTo: String, material: String) {
        materialsData = (materialsData ?: mutableListOf()).apply {
            add(mutableMapOf(applyTo to material))
        }
    }

    @SerializedName("arrays")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var arraysData: ResRenderControllerArrays? = null
        @MonsteraBuildSetter set

    /**
     * Define multiple Textures or Geometries
     */
    fun arrays(data: ResRenderControllerArrays.() -> Unit) {
        arraysData = (arraysData ?: ResRenderControllerArrays(this)).apply(data)
    }

    @SerializedName("uv_anim")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var uvAnimData: ResRenConUvAnim? = null
        @MonsteraBuildSetter set

    fun uvAnim(data: ResRenConUvAnim.() -> Unit) {
        uvAnimData = (uvAnimData ?: ResRenConUvAnim()).apply(data)
    }

    @SerializedName("on_fire_color")
    @Expose
    var onFireColorData: MutableMap<String, Number>? = null
        @MonsteraBuildSetter set

    /**
     * 0..1
     *
     * @param r: 0.0f to 1.0f
     * @param g: 0.0f to 1.0f
     * @param b: 0.0f to 1.0f
     * @param a: 0.0f to 1.0f
     */
    fun onFireColor(r: Number, g: Number, b: Number, a: Number) {
        onFireColorData = mutableMapOf(
            "r" to r,
            "g" to g,
            "b" to b,
            "a" to a
        )
    }

    /**
     * 0..1
     *
     * @param color a java.awt.Color to choose from
     */
    fun onFireColor(color: Color) {
        onFireColor(
            color.red   / 255.0,
            color.green / 255.0,
            color.blue  / 255.0,
            color.alpha / 255.0
        )
    }


    @SerializedName("is_hurt_color")
    @Expose
    var isHurtColorData: MutableMap<String, Number>? = null
        @MonsteraBuildSetter set

    /**
     * 0..1
     *
     * @param r 0.0f to 1.0f
     * @param g 0.0f to 1.0f
     * @param b 0.0f to 1.0f
     * @param a 0.0f to 1.0f
     *
     * ```
     * onHurtColor(0.2f, 0.1f, 0.8f, 0.5f)
     * ```
     */
    fun onHurtColor(r: Number, g: Number, b: Number, a: Number) {
        isHurtColorData = mutableMapOf(
            "r" to r,
            "g" to g,
            "b" to b,
            "a" to a
        )
    }

    /**
     * 0..1
     *
     * @param color a java.awt.Color to choose from
     */
    fun onHurtColor(color: Color) {
        onHurtColor(
            color.red   / 255.0,
            color.green / 255.0,
            color.blue  / 255.0,
            color.alpha / 255.0
        )
    }


    @SerializedName("overlay_color")
    @Expose
    var overlayColorData: MutableMap<String, String>? = null
        @MonsteraBuildSetter set

    /**
     * ```
     * overlayColor(
     *       "variable.is_invulnerable ? 1.0 : this",
     *       "variable.is_invulnerable ? 1.0 : this",
     *       "variable.is_invulnerable ? 1.0 : this",
     *       "variable.is_invulnerable ? query.overlay_alpha : this"
     * )
     * ```
     *
     * @param r: "0.0" to "1.0", query can be attached
     * @param g: "0.0" to "1.0", query can be attached
     * @param b: "0.0" to "1.0", query can be attached
     * @param a: "0.0" to "1.0", query can be attached
     */
    fun overlayColor(r: String, g: String, b: String, a: String) {
        overlayColorData = mutableMapOf(
            "r" to r,
            "g" to g,
            "b" to b,
            "a" to a
        )
    }

    /**
     * 0..1
     *
     * @param color a java.awt.Color to choose from
     */
    fun overlayColor(color: Color) {
        overlayColor(
            (color.red   / 255.0).toString(),
            (color.green / 255.0).toString(),
            (color.blue  / 255.0).toString(),
            (color.alpha / 255.0).toString()
        )
    }

    private fun sampleTextures() {
        arrays {
            textures("variants", Query.variant.toString()) {
                add("var1")
                add("var2")
                addAll(arrayListOf("var3", "var4"))
            }
        }
    }

    private fun sampleGeo() {
        arrays {
            geometries("variants", Query.variant.toString()) {
                add("var1")
                add("var2")
                addAll(arrayListOf("var3", "var4"))
            }
        }
    }
}