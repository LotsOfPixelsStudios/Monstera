package com.lop.devtools.monstera.files.res.rendercontrollers

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.addon.molang.Query
import java.awt.Color

class ResRenderController: MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        /**
         * access to all defined animations
         */
        val general = mutableMapOf<String, Any>()

        val textureList = arrayListOf<String>()
        val uvAnim = ResRenConUvAnim()
        var arraysUsed = false

        val arrayObj = ResRenderControllerArrays(this@ResRenderController)

        override fun getData(): MutableMap<String, Any> {
            general["arrays"] = arrayObj.getData()
            if(uvAnim.unsafe.getData().isNotEmpty()) {
                general["uv_anim"] = uvAnim.unsafe.getData()
            }
            return general
        }
    }

    /**
     * access the unsafe context, alternatively use "unsafe."
     */
    fun unsafe(data: Unsafe.() -> Unit) {
        unsafe.apply(data)
    }

    @Deprecated("use unsafe context", ReplaceWith("unsafe.general"))
    var general: MutableMap<String, Any> = mutableMapOf()

    /**
     * @param texture like "Texture.default" or Array.<namespace>[<query>]
     */
    fun texture(texture: String = "Texture.default") {
        unsafe.textureList.add(texture)
        unsafe.general["textures"] = unsafe.textureList
    }

    /**
     * @param geometry like "Geometry.default" or Array.<namespace>[<query>]
     */
    fun geometry(geometry: String = "Geometry.default") {
        unsafe.general["geometry"] = geometry
    }

    /**
     * Define multiple Textures or Geometries
     */
    fun arrays(data: ResRenderControllerArrays.() -> Unit) {
        unsafe.arrayObj.apply(data)
    }


    /**
     * 1
     *
     * todo if more materials are needed make material accept arrayLists
     * @sample sampleMaterial
     */
    fun materials(
        material: String = "Material.default"
    ) {
        unsafe.general["materials"] = arrayListOf(mutableMapOf("*" to material))
    }

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
    fun onHurtColor(r: Float, g: Float, b: Float, a: Float) {
        unsafe.general.apply {
            put(
                "is_hurt_color", mutableMapOf(
                    "r" to r,
                    "g" to g,
                    "b" to b,
                    "a" to a
                )
            )
        }
    }

    /**
     * 0..1
     *
     * @param color a java.awt.Color to choose from
     */
    fun onHurtColor(color: Color) {
        unsafe.general["on_fire_color"] = mutableMapOf(
            "r" to (color.red / 255).toFloat(),
            "g" to (color.green / 255).toFloat(),
            "b" to (color.blue / 255).toFloat(),
            "a" to (color.alpha / 255).toFloat()
        )
    }

    /**
     * 0..1
     *
     * @param r: 0.0f to 1.0f
     * @param g: 0.0f to 1.0f
     * @param b: 0.0f to 1.0f
     * @param a: 0.0f to 1.0f
     * @sample sampleOnFire
     */
    fun onFireColor(r: Float, g: Float, b: Float, a: Float) {
        unsafe.general.apply {
            put(
                "on_fire_color", mutableMapOf(
                    "r" to r,
                    "g" to g,
                    "b" to b,
                    "a" to a
                )
            )
        }
    }

    /**
     * 0..1
     *
     * @param color a java.awt.Color to choose from
     */
    fun onFireColor(color: Color) {
        unsafe.general["on_fire_color"] = mutableMapOf(
            "r" to (color.red / 255).toFloat(),
            "g" to (color.green / 255).toFloat(),
            "b" to (color.blue / 255).toFloat(),
            "a" to (color.alpha / 255).toFloat()
        )
    }

    /**
     * 0..1
     *
     * @param r: "0.0" to "1.0", query can be attached
     * @param g: "0.0" to "1.0", query can be attached
     * @param b: "0.0" to "1.0", query can be attached
     * @param a: "0.0" to "1.0", query can be attached
     * @sample sampleOverLayColor
     */
    fun overlayColor(r: String, g: String, b: String, a: String) {
        unsafe.general.apply {
            put(
                "overlay_color", mutableMapOf(
                    "r" to r,
                    "g" to g,
                    "b" to b,
                    "a" to a
                )
            )
        }
    }

    /**
     * 0..1
     *
     * @param color a java.awt.Color to choose from
     */
    fun overlayColor(color: Color) {
        unsafe.general["overlay_color"] = mutableMapOf(
            "r" to (color.red / 255).toFloat(),
            "g" to (color.green / 255).toFloat(),
            "b" to (color.blue / 255).toFloat(),
            "a" to (color.alpha / 255).toFloat()
        )
    }

    fun uvAnim(data: ResRenConUvAnim.() -> Unit) {
        unsafe {
            uvAnim.apply(data)
        }
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

    private fun sampleMaterial() {
        materials("Material.default")
    }

    private fun sampleOnFire() {
        onFireColor(0.2f, 0.1f, 0.8f, 0.5f)
    }

    private fun sampleOverLayColor() {
        overlayColor(
            "variable.is_invulnerable ? 1.0 : this",
            "variable.is_invulnerable ? 1.0 : this",
            "variable.is_invulnerable ? 1.0 : this",
            "variable.is_invulnerable ? query.overlay_alpha : this"
        )
    }
}