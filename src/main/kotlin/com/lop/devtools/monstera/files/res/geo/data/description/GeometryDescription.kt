package com.lop.devtools.monstera.files.res.geo.data.description

import com.google.gson.annotations.SerializedName

data class GeometryDescription(
    val identifier: String? = null,
    @SerializedName("texture_width")
    var textureWidth: Int? = null,
    @SerializedName("texture_height")
    var textureHeight: Int? = null,
    @SerializedName("visible_bounds_width")
    var visibleBoundsWidth: Float? = null,
    @SerializedName("visible_bounds_height")
    var visibleBoundsHeight: Float? = null,
    @SerializedName("visible_bounds_offset")
    var visibleBoundsOffset: ArrayList<Float>? = null
)