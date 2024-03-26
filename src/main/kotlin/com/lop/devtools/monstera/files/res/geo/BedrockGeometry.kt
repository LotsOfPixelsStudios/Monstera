package com.lop.devtools.monstera.files.res.geo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.res.geo.data.GeometryData

data class BedrockGeometry(
    @SerializedName("format_version")
    @Expose
    val formatVersion: String,
    @SerializedName("minecraft:geometry")
    @Expose
    val geometry: ArrayList<GeometryData>
)
