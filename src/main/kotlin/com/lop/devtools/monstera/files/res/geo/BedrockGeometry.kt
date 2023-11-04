package com.lop.devtools.monstera.files.res.geo

import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.res.geo.data.GeometryData

data class BedrockGeometry(
    @SerializedName("format_version")
    val formatVersion: String,
    @SerializedName("minecraft:geometry")
    val geometry: ArrayList<GeometryData>
)
