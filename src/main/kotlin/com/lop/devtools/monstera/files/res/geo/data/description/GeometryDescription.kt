package com.lop.devtools.monstera.files.res.geo.data.description

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GeometryDescription(
    @Expose
    val identifier: String? = null,
    @Expose
    @SerializedName("texture_width")
    var textureWidth: Int? = null,
    @Expose
    @SerializedName("texture_height")
    var textureHeight: Int? = null,
    @Expose
    @SerializedName("visible_bounds_width")
    var visibleBoundsWidth: Float? = null,
    @Expose
    @SerializedName("visible_bounds_height")
    var visibleBoundsHeight: Float? = null,
    @Expose
    @SerializedName("visible_bounds_offset")
    var visibleBoundsOffset: ArrayList<Float>? = null
)