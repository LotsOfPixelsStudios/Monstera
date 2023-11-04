package com.lop.devtools.monstera.files.res.geo.data

import com.lop.devtools.monstera.files.res.geo.data.bones.GeoBone
import com.lop.devtools.monstera.files.res.geo.data.description.GeometryDescription

class GeometryData(
    val description: GeometryDescription,
    val bones: ArrayList<GeoBone>
)