package com.lop.devtools.monstera.files.res.geo.data

import com.google.gson.annotations.Expose
import com.lop.devtools.monstera.files.res.geo.data.bones.GeoBone
import com.lop.devtools.monstera.files.res.geo.data.description.GeometryDescription

class GeometryData(
    @Expose
    val description: GeometryDescription,
    @Expose
    val bones: ArrayList<GeoBone>
)