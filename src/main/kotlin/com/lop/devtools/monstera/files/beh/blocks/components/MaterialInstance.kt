package com.lop.devtools.monstera.files.beh.blocks.components

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MaterialInstance {
    val materialSettingsSite = mutableMapOf<String, MaterialSettings>()

    fun all(settings: MaterialSettings.() -> Unit) {
        if (materialSettingsSite.containsKey("*")) {
            materialSettingsSite["*"]!!.apply(settings)
        } else {
            materialSettingsSite["*"] = MaterialSettings().apply(settings)
        }
    }
}

class MaterialSettings {
    @SerializedName("texture")
    @Expose
    var texture: String? = null

    @SerializedName("render_method")
    @Expose
    var renderMethod: RenderMethod? = null

    @SerializedName("ambient_occlusion")
    @Expose
    var ambientOcclusion: Boolean? = null

    @SerializedName("face_dimming")
    @Expose
    var faceDimming: Boolean? = null

    enum class RenderMethod(val s: String) {
        OPAQUE("opaque"),
        DOUBLE_SIDED("double_sided"),
        ALPHA_TEST("alpha_test"),
        BLEND("blend");

        override fun toString(): String {
            return s
        }
    }
}