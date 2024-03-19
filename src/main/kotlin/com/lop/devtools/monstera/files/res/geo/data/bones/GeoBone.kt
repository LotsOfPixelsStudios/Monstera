package com.lop.devtools.monstera.files.res.geo.data.bones

data class GeoBone(
    val name: String,
    val parent: String? = null,
    val pivot: ArrayList<Float>? = null,
    val rotation: ArrayList<Float>? = null,
    val cubes: ArrayList<Cube>? = null,
)
